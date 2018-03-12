package dataHandler;

import java.io.Serializable;
import java.util.ArrayList;
import entityInfo.EntityInfo;

public class EntityInfoDatabase<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7121710844697793086L;
	private ArrayList<T> info;
    
    public EntityInfoDatabase(ArrayList<T> info){
    	this.info = info;
    }
    
    public T getEntityInfo(int BankID){
    	T AssociateInfo = null;
    	for(T CurrentInfo : this.info){
    		if(BankID == ((EntityInfo) CurrentInfo).getEntityBankID()){
    			AssociateInfo = CurrentInfo;
    			break;
    		}
    	}
    	return AssociateInfo;
    }
    
    public T getEntityInfo(String username){
    	T AssociateInfo = null;
    	for(T CurrentInfo : this.info){
    		if(username.compareTo(((EntityInfo) CurrentInfo).getUsername()) == 0){
    			AssociateInfo = CurrentInfo;
    			break;
    		}
    	}
    	return AssociateInfo;
    }
    
    public ArrayList<T> getFullEntityInfo(){
    	return info;
    }
    
    public void addEntityInfo(T AssociateInfo){
    	this.info.add(AssociateInfo);
    }
}
