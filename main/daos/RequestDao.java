package daos;

import request.Request;

public interface RequestDao {
	   public void createRequest(Request request);
	    public Request getNextRequest();
	    public void updateRequest(Request request);
}
