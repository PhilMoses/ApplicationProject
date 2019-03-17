package FileReader;
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
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import Transactions.Transaction;
import java.io.FileNotFoundException;

public class FileReader1 
{
	public static ArrayList<Transaction> generateLedger(String filename)
	{
		ArrayList<Transaction> ledger = new ArrayList<Transaction>();
		File f = new File(filename);
		try {
			Scanner in = new Scanner(f);
			in.next();
			while (in.hasNext()){
				String rawStringTransaction = in.next();
				
				Transaction extractedTransaction = 
				extractTransaction(rawStringTransaction);

				ledger.add(extractedTransaction);
			}
			in.close();
		}catch(FileNotFoundException e){
			System.err.print(filename + "not found");
			System.exit(0);
		}
		
		
		return ledger;
		
	}
	
	public static Transaction extractTransaction(String data)
	{
		int AccountID = 0;
		String AccountType = null;
		String InitiatorType = null;
		String DateTime = null;
		float Value = 0;
		
		String[] values  = data.split(",");
		AccountID = Integer.valueOf(values[0]);
		AccountType = values[1];
		InitiatorType = values[2];
		DateTime = values[3];
		Value = Float.valueOf(values[4]);
		Transaction t = new Transaction(AccountID, AccountType, InitiatorType, 
		DateTime, Value);
		return t;
		
	}
	
	public static ArrayList<Transaction> addTransaction(ArrayList<Transaction> 
	ledger, Transaction t)
	{
		ledger.add(t);
		return ledger;
		
	}

}
