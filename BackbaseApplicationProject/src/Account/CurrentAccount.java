package Account;

public class CurrentAccount extends Account {
	@Override
	public String toString() {
		return "CurrentAccount [AccountID=" + AccountID + ", AccountBalance=" + AccountBalance + ", AccountType="
				+ AccountType + "]";
	}

	private int AccountID;
	private float AccountBalance = 0;
	private String AccountType = "CURRENT";
	
	public CurrentAccount(int AccountID){
		this.AccountID = AccountID;
		
	}
	
	public void transaction(float transactionAmount){
		System.out.println("I AM TRIGGERED TRANSACTION STARTU CURRENT");
		this.AccountBalance = AccountBalance + transactionAmount;
		
	}
	
	public int getAccountID() {
		return AccountID;
	}

	public void setAccountID(int accountID) {
		AccountID = accountID;
	}

	public float getAccountBalance() {
		return AccountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		AccountBalance = accountBalance;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	
}