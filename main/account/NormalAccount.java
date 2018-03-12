package account;

import java.util.ArrayList;

public class NormalAccount implements Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1882037529038128389L;
	private int AccountNumber;
	private int CurrentBalance;
	private int OwnersBankID;
	
    public NormalAccount(int AccNum, int balance, int BankID){
	   AccountNumber = AccNum;	
	   CurrentBalance = balance;
	   OwnersBankID = BankID;
	}

	public boolean subtractBalance(int Amount) {
		if(Amount <= this.CurrentBalance){
			 this.CurrentBalance -= Amount;
			  return true;
			}
			else{
				return false;
			}
	}

	public void addBalance(int Amount) {
		CurrentBalance = CurrentBalance + Amount;
	}
	
	public void setBalance(int balance){
		CurrentBalance = balance;
	}

	public void setAccountNumber(int AccNumber) {
		AccountNumber = AccNumber;
		
	}

	public int getBalance() {
		// TODO Auto-generated method stub
		return this.CurrentBalance;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}
	
	public void setBankID(int id){
		this.OwnersBankID = id;
	}
	
	public int getOwnerBankID(){
		return OwnersBankID;
	}
}
