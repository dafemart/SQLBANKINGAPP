package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import util.ConnectionFactory;
import account.JointAccount;
import account.NormalAccount;

public class AccountDaoImpl implements AccountDao {
	
	public final int DIVIDINGFACTOR = 10000000;

	public AccountDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createNormalAccount(NormalAccount account) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT ACCOUNTNUMBER.NEXTVAL FROM DUAL";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int NewAccountNumber = rs.getInt(1);
			CallableStatement insert_into = conn.prepareCall("{call INSERTACCOUNT(?,?,?)}");
			insert_into.setInt(1, NewAccountNumber);
			insert_into.setInt(2, account.getBalance());
			insert_into.setInt(3, account.getOwnerBankID());
			insert_into.execute();
			conn.close();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void createJointAccount(JointAccount account) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT JOINTACCOUNTNUMBER.NEXTVAL FROM DUAL";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int NewAccountNumber = rs.getInt(1);
			CallableStatement insert_into = conn.prepareCall("{call INSERTJOINTACCOUNT(?,?,?)}");
			insert_into.setInt(1, NewAccountNumber);
			insert_into.setInt(2, account.getBalance());
			insert_into.setInt(3, account.getOwners().get(0));
			insert_into.execute();
			conn.close();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<JointAccount> retrieveJointAccountsByID(int BankID) {
		ArrayList<JointAccount> accounts = new ArrayList<JointAccount>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM JOINTACCOUNTS WHERE OWNERBANKID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, BankID);
			ResultSet rs = ps.executeQuery();
			ArrayList<Integer> OnlyBankId = new ArrayList<Integer>();
			OnlyBankId.add(BankID);
			while(rs.next()){
				accounts.add(new JointAccount(rs.getInt(1),rs.getInt(2),OnlyBankId));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public ArrayList<NormalAccount> retrieveNormalAccountsByID(int BankID) {
		ArrayList<NormalAccount> accounts = new ArrayList<NormalAccount>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM ACCOUNTS WHERE OWNERBANKID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, BankID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				accounts.add(new NormalAccount(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void deleteJointAccount(int AccNumber, int BankID) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "DELETE FROM JOINTACCOUNTS WHERE OWNERBANKID = ? AND ACCOUNTNUMBER = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, BankID);
			ps.setInt(2, AccNumber);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteNormalAccount(int AccNumber, int BankID) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "DELETE FROM ACCOUNTS WHERE OWNERBANKID = ? AND ACCOUNTNUMBER = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, BankID);
			ps.setInt(2, AccNumber);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean depositMoney(int AccNumber, int Amount) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = null;
		try{
		  if(AccNumber/DIVIDINGFACTOR == 7){
			sql = "SELECT COUNT(*) FROM JOINTACCOUNTS WHERE ACCOUNTNUMBER = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, AccNumber);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int RowsNumber = rs.getInt(1);
			if(RowsNumber == 0){
				System.out.println("The specified account doesn't exist");
				return false;
			}
			CallableStatement update = conn.prepareCall("{call DEPOSITTOJOINTACCOUNT(?,?)}"); 
			update.setInt(1, Amount);
			update.setInt(2, AccNumber);
			update.executeQuery();
		  }
	      else{
	    	  sql = "SELECT COUNT(*) FROM ACCOUNTS WHERE ACCOUNTNUMBER = ?";
			  PreparedStatement ps = conn.prepareStatement(sql);
			  ps.setInt(1, AccNumber);
			  ResultSet rs = ps.executeQuery();
			  rs.next();
			  int RowsNumber = rs.getInt(1);
			  if(RowsNumber == 0){
			  System.out.println("The specified account doesn't exist");
				return false;
			  }
			  CallableStatement update = conn.prepareCall("{call DEPOSITTOACCOUNT(?,?)}"); 
			  update.setInt(1, Amount);
			  update.setInt(2, AccNumber);
			  update.execute();
		  }
		  conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
		return true;
	}

	@Override
	public boolean withdrawMoney(int AccNumber, int Amount) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = null;
		try{
		  if(AccNumber/DIVIDINGFACTOR == 7){
			sql = "SELECT COUNT(*) FROM JOINTACCOUNTS WHERE ACCOUNTNUMBER = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, AccNumber);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int RowsNumber = rs.getInt(1);
			if(RowsNumber == 0){
				System.out.println("The specified account doesn't exist");
				return false;
			}
			CallableStatement update = conn.prepareCall("{call WITHDRAWFROMJOINTACCOUNT(?,?)}"); 
			update.setInt(1, Amount);
			update.setInt(2, AccNumber);
			update.executeQuery();
		  }
	      else{
	    	  sql = "SELECT COUNT(*) FROM ACCOUNTS WHERE ACCOUNTNUMBER = ?";
			  PreparedStatement ps = conn.prepareStatement(sql);
			  ps.setInt(1, AccNumber);
			  ResultSet rs = ps.executeQuery();
			  rs.next();
			  int RowsNumber = rs.getInt(1);
			  if(RowsNumber == 0){
			  System.out.println("The specified account doesn't exist");
				return false;
			  }
			  CallableStatement update = conn.prepareCall("{call WITHDRAWFROMACCOUNT(?,?)}"); 
			  update.setInt(1, Amount);
			  update.setInt(2, AccNumber);
			  update.executeQuery();
		  }
		  conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}	
		return true;
	}

	@Override
	public boolean transferMoney(int AccNumberOne, int AccNumberTwo, int Amount) {
	  boolean result = true;
	  try{	
		Connection conn = ConnectionFactory.getInstance().getConnection();
		conn.setAutoCommit(false);
		Savepoint s = conn.setSavepoint("myFirstSavePoint");
		if(!withdrawMoney(AccNumberOne, Amount) || !depositMoney(AccNumberTwo,Amount)){
			conn.rollback(s);
			result = false;
		}
		else conn.commit();
		conn.setAutoCommit(true);
		conn.close();
	  }
	  catch (SQLException e){
		  e.printStackTrace();
	  }
		return result;
	}

	@Override
	public void linkToJointAccount(int toBankID, int AccNum) {
	    String sql = "SELECT COUNT(*) FROM JOINTACCOUNTS WHERE ACCOUNTNUMBER = ?";
	    String balance_query = "SELECT CURRENTBALANCE FROM JOINTACCOUNTS WHERE ACCOUNTNUMBER = ?";
	    String insertBankID = "INSERT INTO JOINTACCOUNTS VALUES(?,?,?)";
	    Connection conn = ConnectionFactory.getInstance().getConnection();
	    try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, AccNum);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int RowsNumber = rs.getInt(1);
			if(RowsNumber == 0){
				System.out.println("The account doesn't exist");
				return;
			}
			PreparedStatement ps2 = conn.prepareStatement(balance_query);
			ps2.setInt(1, AccNum);
			ResultSet rs2 = ps.executeQuery();
			rs2.next();
			int CurrentBalance = rs2.getInt(1);
			PreparedStatement ps3 = conn.prepareStatement(insertBankID);
			ps3.setInt(1, AccNum);
			ps3.setInt(2, CurrentBalance);
			ps3.setInt(3, toBankID);
			ps3.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
