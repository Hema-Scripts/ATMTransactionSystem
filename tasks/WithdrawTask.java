package tasks;

import java.util.concurrent.Callable;
import model.BankAccount;

public class WithdrawTask implements Callable<String> {

    private BankAccount account;
    private double amount;

    public WithdrawTask(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public String call() {

        account.withdraw(amount);

        return "Withdrawal Successful";
    }
}