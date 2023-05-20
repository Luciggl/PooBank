package entities;

public class SavingsAccount extends Account{
    private Double interestRate;

    public SavingsAccount() {
        this(0, "",.0,0.0);
    }


    public SavingsAccount(int number, String holder, Double balance, Double interestRate) {
        super(number, holder, balance);
        this.interestRate = interestRate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public SavingsAccount setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
        return this;
    }
    public void UpdateBalance(){
        balance += balance * interestRate;
    }

    @Override
    public void withdraw(Double amount) {
        balance -= amount;
    }
}
