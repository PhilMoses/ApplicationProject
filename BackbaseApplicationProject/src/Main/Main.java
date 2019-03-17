package Main;

import java.util.ArrayList;
import Account.Account;
import Account.AccountLists;
import Account.Customer;
import FileReader.FileReader1;
import Transactions.ExecuteLedger;
import Transactions.Transaction;
import FileReader.CSVWriter;

public class Main {
	public static void main(String[] args){

		
		UserInputUtil userInput = new UserInputUtil();
		ArrayList<Transaction> ledger = FileReader1.generateLedger(userInput.getInputCSVFile());
		ArrayList<Integer> accountIDCheckList = new ArrayList<Integer>();
		ArrayList<Account> accountList = new ArrayList<Account>() ;
		AccountLists accountsChecker = new AccountLists(accountList, accountIDCheckList);
		accountsChecker = new AccountLists(ledger, accountIDCheckList, accountList);
		Customer currentCustomer = new Customer(userInput.getName(), userInput.getSurname(), AccountLists.findCustomerAccounts(accountsChecker.getAccountList()),accountsChecker.getAccountList(), ledger);
		ExecuteLedger executeOutputLedger = new ExecuteLedger(currentCustomer, ledger, accountsChecker);
		ExecuteLedger.printLedger(executeOutputLedger.getCurrentCustomerLedger());
		CSVWriter.generateOutputFile(executeOutputLedger.getCurrentCustomerLedger(), userInput.getOutputCSVFile());
	}
	
}