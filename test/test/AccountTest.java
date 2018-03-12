package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import account.Account;
import account.JointAccount;
import account.NormalAccount;

public class AccountTest {
	@Test
	public void getBalanceJointAccountTest() {
		ArrayList<Integer> BankIDs= new ArrayList<Integer>();
	    Account testJointAccount  = new JointAccount(12345, 1000, BankIDs);
	    
		assertEquals(1000, testJointAccount.getBalance());
	}
	
	@Test
	public void getAccNumberJointAccountTest(){
		ArrayList<Integer> BankIDs= new ArrayList<Integer>();
	    Account testJointAccount  = new JointAccount(12345, 1000, BankIDs);
	    
	    assertEquals(12345, testJointAccount.getAccountNumber());
	}
	
	@Test
	public void setAccNumberJointAccountTest(){
	    ArrayList<Integer> BankIDs= new ArrayList<Integer>();
	    Account testJointAccount  = new JointAccount(12345, 1000, BankIDs);
	    testJointAccount.setAccountNumber(54321);
	    assertEquals(54321, testJointAccount.getAccountNumber());
	}
	
	@Test
	public void setBalanceJointAccountTest(){
		ArrayList<Integer> BankIDs= new ArrayList<Integer>();
	    Account testJointAccount  = new JointAccount(12345, 1000, BankIDs);
	    testJointAccount.setBalance(2000);
	    assertEquals(2000,testJointAccount.getBalance());
	}
	
	@Test
	public void addBalanceJointAccountTest(){
		ArrayList<Integer> BankIDs= new ArrayList<Integer>();
	    Account testJointAccount  = new JointAccount(12345, 1000, BankIDs);
	    testJointAccount.addBalance(1000);
	    assertEquals(2000,testJointAccount.getBalance());
	}
	@Test
	public void subtractBalanceJointAccountTest(){
		ArrayList<Integer> BankIDs= new ArrayList<Integer>();
	    Account testJointAccount  = new JointAccount(12345, 1000, BankIDs);
	    testJointAccount.subtractBalance(1000);
	    assertEquals(0,testJointAccount.getBalance());
	}
}
