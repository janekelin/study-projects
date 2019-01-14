package iankal7;

import java.util.ArrayList;

/**
 * This class handles all the bank's customers
 * 
 * @author Iana Kalinichenko, iankal-7
 *
 */
public class BankLogic
{
    
    //Instance variables
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    
    //Instance methods
    /**
     * Gathers information about all customers, if customer list is not empty
     * @return a list with the customers' data as a String of "Name Surname PersonalNumber" the customer list is not empty; otherwise - information about absence of customers in the customer list
     */
    public ArrayList<String> getAllCustomers()
    {
        ArrayList<String> infoAllCustomers = new ArrayList<String>();
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                infoAllCustomers.add(eachCustomer.toString());
            }
        }
        
        return infoAllCustomers;
    }
    
    /**
     * Adds a new customer to the customer list
     * @param name - new customer's first name
     * @param surname - new customer's last name
     * @param pNo - new customer's personal number
     * @return - true if the customer was added to the list, and false if there is already a customer with such a personal number in the customer list
     */
    public boolean createCustomer(String name, String surname, String pNo)
    {
        boolean done = false;
        boolean found = false;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    found = true;
                }
            }
        }
        
        if(!found)
        {
            Customer anotherCustomer = new Customer(name, surname, pNo);
            customers.add(anotherCustomer);
            done = true;
        }
        
        return done;
    }
    
    /**
     * Provides information about a specific customer and his/her accounts
     * @param pNo - personal number of the customer
     * @return - a list with Strings representing information about a specific customer and his/her accounts
     */
    public ArrayList<String> getCustomer(String pNo)
    {
        ArrayList<String> infoOneCustomer = new ArrayList<String>();
        boolean found = false;
        int indexOfCustomerToShow = -1;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    found = true;
                    indexOfCustomerToShow = customers.indexOf(eachCustomer);
                }
            }
        }
        
        if(!found)
        {
            infoOneCustomer = null;
        }else
        {
            infoOneCustomer.add(customers.get(indexOfCustomerToShow).toString());
            for(String infoEachAccount : customers.get(indexOfCustomerToShow).listAllAccounts())
            {
                infoOneCustomer.add(infoEachAccount);
            }
        }
        
        return infoOneCustomer;
        
    }
    
    /**
     * Changes the name of a specific customer
     * @param name - new first name of the customer
     * @param surname - new last name of the customer
     * @param pNo - personal number of the customer whose name should be changed
     * @return - true if the customers name was changed, false if the customer with such a personal number does not exist or if the customer list is empty
     */
    public boolean changeCustomerName(String name, String surname, String pNo)
    {
        boolean done = false;
        boolean found = false;
        int indexOfCustomerToHandle = -1;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    found = true;
                    indexOfCustomerToHandle = customers.indexOf(eachCustomer);
                }
            }
        }
        
        if(found)
        {
            customers.get(indexOfCustomerToHandle).changeName(name, surname);
            done = true;
        }
        
        return done;
    }
    
    /**
     * Deletes a specific customer from the customer list 
     *  and this customers accounts from his/her account list
     * @param pNo - personal number of the customer to be deleted
     * @return - a list with Strings representing information about a specific deleted customer and his/her accounts
     */
    public ArrayList<String> deleteCustomer(String pNo)
    {
        ArrayList<String> infoDeletedCustomer = new ArrayList<String>();
        boolean done = false;
        boolean found = false;
        int indexOfCustomerToDelete = -1;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    found = true;
                    indexOfCustomerToDelete = customers.indexOf(eachCustomer);
                }
            }
        }
        
        if(found)
        {
            infoDeletedCustomer.add(customers.get(indexOfCustomerToDelete).toString());
            
            String tempS = null;
            for(Integer numberEachAccount : customers.get(indexOfCustomerToDelete).showAllAccountNumbers())
            {
                tempS = closeAccount(customers.get(indexOfCustomerToDelete).getPersonalNumber(), numberEachAccount);
                infoDeletedCustomer.add(tempS);
            }
            
            customers.remove(indexOfCustomerToDelete);
            done = true;
        }
        
        if(!done)
        {
            infoDeletedCustomer = null;
        }
        
        return infoDeletedCustomer;
        
    }
    
    /**
     * Creates a new savings account for a particular customer
     * @param pNo - personal number of the customer who should get a new account
     * @return - number of a newly created account
     */
    public int createSavingsAccount(String pNo)
    {
        int newAccountNumber = -1;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    newAccountNumber = eachCustomer.addAccount();
                }
            }
        }
        
        return newAccountNumber;
        
    }
    
    /**
     * Shows information about a specific account from a specific customer's accounts
     * @param pNo - personal number of a customer whose account should be shown
     * @param accountId - number of the account to show
     * @return - information about the requested account; null if the customer is not in the list or if the account is not in the customer's accounts list
     */
    public String getAccount(String pNo, int accountId)
    {
        String accountInfo = null;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    for(Integer eachAccountNumber : eachCustomer.showAllAccountNumbers())
                    {
                        if(eachAccountNumber == accountId)
                        {
                            accountInfo = eachCustomer.showOneAccount(eachAccountNumber);
                        }
                    }
                }
            }
            
        }
        
        return accountInfo;
    }
    
    /**
     * Deposits money on a specific customer's account
     * @param pNo - personal number of the customer who deposits money
     * @param accountId - number of the account to deposit money to
     * @param amount - the amount of money to deposit
     * @return - true if the transaction was done successfully; false if the customer is not in the list or if the account is not in the customer's accounts list
     */
    public boolean deposit(String pNo, int accountId, double amount)
    {
        boolean done = false;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    done = eachCustomer.depositOnAccount(accountId, amount);
                }
            }
        }
        
        return done;
    }
    
    /**
     * Withdraws money from a specific customer's account
     * @param pNo - personal number of the customer who withdraws money
     * @param accountId - number of the account to withdraw money from
     * @param amount - the amount of money to withdraw
     * @return - true if the transaction was done successfully; false if the customer is not in the list or if the account is not in the customer's accounts list, or if there are not enough money to withdraw
     */
    public boolean withdraw(String pNo, int accountId, double amount)
    {
        boolean done = false;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNo))
                {
                    done = eachCustomer.withdrawalFromAccount(accountId, amount);
                }
            }
        }
        
        return done;
    }
    
    /**
     * Closes a specific account of a specific customer
     * @param pNr - personal number of a customer whose account should be closed
     * @param accountId - number of the account to close
     * @return - information about the closed account, including the calculated interest
     */
    public String closeAccount(String pNr, int accountId)
    {
        String infoClosedAccount = null;
        
        if(!customers.isEmpty())
        {
            for(Customer eachCustomer : customers)
            {
                if(eachCustomer.getPersonalNumber().equals(pNr))
                {
                    infoClosedAccount = eachCustomer.closeAccount(accountId);
                }
            }
        }
        
        return infoClosedAccount;
    }

}
