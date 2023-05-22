package com.employeecurd.dao;

import java.sql.SQLException;
import java.util.List;

import com.employeecurd.exception.EmployeeNotFoundException;
import com.employeecurd.exception.InvalidDetailException;
import com.employeecurd.model.Employee;

public interface IEmployeeManagementDao {
	public int addEmployee(Employee employee) throws SQLException;

	public int delEmployee(String empId) throws SQLException;

	public Employee updEmployee(String empId, Employee employee) throws SQLException;

	public List<Employee> viewtable() throws SQLException;
	public List<Employee> viewByID(String empID) throws SQLException;

}
