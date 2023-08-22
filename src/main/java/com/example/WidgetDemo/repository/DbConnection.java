package com.example.WidgetDemo.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DbConnection {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException e) {
			System.out.println("SQLException - Unable to connect the database : " + e.getMessage());

		} catch (Exception e) {
			System.out.println("Exception - Unable to connect the database : " + e.getMessage());
		}
		return connection;
	}

	public boolean closeConnection(Connection connect, ResultSet resultSet, CallableStatement calStatement)
			throws SQLException {
		if (connect != null) {
			connect.close();
			if (resultSet != null) {
				resultSet.close();
			}
			if (calStatement != null) {
				calStatement.close();
			}
			return true;
		}
		return false;

	}
}
