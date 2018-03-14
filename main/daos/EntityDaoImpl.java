package daos;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.util.ConnectionFactory;

import entityInfo.BankAdminInfo;
import entityInfo.CustomerInfo;
import entityInfo.EmployeeInfo;
import entityInfo.EntityInfo;

public class EntityDaoImpl<T> implements EntityDao<T> {
	@Override
	public void createEntity(T info) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String WhatTable = null;
		if(info.getClass() == CustomerInfo.class)
			WhatTable = "INSERTCUSTOMER";
		else if(info.getClass() == EmployeeInfo.class)
			WhatTable = "INSERTEMPLOYEE";
		else if(info.getClass() == BankAdminInfo.class)
			WhatTable = "INSERTBANKADMIN";
		try{
			CallableStatement cStmt = conn.prepareCall("{call "+WhatTable+"(?,?,?)}");
			cStmt.setString(1, ((EntityInfo) info).getName());
			cStmt.setString(2, ((EntityInfo) info).getUsername());
			cStmt.setString(3, ((EntityInfo) info).getPassword());
			cStmt.execute();
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public T retrieveEntityByUsername(String username, String EntityType) {
		EntityInfo Entity = null;
		String table = null;
		switch(EntityType){
		case "CUSTOMER":
			table = "CUSTOMERS";
			Entity = new CustomerInfo("null","null","null",0);
			break;
		case "EMPLOYEE":
			table = "EMPLOYEES";
			Entity = new EmployeeInfo("null","null", "null",0);
		   break;
		case "BANKADMIN":
			table = "BANKADMINS";
			Entity = new BankAdminInfo("null","null","null",0);
		break;
		default:
			table = "UNKNOWN";
		break;
		}
		
		if(table == "UNKNOWN")
			return (T) Entity;
		
		String sql = "SELECT * FROM " + table + " WHERE USERNAME = ?";
		try{
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Entity.setName(rs.getString(1));
				Entity.setUsername(rs.getString(2));
				Entity.setPassword(rs.getString(3));
				Entity.setBankID(rs.getInt(4));
			}
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return (T) Entity;
		
	}

	@Override
	public ArrayList<T> retrieveAllEntities(String EntityType) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		ArrayList<T> Entities = new ArrayList<T>();
		String table = null;
		switch(EntityType){
		case "CUSTOMER":
			table = "CUSTOMERS";
			break;
		case "EMPLOYEE":
			table = "EMPLOYEES";
		   break;
		case "BANKADMIN":
			table = "BANKADMINS";
		break;
		default:
			table = "UNKNOWN";
		break;
		}
		
		if(table == "UNKNOWN"){
			System.out.println("unknown entity type");
			return Entities;
		}
		
		String sql = "SELECT * FROM " + table + ";";
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				switch(EntityType){
				case "CUSTOMER":
					CustomerInfo customer = new CustomerInfo(rs.getString(1),
							 rs.getString(2), rs.getString(3), rs.getInt(4));
					Entities.add((T) customer);
					break;
				case "EMPLOYEE":
					EmployeeInfo employee = new EmployeeInfo(rs.getString(1),
							 rs.getString(2), rs.getString(3), rs.getInt(4));
					Entities.add((T)employee);
					table = "EMPLOYEES";
				   break;
				case "BANKADMIN":
					BankAdminInfo BankAdmin = new BankAdminInfo(rs.getString(1),
							 rs.getString(2), rs.getString(3), rs.getInt(4));
					Entities.add((T)BankAdmin);
					table = "BANKADMINS";
				break;
				default:
					table = "UNKNOWN";
				break;
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return Entities;
	}
     
}
