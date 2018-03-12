package dataHandler;

import java.io.Serializable;

public class IdentificationDatabase implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3406552655174501016L;
	private int nextAvailableCustomerBankID;
    private int nextAvailableEmployeeBankID;
    private int nextAvailableAccountNumber;
    
    public IdentificationDatabase(){
    	this.nextAvailableCustomerBankID = 60000000;
    	this.nextAvailableEmployeeBankID = 70000000;
    	this.nextAvailableAccountNumber  = 80000000;
    }
    
    public int getNextAvailableCustomerBankID(){
    	int copy = this.nextAvailableCustomerBankID++;
    	return copy;
    }
    
    public int getNextAvailableEmployeeBankID(){
    	int copy =  this.nextAvailableEmployeeBankID++;
    	return copy;
    }
    
    public int getNextAvailableAccountNumber(){
    	int copy = this.nextAvailableAccountNumber++;
    	return copy;
    }
}
