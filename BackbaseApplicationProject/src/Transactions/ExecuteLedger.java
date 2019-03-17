package Transactions;
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
import Account.Customer;
import Account.SavingsAccount;
import Transactions.Transaction;
import java.util.ArrayList;
import Account.Account;
import Account.AccountLists;
import Account.CurrentAccount;
public class ExecuteLedger 
{	
	private Customer m_CurrentCustomer;
	
	private ArrayList<Transaction> m_CurrentCustomerLedger = 
	new ArrayList<Transaction>();
	
	private AccountLists m_AccountLists;
	
	public ExecuteLedger(Customer c, ArrayList<Transaction> l, AccountLists al)
	{
		this.m_CurrentCustomer = c;
		this.m_AccountLists = al;
		this.m_CurrentCustomerLedger = ValidateLedger(l);
		
	}

	public ArrayList<Transaction> getCurrentCustomerLedger() 
	{
		return m_CurrentCustomerLedger;
	}

	private ArrayList<Transaction> ValidateLedger(ArrayList<Transaction> 
	currentLedger) 
	{
		ArrayList<Transaction> outPutLedger = new ArrayList<Transaction>();
		Transaction executableTransaction;
		int count = 0;
		String findAccountID;
		int transactionLocation = 0;
		ArrayList<Account>accountList = m_AccountLists.getAccountList();
		
		while(currentLedger.size() > count){
			executableTransaction = currentLedger.get(count);
			//transactionLocation is +1 because of the first line in CSV are 
			//fields
			transactionLocation = (count+1);
			findAccountID = findAccount(executableTransaction, accountList, 
			transactionLocation);
			if(findAccountID.equals("UNSUCCESSFUL")){
				System.out.println("ERROR transaction in CSV line " + count+1 +
				" has invalid account");
				System.out.println("This the account id found that caused error"
				+ " " + findAccountID);
			
			}else{
				String[] results  = findAccountID.split(",");		
				int accountLocation = Integer.valueOf(results[1]);
				Account accountBeingWorked = accountList.get(accountLocation);
				
				String accountBeingWorkedType = 
				accountBeingWorked.getAccountType();
				
				outPutLedger = 
				GenerateLedger(outPutLedger, results, accountBeingWorked, 
				accountBeingWorkedType, executableTransaction, 
				transactionLocation);
			}
			
			count++;
		}
		return outPutLedger;
	}

	private ArrayList<Transaction> GenerateLedger(
	ArrayList<Transaction> outPutLedger, String[] results,
	Account accountBeingWorked, String accountBeingWorkedType,
	Transaction executableTransaction, int transactionLocation) 
	{	
		String executableTransactionType = 
		Account.checkType(executableTransaction.getAccountType(),
		transactionLocation);
		if(accountBeingWorkedType.equals(executableTransactionType)){
			if(accountBeingWorkedType.equals("CURRENT")){
				
				accountBeingWorked.transaction(
				executableTransaction.getTransactionValue());
				
				outPutLedger.add(executableTransaction);
			}else if(accountBeingWorkedType.equals("SAVINGS")){
				if((accountBeingWorked.getAccountBalance()+ 
					executableTransaction.getTransactionValue() >= 0))
				{
					
					outPutLedger.add(executableTransaction);
					accountBeingWorked.transaction(
					executableTransaction.getTransactionValue());
				}else{
				
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
		
	private ArrayList<Transaction> FillLedger(Transaction executableTransaction,
	ArrayList<Transaction> outPutLedger, Account accountBeingWorked, 
	int transactionLocation)
	{
		CurrentAccount customerCurrentAccount = 
		m_CurrentCustomer.getCustomerCurrentAccount();
		
		SavingsAccount customerSavingsAccount = 
		m_CurrentCustomer.getCustomerSavingsAccount();
		
		float currentAccountBalance =customerCurrentAccount.getAccountBalance();
		
		float savingsAccountRemainder = accountBeingWorked.getAccountBalance()+
		executableTransaction.getTransactionValue();
		
		float possibleOverallBalance = currentAccountBalance +
		savingsAccountRemainder;
		int currentAccountID = customerCurrentAccount.getAccountID();
		int savingsAccountID = customerSavingsAccount.getAccountID();
		if (possibleOverallBalance >= 0){
			accountBeingWorked.getAccountBalance();
			float savingsAccountDeposit = Math.abs(savingsAccountRemainder);
			
				Transaction systemTransaction=new Transaction(currentAccountID,
				"CURRENT", "SYSTEM", executableTransaction.getDateTime(),
				savingsAccountRemainder);
				
				Transaction systemTransaction2=new Transaction(savingsAccountID,
				"SAVINGS", "SYSTEM", executableTransaction.getDateTime(), 
				savingsAccountDeposit);
				
				customerCurrentAccount.transaction(
				systemTransaction.getTransactionValue());
				
				customerSavingsAccount.transaction(
				systemTransaction2.getTransactionValue());
				outPutLedger.add(systemTransaction);
				outPutLedger.add(systemTransaction2);
				outPutLedger.add(executableTransaction);
			
		}else{
			System.out.println("transaction in CSV in line " + 
			transactionLocation +"\" failed due to limited funds in both "
			+ "CurrentAccount and SavingsAccount");
			
			System.out.println ("The balanced has stayed at" + 
			accountBeingWorked.getAccountBalance() + "in account ID" +
			accountBeingWorked.getAccountID());
		}
		
		return outPutLedger;
	}
	
			
		
		

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

	public Customer getCurrentCustomer() 
	{
		return m_CurrentCustomer;
	}
	
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
