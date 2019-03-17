package Main;
import java.util.ArrayList;
import java.util.Scanner;
import Account.Account;
import Account.AccountLists;
import Account.Customer;
import FileReader.FileReader1;
import Transactions.ExecuteLedger;
import Transactions.Transaction;
import FileReader.CSVWriter;
/**
 * This is the main class
 * @author Philip Evans
 *
 */
public class Main {
	public static void main(String[] args)
	{	
		/**
		 * Loop that allows multiple CSV Files work
		 */
		Scanner newScanner = new Scanner(System.in);
		boolean finished = false;
		do{
			runMain(newScanner);
			String response = UserInputUtil.userContinue(newScanner);
			System.out.println("Response is " + response);
			if (response.equals("Y")){
				System.out.println("Preparing to start again");
			}else{
				finished = true;
			}
		
		}while(!finished);

		newScanner.close();
	}
	
	/**
	 * Running all the methods and classes to complete the task
	 * @param in scanner from main method
	 */
	public static void runMain(Scanner in){
		
		/**
		 * Starting user inputs
		 */
		UserInputUtil userInput = new UserInputUtil(in);
		
		/**
		 * Reading in the csv File
		 */
		ArrayList<Transaction> ledger = FileReader1.
		generateLedger(userInput.getInputCSVFile());
		
		ArrayList<Integer> accountIDCheckList = new ArrayList<Integer>();
		
		ArrayList<Account> accountList = new ArrayList<Account>() ;
		
		/**
		 * Generating AccountList
		 */
		AccountLists accountsChecker = 
		new AccountLists(accountList, accountIDCheckList);
		
		accountsChecker = new AccountLists(
		ledger, accountIDCheckList, accountList);
		
		/**
		 * Generating the Current Customer
		 */
		Customer currentCustomer = new Customer(
		userInput.getName(), userInput.getSurname(),
		AccountLists.findCustomerAccounts(accountsChecker.getAccountList()),
		accountsChecker.getAccountList(), ledger);
		
		ExecuteLedger executeOutputLedger = new ExecuteLedger(
		currentCustomer, ledger, accountsChecker);
		/**
		 * Generating the outPutFile
		 */
		CSVWriter.generateOutputFile(
		executeOutputLedger.getCurrentCustomerLedger(),
		userInput.getOutputCSVFile());
	}
	
}