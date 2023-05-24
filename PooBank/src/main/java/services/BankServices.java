package services;

import entities.Account;
import entities.BusinessAccount;
import exception.AccountAlreadyExistsException;
import exception.AccountDoesNotExistException;
import repository.BankRepository;

import java.util.ArrayList;
import java.util.Objects;

public class BankServices implements BankRepository {

    private final ArrayList<Account> accounts;

    public BankServices(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }


    @Override
    public void openAccount(int number, String holder, double balance) throws AccountAlreadyExistsException {
        if(existAccount(number)){
            throw new AccountAlreadyExistsException("Esta conta ja se encontra no sistema!");
        }
        Account account = new Account(number,holder, balance);
        this.accounts.add(account);
    }

    @Override
    public void openBusinessAccount(int number, String holder, double balance, double loanLimit) throws AccountAlreadyExistsException {
        for(Account c: this.accounts){
            if(Objects.equals(c.getNumber(), number)){
                throw new AccountAlreadyExistsException("Esta conta ja se encontra Cadastrada no sistema, tente novamente!");
            }
        }
        BusinessAccount account = new BusinessAccount(number, holder, balance,loanLimit);
    }

    @Override
    public void openSavingAccount(int number, String holder, double balance, double interestRate) throws AccountAlreadyExistsException {
        for(Account c: this.accounts){
            if(Objects.equals(c.getNumber(), number)){
                throw new AccountAlreadyExistsException("Esta conta ja se encontra Cadastrada no sistema, tente novamente!");
            }
        }
        BusinessAccount account = new BusinessAccount(number, holder, balance,interestRate);
    }

    @Override
    public void removeAccount(int number) throws AccountDoesNotExistException {
        for(Account c : this.accounts){
            if(Objects.equals(c.getBalance(), number)){
                accounts.remove(c);
                break;
            }
            throw new AccountDoesNotExistException("Conta invalida");
        }
    }

    @Override
    public ArrayList<Account> searchAccount(int number) throws AccountDoesNotExistException {
        ArrayList <Account> searchAccounts = new ArrayList<>();
        for(Account c: this.accounts){
            if(Objects.equals(c.getNumber(), number)){
                searchAccounts.add(c);
                return searchAccounts;
            }
        }
        throw new AccountDoesNotExistException("Esta conta é inválida, tente novamente com uma conta válida!");
    }
    
    @Override
    public ArrayList<Account> getAccount() {
        return this.accounts;
    }

    @Override
    public ArrayList<Account> searchAccountsWithNegativeBalance() {
        ArrayList<Account> negativeBalance = new ArrayList<>();
        for(Account c: this.accounts){
            if(c.getBalance() <0){
                negativeBalance.add(c);
            }
        }
        return negativeBalance;
    }

    @Override
    public double consultAccountBalance(int number, String holder) throws AccountDoesNotExistException {
        if(existAccount(number)){
            double saldo = 0;
            for (Account c : this.accounts) {
                if (Objects.equals(c.getNumber(), number)) {
                    saldo = c.getBalance();
                }
            }
            return saldo;
        }
        throw new AccountDoesNotExistException("Não foi possível consultar o saldo de sua conta! Tente novamente com uma conta válida");
    }

    @Override
    public void transfer(int number0, String holderO, int numberD, String holderD, double amount) throws AccountDoesNotExistException {
        if(existAccount(number0) && existAccount(numberD)) {
            Account accountO = null;
            Account accountD = null;
            for (Account c : this.accounts) {
                if (Objects.equals(c.getNumber(), number0) && Objects.equals(c.getNumber(), numberD)) ;
            }
            if(accountO != null && accountD != null && accountO != accountD){
                accountO.withdraw(amount);
                accountD.deposit(amount);
            }
        } throw new AccountDoesNotExistException("Uma das contas ou as duas são(é) inválida(s)");
    }


    @Override
    public void withdraw(int number, String holder, double amount) throws AccountDoesNotExistException {
        if(existAccount(number)) {
            for (Account c : accounts) {
                if (Objects.equals(c.getNumber(), number)) {
                    c.deposit(amount);
                }
                throw new AccountDoesNotExistException("Não foi possivel sacar dinheiro desta conta! tente novamente com uma conta valida!");
            }
        }
    }

    @Override
    public void depositInAccount(int number, String holder, double amount) throws AccountDoesNotExistException {
        if(existAccount(number)){
            for(Account c: accounts){
                if(Objects.equals(c.getNumber(), number)){
                    c.deposit(amount);
                }
                throw new AccountDoesNotExistException("Não foi possivel depositar dinheiro desta conta! tente novamente com uma conta valida!");
            }
        }
    }

    private boolean existAccount(int number) {
        for (Account c : this.accounts){
            if(Objects.equals(c.getNumber(), number)) {
                return true;
            }
        }
        return false;
    }

}
