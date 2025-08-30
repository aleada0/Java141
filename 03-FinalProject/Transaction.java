import java.io.FileWriter;
import java.io.IOException;

/**
 * Transaction: Makes a transaction in an account. gets the transaction type to
 * withdraw or deposit from a bank, gets the amount requested, gets the
 * balance, gets the actions as a string, and the account variable being 
 * affected. either saveAcc and checkAcc can work for this parameter since they
 * inherit the properties of BankAccount2.
 * 
 * The program checks if the limit withdrawn for an account type is exceeded,
 * where if so, will not affect the money in that account. the savings account
 * has a max overdraft of 0, and checkings has a max overdraft of 200.
 * 
 * @author NAME
 * @version 1.0
 * @since 8/23/2025
 * @param transactionType(char),amount(double),balance(double),
 *        description(String),accType(BankAccount2)
 */
public class Transaction {
    private char transactionType;
    private double amount;
    private double balance;
    private String description;
    private boolean possible;

    Transaction(char transactionType, double amount, double balance,
                String description, String location, BankAccount2 accType) {
        this.transactionType = transactionType;   
        this.amount = amount;
        this.balance = balance;
        this.description = description;

        if (transactionType == 'D') {
            // Prints out blanance, amount used, account type, and logs the
            // transaction
            System.out.print("\033[H\033[2J");
            balance += amount;
            accType.changeBalance(balance);
            System.out.printf("Deposited: $%.2f\n", amount);
            System.out.printf("Your %s account balance is: $%.2f\n",
            description, Math.rint(balance*100)/100);
            logTransaction(transactionType, amount, balance, location);
            possible = true;
        }else if (transactionType == 'W'){
            System.out.print("\033[H\033[2J");
            // if its savings acc and less than 0, it does nothing
            if ("Savings".equals(description) && amount > balance) { 
                System.out.println("Max overdraft in a savings account is 0. "+
                "No changes were made.");
                possible = false;

                // if its checking acc and less than -200, it does nothing
            }else if ("Checkings".equals(description) &&
                    amount > (balance + 200)) {
                System.out.println("Max overdraft in a checkings account is "+
                "-200. No changes were made.");
                possible = false;
            }else{
                // Prints out blanance, amount used, account type, and logs the
                // transaction (as long as either two above arent true)
                balance -= amount;
                accType.changeBalance(balance);
                System.out.printf("Withdrew: $%.2f\n", amount);
                System.out.printf("Your %s account balance is: $%.2f\n",
                description, Math.rint(balance*100)/100);
                logTransaction(transactionType, amount, balance, location);
                possible = true;
            }
        }
    
    }

    /**
     * Getter methods: get certain values from the transaction class
     * 
     * @author NAME
     * @version 1.0
     * @since 8/21/2025
     * @param C(char)
     * @return void
     */
    public boolean getPossible(){
        return possible;
    }
    public char getTransacType(){
        return transactionType;
    }
    public double getAmount(){
        return amount;
    }
    public double getBalance(){
        return Math.rint(balance*100)/100;
    }
    public String getDescription(){
        return description;
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
     * @param type(char),amount(double),balance(double),date(Date)
     * @return void
     */
    public void logTransaction(char type, double amount, double balance,
                                String location){   
        try {
            // Chooses a file to write in
            FileWriter file = new FileWriter("file.txt", true);
            // Writes in the chosen file
            file.write(String.format("%-8c $%-11.2f $%-11.2f %-12s", 
            type, amount, balance, location) + "\n");
            file.close(); // Saves the file
        } catch (IOException e) {
            System.out.println("Error writing to transaction file: " +
            e.getMessage());
        }
    }
}

