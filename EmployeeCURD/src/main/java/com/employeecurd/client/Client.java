package com.employeecurd.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.employeecurd.exception.EmployeeNotFoundException;
import com.employeecurd.exception.InvalidDetailException;
import com.employeecurd.model.Employee;
import com.employeecurd.service.EmployeeManagement;

public class Client {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		String str;
		do {
			System.out.println("Press 1 to add the employee ");
			System.out.println("Press 2 to delete the employee ");
			System.out.println("Press 3 to update details of the employee ");
			System.out.println("Press 4 to view all the employee ");
			System.out.println("Press 5 to view employee by id");
			System.out.println("Press any other key to exit ");
			// String checkContinue = scanner.next();
			int userInput = sc.nextInt();
			Employee emploPojo;
			EmployeeManagement service;
			switch (userInput) {
			//case for add employees to database
			case 1:
				emploPojo = new Employee();
				System.out.println("Enter The Employee ID");
				emploPojo.setUserId(sc.next());
				System.out.println("Enter The Employee Name");
				emploPojo.setEmpName(sc.next());
				service = new EmployeeManagement();
				try {
					String result = service.createEmployee(emploPojo);
					System.out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (InvalidDetailException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
				//case to delete the employees from database
			case 2:
				emploPojo = new Employee();
				System.out.println("Enter The Employee ID");
				emploPojo.setUserId(sc.next());
				service = new EmployeeManagement();
				try {
					String result = service.deleteEmployee(emploPojo.getUserId());
					System.out.println(result);
				} catch (EmployeeNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
			//case to update employees in the database
			case 3:
				emploPojo = new Employee();
				System.out.println("Enter The Employee ID");
				emploPojo.setUserId(sc.next());
				System.out.println("Enter The Employee Name to update");
				emploPojo.setEmpName(sc.next());
				service = new EmployeeManagement();
				try {
					Employee result = service.updateEmployee(emploPojo.getUserId(), emploPojo);
					System.out.print("The updated name at the " + emploPojo.getUserId() + " is" + result);
				} catch (InvalidDetailException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
			//case to display the data from the database
			case 4:
				List<Employee> emplist = null;

				service = new EmployeeManagement();
				try {
					emplist = service.viewTableContain();
					System.out.println("Employee List:");
					for (Employee e : emplist) {
						System.out.println(e.getUserId() + "  " + e.getEmpName() + " ");
					}
				} catch (EmployeeNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				List<Employee> empViewBylist=null;
				System.out.println("Please Enter the id to view employee list");
				emploPojo = new Employee();
				emploPojo.setUserId(sc.next());
				service =new EmployeeManagement();
				try {
					empViewBylist=service.viewEmpByID(emploPojo.getUserId());
					System.out.println("Employee List for Id: "+emploPojo.getUserId()+" ");
					for (Employee e : empViewBylist) {
						System.out.println("Employee Name : "+e.getEmpName());
					}
				} catch(EmployeeNotFoundException e)
				{
					System.out.println(e.getMessage());
				} catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
				break;
				
			default:
				System.exit(0);
			}
			System.out.println("please press y to continue");
			str=sc.next();
		} while (str.equals("y"));

	}
}
