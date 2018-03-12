package dataHandler;

import java.io.Serializable;

import java.util.ArrayList;

import account.Account;
import account.JointAccount;
import account.NormalAccount;

public class AccountsDatabase<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 637091477132668554L;
    private ArrayList<T> accounts;
    
    public AccountsDatabase(ArrayList<T> accounts){
    	this.accounts = accounts;
    }
    
    public T getAccountInfo(int AccountNumber){
    	T AssociatedAccount = null;
    	for(T CurrentAccount : this.accounts){
    		if(AccountNumber == ((Account) CurrentAccount).getAccountNumber() ){
    			AssociatedAccount = CurrentAccount;
    			break;
    		}
    	}
    	return AssociatedAccount;
    }
    
    public boolean LeaveAccount(int AccountNumber, int BankID){
    	T dummy = (T) new JointAccount();
    	if(dummy instanceof JointAccount){
    		for(int i = 0; i < accounts.size(); i++){
    			if(((Account) accounts.get(i)).getAccountNumber() == AccountNumber){
    				return  ((JointAccount) accounts.get(i)).removeBankID(BankID);
    			}
    		}
    	}
    	else{
    		for(int i = 0; i < accounts.size(); i++){
    			if(((Account) accounts.get(i)).getAccountNumber() == AccountNumber){
    				if(((NormalAccount) accounts.get(i)).getOwnerBankID()== BankID){
    					((NormalAccount) accounts.get(i)).setBankID(0);
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    public void addAccount(T AssociatedAccount){
    	this.accounts.add(AssociatedAccount);
    }
    
    public ArrayList<T> getAccounts(){
    	return this.accounts;
    }
}
