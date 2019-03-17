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
public abstract class Account 
{
	private int m_AccountID;
	private float m_AccountBalance = 0;
	private String m_AccountType = "ABSTRACT";

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
	

	public void transaction(float transactionAmount)
	{
		this.m_AccountBalance = m_AccountBalance + transactionAmount;
		
	}
	
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
