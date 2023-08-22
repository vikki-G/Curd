package com.example.WidgetDemo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WidgetDemo.model.Model;
import com.example.WidgetDemo.services.Services;


@RestController
//@RequestMapping("/controller")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {
	@Autowired
	Services service;
	
	@GetMapping("/Test")
	public String getTest() {
		return " @GetMapping is Working";
	}
	@PostMapping("/postrequest")
	public Object postrequest(@RequestBody Model request1) {
		Object response = null;
		String procedureName = null;
		try {
			procedureName = getProcedureConfiguration(request1.getProcedureName());
			response = service.db_operation(procedureName,request1.getData());

		} catch (Exception e) {
			System.out.println("Error:  " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	private String getProcedureConfiguration(String procedureName) {

		Properties a_prop = new Properties();
		String procedure_name = null;
		try {
			File a_config = ResourceUtils.getFile("classpath:application.properties");
			a_prop.load( new FileInputStream(a_config.toPath().toString()));
			if(a_prop.get(procedureName)!= null)
			{
				System.out.println("method " +a_prop.get(procedureName)+" passed");
				procedure_name = (String) a_prop.get(procedureName);
			}
		} catch (IOException e) {
			System.out.println("Error:  " + e.getMessage());
			e.printStackTrace();
		}
		return procedure_name;
	}
}
