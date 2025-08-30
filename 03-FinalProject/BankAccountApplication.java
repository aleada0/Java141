import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program Description: this program makes a bank account for the user. It
 * starts by making two account objects: one for savings and one for checkings.
 * The program gives the user will enter certain inputs for actions such as
 * creating one of these account types, depositing and withdrawing money with
 * the account of their choice, as well as displaying an account's info. 
 * @author NAME
 * @version 1.0
 * @since  8/21/2025
 */
public class BankAccountApplication{
    public static boolean canContinue = true;
    public static boolean hasASaveAccount = false;
    public static boolean hasACheckAccount = false;
    public static SavingsAccount saveAcc;
    public static CheckingAccount checkAcc;
    public static ArrayList<BankAccount2> allAccs = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        textFileHeader();
        do {
            menu();
        } while (canContinue);
        System.out.print("\033[H\033[2J");
        System.out.print("Successfully logged out.");
    }

    /**
     * menu: this method asks the user for a prompt in a do while loop,
     * breaking if the input entered is valid. If 1 or 2, the createAccount()
     * method will be passed. 1 will make a savings acc, and 2 will make a
     * checkings acc. Each will be added to arraylist that will hold all
     * existing accounts.
     * 3 is for deposition, 4 is for withdrawal, 5 is for getting the data on
     * an account. before either one of these are passed, the user is prompted
     * for which account they want to access. If they choose an account to
     * change that they don't already have, the request will be declined.
     * if 6 is entered, a header is made in the menu method, and all accounts
     * involved will have all their transactions logged to a text file.
     * the canContinue boolean (in the main method, 
     * it determines if the do-while loop for this menu method to keep going)
     * will be set to false, ending the loop and the program.
     * 
     * If there is a mismatching input, the user will simply be prompted again.
     * 
     * @author NAME
     * @version 1.0
     * @since 8/21/2025
     * @param void
     * @return void
     */
    public static void menu() throws Exception{
        System.out.print("\033[H\033[2J");
        System.out.println(" __________________________________________ ");
        System.out.println("|            Bank Account System           |");
        System.out.println("|                                          |");
        System.out.println("|        1: Create Saving Account          |");
        System.out.println("|        2: Create Checking Account        |");
        System.out.println("|        3: Deposit                        |");
        System.out.println("|        4: Withdraw                       |");
        System.out.println("|        5: View summary                   |");
        System.out.println("|        6: Exit                           |");
        System.out.println("|__________________________________________|");

        Scanner input = new Scanner(System.in);
        Scanner continuing = new Scanner(System.in);
        do{
            try {
                // Enter a number character
                System.out.println("Enter your choice: ");
                break;
            } catch (InputMismatchException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("This is not a number.");
            }
        } while(true);

        switch (input.next().charAt(0)) {
            case '1' -> {
                // Checks if a save acc doesnt exist
                if (!hasASaveAccount) {
                    createAccount('S');
                    allAccs.add(saveAcc);
                    hasASaveAccount = true;
                }else{ // does nothing if there already is one
                    System.out.print("\033[H\033[2J");
                    System.out.println("You already have an account.");
                    System.out.println("Press Enter to continue.");
                    continuing.nextLine();
                }
            }
            case '2' -> {
                // Checks if a check acc doesnt exist
                if (!hasACheckAccount) {
                    createAccount('C');
                    allAccs.add(checkAcc);
                    hasACheckAccount = true;
                }else{ // does nothing if there already is one
                    System.out.print("\033[H\033[2J");
                    System.out.println("You already have an account.");
                    System.out.println("Press Enter to continue.");
                    continuing.nextLine();
                }
            }
            case '3' -> {
                System.out.print("\033[H\033[2J");
                Scanner accChoice = new Scanner(System.in);
                do {
                    System.out.println("Choose the account you'd like to "+
                    "deposit to.");
                    System.out.print("Enter S for savings, C for checkings "+
                    "(not case sensitive)");
                    try {  
                        // Checks what account is being affect (save or check)
                        char choice = accChoice.next().charAt(0);
                        if (choice == 'S' || choice == 's'){
                            // Checks if an acc for this exists
                            if (hasASaveAccount) { 
                                saveAcc.deposit('S');
                                break;
                            }else{
                                System.out.print("\033[H\033[2J");
                                System.out.println("You need a savings "+
                                "account first to use this.");
                                break;
                            }
                        }else if (choice == 'C' || choice == 'c'){
                            // Checks if an acc for this exists
                            if (hasACheckAccount) {
                                checkAcc.deposit('C');
                                break;
                            }else{
                                System.out.print("\033[H\033[2J");
                                System.out.println("You need a checkings "+
                                "account first to use this.");
                                break;
                            }
                        }else{
                            System.out.print("\033[H\033[2J");
                            System.out.println("Try again. "+"(Invalid character)");  
                        } 
                    } catch (InputMismatchException ex) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Try again. (Incorrect input: a "+
                        "letter is required)");
                        input.nextLine();
                    }
                } while (true);
                    
                System.out.println("Press Enter to continue.");
                continuing.nextLine();
            }
            case '4' ->{
                System.out.print("\033[H\033[2J");
                Scanner accChoice = new Scanner(System.in);
                do {
                    System.out.println("Choose the account you'd like to "+
                    "withdraw from.");
                    System.out.print("Enter S for savings, C for checkings "+
                    "(not case sensitive)");
                    try {  
                        // Checks what account is being affect (save or check)
                        char choice = accChoice.next().charAt(0);
                        if (choice == 'S' || choice == 's'){ 
                            // Checks if an acc for this exists
                            if (hasASaveAccount) {
                                saveAcc.withdraw('S');
                                break;
                            }else{
                                System.out.print("\033[H\033[2J");
                                System.out.println("You need a savings "+
                                "account first to use this.");
                                break;
                            }
                        }else if (choice == 'C' || choice == 'c'){
                            // Checks if an acc for this exists
                            if (hasACheckAccount) {
                                checkAcc.withdraw('C');
                                break;
                            }else{
                                System.out.print("\033[H\033[2J");
                                System.out.println("You need a checkings "+
                                "account first to use this.");
                                break;
                            }
                        }else{
                            System.out.print("\033[H\033[2J");
                            System.out.println("Try again. "+
                            "(Invalid character)");  
                        } 
                    } catch (InputMismatchException ex) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Try again. (Incorrect input: a "+
                        "letter is required)");
                        input.nextLine();
                    }
                } while (true);
                System.out.println("Press Enter to continue.");
                continuing.nextLine();
            }
            case '5' ->{
                System.out.print("\033[H\033[2J");
                Scanner accChoice = new Scanner(System.in);
                do {
                    System.out.println("Choose the account you'd like to "+
                    "view.");
                    System.out.print("Enter S for savings, C for checkings "+
                    "(not case sensitive)");
                    try {   
                        // Checks what account is being affect (save or check)
                        char choice = accChoice.next().charAt(0);
                        if (choice == 'S' || choice == 's'){
                            // Checks if an acc for this exists
                            if (hasASaveAccount) {
                                saveAcc.giveData('S'); 
                                break;
                            }else{
                                System.out.print("\033[H\033[2J");
                                System.out.println("You need a savings "+
                                "account first to use this.");
                                break;
                            }
                        }else if (choice == 'C' || choice == 'c'){
                            // Checks if an acc for this exists
                            if (hasACheckAccount) {
                                checkAcc.giveData('C');
                                break;
                            }else{
                                System.out.print("\033[H\033[2J");
                                System.out.println("You need a checkings "+
                                "account first to use this.");
                                break;
                            }
                        }else{
                            System.out.print("\033[H\033[2J");
                            System.out.println("Try again. (Invalid "+
                            "character)");  
                        } 
                    } catch (InputMismatchException ex) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Try again. (Incorrect input: a "+
                        "valid letter is required)");
                        input.nextLine();
                    }
                } while (true);
                System.out.println("Press Enter to continue.");
                continuing.nextLine();
            }
            case '6' ->{
                canContinue = false; 
                // The boolean that loops the menu is now false and will end
            }
            default -> {
                System.out.print("\033[H\033[2J");
                System.out.println("This is not a valid choice. Try again.");
                continuing.nextLine();
            }
        }
    } // End of menu method

    /**
     * textFileHeader: creates a header for the text file with pro1
     * per spacing
     * and formatting.

     * @author NAME
     * @version 1.0
     * @since 8/25/2025
     * @param void
     * @return void
     */
    public static void textFileHeader(){
        try {
            // Chooses a file to write in
            FileWriter file = new FileWriter("file.txt", true);
            // Writes in the chosen file
            file.write(String.format("%-8s %-12s %-12s %-15s", 
            "Type", "amount", "balance", "Descriptions") + "\n");
            file.close(); // Saves the file
        } catch (IOException e) {
            System.out.println("Error writing to transaction file: " +
            e.getMessage());
        }   
    }

    /**
     * createAccount: a parameter to determine which type of account will be
     * made (saving or checking) is passed, and 4 variables are declared.
     * these variables are prompted to the user to enter a proper value, and
     * will then be put through a constuctor to set up the account that matches
     * the letter parameter.
     * 
     * If there is a mismatch input, the user will simply be prompted again.
     * 
     * @author NAME
     * @version 1.0
     * @since 8/21/2025
     * @param C(char)
     * @return void
     */
    public static void createAccount(char C){
        Scanner input = new Scanner(System.in);
        Scanner continuing = new Scanner(System.in);

        String newName;
        int newID;
        double newAmount;
        double newAIR;

        System.out.print("\033[H\033[2J");   
        do {
            try {
                System.out.print("Enter your name: ");
                newName = input.nextLine();
                break;
            } catch (InputMismatchException ex) { // Rejects if no letters
                System.out.print("\033[H\033[2J");
                System.out.println("Try again. (Incorrect input: Words and "+
                "letters are required)");
                input.nextLine();
            }
        } while (true);

        System.out.print("\033[H\033[2J");   
        do {
            try {               
                System.out.print("Create an ID: ");
                newID = input.nextInt();

                if (newID < 0){ // rejects if negative
                    System.out.print("\033[H\033[2J");
                    System.out.println("Cannot be negative, please try "+
                    "again.");
                }else{
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.print("\033[H\033[2J");
                System.out.println("Try again. (Incorrect input: an integer "+
                "is required)");
                input.nextLine();
            }
        } while (true);

        System.out.print("\033[H\033[2J");   
        do {
            try {
                System.out.print("Add a initial amount to the bank: ");
                newAmount = Math.rint(input.nextDouble()*100)/100;
                if (newAmount < 0){ // rejects if negative
                    System.out.print("\033[H\033[2J");
                    System.out.println("Cannot be negative, please try "+
                    "again.");
                }else{
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.print("\033[H\033[2J");
                System.out.println("Try again. (Incorrect input: a number is "+
                "required)");
                input.nextLine();
            }
        } while (true);

        System.out.print("\033[H\033[2J");   
        do {
            try {
                System.out.print("Determine an annual interest rate: ");
                newAIR = input.nextDouble();
                if (newAIR < 0){ // rejects if negative
                    System.out.print("\033[H\033[2J");
                    System.out.println("Cannot be negative, please try "+
                    "again.");
                }else{
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.print("\033[H\033[2J");
                System.out.println("Try again. (Incorrect input: a number is "+
                "required)");
                input.nextLine();
            }
        } while (true);

        if (C == 'S'){ // Puts everything recorded into the savings acc
            saveAcc = new SavingsAccount(newID, newAmount);
            saveAcc.changeCustomerName(newName);
            saveAcc.changeAnnualInterestRate(newAIR);

            System.out.print("\033[H\033[2J");
            System.out.println("CREATING SAVINGS ACCOUNT...");
            System.out.println("Your name is: "+ 
                                saveAcc.getCustomerName());
            System.out.println("Your account ID is: "+ 
                                saveAcc.getAccountID());
            System.out.printf("Your account balance is: $%.2f\n", 
                            saveAcc.getBalance());
            System.out.println("Your annual interest rate is: "+ 
            saveAcc.getAnnualInterestRate()+"%");
            System.out.println("Press Enter to continue.");
            continuing.nextLine();
        }else if (C == 'C'){ // Puts everything recorded into the checkings acc
            checkAcc = new CheckingAccount(newID, newAmount);
            checkAcc.changeCustomerName(newName);
            checkAcc.changeAnnualInterestRate(newAIR);

            System.out.print("\033[H\033[2J");
            System.out.println("CREATING CHECKINGS ACCOUNT...");
            System.out.println("Your name is: "+ 
                                checkAcc.getCustomerName());
            System.out.println("Your account ID is: "+ 
                                checkAcc.getAccountID());
            System.out.printf("Your account balance is: $%.2f%n", 
                            checkAcc.getBalance());
            System.out.println("Your annual interest rate is: "+ 
            checkAcc.getAnnualInterestRate()+"%");
            System.out.println("Press Enter to continue.");
            continuing.nextLine();
        }
    }
}

