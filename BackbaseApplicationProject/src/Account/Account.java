package Account;
/**
 * The Abstract Account class for CurrentAccount and SavingsAccount classes
 * @author Philip Evans
 */
public abstract class Account 
{	
	/**
	 * These are class variables I deduced from the specification account
	 * balance starts at 0. 
	 * Assuming from Appendix1 of the brief an Account should atleast have an
	 * AccountID(m_AccountID) and AccountType(m_AccountType). I added an extra
	 * class variable m_AccountType to help differentiate between Current, 
	 * Savings and Abstract accounts.
	 * This is just an abstract class in only has the class variables that 
	 * 
	 * CurrentAccount and SavingsAccount share.class variables start with an 
	 * "m_" because of the coding convention I followed.
	 */
	private int m_AccountID;
	private float m_AccountBalance = 0;
	private String m_AccountType = "ABSTRACT";
	
	/**
	 *  Accessor/Getter for the class variable Account ID
	 * @return the account's ID is returned
	 */
	public int getAccountID()
	{
		return m_AccountID;
	}
	
	/**
	 * Accessor/Getter for the class variable Account Balance
	 * @return the account's balance is returned
	 */
	public float getAccountBalance()
	{
		return m_AccountBalance;
	}
	
	/**
	 * Accessor for the class variable which holds what type of Account this is
	 * @return this current Account type of this account
	 */
	public String getAccountType()
	{
		return m_AccountType;
	}
	
	/**
	 * This method handles transactions
	 * @param transactionAmount this is the float value of how much is being
	 * added/subtracted to the current Account
	 */
	public void transaction(float transactionAmount)
	{
		this.m_AccountBalance = m_AccountBalance + transactionAmount;
		
	}
	
	/**
	 * This method checks if the account type is valid
	 * @param newAccountType this takes in string of raw accountType
	 * @return this returns either "CURRENT" or "SAVINGS" and the error will be 
	 * returned as "UNKNOWN"
	 */
	public static String checkType(String newAccountType) 
	{
		if (newAccountType.equals("CURRENT")){
			return "CURRENT";
		}else if(newAccountType.equals("SAVINGS")){
			return "SAVINGS";
		}else{
			System.out.println("There was an error processing the accountype of"
					+"transaction");
			System.out.println("Because the result was"+newAccountType+"is "
					+"not either SAVINGS or CURRENT accountType");
			return "UNKNOWN";
		}
	}

	/**
	 * Overloaded method of the previous method modified to take an extra param
	 * @param newAccountType this takes in string of raw accountType
	 * @param transactionLocation location of the transaction in the ledger/
	 * csv file
	 * @return this returns either "CURRENT" or "SAVINGS" and the error will be 
	 * returned as "UNKNOWN"
	 */
	public static String checkType(String newAccountType,int transactionLocation
	) 
	{
		if (newAccountType.equals("CURRENT")){
			return "CURRENT";
		}else if(newAccountType.equals("SAVINGS")){
			return "SAVINGS";
		}else{
			System.out.println("There was an error processing the accountype of"
					+ "transaction");
			System.out.println("In line "+transactionLocation+" of the CSV "
					+ "file");
			System.out.println("Because the result was "+newAccountType+"is "
					+ "not either SAVINGS or CURRENT accountType");
			return "UNKNOWN";
		}
		
	}
}
