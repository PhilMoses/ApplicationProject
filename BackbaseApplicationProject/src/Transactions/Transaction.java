package Transactions;
public class Transaction {
	@Override
	public String toString() {
		return "Transaction [AccountID=" + AccountID + ", AccountType=" + AccountType + ", InitiatorType="
				+ InitiatorType + ", DateTime=" + DateTime + ", TransactionValue=" + TransactionValue + "]";
	}

	private int AccountID;
	private String AccountType;
	private String InitiatorType;
	private String DateTime;
	private float TransactionValue;
	
	public Transaction(int AccountID, String AccountType, String IniatorType, String DateTime, float TransactionValue){
		this.AccountID = AccountID;
		this.AccountType = AccountType;
		this.InitiatorType = IniatorType;
		this.DateTime = DateTime;
		this.TransactionValue = TransactionValue;
	}
	
	public int getAccountID(){
		return AccountID;
	}
	
	public String getAccountType(){
		return AccountType;
	}
	
	public String getInitiatorType(){
		return InitiatorType;
	}
	
	public String getDateTime(){
		return DateTime;
	}
	
	public float getTransactionValue(){
		return TransactionValue;
	}
	

}
