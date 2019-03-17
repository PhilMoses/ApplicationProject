package Account;
import java.util.ArrayList;
import Transactions.Transaction;
import Account.CurrentAccount;
import Account.SavingsAccount;

public class Customer {
	private static int countCustomerID = 0;
	private final int CustomerID;
	private String FirstName;
	private String Surname;
	private CurrentAccount CustomerCurrentAccount;
	private SavingsAccount CustomerSavingsAccount;
	private ArrayList<Transaction> CustomerLegder = new ArrayList<Transaction>();
	
	public Customer(String firstname, String surname, CurrentAccount customerCurrent, SavingsAccount customerSavings, ArrayList<Transaction> ledger){
		this.FirstName = firstname;
		this.Surname = surname;
		this.CustomerCurrentAccount = customerCurrent;
		this.CustomerSavingsAccount = customerSavings;
		this.CustomerLegder = ledger;
		this.CustomerID = countCustomerID++;
	}
	
	public Customer (String name, String surname,String accountResults, ArrayList<Account> accountList,  ArrayList<Transaction> ledger){
		String[] rawAccountResults  = accountResults.split(",");
		String savingsAccountLocation = rawAccountResults[0];
		String currentAccountLocation = rawAccountResults[1];
		
		SavingsAccount savingsAccount = (SavingsAccount) accountList.get(Integer.valueOf(savingsAccountLocation));
		
		CurrentAccount currentAccount = (CurrentAccount) accountList.get(Integer.valueOf(currentAccountLocation));
		
		this.FirstName = name;
		this.Surname = surname;
		this.CustomerCurrentAccount = currentAccount;
		this.CustomerSavingsAccount = savingsAccount;
		this.CustomerLegder = ledger;
		this.CustomerID = countCustomerID++;
		
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public CurrentAccount getCustomerCurrentAccount() {
		return CustomerCurrentAccount;
	}

	public void setCustomerCurrentAccount(CurrentAccount customerCurrentAccount) {
		CustomerCurrentAccount = customerCurrentAccount;
	}

	public SavingsAccount getCustomerSavingsAccount() {
		return CustomerSavingsAccount;
	}

	public void setCustomerSavingsAccount(SavingsAccount customerSavingsAccount) {
		CustomerSavingsAccount = customerSavingsAccount;
	}

	public ArrayList<Transaction> getCustomerLegder() {
		return CustomerLegder;
	}

	public void setCustomerLegder(ArrayList<Transaction> customerLegder) {
		CustomerLegder = customerLegder;
	}

	public int getCustomerID() {
		return CustomerID;
	}
}

