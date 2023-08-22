package com.example.WidgetDemo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Model {
	@JsonProperty("Data")
	 private Object Data;
	 public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
	public String getProcedureName() {
		return ProcedureName;
	}
	public void setProcedureName(String procedureName) {
		ProcedureName = procedureName;
	}
	@JsonProperty("ProcedureName")
	    private String ProcedureName;
	
}
