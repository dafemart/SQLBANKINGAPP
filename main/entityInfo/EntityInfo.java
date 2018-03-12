  package entityInfo;

import java.io.Serializable;


public abstract class EntityInfo implements Serializable{
   /**
	 * 
	 */
   private static final long serialVersionUID = -2076714910968269148L;
   protected String name;
   protected String username;
   protected String password;
   protected int EntityBankID;
   
   public EntityInfo(String name, String username, String password, int EntityBankID){
	   this.name = name;
	   this.username = username;
	   this.password = password;
	   this.EntityBankID = EntityBankID;
   }
   
   public String getName(){
  	 return this.name;
   }
   
   public String getPassword(){
  	 return this.password;
   }
   
   public String getUsername(){
  	 return this.username;
   }
   
   public int getEntityBankID(){
  	 return this.EntityBankID;
   }
   
   public void setName(String name){
	   this.name = name;
   }
   
   public void setUsername(String username){
	   this.username = username;
   }
   
   public void setPassword(String password){
	  this.password = password;  
   } 
   
   public abstract void setBankID(int BankID);
}
