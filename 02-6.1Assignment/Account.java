import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class description: makes a class where objects for an bank account is made,
 * including the date the account was created.
 * @author NAME
 * @version 1.0
 * @since  8/13/2025
 */
public class Account {
    private int accountID;
    private double balance;
    private double annualInterestRate;
    final private Date dateCreated = new Date();

    /**
     * Account: default creation of an account with no parameters
     * 
     * @author NAME
     * @version 1.0
     * @since 8/14/2025
     * 
     * @param void
     */
    Account() {
        accountID = 0;
        balance = 0;
        annualInterestRate = 0;
    }
   
    /**
     * Account: creating an account but with parameters for the ID and balance.
     * 
     * @author NAME
     * @version 1.0
     * @since 8/14/2025
     * 
     * @param accountID(int),balance(double)
     */
    Account(int accountID, double balance){
        this.accountID = accountID;
        this.balance = balance;
    }

    /**
     * getter methods: return private values seperately for ID, balance,
     * annual interest rate, date created, and monthly interest rate.
     * 
     * @author NAME
     * @version 1.0
     * @since 8/14/2025
     * 
     * @param void
     * @return accountID(int), balance(double), annualInterestRate(double),
     * dateCreated(date) annualInterestRate / 12(double);
     */
    public int getAccountID(){
        return accountID;
    }
    public double getBalance(){
        return balance;
    }
    public double getAnnualInterestRate(){
        return annualInterestRate;
    }
    public Date getDateCreated(){
        return dateCreated;
    }
    public double getMonthlyInterestRate(){
        return annualInterestRate / 12;
    }

    /**
     * change methods: change the values of the ID, balance, and annual
     * interest rate.
     * 
     * @author NAME
     * @version 1.0
     * @since 8/14/2025
     * 
     * @param accountID(int),balance(double),annualInterestRate(double)
     * @return void
     */
    public void changeAccountID(int accountID){
        this.accountID = accountID;
    }
    public void changeBalance(double balance){
        this.balance = balance;
    }
    public void changeAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * deposit/withdraw methods: add or subtract how much you have from the 
     * bank
     * 
     * @author NAME
     * @version 1.0
     * @since 8/14/2025
     * 
     * @param withdraw(double),deposit(double)
     * @return void
     */
    public void withdraw(double withdrawn){
        //should lose money here, make a error check if a negative is entered
        balance -= withdrawn;
        logTransaction("Withdraw",withdrawn);
    }
    public void deposit(double deposited){
        //should get money here, make a error check if a negative is entered
        balance += deposited;
        logTransaction("Deposit",deposited);
    }


    /**
     * logTransaction: Filewriter makes a text file to write in, then
     * SimpleDateFormat gets the current date.
     * 
     * When this is called in the deposit or withdraw methods, the info of
     * the action, date, and time, is printed out to the text file chosen.
     * 
     * @author NAME
     * @version 1.0
     * @since 8/16/2025
     * 
     * @param type(String),amount(double)
     * @return void
     */
    private void logTransaction(String type, double amount) {
        try {
            FileWriter edit = new FileWriter("file.txt", true);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            
            edit.write(type +" $"+ String.format("%.2f", amount) +  ", "+
            sdf.format(new Date()) + "\n");
            edit.close();
            
        //exception handling for text file    
        } catch (IOException e) {
            System.out.println("Error writing to transaction file: " + e.getMessage());
        }
    }
}

