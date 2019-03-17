package FileReader;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import Transactions.Transaction;
import java.io.FileNotFoundException;
/**
 * This is the FileReader class which is responsible for reading the csv file 
 * the user has inputed
 * @author Philip Evans
 *
 */
public class FileReader1 
{	
	/**
	 * This method generates the ledger of the Customer it does this by 
	 * making a new blank ledger and then searching through every line excluding
	 * the first line of the csv file and then passing out the raw text data to 
	 * the extractTransaction function which returns a transaction object. The
	 * transaction is then added to the new ledger. This process keeps going until
	 * the while loop doesn't find any data.
	 * @param filename filename of input file passed on by the user
	 * @return
	 */
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
	/**
	 * This Method is used to extract the information from the raw text data
	 * from the csv file. It is passed a line of String separated by commas. 
	 * Then it splices the string into data that be used to instantiate a 
	 * transaction object.
	 * 
	 * @param data
	 * @return
	 */
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

}
