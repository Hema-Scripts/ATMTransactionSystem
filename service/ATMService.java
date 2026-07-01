package service;

import model.BankAccount;
import repository.AccountRepository;
import tasks.DepositTask;
import tasks.WithdrawTask;

import exceptions.AccountNotFoundException;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidPinException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ATMService {

    private AccountRepository repository = new AccountRepository();

    private ExecutorService executor = Executors.newFixedThreadPool(3);

    // Create Account
    public void createAccount(String accountNumber,
            String holderName,
            String pin,
            double balance) {

        if (repository.accountExists(accountNumber)) {
            System.out.println("Account already exists.");
            return;
        }

        BankAccount account = new BankAccount(accountNumber,
                holderName,
                pin,
                balance);

        repository.addAccount(account);

        System.out.println("Account Created Successfully.");
    }

    // Search Account
    public BankAccount searchAccount(String accountNumber,
            String pin)
            throws AccountNotFoundException,
            InvalidPinException {

        BankAccount account = repository.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account Not Found.");
        }

        verifyPin(account, pin);

        return account;
    }

    // Verify PIN
    private void verifyPin(BankAccount account,
            String pin)
            throws InvalidPinException {

        if (!account.getPin().equals(pin)) {

            throw new InvalidPinException(
                    "Invalid PIN.");

        }
    }

    // Check Balance
    public double checkBalance(String accountNumber,
            String pin)
            throws AccountNotFoundException,
            InvalidPinException {

        BankAccount account = searchAccount(accountNumber, pin);
        verifyPin(account, pin);

        return account.getBalance();
    }

    // Deposit
    public void deposit(String accountNumber,
            String pin,
            double amount)
            throws AccountNotFoundException,
            InvalidPinException {

        BankAccount account = searchAccount(accountNumber, pin);
        verifyPin(account, pin);

        DepositTask task = new DepositTask(account, amount);

        try {

            Future<String> future = executor.submit(task);

            System.out.println(future.get());

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    // Withdraw
    public void withdraw(String accountNumber,
            String pin,
            double amount)
            throws AccountNotFoundException,
            InvalidPinException,
            InsufficientBalanceException {

        BankAccount account = searchAccount(accountNumber, pin);

        verifyPin(account, pin);

        if (account.getBalance() < amount) {

            throw new InsufficientBalanceException(
                    "Insufficient Balance.");

        }

        WithdrawTask task = new WithdrawTask(account, amount);

        try {

            Future<String> future = executor.submit(task);

            System.out.println(future.get());

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    public void shutdown() {

        executor.shutdown();

    }

}