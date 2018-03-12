package employeeDuties;

import java.io.IOException;

import accountHandler.AccountHandler;


public  class BankAdminDuties extends EmployeeDuties {
	 public void  withdrawFromAccount(int AccountNumber, int Amount) throws ClassNotFoundException, IOException{
	     AccountHandler.withdrawFromAccount(AccountNumber, Amount);
	 }
	    
	 public void depositToAccount(int AccountNumber, int Amount) throws ClassNotFoundException, IOException{
	     AccountHandler.withdrawFromAccount(AccountNumber, Amount);
	 }
	    
	 public void transferMoney(int fromAccNumber, int toAccNumber, int Amount) throws ClassNotFoundException, IOException{
	       	AccountHandler.transferMoney(fromAccNumber, toAccNumber, Amount);
	 } 
}
