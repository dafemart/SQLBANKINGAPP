package userInterface;

import java.io.IOException;
import java.util.Scanner;

import com.revature.util.ConnectionFactory;

import daos.EntityDaoImpl;
import dataHandler.DataHandler;
import entityInfo.CustomerInfo;
import entityInfo.EmployeeInfo;

public class UserInterface {
   public void displayCustomersInterface() throws ClassNotFoundException, IOException{
	   while(true){
		 boolean BreakLoop = false;
	     Scanner rc = new Scanner(System.in);
	     System.out.println("Press: ");
	     System.out.println("0 for Login");
	     System.out.println("1 for registration");
	     int input = rc.nextInt();
	     switch(input){
	     case 0:
	    	 login.loginCustomer();
	     break;
	     case 1:
	    	 register.registerCustomer();
	     break;
	     default:
	    	  BreakLoop = true;
	     break;
	     }
	     if(BreakLoop)
	    	 break;
	   }
   }
   
   public void displayEmployeeInterface() throws ClassNotFoundException, IOException{
	   while(true){
	   boolean BreakLoop = false;
	   Scanner rc = new Scanner(System.in);
	   System.out.println("Press: ");
	   System.out.println("0 for Login");
	   System.out.println("1 for registration");
	   System.out.println("Return to the main menu");
	   int input = rc.nextInt();
	   switch(input){
	   case 0:
		   login.loginEmployee();
		   break;
	   case 1:
		   register.registerEmployee();
		   break;
	   default:
		   BreakLoop = true;
		   break;
	    }
	     if(BreakLoop)
	    	 break;
	   }
	   
   }
   
   public void displayBankAdminsInterface(){
	   
   }
   
   public static void main(String[] args){
	    try {
		DataHandler.initializeFileSystem();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	    UserInterface userinterface = new UserInterface();
	    while(true){
	      Scanner rc = new Scanner(System.in);
	      System.out.println("Press: ");
	      System.out.println("0 for Customers");
	      System.out.println("1 for Employees");
	      int input = rc.nextInt();
	      try{
	      switch(input){
	      case 0:
				userinterface.displayCustomersInterface();
	      break;
	      case 1:
	    	  userinterface.displayEmployeeInterface();
	      break;
	      case 2:
	        break;
	      default:
	      break;
	       }
	      }
	      catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	       }
   }
}
