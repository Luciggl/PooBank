package entities;

public class BusinessAccount extends Account {
    private Double loanLimit;

    public BusinessAccount(){
        this(0, "",.0,0.0);
    }

    public BusinessAccount(int number, String holder, Double balance, Double loanLimit) {
        super(number, holder, balance);
        this.loanLimit = loanLimit;
    }

    public Double getLoanLimit() {
        return loanLimit;
    }

    public BusinessAccount setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
        return this;
    }

    public void loan(Double amount){
        if (amount <= loanLimit){
            balance += amount -10.0;
        }
    }

    @Override
    public void withdraw(Double amount) {
        super.withdraw(amount);
        balance -= 2;
    }

}
