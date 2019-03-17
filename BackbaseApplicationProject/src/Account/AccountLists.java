package Account;
import java.util.ArrayList;
import Transactions.Transaction;
/**
 * This is the AccountList class which makes it easier to return multiple 
 * the ArrayList of Accounts and ArrayList of AccountID in integer this is
 * useful for searching for Accounts
 * @author Philip Evans
 *
 */
public class AccountLists 
{
/**
 * AccountList and AccountIDChecker is an Array mainly because they are flexible
 * and it easy to manipulate. I have used these extensively in university
 * projects
 * class variables start with an "m_" because of the coding convention I 
 * followed.
 */
private ArrayList<Account> m_AccountList = new ArrayList<Account>();
private ArrayList<Integer> m_AccountIDChecker = new ArrayList<Integer>();
	
	/**
	 * This is the first constructor of the AccountList Class
	 * @param accountList this is an ArrayList of accounts recovered from the 
	 * input file
	 * @param accountIDChecker this the ID of Accounts available used for 
	 * validation
	 */
	public AccountLists(ArrayList<Account> accountList, ArrayList<Integer> 
	accountIDChecker)
	{
		this.m_AccountList = accountList;
		this.m_AccountIDChecker = accountIDChecker;
	}
	
	/**
	 * This is an overloaded method for the AccountList Constructor
	 * 
	 * While loop checks every transaction object in the ledger.
	 * Inside the while loop is an if statement which checks if the accountID of
	 * the transaction object exists in the accountIDchecklist to check if it is
	 * already added to the ArrayList of Accounts accountList. If it doesn't
	 * exist then the nested statements are triggered.
	 * 
	 * Nested inside the first if statement another if, else if and else
	 * statements are found. If these statements are creates the correct account
	 * and adds it to the accountList and the accoundIDCheckList if the else
	 * statement is triggered it returns an error.
	 * 
	 * @param ledger the ArrayList of Transactions generated from the input csv 
	 * file
	 * @param accountIDCheckList this the ID of Accounts available used for 
	 * validation the m_AccountIDChecker of the current object will become the
	 * same as this param at the end of this methods execution.
	 * @param accountList ArrayList of accounts generated from the input csv 
	 * file m_AccountList of the current object will become the
	 * same as this param at the end of this methods execution.
	 */
	public AccountLists(ArrayList<Transaction> ledger, ArrayList<Integer> 
	accountIDCheckList, ArrayList<Account> accountList)
	{
		int count = 0;
		
		while (ledger.size() > count){
			String checkTransaction = ledger.get(count).getAccountType();
			int checkAccountID = ledger.get(count).getAccountID();
			if (accountIDCheckList.indexOf(checkAccountID) == -1){
				if (Account.checkType(checkTransaction).equals("CURRENT")){
					CurrentAccount newCurrentAccount = new CurrentAccount(
					checkAccountID);
					accountList.add(newCurrentAccount);
					accountIDCheckList.add(checkAccountID);
					
					//System.out.println("SUCCESSFUL");
				}else if(Account.checkType(checkTransaction).equals("SAVINGS")){
					SavingsAccount newSavingsAccount = new SavingsAccount(
					checkAccountID);
					accountList.add(newSavingsAccount);
					accountIDCheckList.add(checkAccountID);
					
					//System.out.println("SUCCESSFUL");
					
				}else{
					System.out.println("Account in CSV line " + (count+1) + 
					"Has invalid account type");
					System.out.println("UNSUCCESSFUL");
				}
				
				
			}
			
			count = count + 1;
		}
		this.m_AccountList = accountList;
		this.m_AccountIDChecker = accountIDCheckList;
	}

	/**
	 * Accessor/Getter for the class variable m_AccountList
	 * @return List of the current customers accounts
	 */
	public ArrayList<Account> getAccountList() 
	{
		return m_AccountList;
	}
	
	/**
	 * Accessor/Getter for the class variable m_AccountIDChecker
	 * @return list of the current customer accountIDs
	 */
	public ArrayList<Integer> getAccountIDChecker() 
	{
		return m_AccountIDChecker;
	}
	
	/**
	 * This method differentiates which account in the list is the customers 
	 * Current Account or Savings Account. The While goes through the entire
	 * accountList(which should be a size of 2) and then the if statement uses
	 * the boolean values of svaingsAccountFound and currentAccountFound. If 
	 * either of these values are false then it will execute the nest if and
	 * else. The nested if catches the current account being evaluated is a 
	 * Savings account, if it is then saves the location in accountList as an
	 * int savingsAccountLocation. The else statement does the same but for 
	 * Current accounts. Then finally the else statement just increases the
	 * count to keep the search going.
	 * 
	 * @param accountList an ArrayList of accounts
	 * @return overallResult contains the location of the savingsAccount and
	 * currentAccount in the accountList as String seperated by a comma
	 */
	public static String findCustomerAccounts(ArrayList<Account> accountList)
	{
		int savingsAccountLocation = 0;
		int currentAccountLocation = 0;
		int count = 0;
		boolean savingsAccountFound = false;
		boolean currentAccountFound = false;
		while(accountList.size() > count){	
			String accountType = Account.checkType(accountList.get(count).
			getAccountType());
			if(savingsAccountFound == false || currentAccountFound == false){
				if(accountType.equals("SAVINGS")&&savingsAccountFound == false){
					savingsAccountLocation = count;
					savingsAccountFound = true;
					count++;
				}else if(accountType.equals("CURRENT") && currentAccountFound == 
				false){
					currentAccountLocation = count;	
					currentAccountFound = true;
					count++;
				}else{
				count++;
				}		
			}
		}		
		String overallResult=savingsAccountLocation+","+currentAccountLocation;
		return overallResult;
	}
	
	
	/**
	 * Method that prints out an ArrayList filled with accounts;
	 * @param accountList
	 */
	public static void printAccountArrayList(ArrayList<Account> accountList)
	{
		int count = 0;
		while (accountList.size() > count){
			System.out.println("This is the count" + count);
			System.out.println(accountList.get(count).toString());
			
			count++;
		}
		
	}



}
