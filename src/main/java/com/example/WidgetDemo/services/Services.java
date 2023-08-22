package com.example.WidgetDemo.services;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WidgetDemo.repository.DbConnection;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Services {
	@Autowired
	private DbConnection dbconnection;
	public Object db_operation(String procedure_name, Object request1) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		CallableStatement callableStatement = null;
		
		connection = dbconnection.getConnection();
		
		try
		{
			Object response = new Object();
			callableStatement = connection.prepareCall("{call "+procedure_name+"(?)}");

			String json = new ObjectMapper().writeValueAsString(request1);
			System.out.println("Serialized JSON: " + json);
			callableStatement.setString(1, json.toString());

			rs =callableStatement.executeQuery();
			System.out.println("Query executed successfully");
			while(rs.next()) 
			{
				response = rs.getObject ("responseJSON");
				System.out.println("response received");
				return response;
			}
		}

	      catch(Exception e)
		{
			System.out.println("Exception in wid call detail  : "+e.getMessage());
			throw e;
		}
		finally 
		{
			try {
				dbconnection.closeConnection(connection,rs,callableStatement);
			} catch (Exception e) {
				System.out.println("Exception to close the database connection : "+e.getMessage());
				System.out.println("database connection gets closed");
			}
		}
		return null;
	}
}
