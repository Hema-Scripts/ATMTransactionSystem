import java.util.Scanner;

import exceptions.AccountNotFoundException;
import exceptions.InsufficientBalanceException;
import model.BankAccount;
import service.ATMService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ATMService atm = new ATMService();

        int choice;

        do {

            System.out.println("\n==============================");
            System.out.println(" ATM TRANSACTION MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Search Account");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Account Number : ");
                    String accountNumber = sc.next();

                    System.out.print("Holder Name : ");
                    String holderName = sc.next();

                    System.out.print("Enter 4-digit PIN : ");
                    String pin = sc.next();

                    System.out.print("Initial Balance : ");
                    double balance = sc.nextDouble();

                    atm.createAccount(
                            accountNumber,
                            holderName,
                            pin,
                            balance);

                    break;

                case 2:

                    try {

                        System.out.print("Account Number : ");
                        accountNumber = sc.next();

                        System.out.print("PIN : ");
                        pin = sc.next();

                        System.out.print("Amount : ");
                        double depositAmount = sc.nextDouble();

                        atm.deposit(accountNumber, pin, depositAmount);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 3:

                    try {

                        System.out.print("Account Number : ");
                        accountNumber = sc.next();

                        System.out.print("PIN : ");
                        pin = sc.next();

                        System.out.print("Amount : ");
                        double withdrawAmount = sc.nextDouble();

                        atm.withdraw(accountNumber, pin, withdrawAmount);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 4:

                    try {

                        System.out.print("Account Number : ");
                        accountNumber = sc.next();

                        System.out.print("PIN : ");
                        pin = sc.next();

                        BankAccount account = atm.searchAccount(accountNumber, pin);

                        System.out.println("\n------ Account Details ------");
                        System.out.println("Account Number : " + account.getAccountNumber());
                        System.out.println("Holder Name    : " + account.getHolderName());
                        System.out.println("Balance        : " + account.getBalance());

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    break;
                case 5:

                    try {

                        System.out.print("Account Number : ");
                        accountNumber = sc.next();

                        System.out.print("PIN : ");
                        pin = sc.next();

                        double currentBalance = atm.checkBalance(accountNumber, pin);

                        System.out.println("Current Balance : " + currentBalance);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 6:

                    atm.shutdown();

                    System.out.println("Thank You!");

                    break;

                default:

                    System.out.println("Invalid Choice.");

            }

        } while (choice != 6);

        sc.close();

    }

}