package entityInfo;

public class EmployeeInfo extends EntityInfo{  
	/**
	 * 
	 */
	private static final long serialVersionUID = -4882571229463562435L;

	public EmployeeInfo(String name, String username, String password, int EntityBankID){
		super(name,username,password,EntityBankID);
    }
     
    public void setBankID(int BankID){
    	this.EntityBankID = BankID;
    }
}
