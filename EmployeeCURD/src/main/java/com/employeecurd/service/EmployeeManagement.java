package com.employeecurd.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.employeecurd.dao.EmployeeManagementDao;
import com.employeecurd.dao.IEmployeeManagementDao;
import com.employeecurd.exception.EmployeeNotFoundException;
import com.employeecurd.exception.InvalidDetailException;
import com.employeecurd.model.Employee;

public class EmployeeManagement implements IEmployeeManagement {
	Employee empObject = new Employee();
	IEmployeeManagementDao empDetailsDao = new EmployeeManagementDao();

	public boolean validateId(String employeeId) {
		Pattern pattern = Pattern.compile("^[0-9]+");
		Matcher matcher = pattern.matcher(employeeId);
		return matcher.matches();
	}

	public boolean validateName(String employeeName) {
		Pattern pattern = Pattern.compile("[A-Za-z]+");
		Matcher matcher = pattern.matcher(employeeName);
		return matcher.matches();
	}

	public String createEmployee(Employee employee) throws InvalidDetailException, SQLException {

		if (validateId(employee.getUserId())) {
			empObject.setUserId(employee.getUserId());
		} else {
			throw new InvalidDetailException("Id Is Invalid");
		}
		if (validateName(employee.getEmpName())) {
			empObject.setEmpName(employee.getEmpName());
		} else {
			throw new InvalidDetailException("Employee Name is Invalid");
		}
		int i = empDetailsDao.addEmployee(empObject);
		if (i > 0) {
			return "Employee is added Successfully";
		} else {
			return "Employee is not added";
		}

	}

	public String deleteEmployee(String empId) throws EmployeeNotFoundException, SQLException {
		int i = empDetailsDao.delEmployee(empId);
		if (i < 0) {
			throw new EmployeeNotFoundException("Employee with" + empId + "not Found");
		} else {
			return "Employee With" + empId + "Deleted Successfully";
		}
	}

	public Employee updateEmployee(String empId, Employee employee) throws SQLException, InvalidDetailException {

		if (validateName(employee.getEmpName())) {
			empObject.setEmpName(employee.getEmpName());
		} else {
			throw new InvalidDetailException("Employee Name is Invalid");
		}
		return empDetailsDao.updEmployee(empId, employee);

	}

	public List<Employee> viewTableContain() throws SQLException, EmployeeNotFoundException {
		List<Employee> emplist = empDetailsDao.viewtable();
		if (emplist.isEmpty()) {
			throw new EmployeeNotFoundException("Employee List is Empty");
		}
		return emplist;
	}
	
	public List<Employee> viewEmpByID(String empId) throws SQLException, EmployeeNotFoundException
	{
		 List<Employee> emplist=empDetailsDao.viewByID(empId);
		 if(emplist.isEmpty())
		 {
			 throw new EmployeeNotFoundException("Employee List is Empty");
		 }
		 return emplist;
	}

	

}
