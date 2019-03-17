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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Transactions.Transaction;

public class CSVWriter 
{
	public static void generateOutputFile(ArrayList<Transaction> ledger, 
	String filename)
	{
		String generateFileName = (filename+".csv");
		File maybeExists = new File(generateFileName);
		System.out.println("checking if file exists");
		if (maybeExists.exists() == true){
			System.out.println("file already exists overwriting file");
			saveCSV(ledger, maybeExists.getName());
		}else{
			try{
				maybeExists.createNewFile();
				System.out.println("Generating file name is"+generateFileName);
			}catch(IOException e){
				e.printStackTrace();
			}
			
			saveCSV(ledger, maybeExists.getName());
			
		}
	}

	private static void saveCSV(ArrayList<Transaction> ledger, String name) 
	{
		try{
			PrintWriter os = new PrintWriter(name);
			os.print(("AccountID,AccountType,InitiatorTyper,DateTime"
			+ ",TransactionValue"));
			os.print("\n");
			System.out.println("LEDGER SIZE IS " + ledger.size());
			for(int i = 0; i < (ledger.size());){
				os.print(ledger.get(i).getAccountID()+",");
				os.print(ledger.get(i).getAccountType()+",");
				os.print(ledger.get(i).getInitiatorType()+",");
				os.print(ledger.get(i).getDateTime()+",");
				os.print(ledger.get(i).getTransactionValue()+",");
				os.print("\n");
				i++;
			}
			
			os.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
			
		}
		
	}
	

}