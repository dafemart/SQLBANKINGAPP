package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;

import request.Request;

public class RequestDaoImpl implements RequestDao {
	
	final String OPENNORMALACCOUNT = "OPENNORMALACCOUNT";
	final String OPENJOINTACCOUNT = "OPENJOINTACCOUNT";
	final String CLOSEJOINTACCOUNT = "CLOSEJOINTACCOUNT";
	final String CLOSENORMALACCOUNT = "CLOSENORMALACCOUNT";
	final String LINKTOACCOUNT = "LINKTOACCOUNT";
	
	final String ONHOLD = "ONHOLD";
	final String APPROVED = "APPROVED";
	final String DENIED = "DENIED";
	
	public String boxRequestType(Request.RequestType type){
		String StringType = null;
	     if(type == Request.RequestType.OPENNORMALACCOUNT)
	    	 StringType = OPENNORMALACCOUNT;
	     else if(type == Request.RequestType.OPENJOINTACCOUNT)
	    	 StringType = OPENJOINTACCOUNT;
	     else if(type == Request.RequestType.CLOSEJOINTACCOUNT)
	    	 StringType = CLOSEJOINTACCOUNT;
	     else if(type == Request.RequestType.CLOSENORMALACCOUNT )
	    	 StringType = CLOSENORMALACCOUNT;
	     else if(type == Request.RequestType.LINKTOACCOUNT)
	    	 StringType = LINKTOACCOUNT;
	     return StringType;
	}
	
	public Request.RequestType unboxRequestType(String type){
		Request.RequestType EnumType = null;
		if(type == OPENNORMALACCOUNT)
			EnumType = Request.RequestType.OPENNORMALACCOUNT;
		else if(type == OPENJOINTACCOUNT)
			EnumType = Request.RequestType.OPENJOINTACCOUNT;
		else if(type == CLOSEJOINTACCOUNT)
			EnumType = Request.RequestType.CLOSEJOINTACCOUNT;
		else if(type == CLOSENORMALACCOUNT)
			EnumType = Request.RequestType.CLOSENORMALACCOUNT;
		else if(type == LINKTOACCOUNT)
			EnumType = Request.RequestType.LINKTOACCOUNT;
		return EnumType;
	}
	
	public String boxRequestStatus(Request.RequestStatus status){
		String StringStatus = null;
		if(status == Request.RequestStatus.ONHOLD)
			StringStatus = ONHOLD;
		else if(status == Request.RequestStatus.APPROVED)
			StringStatus = APPROVED;
		else if(status == Request.RequestStatus.DENIED)
			StringStatus = DENIED;  
		return StringStatus;
	}
	
	public Request.RequestStatus unboxRequestStatus(String status){
		Request.RequestStatus EnumStatus = null;
		if(status == ONHOLD)
			EnumStatus = Request.RequestStatus.ONHOLD;
		else if(status == APPROVED)
			EnumStatus = Request.RequestStatus.APPROVED;
		else if(status == DENIED)
			EnumStatus = Request.RequestStatus.DENIED;
		return EnumStatus;
	}

	@Override
	public void createRequest(Request request) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try{
			CallableStatement cStmt = conn.prepareCall("{call INSERTREQUEST(?,?,?,?,?)}");
			cStmt.setString(1, this.boxRequestType(request.getRequestType()));
			cStmt.setString(2, this.boxRequestStatus(request.getRequestStatus()));
			cStmt.setInt(3, request.getAssociatedBankID());
			cStmt.setInt(4,request.getAccountNumber());
			cStmt.setInt(5, request.getLinkToBankID());
			cStmt.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public Request getNextRequest() {
		// TODO Auto-generated method stub
		Request NextRequest = null;
		
		String sql = "SELECT * FROM REQUESTS";
		
		try{
			PreparedStatement ps = 
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if(rs.getString(3) == ONHOLD){
					NextRequest = new Request();
					
				}
			}
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public void updateRequest(Request request) {
		// TODO Auto-generated method stub
		
	}

}
