package account;

import java.util.ArrayList;

public class JointAccount implements Account {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = -2936039876710552897L;
	private int AccountNumber;
	private int CurrentBalance;
	private ArrayList<Integer> OwnersBankIDs;
	
	public JointAccount(){
		
	}
	
	public JointAccount(int AccNum, int balance, ArrayList<Integer> BankIDs){
		AccountNumber = AccNum;
		CurrentBalance = balance;
		OwnersBankIDs = BankIDs;
	}

	public boolean subtractBalance(int balance) {
		if(balance <= this.CurrentBalance){
		 this.CurrentBalance -= balance;
		  return true;
		}
		else{
			return false;
		}
	}

	public void addBalance(int balance) {
	   this.CurrentBalance += balance;	
	}
	
	public void setBalance(int balance){
		this.CurrentBalance = balance;
	}

	public void setAccountNumber(int AccNumber) {
		this.AccountNumber = AccNumber;
	}

	public int getBalance() {
		return this.CurrentBalance;
	}

	public int getAccountNumber() {
		return this.AccountNumber;
	}
	
	public boolean removeBankID(int BankID){
		for(int i = 0; i < OwnersBankIDs.size(); ++i){
			if(OwnersBankIDs.get(i) == BankID){
				OwnersBankIDs.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean containsBankID(int BankID){
		return this.OwnersBankIDs.contains(BankID);
	}
	
	public void addBankID(int BankID){
		this.OwnersBankIDs.add(BankID);
	}
}
