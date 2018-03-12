package userInterface;

import java.io.IOException;
import java.util.Scanner;

import daos.EntityDaoImpl;
import dataHandler.DataHandler;
import dataHandler.EntityInfoDatabase;
import dataHandler.IdentificationDatabase;
import entityInfo.CustomerInfo;
import entityInfo.EmployeeInfo;

public class register {
        public static void registerCustomer() throws ClassNotFoundException, IOException{
        	Scanner rc = new Scanner(System.in);
        	String name;
        	String username;
        	String password;
        	int BankID;
        	System.out.println("Enter name");
        	name = rc.nextLine();
        	System.out.println("Enter Username");
        	username = rc.nextLine();
        	System.out.println("Enter Password");
        	password = rc.nextLine();
        	CustomerInfo newCustomer = new CustomerInfo(name,username,password,0);
        	EntityDaoImpl<CustomerInfo> CustDao = new EntityDaoImpl<CustomerInfo>();
        	CustDao.createEntity(newCustomer);
        	System.out.println("Successfull Registration!");
        }
       
        public static void registerEmployee() throws ClassNotFoundException, IOException{
        	Scanner rc = new Scanner(System.in);
        	String name;
        	String username;
        	String password;
        	int BankID;
        	System.out.println("Enter name");
        	name = rc.nextLine();
        	System.out.println("Enter Username");
        	username = rc.nextLine();
        	System.out.println("Enter Password");
        	password = rc.nextLine();
        	EmployeeInfo newEmployee = new EmployeeInfo(name,username,password,0);
        	EntityDaoImpl<EmployeeInfo> CustDao = new EntityDaoImpl<EmployeeInfo>();
        	CustDao.createEntity(newEmployee);
        	System.out.println("Successfull Registration!");	
        }
        
        public static void registerBankAdminInfo(){
        	
        }
}
