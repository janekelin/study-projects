package iankal7;

import java.util.ArrayList;

/**
 * This class handles the bank's customer 
 *  as a type of person interacting with the bank services
 * 
 * @author Iana Kalinichenko, iankal-7
 *
 */
public class Customer
{
    
    //Instance variables
    private String firstName;
    private String lastName;
    private String personalNumber;
    private ArrayList<SavingsAccount> accounts = new ArrayList<SavingsAccount>();
    
    //Constructors
    public Customer()
    {
        firstName = "No first name has been set yet";
        lastName = "No last name has been set yet";
        personalNumber = "No personal number has been set yet";
    }
    
    public Customer(String newFirstName, String newLastName, String newPersonalNumber)
    {
        firstName = newFirstName;
        lastName = newLastName;
        personalNumber = newPersonalNumber;
    }
    
    //Setters
    /**
     * Changes the customer's first name to a new one
     * @param newFirstName - new first name for the customer
     */
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }
    
    /**
     * Changes the customer's last name to a new one
     * @param newLastName - new last name for the customer
     */
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }
    
    /**
     * Updates the customer's personal number to the correct one
     * @param newPersonalNumber - the correct personal number
     */
    public void setPersonalNumber(String newPersonalNumber)
    {
        personalNumber = newPersonalNumber;
    }
    
    //Getters
    /**
     * Shows the customer's first name
     * @return - the customer's first name
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * Shows the customer's last name
     * @return - the customer's last name
     */
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     * Shows the customer's personal number
     * @return - the customer's personal number
     */
    public String getPersonalNumber()
    {
        return personalNumber;
    }
    
    //Instance Methods
    /**
     * Changes the customer's name to the new one; 
     *  this method is intended to be used for an already created object
     * @param newFirstName - a new first name that the customer should get
     * @param newLastName - a new last name that the customer should get
     */
    public void changeName(String newFirstName, String newLastName)
    {
        this.setFirstName(newFirstName);
        this.setLastName(newLastName);
    }
    
    /**
     * Overrides java.lang.Object.toString() methods 
     *  and provides information about the customer 
     * @return - a String with information about the customer
     */
    public String toString()
    {
        String printout = firstName + " " + lastName + " " + personalNumber;
        return printout;
    }
    
    /**
     * Provides information about each account that the customer has
     * @return - list with Strings containing information about each account that the customer has
     */
    public ArrayList<String> listAllAccounts()
    {
        ArrayList<String> infoEachAccount = new ArrayList<String>();
        
        for(SavingsAccount eachAccount : this.accounts)
        {
            infoEachAccount.add(eachAccount.toString());
        }
        
        return infoEachAccount;
    }
    
    /**
     * Puts money on one of the customer's accounts
     * @param accountNumber - the account for depositing money on
     * @param depositSum - the amount of money to put on the account
     * @return - true if transaction went through successfully; false if no account with such a number was found
     */
    public boolean depositOnAccount(int accountNumber, double depositSum)
    {
        boolean done = false;
        for(SavingsAccount currentAccount : this.accounts)
        {
            if(currentAccount.getAccountNumber() == accountNumber)
            {
                currentAccount.deposit(depositSum);
                done = true;
            }
        }
        return done;
    }
    
    /**
     * Takes money from one of the customer's accounts
     * @param accountNumber - the account for withdrawing money from
     * @param withdrawalSum - the amount of money to take away from the account
     * @return - true if transaction went through successfully; false if no account with such a number was found or if the balance is less than the withdrawal sum 
     */
    public boolean withdrawalFromAccount(int accountNumber, double withdrawalSum)
    {
        boolean done = false;
        for(SavingsAccount currentAccount : this.accounts)
        {
            if(currentAccount.getAccountNumber() == accountNumber)
            {
                if(withdrawalSum <= currentAccount.getBalance())
                {
                    currentAccount.withdrawal(withdrawalSum);
                    done = true;  
                }
            }
        }
        return done;
    }
    
    /**
     * Shows numbers of all the accounts that the customer has
     * @return - list with Integers containing all the accounts' numbers
     */
    public ArrayList<Integer> showAllAccountNumbers()
    {
        ArrayList<Integer> numberEachAccount = new ArrayList<Integer>();
        
        for(SavingsAccount eachAccount : this.accounts)
        {
            numberEachAccount.add(eachAccount.getAccountNumber());
        }
        
        return numberEachAccount;
    }
    
    /**
     * Adds new account to the customer's accounts list
     * @return - the number of the newly created account
     */
    public int addAccount()
    {
        SavingsAccount newAccount = new SavingsAccount();
        accounts.add(newAccount);
        return newAccount.getAccountNumber();
    }
    
    /**
     * Shows information about a specific account from the customer's accounts
     * @param accountId - the number of the account to be shown
     * @return - String with the account's information; null if no account with the required number was found
     */
    public String showOneAccount(int accountId)
    {
        String infoOneAccount = null;
        
        for(SavingsAccount eachAccount : this.accounts)
        {
            if(eachAccount.getAccountNumber() == accountId)
            {
                infoOneAccount = eachAccount.toString();
            }
        }
        
        return infoOneAccount;
    }
    
    /**
     * Closes one of the customer's accounts
     * @param accountId - number of the account to close
     * @return - information about the closed account, including the calculated interest
     */
    public String closeAccount(int accountId)
    {
        String closedAccount = null;
        int indexOfAccountToRemove = -1;
        
        for(SavingsAccount oneAccount : this.accounts)
        {
            if(oneAccount.getAccountNumber() == accountId)
            {
                closedAccount = oneAccount.toString() + " " + Double.toString(oneAccount.calculateInterest());
                indexOfAccountToRemove = accounts.indexOf(oneAccount);
            }
        }
        
        if(indexOfAccountToRemove >= 0) //in other words if the account was found
        {
            accounts.remove(accounts.get(indexOfAccountToRemove));
        }
        
        return closedAccount;
    }

}
