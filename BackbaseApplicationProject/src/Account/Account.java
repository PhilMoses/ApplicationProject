package Account;


public abstract class Account {
	private int AccountID;
	private float AccountBalance = 0;
	private String AccountType = "ABSTRACT";

	
	public int getAccountID(){
		return AccountID;
	}
	
	public float getAccountBalance(){
		return AccountBalance;
	}
	
	public String getAccountType(){
		return AccountType;
	}
	
	public void transaction(float transactionAmount){
		this.AccountBalance = AccountBalance + transactionAmount;
		
	}
	
	public static String checkType(String newAccountType) {
		if (newAccountType.equals("CURRENT")){
			return "CURRENT";
		}else if(newAccountType.equals("SAVINGS")){
			return "SAVINGS";
		}else{
			System.out.println("There was an error processing the accountype of transaction");
			System.out.println("Because the result was " + newAccountType + "is not either SAVINGS or CURRENT accountType");
			return "UNKNOWN";
		}
	}

	
	public static String checkType(String newAccountType, int transactionLocation) {
		if (newAccountType.equals("CURRENT")){
			return "CURRENT";
		}else if(newAccountType.equals("SAVINGS")){
			return "SAVINGS";
		}else{
			System.out.println("There was an error processing the accountype of transaction");
			System.out.println("In line " + transactionLocation + " of the CSV file");
			System.out.println("Because the result was " + newAccountType + "is not either SAVINGS or CURRENT accountType");
			return "UNKNOWN";
		}
		
	}
}
