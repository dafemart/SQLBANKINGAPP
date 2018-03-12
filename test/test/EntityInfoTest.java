package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entityInfo.EmployeeInfo;
import entityInfo.EntityInfo;

public class EntityInfoTest {
	
	@Test
	public void getNameTest(){
		EntityInfo info = new EmployeeInfo("daniel","dafemart","naruto",12345);
		assertEquals("daniel", info.getName());
	}

	@Test
	public void setNameTest() {
		EntityInfo info = new EmployeeInfo("daniel","dafemart","naruto",12345);
		info.setName("fernando");
		assertEquals("fernando", info.getName());
	}
	
	@Test
	public void getPasswordTest(){
		EntityInfo info = new EmployeeInfo("daniel","dafemart","naruto",12345);
		assertEquals("naruto", info.getPassword());
	}
	
	@Test
	public void setPasswordTest(){
		EntityInfo info = new EmployeeInfo("daniel","dafemart","naruto",12345);
		info.setPassword("dell");
		assertEquals("dell", info.getPassword());
	}
	
	@Test
	public void getUsernameTest(){
	    EntityInfo info = new EmployeeInfo("daniel","dafemart","naruto",12345);
	    assertEquals("dafemart", info.getUsername());
	}
	
	@Test
	public void setUsernameTest(){
		EntityInfo info = new EmployeeInfo("daniel","dafemart","naruto",12345);
		info.setUsername("dorott");
		assertEquals("dorott", info.getUsername());
	}
	
	
}
