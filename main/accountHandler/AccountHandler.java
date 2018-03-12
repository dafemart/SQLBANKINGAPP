package accountHandler;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import userInterface.LoggingUtil;
import account.JointAccount;
import account.NormalAccount;
import dataHandler.AccountsDatabase;
import dataHandler.DataHandler;
import dataHandler.EntityInfoDatabase;
import entityInfo.*;
public class AccountHandler  {
	private static final Logger Log = Logger.getLogger(AccountHandler.class);
    public static CustomerInfo retrieveCustomerInfo(String username) throws ClassNotFoundException, IOException{
    	EntityInfoDatabase<CustomerInfo> base = DataHandler.readCustomerInfo();
    	ArrayList<CustomerInfo> info = base.getFullEntityInfo();
    	for(CustomerInfo customer : info){
    		if(customer.getUsername() == username){
    			return customer;
    		}
    	}
    	return null;
    }
    
    public static EmployeeInfo retrieveEmployeeInfo(String username) throws ClassNotFoundException, IOException{
    	EntityInfoDatabase<EmployeeInfo> base = DataHandler.readEmployeeInfo();
    	ArrayList<EmployeeInfo> info = base.getFullEntityInfo();
    	for(EmployeeInfo employee : info){
    		if(employee.getUsername() == username){
    			return employee;
    		}
    	}
    	return null;
    }
    
    public static BankAdminInfo retrieveBankAdminInfo(String username) throws ClassNotFoundException, IOException{
    	EntityInfoDatabase<BankAdminInfo> base = DataHandler.readBankAdminInfo();
    	ArrayList<BankAdminInfo> info = base.getFullEntityInfo();
    	for(BankAdminInfo BankAdmin : info){
    		if(BankAdmin.getUsername() == username){
    			return BankAdmin;
    		}
    	}
    	return null;
    }
    
    public static boolean  withdrawFromAccount(int AccountNumber, int Amount) throws ClassNotFoundException, IOException{;
    	AccountsDatabase<JointAccount> JointAccountBase = DataHandler.readJointAccount();
    	JointAccount jointaccount = JointAccountBase.getAccountInfo(AccountNumber);
    	if(jointaccount != null){
    		if(!jointaccount.subtractBalance(Amount))
    			 return false;
    		DataHandler.storeJointAccount(JointAccountBase);
    		LoggingUtil.logInfo("WITHDREW" + Amount + " FROM " + AccountNumber);
    		return true;
    	}
    	
    	AccountsDatabase<NormalAccount> NormalAccountBase = DataHandler.readNormalAccount();
    	NormalAccount normalaccount = NormalAccountBase.getAccountInfo(AccountNumber);
    	if(normalaccount != null){
    		if(!normalaccount.subtractBalance(Amount))
    			return false;
    		DataHandler.storeNormalAccount(NormalAccountBase);
    		LoggingUtil.logInfo("WITHDREW " + Amount + " FROM " + AccountNumber);
    		return true;
    	}
    	System.out.println("Account not found");
    	return false;
    }
    
    public static boolean depositToAccount(int AccountNumber, int Amount) throws ClassNotFoundException, IOException{
    	AccountsDatabase<JointAccount> JointAccountBase = DataHandler.readJointAccount();
    	JointAccount jointaccount = JointAccountBase.getAccountInfo(AccountNumber);
    	if(jointaccount != null){
    		jointaccount.addBalance(Amount);
    		DataHandler.storeJointAccount(JointAccountBase);
    		LoggingUtil.logInfo("DEPOSITED " + Amount + " ON " + AccountNumber);
    		return true;
    	}
    	
    	AccountsDatabase<NormalAccount> NormalAccountBase = DataHandler.readNormalAccount();
    	NormalAccount normalaccount = NormalAccountBase.getAccountInfo(AccountNumber);
    	if(normalaccount != null){
    		normalaccount.addBalance(Amount);
    		DataHandler.storeNormalAccount(NormalAccountBase);
    		LoggingUtil.logInfo("DEPOSITED " + Amount + " ON " + AccountNumber);
    		return true;
    	}
    	System.out.println("Account not found");
    	return false;
    }
    
    public static boolean transferMoney(int fromAccNumber, int toAccNumber, int Amount) throws ClassNotFoundException, IOException{
       	if(withdrawFromAccount(fromAccNumber,Amount)){
       		if(depositToAccount(toAccNumber,Amount)){
       			LoggingUtil.logInfo("TRANSFERED" + Amount + " FROM " + fromAccNumber + " TO " + toAccNumber);
       			return true;
       		}
       		else depositToAccount(fromAccNumber,Amount);
       	}
       	return false;
    } 
}

