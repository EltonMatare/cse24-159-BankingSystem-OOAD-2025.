package bank.model;

import java.io.Serializable;

public class SavingsAccount extends Account implements InterestBearing, Serializable {
    private static final long serialVersionUID = 1L;
    private static final double INTEREST_RATE = 0.0005; 

    public SavingsAccount(String accountNumber, String branch, Customer owner, double balance) {
        super(accountNumber, branch, owner, balance, "SAVINGS");
    }

    @Override
    public boolean canWithdraw() {
        return false;
    }

    @Override
    public String getAccountDetails() {
        return "Savings Account - " + getFormattedBalance() + " - 0.05% monthly interest - No withdrawals";
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }

    @Override
    public void applyInterest() {
        double interest = calculateInterest();
        if (interest > 0) {
            deposit(interest);
        }
    }
}

