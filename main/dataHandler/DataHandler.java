package dataHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entityInfo.*;
import account.*;
import request.Request;

public class DataHandler {
	 static private FileWriter RequestFileWriter;
	 static private FileWriter CustomerInfoFileWriter;
	 static private FileWriter EmployeeInfoFileWriter;
	 static private FileWriter BankInfoFileWriter;
	 static private FileWriter JointAccountInfoFileWriter;
	 static private FileWriter NormalAccountInfoFileWriter;
	 static private FileWriter IdentificationWriter;
	 
	 final private static String RequestFilename      = "RequestFile.txt";
	 final private static String CustomerInfoFilename = "CustomerInfoFile.txt";
	 final private static String EmployeeInfoFilename = "EmployeeInfoFile.txt";
	 final private static String BankAdminInfoFilename     = "BankInfoFile.txt";
	 final private static String JointAccountInfoFilename  = "JointAccountInfo.txt";
	 final private static String NormalAccountInfoFilename = "NormalAccountInfo.txt";
	 final private static String IdentificationFilename = "IdentificationFile.txt";
	 
	 private static ArrayList<CustomerInfo> CustomerInfoArray;
	 private static ArrayList<EmployeeInfo> EmployeeInfoArray;
	 private static ArrayList<BankAdminInfo> BankAdminInfoArray;
	 private static ArrayList<Request> RequestArray;
	 private static ArrayList<JointAccount> JointAccountArray;
	 private static ArrayList<NormalAccount> NormalAccountArray;
	 
	 private static EntityInfoDatabase<CustomerInfo> CustomerInfoDatabase;
	 private static EntityInfoDatabase<EmployeeInfo> EmployeeInfoDatabase;
	 private static EntityInfoDatabase<BankAdminInfo> BankAdminInfoDatabase;
	 private static RequestsDatabase RequestsDatabase;
	 private static AccountsDatabase<JointAccount> JointAccountsDatabase;
	 private static AccountsDatabase<NormalAccount> NormalAccountsDatabase;
	 private static IdentificationDatabase IDBase;

	 
	 public static void initializeFileSystem() throws IOException{
		 RequestFileWriter            = new FileWriter(RequestFilename);
		 CustomerInfoFileWriter       = new FileWriter(CustomerInfoFilename);
		 EmployeeInfoFileWriter       = new FileWriter(EmployeeInfoFilename);
		 BankInfoFileWriter           = new FileWriter(BankAdminInfoFilename);
		 JointAccountInfoFileWriter   = new FileWriter(JointAccountInfoFilename);
		 NormalAccountInfoFileWriter  = new FileWriter(NormalAccountInfoFilename);
		 IdentificationWriter         = new FileWriter(IdentificationFilename);
		 
		 
		 CustomerInfoArray       = new ArrayList<CustomerInfo>();
		 EmployeeInfoArray       = new ArrayList<EmployeeInfo>();
		 BankAdminInfoArray      = new ArrayList<BankAdminInfo>();
		 RequestArray            = new ArrayList<Request>();
		 JointAccountArray       = new ArrayList<JointAccount>();
		 NormalAccountArray      = new ArrayList<NormalAccount>();
		 
		 CustomerInfoDatabase   = new EntityInfoDatabase<CustomerInfo>(CustomerInfoArray);
		 EmployeeInfoDatabase   = new EntityInfoDatabase<EmployeeInfo>(EmployeeInfoArray);
		 BankAdminInfoDatabase  = new EntityInfoDatabase<BankAdminInfo>(BankAdminInfoArray);
         RequestsDatabase       = new RequestsDatabase(RequestArray);
         JointAccountsDatabase  = new AccountsDatabase<JointAccount>(JointAccountArray);
         NormalAccountsDatabase = new AccountsDatabase<NormalAccount>(NormalAccountArray); 
         IDBase                 = new IdentificationDatabase();
		 
		 
		 storeCustomerInfo(CustomerInfoDatabase);
		 storeBankAdminInfo(BankAdminInfoDatabase);
		 storeEmployeeInfo(EmployeeInfoDatabase);
		 storeRequest(RequestsDatabase);
		 storeJointAccount(JointAccountsDatabase);
		 storeNormalAccount(NormalAccountsDatabase);
		 storeIdentificationBase(IDBase);
		 
		 RequestFileWriter.close();
		 CustomerInfoFileWriter.close();       
		 EmployeeInfoFileWriter.close();       
		 BankInfoFileWriter.close();          
		 JointAccountInfoFileWriter.close();   
		 NormalAccountInfoFileWriter.close();  
		 IdentificationWriter.close();
	 }
	
   

	 public static void storeCustomerInfo(
			   EntityInfoDatabase<CustomerInfo> info) throws IOException{
    	FileOutputStream CustomerInfoFile = new FileOutputStream(CustomerInfoFilename); 
    	ObjectOutputStream out = new ObjectOutputStream(CustomerInfoFile);
    	out.writeObject(info);
    	CustomerInfoFile.close();
    	out.close();
     }
     
	 @SuppressWarnings("unchecked")
	 public static EntityInfoDatabase<CustomerInfo> readCustomerInfo(
			 ) throws IOException, ClassNotFoundException{
    	EntityInfoDatabase<CustomerInfo> CustomerInfoBase = null;
    	FileInputStream CustomerInfoFile = new FileInputStream(CustomerInfoFilename);
        ObjectInputStream in = new ObjectInputStream(CustomerInfoFile);
        CustomerInfoBase = (EntityInfoDatabase<CustomerInfo>)in.readObject();
        in.close();
        CustomerInfoFile.close();
        return CustomerInfoBase;
     }
     
     public static void storeBankAdminInfo(EntityInfoDatabase<BankAdminInfo> info) throws IOException{
    	FileOutputStream BankAdminInfoFile = new FileOutputStream(BankAdminInfoFilename); 
     	ObjectOutputStream out = new ObjectOutputStream(BankAdminInfoFile);
     	out.writeObject(info);;
     	BankAdminInfoFile.close();
     	out.close(); 	 
     }
     
     @SuppressWarnings("unchecked")
	public static EntityInfoDatabase<BankAdminInfo> readBankAdminInfo() throws IOException, ClassNotFoundException{
    	EntityInfoDatabase<BankAdminInfo> BankInfoBase = null;
     	FileInputStream BankAdminInfoFile = new FileInputStream(BankAdminInfoFilename);
        ObjectInputStream in = new ObjectInputStream(BankAdminInfoFile);
        BankInfoBase = (EntityInfoDatabase<BankAdminInfo>)in.readObject();
        in.close();
        BankAdminInfoFile.close();
        return BankInfoBase;
     }
     
     public static void storeEmployeeInfo(EntityInfoDatabase<EmployeeInfo> info) throws IOException{
    	FileOutputStream EmployeeInfoFile = new FileOutputStream(EmployeeInfoFilename); 
      	ObjectOutputStream out = new ObjectOutputStream(EmployeeInfoFile);
      	out.writeObject(info);
      	EmployeeInfoFile.close();
      	out.close();  
     }
     
     @SuppressWarnings("unchecked")
	public static EntityInfoDatabase<EmployeeInfo> readEmployeeInfo() throws IOException, ClassNotFoundException{
    	EntityInfoDatabase<EmployeeInfo> EmployeeInfoBase = null;
      	FileInputStream EmployeeAdminInfoFile = new FileInputStream(EmployeeInfoFilename);
        ObjectInputStream in = new ObjectInputStream(EmployeeAdminInfoFile);
        EmployeeInfoBase = (EntityInfoDatabase<EmployeeInfo>)in.readObject();
        in.close();
        EmployeeAdminInfoFile.close();
        return EmployeeInfoBase;
     }
     
     public static void storeJointAccount(AccountsDatabase<JointAccount> account) throws IOException{
    	FileOutputStream JointAccountInfoFile = new FileOutputStream(JointAccountInfoFilename); 
       	ObjectOutputStream out = new ObjectOutputStream(JointAccountInfoFile);
       	out.writeObject(account);
       	JointAccountInfoFile.close();
       	out.close(); 
     }
     
     @SuppressWarnings("unchecked")
	public static AccountsDatabase<JointAccount> readJointAccount() throws IOException, ClassNotFoundException{
    	 AccountsDatabase<JointAccount> JointAccountBase = null;
       	 FileInputStream JointAccountFile = new FileInputStream(JointAccountInfoFilename);
         ObjectInputStream in = new ObjectInputStream(JointAccountFile);
         JointAccountBase = (AccountsDatabase<JointAccount>)in.readObject();
         in.close();
         JointAccountFile.close();
         return JointAccountBase;
     }
     
     public static void storeNormalAccount(AccountsDatabase<NormalAccount> account) throws IOException{
    	 FileOutputStream NormalAccountInfoFile = new FileOutputStream(NormalAccountInfoFilename); 
         ObjectOutputStream out = new ObjectOutputStream(NormalAccountInfoFile);
         out.writeObject(account);
         NormalAccountInfoFile.close();
         out.close();
     }
     
     @SuppressWarnings("unchecked")
	public static AccountsDatabase<NormalAccount> readNormalAccount() throws IOException, ClassNotFoundException{
    	 AccountsDatabase<NormalAccount> NormalAccountBase = null;
       	 FileInputStream NormalAccountFile = new FileInputStream(NormalAccountInfoFilename);
         ObjectInputStream in = new ObjectInputStream(NormalAccountFile);
         NormalAccountBase = (AccountsDatabase<NormalAccount>)in.readObject();
         in.close();
         NormalAccountFile.close();
         return NormalAccountBase; 
     }
     
     public static void storeRequest(RequestsDatabase requestsDatabase) throws IOException{
    	 FileOutputStream RequestFile = new FileOutputStream(RequestFilename); 
         ObjectOutputStream out = new ObjectOutputStream(RequestFile);
         out.writeObject(requestsDatabase);
         RequestFile.close();
         out.close();	 
     }
     
     public static RequestsDatabase readRequest() throws IOException, ClassNotFoundException{
    	 RequestsDatabase RequestBase = null;
       	 FileInputStream RequestFile = new FileInputStream(RequestFilename);
         ObjectInputStream in = new ObjectInputStream(RequestFile);
         RequestBase = (RequestsDatabase)in.readObject();
         in.close();
         RequestFile.close();
         return RequestBase;
     }
     
     public static void storeIdentificationBase(IdentificationDatabase base) throws IOException{
    	 FileOutputStream IdentificationFile = new FileOutputStream(IdentificationFilename); 
         ObjectOutputStream out = new ObjectOutputStream(IdentificationFile);
         out.writeObject(base);
         IdentificationFile.close();
         out.close();
     }
     public static IdentificationDatabase readIdentificationBase() throws IOException, ClassNotFoundException{
    	 IdentificationDatabase IdentificationBase = null;
       	 FileInputStream IdentificationFile = new FileInputStream(IdentificationFilename);
         ObjectInputStream in = new ObjectInputStream(IdentificationFile);
         IdentificationBase = (IdentificationDatabase)in.readObject();
         in.close();
         IdentificationFile.close();
         return IdentificationBase;
     }
}
