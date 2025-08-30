import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Program Description: this program makes a bank account for the user. It
 * starts by making an Account object, and making an editable text file where
 * certain actions will be logged. The program gives the user will enter
 * certain inputs for actions such as creating an account 
 * and depositing and withdrawing money from the bank. 
 * @author Alencastro
 * @version 1.0
 * @since  8/13/2025
 */
public class TestAccount {
    public static boolean canContinue = true;
    public static boolean hasAnAccount = false;
    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        
        Account account = new Account();
        do {
            menu(account);
        } while (canContinue);
        System.out.print("\033[H\033[2J");
        System.out.print("Successfully logged out.");
    }

    /**
     * menu: this method asks the user for a prompt in a do while loop,
     * breaking if the input entered is valid. If 1, the createAccount() method
     * will be passed. if 2, the deposit method is passed,  if 3, the withdraw
     * method is passed, 4, giveData() will be passed where the account info is
     * displayed. if 5 is entered, the canContinue boolean (in the main method,
     * it determines if the do-while loop for this menu method to keep going)
     * will be set to false, ending the loop and the program, as well as
     * clearing the text file.
     * 
     * It also checks for certain actions (specifically 2-4) if the user didn't
     * make an account already.
     * 
     * If there is a mismatch input, the user will simply be prompted again.
     * 
     * @author Alencastro
     * @version 1.0
     * @since 8/13/2025
     * @param acc(Account)
     * @return void
     */
    public static void menu(Account acc) 
    throws Exception{
        System.out.print("\033[H\033[2J");
        System.out.println(" ______________________________________________ ");
        System.out.println("|                  Bank Account                |");
        System.out.println("|                                              |");
        System.out.println("|        1: Create Account                     |");
        System.out.println("|        2: Deposit                            |");
        System.out.println("|        3: Withdraw                           |");
        System.out.println("|        4: Display account information        |");
        System.out.println("|        5: Log out and end session            |");
        System.out.println("|______________________________________________|");

        Scanner input = new Scanner(System.in);
        Scanner continuing = new Scanner(System.in);
        do{
            try {
                System.out.println("Enter your choice: ");
                break;
            } catch (InputMismatchException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("This is not a number.");
            }
        } while(true);

        switch (input.next().charAt(0)) {
            case '1' -> {
                if (!hasAnAccount) {
                    createAccount(acc);
                    hasAnAccount = true;
                }else{
                    System.out.print("\033[H\033[2J");
                    System.out.println("You already have an account.");
                    System.out.println("Press Enter to continue.");
                    continuing.nextLine();
                }
            }
            case '2' -> {
                if (hasAnAccount) {
                    deposit(acc);
                }else{
                    System.out.print("\033[H\033[2J");
                    System.out.println("You need a bank account first to use "+
                    "this.");
                }
                System.out.println("Press Enter to continue.");
                continuing.nextLine();
            }
            case '3' ->{
                if (hasAnAccount) {
                    withdraw(acc);
                }else{
                    System.out.print("\033[H\033[2J");
                    System.out.println("You need a bank account first to use "+
                    "this.");
                }
                System.out.println("Press Enter to continue.");
                continuing.nextLine();
            }
            case '4' ->{
                if (hasAnAccount) {
                    giveData(acc);
                }else{
                    System.out.print("\033[H\033[2J");
                    System.out.println("You need a bank account first to use "+
                    "this.");
                }
                System.out.println("Press Enter to continue.");
                continuing.nextLine();
            }
            case '5' ->{
                canContinue = false;
                    //reset file.txt to empty between sessions
                    try (FileWriter file = new FileWriter("file.txt")) {
                        file.write("");
                        System.out.println("File has been reset.");
                    } catch (IOException e) {
                        System.out.println("Error resetting file: " +
                        e.getMessage());
                    }
            }
            default -> {
                System.out.print("\033[H\033[2J");
                System.out.println("This is not a valid choice. Try again.");
                continuing.nextLine();
            }
        }
    } // End of menu method

    /**
     * createAccount: ID, bank amount, and the annual interest rate, in that
     * order will be displayed.
     * @author Alencastro
     * @version 1.0
     * @since 8/15/2025
     * @param acc(Account)
     * @return void
     */
    public static void createAccount(Account acc){
        Scanner input = new Scanner(System.in);
        Scanner continuing = new Scanner(System.in);
        System.out.print("\033[H\033[2J");   
        acc.changeAccountID(1122);
        acc.changeBalance(20000);
        acc.changeAnnualInterestRate(4.5);

        System.out.print("\033[H\033[2J");
        System.out.println("CREATING ACCOUNT...");
        System.out.println("Your account ID is: "+ acc.getAccountID());
        System.out.printf("Your account balance is: $%.2f%n", acc.getBalance());
        System.out.println("Your annual interest rate is: "+ 
        acc.getAnnualInterestRate()+"%");
        System.out.println("Press Enter to continue.");
        continuing.nextLine();
    }
    /**
     * deposit: adds 3000 to the bank.
     * 
     * Each time this is passed, it is printed to an outside text file, stating
     * how much was taken and when.
     * @author Alencastro
     * @version 1.0
     * @since 8/15/2025
     * @param acc(Account)
     * @return void
     */
    public static void deposit(Account acc) {
        System.out.print("\033[H\033[2J");
        double dAmount = 3000.00;
        acc.deposit(dAmount);
        System.out.printf("DEPOSITING $%.2f...%n", dAmount);
        System.out.printf("Your balance in the bank is $%.2f%n", 
        acc.getBalance());
    }

    /**
     * withdraw: same thing with deposit, except the
     * amount entered with withdrawn (subtracted) from the bank.
     *
     * @author Alencastro
     * @version 1.0
     * @since 8/15/2025
     * @param acc(Account)
     * @return void
     */
    public static void withdraw(Account acc) {
        System.out.print("\033[H\033[2J");
        double wAmount = 2500.00;
        acc.withdraw(wAmount);
        System.out.printf("WITHDRAWING $%.2f...%n", wAmount);
        System.out.printf("Your balance in the bank is $%.2f%n",
        acc.getBalance());
    }

    /**
     * giveData: Taking the parameter from account, it'll print out the
     * account's balance, annual and monthly interest rate, and the date
     * it was created.
     * @author Alencastro
     * @version 1.0
     * @since 8/14/2025
     * @param acc(Account)
     * @return void
     */
    public static void giveData(Account acc){
        System.out.print("\033[H\033[2J");

        System.out.println("Account ID: " + acc.getAccountID());
        System.out.printf("Balance: $%.2f%n", acc.getBalance());
        System.out.println("Annual Interest Rate: " + 
        acc.getAnnualInterestRate()+"%");
        System.out.println("Monthly Interest Rate: " + 
        acc.getMonthlyInterestRate()+"%");
        System.out.println("Account Creation Date: " + acc.getDateCreated());
    }
}

