package com.employeecurd.model;

public class Employee {
	
	private String empName; 
	private String userId;
	
	
	//getters and setters
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", userId=" + userId + "]";
	}
	

}
