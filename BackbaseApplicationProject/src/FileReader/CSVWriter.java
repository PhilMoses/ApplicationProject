package FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Transactions.Transaction;

public class CSVWriter {
	public static void generateOutputFile(ArrayList<Transaction> ledger, String filename){
		String generateFileName = (filename+".csv");
		File maybeExists = new File(generateFileName);
		System.out.println("checking if file exists");
		if (maybeExists.exists() == true){
			System.out.println("file already exists overwriting file");
			saveCSV(ledger, maybeExists.getName());
		}else{
			try{
				maybeExists.createNewFile();
				System.out.println("Generating file name is" + generateFileName);
			}catch(IOException e){
				e.printStackTrace();
			}
			
			saveCSV(ledger, maybeExists.getName());
			
		}
	}

	private static void saveCSV(ArrayList<Transaction> ledger, String name) {
		try{
			PrintWriter os = new PrintWriter(name);
			os.print(("AccountID,AccountType,InitiatorTyper,DateTime,TransactionValue"));
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