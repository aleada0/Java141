/**
 * SavingsAccount: the savings account that inherits properties of BankAcc2 
 * 
 * @author Alencastro
 * @version 1.0
 * @since 8/22/2025
 * @param accountID(int),balance(double)
 */
public class SavingsAccount extends BankAccount2{
    public SavingsAccount() {
        changeAccountID(0);
        changeBalance(0);
    }
    public SavingsAccount(int accountID, double balance) {
        changeAccountID(accountID);
        changeBalance(balance);
    }
}
