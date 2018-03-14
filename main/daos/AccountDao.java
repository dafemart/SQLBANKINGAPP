package daos;

import java.util.ArrayList;

import account.JointAccount;
import account.NormalAccount;

public interface AccountDao {
    public void createNormalAccount(NormalAccount account);
    public void createJointAccount(JointAccount account);
    public void linkToJointAccount(int BankID, int AccNum);
    public ArrayList<JointAccount> retrieveJointAccountsByID(int BankID);
    public ArrayList<NormalAccount> retrieveNormalAccountsByID(int BankID);
    public void deleteJointAccount(int AccNumber, int BankID);
    public void deleteNormalAccount(int AccNumber, int BankID);
    public boolean depositMoney(int AccNumber, int Amount);
    public boolean withdrawMoney(int AccNumberOne, int Amount);
    public boolean transferMoney(int AccNumber, int AccNumberTwo, int Amount);
}
