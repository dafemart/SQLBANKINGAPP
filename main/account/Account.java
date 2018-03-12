package account;

import java.io.Serializable;

public interface Account extends Serializable{
     boolean subtractBalance(int balance);
     void addBalance(int balance);
     void setBalance(int balance);
     int getBalance();
     void setAccountNumber(int AccNumber);
     int getAccountNumber();
}
