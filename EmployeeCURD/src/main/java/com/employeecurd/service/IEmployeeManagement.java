package com.employeecurd.service;

import java.sql.SQLException;

import com.employeecurd.exception.EmployeeNotFoundException;
import com.employeecurd.exception.InvalidDetailException;
import com.employeecurd.model.Employee;

public interface IEmployeeManagement {
	public String createEmployee(Employee employee) throws InvalidDetailException,SQLException;
	public String deleteEmployee(String empId) throws EmployeeNotFoundException, SQLException;

}
