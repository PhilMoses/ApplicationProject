package Transactions;
/**
 * Transaction class 
 * @author Philip Evans
 *
 */
public class Transaction 
{	
	/**
	 * Class variables taken from the field names in the CSV file in line 1
	 */
	private int m_AccountID;
	private String m_AccountType;
	private String m_InitiatorType;
	private String m_DateTime;
	private float m_TransactionValue;
	
	/**
	 * The Constructor of the Transaction Class
	 * @param AccountID
	 * @param AccountType
	 * @param IniatorType
	 * @param DateTime
	 * @param TransactionValue
	 */
	public Transaction(int AccountID, String AccountType, String IniatorType, 
	String DateTime, float TransactionValue)
	{
		this.m_AccountID = AccountID;
		this.m_AccountType = AccountType;
		this.m_InitiatorType = IniatorType;
		this.m_DateTime = DateTime;
		this.m_TransactionValue = TransactionValue;
	}

	/**
	 * Accessor to get the m_AccountID class variable
	 * @return the Account ID of the current transaction
	 */
	public int getAccountID()
	{
		return m_AccountID;
	}

	/**
	 * Accessor to get the m_AccountType class variable
	 * @return the AccountType of the current transaction
	 */
	public String getAccountType()
	{
		return m_AccountType;
	}

	/**
	 * Accessor to get the m_InitiatorType class variable
	 * @return returns the initiator of the current transaction
	 */
	public String getInitiatorType()
	{
		return m_InitiatorType;
	}

	/**
	 * Accessor to get the m_DateTime class variable
	 * @return the Date and Time of the current transaction
	 */
	public String getDateTime()
	{
		return m_DateTime;
	}

	/**
	 * Accessor to get the m_TransactionValue class variable
	 * @return the value of the current transaction
	 */
	public float getTransactionValue()
	{
		return m_TransactionValue;
	}
	
	@Override
	public String toString() 
	{
		return "Transaction [AccountID=" + m_AccountID + ", AccountType=" + 
		m_AccountType + ", InitiatorType=" + m_InitiatorType + ", DateTime=" + 
		m_DateTime + ", TransactionValue=" + m_TransactionValue + "]";
	}

}
