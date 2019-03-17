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

public class AccountLists 
{
private ArrayList<Account> m_AccountList = new ArrayList<Account>();
private ArrayList<Integer> m_AccountIDChecker = new ArrayList<Integer>();

	public AccountLists(ArrayList<Account> accountList, ArrayList<Integer> 
	accountIDChecker)
	{
		this.m_AccountList = accountList;
		this.m_AccountIDChecker = accountIDChecker;
	}
	
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
	
	public ArrayList<Account> getAccountList() 
	{
		return m_AccountList;
	}
	
	public void setAccountList(ArrayList<Account> accountList) 
	{
		this.m_AccountList = accountList;
	}
	
	public ArrayList<Integer> getAccountIDChecker() 
	{
		return m_AccountIDChecker;
	}
	
	public void setAccountIDChecker(ArrayList<Integer> accountIDChecker) 
	{
		this.m_AccountIDChecker = accountIDChecker;
	}
	
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
			if (accountType.equals("SAVINGS") && savingsAccountFound == false){
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
