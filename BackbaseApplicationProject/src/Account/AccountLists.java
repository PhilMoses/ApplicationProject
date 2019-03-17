package Account;

import java.util.ArrayList;

import Transactions.Transaction;

public class AccountLists {
private ArrayList<Account> accountList = new ArrayList<Account>();
private ArrayList<Integer> accountIDChecker = new ArrayList<Integer>();

	public AccountLists(ArrayList<Account> accountList, ArrayList<Integer> accountIDChecker){
		this.accountList = accountList;
		this.accountIDChecker = accountIDChecker;
	}
	
	public AccountLists(ArrayList<Transaction> ledger, ArrayList<Integer> accountIDCheckList, ArrayList<Account> accountList){
		int count = 0;
		
		while (ledger.size() > count){
			String checkTransaction = ledger.get(count).getAccountType();
			int checkAccountID = ledger.get(count).getAccountID();
			if (accountIDCheckList.indexOf(checkAccountID) == -1){
				if (Account.checkType(checkTransaction).equals("CURRENT")){
					CurrentAccount newCurrentAccount = new CurrentAccount(checkAccountID);
					accountList.add(newCurrentAccount);
					accountIDCheckList.add(checkAccountID);
					
					//System.out.println("SUCCESSFUL");
				}else if(Account.checkType(checkTransaction).equals("SAVINGS")){
					SavingsAccount newSavingsAccount = new SavingsAccount(checkAccountID);
					accountList.add(newSavingsAccount);
					accountIDCheckList.add(checkAccountID);
					
					//System.out.println("SUCCESSFUL");
					
				}else{
					System.out.println("Account in CSV line " + (count+1) + "Has invalid account type");
					System.out.println("UNSUCCESSFUL");
				}
				
				
			}
			
			count = count + 1;
		}
		this.accountList = accountList;
		this.accountIDChecker = accountIDCheckList;
	}
	
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	
	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}
	
	public ArrayList<Integer> getAccountIDChecker() {
		return accountIDChecker;
	}
	
	public void setAccountIDChecker(ArrayList<Integer> accountIDChecker) {
		this.accountIDChecker = accountIDChecker;
	}
	
	public static String findCustomerAccounts(ArrayList<Account> accountList){
		int savingsAccountLocation = 0;
		int currentAccountLocation = 0;
		int count = 0;
		boolean savingsAccountFound = false;
		boolean currentAccountFound = false;
		while(accountList.size() > count){	
			String accountType = Account.checkType(accountList.get(count).getAccountType());
			if(savingsAccountFound == false || currentAccountFound == false){
			if (accountType.equals("SAVINGS") && savingsAccountFound == false){
				savingsAccountLocation = count;
				savingsAccountFound = true;
				count++;
			}else if(accountType.equals("CURRENT") && currentAccountFound == false){
				currentAccountLocation = count;	
				currentAccountFound = true;
				count++;
			}else{
			count++;
			}	
			}
		}		
		String overallResult = savingsAccountLocation+","+currentAccountLocation;
		return overallResult;
	}
	
	public static void printAccountArrayList(ArrayList<Account> accountList){
		int count = 0;
		while (accountList.size() > count){
			System.out.println("This is the count" + count);
			System.out.println(accountList.get(count).toString());
			
			count++;
		}
		
	}


}
