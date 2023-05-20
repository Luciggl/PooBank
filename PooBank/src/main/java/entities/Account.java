package entities;

import java.util.Objects;

public class Account {
    private static Object o;
    protected int number;
    protected String holder;
    protected Double balance;

    public Account(int number, String holder, Double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }

    public Account() {
        this(0 ,"", 0.0);
    }

    public void withdraw(Double amount) {
        balance -= amount + 5.0;
    }

    public void deposit(Double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", holder='" + holder + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        Account.o = o;
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;

        if (number != account.number) return false;
        if (!Objects.equals(holder, account.holder)) return false;
        return Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (holder != null ? holder.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    public int getNumber() {
        return number;
    }

    public Account setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getHolder() {
        return holder;
    }

    public Account setHolder() {
        this.holder = holder;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public Account setBalance(Double balance) {
        this.balance = balance;
        return this;
    }
}
