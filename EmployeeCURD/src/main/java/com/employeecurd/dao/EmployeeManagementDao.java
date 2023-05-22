package com.employeecurd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employeecurd.model.Employee;

public class EmployeeManagementDao implements IEmployeeManagementDao {

	Connection con; //null
	PreparedStatement stmt;
	public EmployeeManagementDao()  {
		try {
		Class.forName("org.postgresql.Driver");
		con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/employeetable","postgres","root");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public int addEmployee(Employee employee) throws SQLException {
		// TODO Auto-generated method stub
		String query="Insert into employeetable (id,name)  values(?,?)";
	stmt=con.prepareStatement(query);
		stmt.setString(1, employee.getUserId());
		stmt.setString(2, employee.getEmpName());

		int i=stmt.executeUpdate();
		return i;
	}
	
	public int delEmployee(String empId) throws SQLException{
		String query="delete from employeetable where id=?";
		stmt=con.prepareStatement(query);
		stmt.setString(1, empId);
		int i=stmt.executeUpdate();
		return i;
	}
	
	public Employee updEmployee(String empId,Employee employee) throws SQLException{
		String query="update employeetable set name=? where id=?";
		stmt=con.prepareStatement(query);
		stmt.setString(1, employee.getEmpName());
		stmt.setString(2, empId);
		int i=stmt.executeUpdate();
		return employee;
	}
	public List<Employee> viewtable() throws SQLException
	{
		Employee addEmp;
		List<Employee> emplist=new ArrayList<Employee>();
		String query="select * from employeetable";
		stmt=con.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
		{
			addEmp=new Employee();
			addEmp.setUserId(rs.getString(1));
			addEmp.setEmpName(rs.getString(2));
			emplist.add(addEmp);
			
		}
		return emplist;

	}
	public List<Employee> viewByID(String empID) throws SQLException
	{
		Employee addEmp;
		List<Employee> emplist=new ArrayList<Employee>();
		String query="select * from employeetable where id=?";
		stmt=con.prepareStatement(query);
		stmt.setString(1, empID);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
		{
			addEmp=new Employee();
//			addEmp.setUserId(rs.getString(1));
			addEmp.setEmpName(rs.getString(2));
			emplist.add(addEmp);
			
		}
		return emplist;
	}
	
}
