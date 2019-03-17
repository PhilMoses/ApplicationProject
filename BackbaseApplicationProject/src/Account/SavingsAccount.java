package Account;
//Coding Conventions Check Bob Laramee's Coding conventions
//Passed Rule 1 Method Length
//Passed Rule 2 Indentation
//Passed Rule 3 No line of Code exceeds 80 characters
//Passed Rule 4 All class variables must start with two character sequence
//Passed Rule 5 All class variables are accessed with accessor methods
//Passed Rule 6 Accessor Methods come at the top
//Passed Rule 7 Class Variables are private
//Passed Rule 8 Method Naming public methods begin with an upper case letter
			// 	Naming private methods with lower-case letter.
//Passed Rule 9 Methods have no more than 5 parameters
//Passed Rule 10 No Magic Numbers

public class SavingsAccount extends Account 
{


	private int m_AccountID;
	private float m_AccountBalance = 0;
	private String m_AccountType = "SAVINGS";
	private static int OVERDRAFTLIMIT = 0;
	
	public SavingsAccount(int AccountID)
	{
		this.m_AccountID = AccountID;
		
	}

	public int getAccountID() 
	{
		return m_AccountID;
	}

	public float getAccountBalance() 
	{
		return m_AccountBalance;
	}

	public String getAccountType() 
	{
		return m_AccountType;
	}

	public void setAccountType(String accountType) 
	{
		m_AccountType = accountType;
	}

	public static int getOVERDRAFTLIMIT() 
	{
		return OVERDRAFTLIMIT;
	}
	
	public void transaction(float transactionAmount)
	{
		System.out.println("I AM TRIGGERED TRANSACTION STARTU SAVINGS");
		float overDraftCheck = (this.getAccountBalance() + transactionAmount);
		System.out.println("This is the transcation result " + overDraftCheck);
		
		if(overDraftCheck >= OVERDRAFTLIMIT){
			this.m_AccountBalance = overDraftCheck;
		}else{
			System.out.println("Unsuccessful Transaction there is not enough "
					+ "money in the Savings Account");
		}
	}
	
	@Override
	public String toString() 
	{
		return "SavingsAccount [AccountID=" + m_AccountID + ", AccountBalance="+ 
		m_AccountBalance + ", AccountType=" + m_AccountType + "]";
	}

}
