package repository;

import entities.Account;
import exception.AccountDoesNotExistException;
import exception.AccountAlreadyExistsException;

import java.util.ArrayList;

public interface BankRepository {
    void openAccount(int number, String holder, double balance) throws AccountAlreadyExistsException;
    void openBusinessAccount(int number, String holder, double balance, double loanLimit) throws AccountAlreadyExistsException;
    void openSavingAccount(int number, String holder, double balance, double interestRate) throws AccountAlreadyExistsException;
    void removeAccount(int number) throws AccountDoesNotExistException;
    ArrayList<Account> searchAccount(int number) throws AccountDoesNotExistException;
    ArrayList<Account> getAccount();
    ArrayList<Account> searchAccountsWithNegativeBalance();
    double consultAccountBalance(int number, String holder) throws AccountDoesNotExistException;
    void transfer(int number0,String holderO , int numberD, String holderD, double amount) throws AccountDoesNotExistException;
    void withdraw(int number, String holder, double amount) throws AccountDoesNotExistException;
    void depositInAccount(int number, String holder,double amount) throws AccountDoesNotExistException;

}
