package Account;

public class SavingsAccount extends Account {
	@Override
	public String toString() {
		return "SavingsAccount [AccountID=" + AccountID + ", AccountBalance=" + AccountBalance + ", AccountType="
				+ AccountType + "]";
	}

	private int AccountID;
	private float AccountBalance = 0;
	private String AccountType = "SAVINGS";
	private static int OVERDRAFTLIMIT = 0;
	
	public SavingsAccount(int AccountID){
		this.AccountID = AccountID;
		
	}
	
	public void transaction(float transactionAmount){
		System.out.println("I AM TRIGGERED TRANSACTION STARTU SAVINGS");
		float overDraftCheck = (this.getAccountBalance() + transactionAmount);
		System.out.println("This is the transcation result " + overDraftCheck);
		
		if(overDraftCheck >= OVERDRAFTLIMIT){
			System.out.print("LOOK ME IM SUCCESSFUL :DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
			this.AccountBalance = overDraftCheck;
		}else{
			System.out.println("Unsuccessful Transaction there is not enough money in the Savings Account");
		}
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

	public static int getOVERDRAFTLIMIT() {
		return OVERDRAFTLIMIT;
	}

}
