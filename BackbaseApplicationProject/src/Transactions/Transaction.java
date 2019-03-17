package Transactions;
public class Transaction 
{
	private int m_AccountID;
	private String m_AccountType;
	private String m_InitiatorType;
	private String m_DateTime;
	private float m_TransactionValue;
	
	public Transaction(int AccountID, String AccountType, String IniatorType, 
	String DateTime, float TransactionValue)
	{
		this.m_AccountID = AccountID;
		this.m_AccountType = AccountType;
		this.m_InitiatorType = IniatorType;
		this.m_DateTime = DateTime;
		this.m_TransactionValue = TransactionValue;
	}
	
	public int getAccountID()
	{
		return m_AccountID;
	}
	
	public String getAccountType()
	{
		return m_AccountType;
	}
	
	public String getInitiatorType()
	{
		return m_InitiatorType;
	}
	
	public String getDateTime()
	{
		return m_DateTime;
	}
	
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
