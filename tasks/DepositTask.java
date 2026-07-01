package tasks;

import java.util.concurrent.Callable;

import model.BankAccount;

public class DepositTask implements Callable<String> {

    private BankAccount account;

    private double amount;

    public DepositTask(BankAccount account,
            double amount) {

        this.account = account;

        this.amount = amount;

    }

    @Override
    public String call() {

        account.deposit(amount);

        return "Deposit Successful";

    }

}