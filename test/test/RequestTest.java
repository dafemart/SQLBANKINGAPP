package test;

import static org.junit.Assert.*;

import org.junit.Test;

import request.Request;

public class RequestTest {

	@Test
	public void getRequestTypeTest(){
		Request.RequestType type = Request.RequestType.OPENJOINTACCOUNT; 
		Request.RequestStatus status = Request.RequestStatus.ONHOLD;
		Request MyRequest = new Request(type,status,12345);
		assertEquals(type, MyRequest.getRequestType());
	}
	
	@Test
	public void setRequestTypeTest(){
		Request.RequestType type = Request.RequestType.OPENNORMALACCOUNT; 
		Request.RequestStatus status = Request.RequestStatus.ONHOLD;
		Request MyRequest = new Request(type,status,12345);
		MyRequest.setRequestType(Request.RequestType.CLOSENORMALACCOUNT);
		assertEquals(Request.RequestType.CLOSENORMALACCOUNT, MyRequest.getRequestType());
	}
	
	@Test
	public void getRequestStatusTest(){
		Request.RequestType type = Request.RequestType.OPENJOINTACCOUNT; 
		Request.RequestStatus status = Request.RequestStatus.ONHOLD;
		Request MyRequest = new Request(type,status,12345);
		assertEquals(Request.RequestStatus.ONHOLD, MyRequest.getRequestStatus());
	}
	
	@Test
	public void setRequestStatusTest(){
		Request.RequestType type = Request.RequestType.OPENJOINTACCOUNT; 
		Request.RequestStatus status = Request.RequestStatus.ONHOLD;
		Request MyRequest = new Request(type,status,12345);
		MyRequest.setRequestStatus(Request.RequestStatus.APPROVED);
		assertEquals(Request.RequestStatus.APPROVED, MyRequest.getRequestStatus());
	}
	
	@Test
	public void getAssociatedBankIDTest(){
		Request.RequestType type = Request.RequestType.OPENJOINTACCOUNT; 
		Request.RequestStatus status = Request.RequestStatus.ONHOLD;
		Request MyRequest = new Request(type,status,12345);
		assertEquals(12345, MyRequest.getAssociatedBankID());
	}
}
