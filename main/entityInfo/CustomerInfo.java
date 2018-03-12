package entityInfo;


public class CustomerInfo extends EntityInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1827637634784002640L;
	

	public CustomerInfo(String name, String username, String password, int EntityBankID){
		super(name,username,password,EntityBankID);
	}     
	
	public void setBankID(int BankID){
	  this.EntityBankID = BankID;
	}    
}
