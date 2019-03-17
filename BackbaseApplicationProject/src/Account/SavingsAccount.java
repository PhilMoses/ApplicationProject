package Account;

public class SavingsAccount extends Account 
{
	/**
	 *@see Account
	 *This has the same class variable as Account but it the type is set to 
	 *"SAVINGS" instead of "ABSTRACT" the difference between this class and
	 *CurrentAccount is that in the brief it describes that this accounts
	 *balance cannot be under 0 therefore the OVERDRAFTLIMIT = 0 which is used
	 *to compare to the balance and if the balance+transaction > OVERDRAFTLIMIT
	 *then the transaction is invalid. 
	 */
	private int m_AccountID;
	private float m_AccountBalance = 0;
	private String m_AccountType = "SAVINGS";
	private static int OVERDRAFTLIMIT = 0;
	
	/**
	 * Constructor of the SavingsAccount, you only need an accountID to create
	 * a SavingsAccount Object
	 * @param AccountID the accountID found from the input csv aka input ledger
	 */
	public SavingsAccount(int AccountID)
	{
		this.m_AccountID = AccountID;
		
	}
	
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
	 * @return the current AccountType of this account, it should be "SAVINGS"
	 */
	public String getAccountType() 
	{
		return m_AccountType;
	}
	/**
	 * Accessor to get the overdraft limit
	 * @return 0 which is the overdraft allowed aka none
	 */
	public static int getOVERDRAFTLIMIT() 
	{
		return OVERDRAFTLIMIT;
	}
	
	/**
	 * The transaction class here is different because it first needs to check
	 * if transaction is possible. This is used as the last catch to any invalid 
	 * transactions that may pass through the other methods of validation of 
	 * transaction in other methods before this one.
	 */
	public void transaction(float transactionAmount)
	{;
		float overDraftCheck = (this.getAccountBalance() + transactionAmount);
		
		if(overDraftCheck >= OVERDRAFTLIMIT){
			this.m_AccountBalance = overDraftCheck;
		}else{
			System.out.println("Unsuccessful Transaction there is not enough "
					+ "money in the Savings Account");
		}
	}
	/**
	 * The toString method
	 */
	@Override
	public String toString() 
	{
		return "SavingsAccount [AccountID=" + m_AccountID + ", AccountBalance="+ 
		m_AccountBalance + ", AccountType=" + m_AccountType + "]";
	}

}
