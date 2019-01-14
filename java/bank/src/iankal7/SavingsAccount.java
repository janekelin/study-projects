package iankal7;

/**
 * This class handles saving account as a type of the bank's accounts
 * 
 * @author Iana Kalinichenko, iankal-7
 *
 */
public class SavingsAccount
{
    
    //Constants
    private static final double INTERESET_RATE = 1; //meaning 1%
    private static final String TYPE = "Sparkonto";
    
    //Class variable
    private static int lastAssignedAccountNumber = 1000;
    
    //Instance variables
    private double balance;
    private int number;
    
    //Constructor
    public SavingsAccount()
    {
        balance = 0.0;
        lastAssignedAccountNumber++;
        this.number = lastAssignedAccountNumber;
    }
    
    //Setter
    /**
     * Changes the amount of money on the account's balance to a whole new sum
     * @param newSum - new amount of money to be set as the account's balance
     */
    public void setBalance(double newSum)
    {
        balance = newSum;
    }
    
    //Getters
    /**
     * Shows the account's current balance
     * @return - current amount of money on the account's balance
     */
    public double getBalance()
    {
        return balance;
    }
    
    /**
     * Shows the account's number
     * @return - the account's number as an integer
     */
    public int getAccountNumber()
    {
        return this.number;
    }
    
    //Instance Methods
    /**
     * Adds a deposit amount to the current balance
     * @param depositSum - the amount of money to add to the account's balance
     */
    public void deposit(double depositSum)
    {
        balance += depositSum;
    }
    
    /**
     * Subtracts a withdrawal amount from the current balance
     * @param withdrawalSum the amount of money to withdraw from the account's balance
     */
    public void withdrawal(double withdrawalSum)
    {
        balance -= withdrawalSum;
    }
    
    /**
     * Calculates the amount of interest depending on the current interest rate 
     *  and the sum on the account's balance
     * @return - the amount of interest
     */
    public double calculateInterest()
    {
        return (balance * (INTERESET_RATE / 100.0));
    }
    
    
    /**
     * Overrides java.lang.Object.toString() methods 
     *  and provides information about the account 
     * @return - a String with information about the account
     */
    public String toString()
    {
        String printout = number + " " + balance + " " + TYPE + " " + INTERESET_RATE;
        return printout;
    }

}
