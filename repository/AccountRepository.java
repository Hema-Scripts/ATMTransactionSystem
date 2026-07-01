package repository;

import java.util.HashMap;
import java.util.Map;

import model.BankAccount;

public class AccountRepository {

    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean accountExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public Map<String, BankAccount> getAllAccounts() {
        return accounts;
    }
}