package Account;
/**
 * This is the Current Account Class this is for the Current Account mentioned 
 * in the project brief
 * @author Philip Evans
 *
 */
public class CurrentAccount extends Account 
{	
	/**
	 *@see Account
	 *This has the same class variable as Account but it the type is set to 
	 *"CURRENT" instead of "ABSTRACT" the difference between this class and
	 *SavingsAccount is that in the brief I assumed that this has unlimited 
	 *Overdraft therefore it is not not needed unlike in the SavingsAccount 
	 *class.
	 */
	private int m_AccountID;
	private float m_AccountBalance = 0;
	private String m_AccountType = "CURRENT";
	
	/**
	 * Constructor of the CurrentAccount, you only need an accountID to create
	 * a CurrentAccount Object
	 * @param AccountID he accountID found from the input csv aka input ledger
	 */
	public CurrentAccount(int AccountID){
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
	 * @return the current AccountType of this account, it should be "CURRENT"
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
	 * This is the to string method that prints the transaction
	 */
	@Override
	public String toString() 
	{
		return "CurrentAccount [AccountID=" + m_AccountID + ", AccountBalance="+ 
	m_AccountBalance + ", AccountType=" + m_AccountType + "]";
	}
}