package entityInfo;

public class BankAdminInfo extends EntityInfo{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1339307611582128111L;

	public BankAdminInfo(String name, String username, String password, int EntityBankID){
		super(name,username,password,EntityBankID);
     }
	 
     public void setBankID(int BankID){
    	 this.EntityBankID = BankID;
     }
}
