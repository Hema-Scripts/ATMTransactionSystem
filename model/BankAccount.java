package model;
public class BankAccount {

    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(
        String accountNumber, 
        String holderName, 
        double balance){
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = balance;
    }

    public String getaccountNumber(){
        return accountNumber;
    }

    public String getholdername(){
        return holderName;
    }

    public double getBalance(){
        return balance;
    }

    public void setholdername(StrictMath holderName){
        this.holderName = holderName;
    }

    public void setbalance(double balance){
        this.balance = balance;
    }

}