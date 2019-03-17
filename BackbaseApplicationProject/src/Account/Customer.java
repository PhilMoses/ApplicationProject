package Account;
import java.util.ArrayList;
import Transactions.Transaction;
import Account.CurrentAccount;
import Account.SavingsAccount;
/**
 * This is the Customer Class which holds all the data for the Customer
 * @author Philip Evans
 *
 */
public class Customer 
{	
	/**
	 * Most of these Customers class variables are assumptions which the 
	 * exception of Current and Savings account as stated in Background of the
	 * Brief.
	 * m_CountCustomerID ensures that the customerID is unique
	 * customerID final int to make sure it isn't the same as any other
	 * firstname, surname of the customer are class variables that I assumed
	 * would be neccessary. The ArrayList of Transaction is the customer ledger
	 * aka the csv inputFile or outputFile.
	 */
	private static int m_CountCustomerID = 0;
	private final int m_CustomerID;
	private String m_FirstName;
	private String m_Surname;
	private CurrentAccount m_CustomerCurrentAccount;
	private SavingsAccount m_CustomerSavingsAccount;
	private ArrayList<Transaction> m_CustomerLegder = new 
	ArrayList<Transaction>();
	
	/**
	 * This is the Constructor for the customer
	 * @param firstname
	 * @param surname
	 * @param customerCurrent
	 * @param customerSavings
	 * @param ledger
	 */
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
	/**
	 * This is the Overloaded Constructor for the customer which takes in an 
	 * accountList and accountResults this is useful if the CurrentAccount and
	 * SavingsAccount is stored in an accountList and needs to be differentiated
	 * 
	 * The Try attempts to extract the CurrentAccount and SavingAccount of the
	 * customer by splitting the accountResult which has the information of the
	 * each Accounts location. And then attempt to add the Accounts using the 
	 * information from savingsAccountLocation and currentAccountLocation which
	 * points to where the Accounts are in the accountList.
	 * 
	 * If this is unsuccessful it will end the program mainly because it means
	 * that the input file is invalid. 
	 * 
	 * Assuming that every csv InputFile will have a current and savings account
	 * in the file.
	 * 
	 * @param name
	 * @param surname
	 * @param accountResults
	 * @param accountList
	 * @param ledger
	 */
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
	/**
	 * Accessor Method for the m_Firstname class variable
	 * @return returns the customers first name
	 */
	public String getFirstName() 
	{
		return m_FirstName;
	}
	
	/**
	 * Accessor Method for the m_Surname class variable
	 * @return returns the customers surname
	 */
	public String getSurname() 
	{
		return m_Surname;
	}

	/**
	 * Accessor Method for the m_CustomerCurrentAccount class variable
	 * @return returns the customers CurrentAccount object
	 */
	public CurrentAccount getCustomerCurrentAccount() 
	{
		return m_CustomerCurrentAccount;
	}
	
	/**
	 * Accessor Method for the m_CustomerSavingsAccount class variable
	 * @return returns the customers SavingsAccount object
	 */
	public SavingsAccount getCustomerSavingsAccount()
	{
		return m_CustomerSavingsAccount;
	}
	
	/**
	 * Accessor Method for the m_CustomerLegder class variable
	 * @return returns the customers ledger which is an ArrayList of 
	 * Transactions
	 */
	public ArrayList<Transaction> getCustomerLegder() 
	{
		return m_CustomerLegder;
	}
	
	/**
	 * Accessor Method for the m_CustomerID class variable
	 * @return returns the customers ID
	 */
	public int getCustomerID() 
	{
		return m_CustomerID;
	}
}

