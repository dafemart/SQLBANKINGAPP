package userInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import request.Request;
import account.JointAccount;
import account.NormalAccount;
import accountHandler.AccountHandler;
import daos.EntityDaoImpl;
import daos.RequestDaoImpl;
import dataHandler.AccountsDatabase;
import dataHandler.DataHandler;
import dataHandler.EntityInfoDatabase;
import dataHandler.RequestsDatabase;
import employeeDuties.EmployeeDuties;
import entityInfo.CustomerInfo;
import entityInfo.EmployeeInfo;

public class login {
	
	public static boolean displayAccountOptions(ArrayList<Integer> CustNormalAccNumbers,
			ArrayList<Integer>CustJointAccNumbers, CustomerInfo info) throws ClassNotFoundException, IOException{
		boolean BreakLoop = false;
		Scanner rc = new Scanner(System.in);
		System.out.println("0---withdraw");
		System.out.println("1---deposit");
		System.out.println("2---transfer");
		System.out.println("3---cancel joint account");
		System.out.println("4---cancel normal account");
		System.out.println("5---link account");
		System.out.println("6---exit");
		int AccNum = 0;
		int ToAccNum = 0;
		int Amount = 0;
		int input = rc.nextInt();
		switch(input){
		case 0:
		    System.out.println("Enter AccountNumber");
		    AccNum = rc.nextInt();
		    System.out.println("How much money do you want to withdraw");
		    Amount = rc.nextInt();
		    if(CustNormalAccNumbers.contains(AccNum) || CustJointAccNumbers.contains(AccNum))
		    	AccountHandler.withdrawFromAccount(AccNum, Amount);
		    else System.out.println("You don't own this account");
			break;
		case 1:
			System.out.println("Enter AccountNumber");
		    AccNum = rc.nextInt();
		    System.out.println("How much money do you want to deposit");
		    Amount = rc.nextInt();
		    if(CustNormalAccNumbers.contains(AccNum) || CustJointAccNumbers.contains(AccNum))
		    	AccountHandler.depositToAccount(AccNum, Amount);
		    else System.out.println("You don't own this account");
			break;
		case 2:
			System.out.println("Enter account number");
		    AccNum = rc.nextInt();
		    System.out.println("To which account number");
		    ToAccNum = rc.nextInt();
		    System.out.println("How much money do you want to transfer");
		    Amount = rc.nextInt();
		    if(CustNormalAccNumbers.contains(AccNum) || CustJointAccNumbers.contains(AccNum))
		    	AccountHandler.transferMoney(AccNum, ToAccNum, Amount);
			break;
		case 3:
			System.out.println("Enter account number");
			AccNum = rc.nextInt();
			if(CustJointAccNumbers.contains(AccNum)){
				Request request = new Request(Request.RequestType.CLOSEJOINTACCOUNT,
						    Request.RequestStatus.ONHOLD,info.getEntityBankID());
				request.setAccountNumer(AccNum);
				RequestsDatabase ReqBase = DataHandler.readRequest();
				ReqBase.addRequest(request);
				DataHandler.storeRequest(ReqBase);
			}
			else System.out.println("You don't own this account");
			break;
		case 4:
			System.out.println("Enter account number");
			AccNum = rc.nextInt();
			if(CustNormalAccNumbers.contains(AccNum)){
				Request request = new Request(Request.RequestType.CLOSENORMALACCOUNT,
						    Request.RequestStatus.ONHOLD,info.getEntityBankID());
				request.setAccountNumer(AccNum);
				RequestsDatabase ReqBase = DataHandler.readRequest();
				ReqBase.addRequest(request);
				DataHandler.storeRequest(ReqBase);
			}
			else System.out.println("You don't own this account");
			break;
		case 5:
			 System.out.println("Enter account number");
			 AccNum = rc.nextInt();
			 if(CustJointAccNumbers.contains(AccNum)){
				 Request request = new Request(Request.RequestType.LINKTOACCOUNT,
						   Request.RequestStatus.ONHOLD, info.getEntityBankID());
				 request.setAccountNumer(AccNum);
				System.out.println("Enter the target BankID ");
				int targetBankID = rc.nextInt();
				 request.setLinkToBankID(targetBankID);
				 RequestsDatabase reqBase = DataHandler.readRequest();
				 reqBase.addRequest(request);
				 DataHandler.storeRequest(reqBase);
			 }
			 else System.out.println("you don't own this account");
			break;
		default:
			BreakLoop = true;
		    break;
		}
		return BreakLoop;
	}
	
	public static void ApplyForNormalAccount(CustomerInfo info) throws ClassNotFoundException, IOException{
		Request openNormalAccountRequest = new Request(Request.RequestType.OPENNORMALACCOUNT,
				Request.RequestStatus.ONHOLD,info.getEntityBankID());
		RequestDaoImpl requestDao = new RequestDaoImpl();
		requestDao.createRequest(openNormalAccountRequest);
		System.out.println("You just applied for a normal account");
	}
	
	public static void ApplyForJointAccount(CustomerInfo info) throws ClassNotFoundException, IOException{
		   Request openJointAccountRequest = new Request(Request.RequestType.OPENJOINTACCOUNT,
				   Request.RequestStatus.ONHOLD,info.getEntityBankID());
		   RequestDaoImpl requestDao = new RequestDaoImpl();
		   requestDao.createRequest(openJointAccountRequest);
		   System.out.println("You just applied for a joint account");
	}
	
	 public static void displayAssociatedAccounts(CustomerInfo info) throws ClassNotFoundException, IOException{
	     boolean BreakLoop = false;
		 while(true){
		 System.out.println("Normal Accounts: ");
		 ArrayList<Integer> NormalAccountNumbers = new ArrayList<Integer>();
		 AccountsDatabase<NormalAccount> base = DataHandler.readNormalAccount();
		 System.out.println("AccountNumber---Balance");
		 for(NormalAccount acc : base.getAccounts()){
			 if(acc.getOwnerBankID() == info.getEntityBankID()){
				 System.out.println(acc.getAccountNumber()+"---"+acc.getBalance());
				 NormalAccountNumbers.add(acc.getAccountNumber());
			 }
		 }
		 System.out.println("");
		 System.out.println("Joint Accounts");
		 ArrayList<Integer> JointAccountNumbers = new ArrayList<Integer>();
		 AccountsDatabase<JointAccount> JointAccBase = DataHandler.readJointAccount();
		 System.out.println("AccountNumber---Balance");
		 for(JointAccount acc : JointAccBase.getAccounts()){
			 if(acc.containsBankID(info.getEntityBankID())){
				 System.out.println(acc.getAccountNumber()+"---"+acc.getBalance());
				 JointAccountNumbers.add(acc.getAccountNumber());
			 }
		 }
		 System.out.println();
		 BreakLoop = login.displayAccountOptions(NormalAccountNumbers,JointAccountNumbers,info);
		 if(BreakLoop)
			 break;
	   }
	 }
	 
	 public static void displayIssueRequestOption(CustomerInfo info) throws ClassNotFoundException, IOException{
		 Scanner rc = new Scanner(System.in);
		 RequestsDatabase base = DataHandler.readRequest();
		 for(Request req : base.getRequests()){
			 if(req.getRequestType() == Request.RequestType.LINKTOACCOUNT &&
				 req.getLinkToBankID() == info.getEntityBankID()){
				 System.out.println("LinkAccount request received from the BankID" + 
				                   req.getAssociatedBankID());	 
				 System.out.println("Do you want to approve this request?");
				 System.out.println("yes -> 0, no -> 1");
				 int input = rc.nextInt();
				 switch(input){
				 case 0:
					 req.setRequestStatus(Request.RequestStatus.APPROVED);
					 AccountsDatabase<JointAccount> accBase = DataHandler.readJointAccount();
					 JointAccount acc = accBase.getAccountInfo(req.getAccountNumber());
					 acc.addBankID(info.getEntityBankID());
					 DataHandler.storeJointAccount(accBase);
					 break;
				 case 1:
					 req.setRequestStatus(Request.RequestStatus.DENIED);
					 break;
				 default:
					 break;
				 }
				 DataHandler.storeRequest(base);
			 }
		 }
	 }
	
	 public static void displayCustomerOptions(CustomerInfo info) throws ClassNotFoundException, IOException{
	   while(true){
		 boolean BreakLoop = false;
		 Scanner rc = new Scanner(System.in);
		 System.out.println("Hi " + info.getName());
		 System.out.println("BankID: " + info.getEntityBankID());
		 System.out.println("Press: ");
		 System.out.println("0 - View your accounts");
		 System.out.println("1 - Apply for a normal account");
		 System.out.println("2 - Apply for a joint account");
		 System.out.println("3 - issue requests associated to you");
		 System.out.println("4 - logout");
		 int input = rc.nextInt();
		 switch(input){
		 case 0:
			 login.displayAssociatedAccounts(info);
		 break;
		 case 1:
			 login.ApplyForNormalAccount(info);
		 break;
		 case 2:
			 login.ApplyForJointAccount(info);
		 break;
		 case 3:
			login.displayIssueRequestOption(info);
		 break;
		 default:
			 BreakLoop = true;
		 break;
		 }
		 if(BreakLoop)
			 break;
	   }
	 }
	 
	 public static void displayEmployeeOptions(EmployeeInfo info) throws ClassNotFoundException, IOException{
	   while(true){
		 boolean BreakLoop = false;
		 Scanner rc = new Scanner(System.in);
		 System.out.println("Hi " + info.getName());
		 System.out.println("BankID: " + info.getEntityBankID());
		 System.out.println("Press: ");
		 System.out.println("0 --- display customer info");
		 System.out.println("1 --- issue next request ");
		 int input = rc.nextInt();
		 switch(input){
		 case 0:
			 EntityInfoDatabase<CustomerInfo> base = DataHandler.readCustomerInfo();
			 System.out.println("Enter customer BankID");
			 int BankID = rc.nextInt();
			 CustomerInfo CustInfo  = base.getEntityInfo(BankID);
			 if(CustInfo != null){
				 System.out.println("Customer name: " + CustInfo.getName());
				 System.out.println("Customer BankID: " + CustInfo.getEntityBankID());
				 login.displayAssociatedAccounts(CustInfo);
			 }
			 else System.out.println("customer not found");
			 break;
		 case 1:
			 EmployeeDuties duties = new EmployeeDuties();
			 duties.handleCustomerRequests();
			 break;
		 default:
			 BreakLoop = true;
			 break;
		 }
		 if(BreakLoop)
			 break;
	   }
	 }
	
     public static void loginCustomer() throws ClassNotFoundException, IOException{
    	 Scanner rc = new Scanner(System.in);
    	 String username = null;
    	 String password = null;
    	 System.out.println("Enter your username");
    	 username = rc.nextLine();
    	 System.out.println("Enter your password");
    	 password = rc.nextLine();
    	 EntityDaoImpl<CustomerInfo> CustDao = new EntityDaoImpl<CustomerInfo>();
    	 CustomerInfo customer = CustDao.retrieveEntityByUsername(username, "CUSTOMER");
    	 if(customer != null && (password.compareTo(customer.getPassword()) == 0)){
    		 System.out.println("Successfull login");
    		 displayCustomerOptions(customer);
    	 }
    	 else System.out.println("Unsuccessful login");
    	 
     }
     
     public static void loginEmployee() throws ClassNotFoundException, IOException{
    	 Scanner rc = new Scanner(System.in);
    	 String username = null;
    	 String password = null;
    	 System.out.println("Enter your username");
    	 username = rc.nextLine();
    	 System.out.println("Enter your password");
    	 password = rc.nextLine();
    	 EntityDaoImpl<EmployeeInfo> EmployeeDao = new EntityDaoImpl<EmployeeInfo>();
    	 EmployeeInfo employee = EmployeeDao.retrieveEntityByUsername(username, "EMPLOYEE");
    	 if(employee != null && (password.compareTo(employee.getPassword()) == 0)){
    		 System.out.println("Successfull login");
    		 login.displayEmployeeOptions(employee);
    	 }
    	 else System.out.println("Unsuccessful login");
     }
}
