package dataHandler;

import java.io.Serializable;
import java.util.ArrayList;

import request.Request;

public class RequestsDatabase implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3305766576290465519L;
	private ArrayList<Request> info;
    
    public RequestsDatabase(ArrayList<Request> info){
    	this.info = info;
    }
    
    public Request getRequestInfo(int BankID){
    	Request RequestInfo = null;
    	for(Request CurrentInfo : this.info){
    		if(BankID == CurrentInfo.getAssociatedBankID()){
    			RequestInfo = CurrentInfo;
    			break;
    		}
    	}
    	return RequestInfo;
    }
    
    
    public Request getNextRequest(){
    	for(Request request : info){
    		if(request.getRequestStatus() == Request.RequestStatus.ONHOLD){
    			return request;
    		}
    	}
    	return null;
    }
    
    public void addRequest(Request RequestInfo){
    	this.info.add(RequestInfo);
    }
    
    public ArrayList<Request> getRequests(){
    	return this.info;
    }
    
}
