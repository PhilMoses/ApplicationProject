package Transactions;

import Account.Customer;
import Account.SavingsAccount;
import Transactions.Transaction;
import java.util.ArrayList;
import Account.Account;
import Account.AccountLists;
import Account.CurrentAccount;
/**
 * This is the class which handles feature described in the briefs Requirements
 * @author Philip Evans
 *
 */
public class ExecuteLedger 
{	
	/**
	 * Class variables 
	 * It Stores the Customer that is related to the ledger
	 * It Stores the ArrayList of Transactions related to the m_CurrentCustomer
	 * It Stores the AccountList of the Customer
	 */
	private Customer m_CurrentCustomer;
	
	private ArrayList<Transaction> m_CurrentCustomerLedger = 
	new ArrayList<Transaction>();
	
	private AccountLists m_AccountLists;
	
	/**
	 * Constructor for the Execute Ledger this is important because it uses the
	 * ValidateLegder method which executes the features described in the
	 * requirements
	 * 
	 * @param c is Customer
	 * @param l is ledger
	 * @param al is an accountList
	 */
	public ExecuteLedger(Customer c, ArrayList<Transaction> l, AccountLists al)
	{
		this.m_CurrentCustomer = c;
		this.m_AccountLists = al;
		this.m_CurrentCustomerLedger = ValidateLedger(l);
		
	}
	
	/**
	 * Accessor to get the m_CurrentCustomer class variable
	 * @return the current customers information
	 */
	
	public Customer getCurrentCustomer() 
	{
		return m_CurrentCustomer;
	}
	
	/**
	 * Accessor to get the m_OutputCSVFile class variable
	 * @return the name of the outputFile
	 */
	public ArrayList<Transaction> getCurrentCustomerLedger() 
	{
		return m_CurrentCustomerLedger;
	}
	/**
	 * This method Validates the currentLedger generates an outPutLedger which
	 * contains the System transaction made (if any) to prevent the customer's
	 * savings account from reaching zero
	 * @param currentLedger
	 * @return
	 */
	private ArrayList<Transaction> ValidateLedger(ArrayList<Transaction> 
	currentLedger) 
	{
		ArrayList<Transaction> outPutLedger = new ArrayList<Transaction>();
		Transaction executableTransaction;
		int count = 0;
		String findAccountID;
		int transactionLocation = 0;
		ArrayList<Account>accountList = m_AccountLists.getAccountList();
		/**
		 * While loop used to navigate through the currentLegder
		 * while executableTransaction stores the transaction being navigated to
		 * transactionLocation is count+1 because the first line in the CSV
		 * is just fields.
		 * findAccountID validates whether current transactions account ID is 
		 * valid and exists within the accountList of the customer
		 */
		while(currentLedger.size() > count){
			executableTransaction = currentLedger.get(count);
			transactionLocation = (count+1);
			/**
			 * This is to find the account in the ledger that corresponds to the
			 * account in the accountList and where it is
			 */
			findAccountID = findAccount(executableTransaction, accountList, 
			transactionLocation);
			/**
			 * This is triggered if the value of findAccountID = "unsucessful"
			 * meaning the account in the transaction does not exist in the 
			 * accountListmeaning it is invalid
			 */
			if(findAccountID.equals("UNSUCCESSFUL")){
				System.out.println("ERROR transaction in CSV line " + count+1 +
				" has invalid account");
				System.out.println("This the account id found that caused error"
				+ " " + findAccountID);
			/**
			 * If this is triggered then transaction  has a valid account ID and
			 * can now be used to affect the customer account and make a ledger
			 * the outPutLedger keeps getting bigger for every pass.
			 */
			}else{
				String[] results  = findAccountID.split(",");		
				int accountLocation = Integer.valueOf(results[1]);
				
				Account accountBeingWorked = accountList.get(accountLocation);
				
				outPutLedger = 
				GenerateLedger(outPutLedger, accountBeingWorked,
				executableTransaction, 
				transactionLocation);
			}
			
			count++;
		}
		return outPutLedger;
	}
	/**
	 * This Method adds to the outPutLegder
	 * @param outPutLedger outPutLedger that includes the System Transactions
	 * @param accountBeingWorked the account that is going to have the 
	 * transaction performed on it
	 * @param executableTransaction the transaction being used
	 * @param transactionLocation location of the transaction accountList
	 * @return
	 */
	private ArrayList<Transaction> GenerateLedger(
	ArrayList<Transaction> outPutLedger,Account accountBeingWorked,
	Transaction executableTransaction, int transactionLocation) 
	{	
		String accountBeingWorkedType = accountBeingWorked.getAccountType();
		/**
		 * check the type of the executableTransaction to make sure the
		 * transaction affects the right kind of account;
		 */
		String executableTransactionType = 
		Account.checkType(executableTransaction.getAccountType(),
		transactionLocation);
		/**
		 * If the account taken is has the same type as the executable
		 * transaction then it is valid and go into the nested if
		 */
		if(accountBeingWorkedType.equals(executableTransactionType)){
			/**
			 * This if else determines whether account type is "CURRENT" or 
			 * "SAVINGS" if it is "CURRENT" then it can do any transaction 
			 * because there is no overdraft
			 */
			if(accountBeingWorkedType.equals("CURRENT")){
				
				accountBeingWorked.transaction(
				executableTransaction.getTransactionValue());
				
				outPutLedger.add(executableTransaction);
			/**
			 * If the account type is "SAVINGS" then more precautions need to be
			 * made because the account balance cannot be lower than 0 
			 */
			}else if(accountBeingWorkedType.equals("SAVINGS")){
				/**
				 * Checks whether if the result of the current account balance +
				 * the executable transaction is greater or equal to 0
				 */
				if((accountBeingWorked.getAccountBalance()+ 
					executableTransaction.getTransactionValue() >= 0))
				{
					/**
					 * If it is then it is simple just allow the transaction
					 * to go through and add it to the ledger aswell as
					 * changing the account balance for the current account
					 */
					outPutLedger.add(executableTransaction);
					accountBeingWorked.transaction(
					executableTransaction.getTransactionValue());
				}else{
					/**
					 * This is the important part or basically the feature
					 * this is what handles the feature met in requirements
					 * the FillLedger allows the System to step in and take
					 * money from the Current Account and give it to the 
					 * Savings Account
					 */
					outPutLedger = FillLedger(executableTransaction,
					outPutLedger,accountBeingWorked,transactionLocation);
					
			}
			
			}else{
				System.out.print("The Account type in the Account list does "
				+ "not match the ledger in CSV line " + transactionLocation);
				
				System.out.print("This transaction will be skipped due to "
				+ "error");
			}
			
		}
		return outPutLedger;
	}

	/**
	 * This method is the feature described in the Requirements of the Brief
	 * @param executableTransaction executableTransaction the transaction being 
	 * used
	 * @param outPutLedger outPutLedger that includes the System Transactions
	 * @param accountBeingWorked the account that is gonna have the transaction 
	 * performed on it
	 * @param transactionLocation location of the transaction accountList
	 * @return
	 */
	private ArrayList<Transaction> FillLedger(Transaction executableTransaction,
	ArrayList<Transaction> outPutLedger, Account accountBeingWorked, 
	int transactionLocation)
	{	
		/**
		 * This gets the appropriate Current Account and Savings Account
		 */
		CurrentAccount customerCurrentAccount = 
		m_CurrentCustomer.getCustomerCurrentAccount();
		
		SavingsAccount customerSavingsAccount = 
		m_CurrentCustomer.getCustomerSavingsAccount();
			
		/**
		 * The Savings Account Balance if transaction is successful
		 */
		float savingsAccountRemainder = accountBeingWorked.getAccountBalance()+
		executableTransaction.getTransactionValue();
		

		int currentAccountID = customerCurrentAccount.getAccountID();
		int savingsAccountID = customerSavingsAccount.getAccountID();
		
		
		accountBeingWorked.getAccountBalance();
		/**
		 * The amount of money needed to make savings balance 0 again
		 */
		float savingsAccountDeposit = Math.abs(savingsAccountRemainder);
		
		/**
		 * Take enough money from the Current Account 
		 */
		Transaction systemTransaction=new Transaction(currentAccountID,
		"CURRENT", "SYSTEM", executableTransaction.getDateTime(),
		savingsAccountRemainder);
		
		/**
		 * Give the amount of money needed to savings to make it 0 again
		 */
		Transaction systemTransaction2=new Transaction(savingsAccountID,
		"SAVINGS", "SYSTEM", executableTransaction.getDateTime(), 
		savingsAccountDeposit);
		
		/**
		 * Apply the transactions to the Current and Savings Account of the
		 * Customer
		 */
		customerCurrentAccount.transaction(
		systemTransaction.getTransactionValue());
		
		customerSavingsAccount.transaction(
		systemTransaction2.getTransactionValue());
		
		/**
		 * Add the transactions to the outputLedger
		 */
		outPutLedger.add(systemTransaction);
		outPutLedger.add(systemTransaction2);
		outPutLedger.add(executableTransaction);
	
		
		return outPutLedger;
	}
	
			
		
		
	/**
	 * This Method is used to check whether transactions account ID exists
	 * in the ArrayList accountList
	 * 
	 * If it does return the information in String which can be separated and
	 * turned into data 
	 * 
	 * or Error value string "UNSUCESSFUL"
	 * 
	 * @param transaction transaction being evaluated
	 * @param accountList accounts of Customer
	 * @param transactionLocation location of the transaction in the csv file/
	 * ledger
	 * @return
	 */
	private String findAccount(Transaction transaction, ArrayList<Account>
	accountList, int transactionLocation) 
	{
		
		int count = 0;
		int accountID = transaction.getAccountID();
		while (accountList.size() > count){
			int accountListID = accountList.get(count).getAccountID();
			if (accountListID == accountID){
				String result = accountListID + "," + count ;
				return result;
			}else{
				count++;				
			}		
		}
		System.out.println("That account couldn't be found");
		System.out.println("Account ID " + accountID + "in transaction in line "
		+ transactionLocation + " could not be found");
		return "UNSUCCESSFUL";
	}

	/**
	 * Print Ledger Method
	 * @param ledger
	 */
	public static void printLedger(ArrayList<Transaction> ledger)
	{
		int count = 0;
		while(ledger.size() > count){
			System.out.println("This is count " + count);
			System.out.println(ledger.get(count).toString());
			
			count++;
		}
	}
	
}
