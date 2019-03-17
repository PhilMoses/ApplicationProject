package FileReader;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import Transactions.Transaction;
import java.io.FileNotFoundException;

public class FileReader1 {
	public static ArrayList<Transaction> generateLedger(String filename){
		ArrayList<Transaction> ledger = new ArrayList<Transaction>();
		File f = new File(filename);
		try {
			Scanner in = new Scanner(f);
			in.next();
			while (in.hasNext()){
				String rawStringTransaction = in.next();
				Transaction extractedTransaction = extractTransaction(rawStringTransaction);
				ledger.add(extractedTransaction);
			}
			in.close();
		}catch(FileNotFoundException e){
			System.err.print(filename + "not found");
			System.exit(0);
		}
		
		
		return ledger;
		
	}
	
	public static Transaction extractTransaction(String data){
		int AccountID = 0;
		String AccountType = null;
		String InitiatorType = null;
		String DateTime = null;
		float Value = 0;
		
		String[] values  = data.split(",");
		AccountID = Integer.valueOf(values[0]);
		//System.out.println("This is accountID " + AccountID);
		AccountType = values[1];
		//System.out.println("This is accountType " + AccountType);
		InitiatorType = values[2];
		//System.out.println("This is initiatorType " + InitiatorType);
		DateTime = values[3];
		//System.out.println("This is DateTime " + DateTime);
		Value = Float.valueOf(values[4]);
		//System.out.println("This is Value " + Value);
		
		Transaction t = new Transaction(AccountID, AccountType, InitiatorType, DateTime, Value);
		
		return t;
		
	}
	
	public static ArrayList<Transaction> addTransaction(ArrayList<Transaction> ledger, Transaction t){
		ledger.add(t);
		return ledger;
		
	}

}
