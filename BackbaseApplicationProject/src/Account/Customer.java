package Account;
//Coding Conventions Check Bob Laramee's Coding conventions
//Passed Rule 1 Method Length
//Passed Rule 2 Indentation
//Passed Rule 3 No line of Code exceeds 80 characters
//Passed Rule 4 All class variables must start with two character sequence
//Passed Rule 5 All class variables are accessed with accessor methods
//Passed Rule 6 Accessor Methods come at the top
//Passed Rule 7 Class Variables are private
//Passed Rule 8 Method Naming public methods begin with an upper case letter
			// 	Naming private methods with lower-case letter.
//Passed Rule 9 Methods have no more than 5 parameters
//Passed Rule 10 No Magic Numbers

import java.util.ArrayList;
import Transactions.Transaction;
import Account.CurrentAccount;
import Account.SavingsAccount;

public class Customer 
{
	private static int m_CountCustomerID = 0;
	private final int m_CustomerID;
	private String m_FirstName;
	private String m_Surname;
	private CurrentAccount m_CustomerCurrentAccount;
	private SavingsAccount m_CustomerSavingsAccount;
	private ArrayList<Transaction> m_CustomerLegder = new 
	ArrayList<Transaction>();
	
	public Customer(String firstname, String surname, CurrentAccount 
	customerCurrent, SavingsAccount customerSavings, ArrayList<Transaction> 
	ledger)
	{
		this.m_FirstName = firstname;
		this. m_Surname = surname;
		this.m_CustomerCurrentAccount = customerCurrent;
		this.m_CustomerSavingsAccount = customerSavings;
		this.m_CustomerLegder = ledger;
		this.m_CustomerID = m_CountCustomerID++;
	}
	//WORK ON THIS NULL POINTER FOR NOT FINDING ACCOUNTS
	public Customer (String name,String surname,String accountResults,
	ArrayList<Account> accountList,ArrayList<Transaction> ledger)
	{
		String[] rawAccountResults  = accountResults.split(",");
		String savingsAccountLocation = rawAccountResults[0];
		String currentAccountLocation = rawAccountResults[1];
		
		try{
			SavingsAccount savingsAccount = (SavingsAccount) 
			accountList.get(Integer.valueOf(savingsAccountLocation));
			
			CurrentAccount currentAccount = (CurrentAccount) 
			accountList.get(Integer.valueOf(currentAccountLocation));
			
			this.m_FirstName = name;
			this.m_Surname = surname;
			this.m_CustomerCurrentAccount = currentAccount;
			this.m_CustomerSavingsAccount = savingsAccount;
			this.m_CustomerLegder = ledger;
			
			
		}catch(IndexOutOfBoundsException exception){
			System.out.println("Customer was not created there is missing "
			 +"current Account or missing savings Account" +"\n" + "Check the"
			 +"input CSV file may be empty or missing current/savings account");
			System.exit(0);
		}
		
		this.m_CustomerID = m_CountCustomerID++;
		
	}

	public String getFirstName() 
	{
		return m_FirstName;
	}

	public void setFirstName(String firstName) 
	{
		m_FirstName = firstName;
	}

	public String getSurname() 
	{
		return m_Surname;
	}

	public void setSurname(String surname) 
	{
		m_Surname = surname;
	}

	public CurrentAccount getCustomerCurrentAccount() 
	{
		return m_CustomerCurrentAccount;
	}

	public void setCustomerCurrentAccount(CurrentAccount customerCurrentAccount)
	{
		m_CustomerCurrentAccount = customerCurrentAccount;
	}

	public SavingsAccount getCustomerSavingsAccount()
	{
		return m_CustomerSavingsAccount;
	}

	public void setCustomerSavingsAccount(SavingsAccount customerSavingsAccount)
	{
		m_CustomerSavingsAccount = customerSavingsAccount;
	}

	public ArrayList<Transaction> getCustomerLegder() 
	{
		return m_CustomerLegder;
	}

	public void setCustomerLegder(ArrayList<Transaction> customerLegder) 
	{
		m_CustomerLegder = customerLegder;
	}

	public int getCustomerID() 
	{
		return m_CustomerID;
	}
}

