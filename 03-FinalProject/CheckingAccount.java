/**
 * CheckingAccount: the checking account that inherits properties of BankAcc2 
 * 
 * @author Alencastro
 * @version 1.0
 * @since 8/22/2025
 * @param accountID(int),balance(double)
 */
public class CheckingAccount extends BankAccount2{
    public CheckingAccount() {
        changeAccountID(0);
        changeBalance(0);
    }
    public CheckingAccount(int accountID, double balance) {
        changeAccountID(accountID);
        changeBalance(balance);
    }
}
