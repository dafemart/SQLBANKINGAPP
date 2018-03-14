package accountHandler;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import userInterface.LoggingUtil;
import account.JointAccount;
import account.NormalAccount;
import daos.AccountDaoImpl;
import daos.EntityDaoImpl;
import dataHandler.AccountsDatabase;
import dataHandler.DataHandler;
import dataHandler.EntityInfoDatabase;
import entityInfo.*;
public class AccountHandler  {
	private static final Logger Log = Logger.getLogger(AccountHandler.class);
    public static CustomerInfo retrieveCustomerInfo(String username) throws ClassNotFoundException, IOException{
    	EntityDaoImpl<CustomerInfo> entitydao = new EntityDaoImpl<CustomerInfo>();
    	return entitydao.retrieveEntityByUsername(username, "CUSTOMER");
    }
    
    public static EmployeeInfo retrieveEmployeeInfo(String username) throws ClassNotFoundException, IOException{
    	EntityDaoImpl<EmployeeInfo> entitydao = new EntityDaoImpl<EmployeeInfo>();
    	return entitydao.retrieveEntityByUsername(username, "EMPLOYEE");
    }
    
    public static BankAdminInfo retrieveBankAdminInfo(String username) throws ClassNotFoundException, IOException{
    	EntityDaoImpl<BankAdminInfo> entitydao = new EntityDaoImpl<BankAdminInfo>();
    	return entitydao.retrieveEntityByUsername(username, "BANKADMIN");
    }
    
    public static boolean  withdrawFromAccount(int AccountNumber, int Amount) throws ClassNotFoundException, IOException{;
    	AccountDaoImpl accdao = new AccountDaoImpl();
    	if(accdao.withdrawMoney(AccountNumber, Amount)){
    		LoggingUtil.logInfo("WITHDREW" + Amount + " FROM " + AccountNumber);
    		return true;
    	}
    	return false;
    }
    
    public static boolean depositToAccount(int AccountNumber, int Amount) throws ClassNotFoundException, IOException{
    	AccountDaoImpl accdao = new AccountDaoImpl();
    	if(accdao.depositMoney(AccountNumber, Amount)){
    		LoggingUtil.logInfo("DEPOSITED " + Amount + " ON " + AccountNumber);
    		return true;
    	}
    	return false;
    }
    
    public static boolean transferMoney(int fromAccNumber, int toAccNumber, int Amount) throws ClassNotFoundException, IOException{
    	AccountDaoImpl accdao = new AccountDaoImpl();
    	if(accdao.transferMoney(fromAccNumber, toAccNumber, Amount)){
            LoggingUtil.logInfo("TRANSFERED" + Amount + " FROM " + fromAccNumber + " TO " + toAccNumber);
       		return true;
       	}
       	return false;
    } 
}

