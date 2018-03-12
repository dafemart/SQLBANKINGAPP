package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import dataHandler.DataHandler;
import dataHandler.EntityInfoDatabase;
import entityInfo.CustomerInfo;

public class DataHandlerTest {

	@Test
	public void initializeFileSystemTest() {
		try {
			DataHandler.initializeFileSystem();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    @Test
    public void readCustomerInfoTest() {
    	try {
    		DataHandler.initializeFileSystem();
			EntityInfoDatabase<CustomerInfo> obj = DataHandler.readCustomerInfo();
			assertEquals(0, obj.getFullEntityInfo().size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    @Test
    public void storeCustomerInfoTest(){
		try {
			DataHandler.initializeFileSystem();
			EntityInfoDatabase<CustomerInfo> obj;
			obj = DataHandler.readCustomerInfo();
			obj.addEntityInfo(new CustomerInfo("daniel","dafemart","naruto1234",12345));
			DataHandler.storeCustomerInfo(obj);
			obj = DataHandler.readCustomerInfo();
			ArrayList<CustomerInfo> list = obj.getFullEntityInfo();
			String name = list.get(0).getName();
			assertEquals("daniel",name); 
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
