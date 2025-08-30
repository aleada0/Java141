import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * BankAccount2: Variables are made along with a date, which records when an
 * object of this class was made. an array list is also made to record
 * transactions, only accepting values of the transaction class.
 * 
 * @author Alencastro
 * @version 1.0
 * @since  8/22/2025
 */
public class BankAccount2 {
    private int accountID;
    private String customerName;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated = new Date();
    private static ArrayList<Transaction> transaction = new ArrayList<>();
    Transaction exchange;

    public BankAccount2() {
        accountID = 00000;
        customerName = "";
        balance = 0;
        annualInterestRate = 0;

    }
    public BankAccount2(int accountID, double balance) {
        this.accountID = accountID;
        customerName = "";
        this.balance = Math.rint(balance*100)/100;
        annualInterestRate = 0;
        
    }
    public BankAccount2(String customerName, int accountID, double balance) {
        this.accountID = accountID;
        this.customerName = customerName;
        this.balance = Math.rint(balance*100)/100;
        annualInterestRate = 0;
    }

    /**
     * Getter methods: get the values of the ID, name, balance, annual and
     * monthly interest rate, and monthly interest.
     * 
     * @author Alencastro
     * @version 1.0
     * @since 8/22/2025
     * @param accountID(int),balance(double),annualInterestRate(double),
     * customerName(String)
     * @return void
     */
    public int getAccountID(){
        return accountID;
    }
    public String getCustomerName(){
        return customerName;
    }
    public double getBalance(){
        return Math.rint(balance*100)/100;
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
    public double getMonthlyInterest(){
        return balance * (annualInterestRate / 100) / 12;
    }


    /**
     * change methods: change the values of the ID, balance, annual
     * interest rate, and name.
     * 
     * @author Alencastro
     * @version 1.0
     * @since 8/22/2025
     * @param accountID(int),balance(double),annualInterestRate(double),
     * customerName(String)
     * @return void
     */
    public void changeAccountID(int accountID){
        this.accountID = accountID;
    }
    public void changeBalance(double balance){
        this.balance = Math.rint(balance*100)/100;
    }
    public void changeAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }
    public void changeCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**
     * deposit/withdraw: Asks for the amount the user wants to give or take,
     * then by using the choice parameter, it turns the string accountType into
     * whatevers in the matching if condition to be printed in something in 
     * the Transaction class. this transaction is then added to an array. 
     * 
     * @author Alencastro
     * @version 1.0
     * @since 8/24/2025
     * @param choice(char)
     * @return void
     */
    public void deposit(char choice) {
        Scanner input = new Scanner(System.in);
        double amount = moneyAmount("deposit");
        String accountType = null;
        String transactionLoc = null;
        System.out.print("\033[H\033[2J");
        do{
            System.out.println("Enter where you want to take money from: ");
            System.out.println("'A' for ATM transaction, 'B' for Bank "+
            "transaction (not case sensitive)");
            char locChoice = input.next().charAt(0);
            try {
                // Determines to take from atm or bank itself
                if(locChoice == 'A' || locChoice == 'a'){
                    transactionLoc = "ATM transaction";
                    break;
                }else if (locChoice == 'B' || locChoice == 'b'){
                    transactionLoc = "Bank transaction";
                    break;
                }
                System.out.print("\033[H\033[2J");
                System.out.println("Try again. (Invalid character)");
            } catch (InputMismatchException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("This is not a number.");
            }
        } while(true);
        
        if(choice == 'S'){
            accountType = "savings";
        }else if (choice == 'C'){
            accountType = "checkings";
        }
        
        // makes a transaction object and puts it into and array
        exchange = new Transaction('D', amount, getBalance(),
                                    accountType, transactionLoc, this);
        if(exchange.getPossible()){
            transaction.add(exchange);
        }
    }
    public void withdraw(char choice) {
        Scanner input = new Scanner(System.in);
        double amount = moneyAmount("withdraw");
        String accountType = null;
        String transactionLoc = null;
        System.out.print("\033[H\033[2J");
        do {
            System.out.println("Enter where you want to take money from: ");
            System.out.println("'A' for ATM transaction, 'B' for Bank "+
            "transaction (not case sensitive)");
            char locChoice = input.next().charAt(0);
            try {
                // Determines to take from atm or bank itself
                if(locChoice == 'A' || locChoice == 'a'){
                    transactionLoc = "ATM transaction";
                    break;
                }else if (locChoice == 'B' || locChoice == 'b'){
                    transactionLoc = "Bank transaction";
                    break;
                }
                System.out.print("\033[H\033[2J");
                System.out.println("Try again. (Invalid character)");
            } catch (InputMismatchException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("This is not a number.");
            }
        } while(true);

        if (choice == 'S'){
            accountType = "Savings";
        }else if (choice == 'C'){
            accountType = "Checkings";
        }

        // makes a transaction object and puts it into and array
        exchange = new Transaction('W', amount, getBalance(), 
                                    accountType, transactionLoc, this);
        if(exchange.getPossible()){
            transaction.add(exchange);
        }
    }

    /**
     * giveData: Taking the parameter from letter, it'll print out the
     * account's type, name, annual interest rate, monthly interest, 
     * and the date it was created.
     * 
     * @author Alencastro
     * @version 1.0
     * @since 8/22/2025
     * @param letter(char)
     * @return void
     */
    public void giveData(char letter){
        System.out.print("\033[H\033[2J");
        if (letter == 'S') {
            System.out.println("Account type: Savings");
        }else if (letter == 'C'){
            System.out.println("Account type: Checkings");
        }
        System.out.println("Customer name: " + customerName);
        System.out.println("Interest Rate: " + 
        getAnnualInterestRate()+"%");
        System.out.printf("Monthly Interest: $%.2f\n", 
        Math.rint(getMonthlyInterest()*100)/100);
        System.out.printf("Balance: $%.2f\n", getBalance());
    }

    /**
     * moneyAmount: This program simply prompts the user for a double value.
     * which is returned to transaction
     * Will not accept negative values.
     * @author Alencastro
     * @version 1.0
     * @since 8/23/2025
     * @param type(String)
     * @return deposOrWithdraw(double)
     */
    
    public static double moneyAmount(String type){
        Scanner input = new Scanner(System.in);
        double deposOrWithdraw;
        System.out.print("\033[H\033[2J");
        do {
            try {
                // the 'type' is either deposit or withdrawal
                System.out.println("Enter the amount you want to "+ type);
                deposOrWithdraw = input.nextDouble();
                if (deposOrWithdraw < 0){ // rejects if negative
                    System.out.print("\033[H\033[2J");
                    System.out.println("Cannot be negative, please try "+
                    "again.");
                }else{
                    System.out.print("\033[H\033[2J");
                    break;
                }
            } catch (Exception ex) {
                System.out.print("\033[H\033[2J");
                System.out.println("Try again. (Incorrect input: an double "+
                "is required)");
                input.nextDouble();
            }
        } while (true);
        return deposOrWithdraw;
    }
}
