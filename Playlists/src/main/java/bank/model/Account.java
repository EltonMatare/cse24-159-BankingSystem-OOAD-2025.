package bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String accountNumber;
    protected String branch;
    protected Customer owner;
    protected double balance;
    protected List<Transaction> transactions;
    protected String accountType;

    public Account(String accountNumber, String branch, Customer owner, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = owner;
        this.balance = balance;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public abstract boolean canWithdraw();
    public abstract String getAccountDetails();

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("DEPOSIT", amount, this.balance, "Deposit"));
        }
    }

    public boolean withdraw(double amount) {
        if (canWithdraw() && amount > 0 && balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("WITHDRAWAL", -amount, this.balance, "Withdrawal"));
            return true;
        }
        return false;
    }

    
    public String getAccountNumber() { return accountNumber; }
    public String getBranch() { return branch; }
    public Customer getOwner() { return owner; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }
    public String getAccountType() { return accountType; }

    public String getFormattedBalance() {
        return String.format("P %.2f", balance);
    }
}