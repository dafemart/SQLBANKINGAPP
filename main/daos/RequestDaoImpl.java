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
		if(type.compareTo(OPENNORMALACCOUNT) == 0)
			EnumType = Request.RequestType.OPENNORMALACCOUNT;
		else if(type.compareTo(OPENJOINTACCOUNT) == 0)
			EnumType = Request.RequestType.OPENJOINTACCOUNT;
		else if(type.compareTo(CLOSEJOINTACCOUNT) == 0)
			EnumType = Request.RequestType.CLOSEJOINTACCOUNT;
		else if(type.compareTo(CLOSENORMALACCOUNT) == 0)
			EnumType = Request.RequestType.CLOSENORMALACCOUNT;
		else if(type.compareTo(LINKTOACCOUNT) == 0)
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
		if(status.compareTo(ONHOLD) == 0)
			EnumStatus = Request.RequestStatus.ONHOLD;
		else if(status.compareTo(APPROVED) == 0)
			EnumStatus = Request.RequestStatus.APPROVED;
		else if(status.compareTo(DENIED) == 0)
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
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public Request getNextRequest() {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
		Request NextRequest = null;
		String sql = "SELECT * FROM REQUESTS";
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if(rs.getString(3).compareTo(ONHOLD) == 0){
					NextRequest = new Request();
					NextRequest.setRequestID(rs.getInt(1));
					NextRequest.setRequestType(unboxRequestType(rs.getString(2)));
					NextRequest.setRequestStatus(unboxRequestStatus(rs.getString(3)));
					NextRequest.setAssociatedBankID(rs.getInt(4));
					NextRequest.setAccountNumer(rs.getInt(5));
					NextRequest.setLinkToBankID(rs.getInt(6));
					break;
				}
			}
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return NextRequest;
	}

	@Override
	public void updateRequest(Request request) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "UPDATE REQUESTS "
				     + "SET REQUESTSTATUS = ? "
				     + "WHERE REQUESTID = ?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.boxRequestStatus(request.getRequestStatus()));
			ps.setInt(2, request.getRequestID());
			ps.executeUpdate();
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}				
	}
}
