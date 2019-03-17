package Main;

import java.io.File;
import java.util.Scanner;

public class UserInputUtil {
	private String name;
	private String surname;
	private String inputCSVFile;
	private String outputCSVFile;
	
	public UserInputUtil(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Philip Moses Evans' Backbase application task");
		this.name = UserInputUtil.inputString(input, "firstname");
		this.surname = UserInputUtil.inputString(input, "surname");
		this.inputCSVFile = UserInputUtil.inputFile(input);
		this.outputCSVFile = UserInputUtil.inputString(input, "outputFile");
		
		input.close();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getInputCSVFile() {
		return inputCSVFile;
	}

	public void setInputCSVFile(String inputCSVFile) {
		this.inputCSVFile = inputCSVFile;
	}

	public String getOutputCSVFile() {
		return outputCSVFile;
	}

	public void setOutputCSVFile(String outputCSVFile) {
		this.outputCSVFile = outputCSVFile;
	}

	public static String inputString(Scanner in, String type){
		String outputString = "";
		boolean hasNext = true;
		do{
			if (type.equals("firstname")){
				System.out.println("Please enter the first name of the customer you wish to evaluate");
			}else if(type.equals("surname")){
				System.out.println("Please enter the surname of the customer you wish to evaluate");
			}else{
				System.out.println("Please enter output CSV file name");
			}
			
			if(in.hasNext()){
				outputString = in.next();
			}else{
			System.out.println("Please enter something");
			
			hasNext = false;
			in.next();
			}
		
		} while (!(hasNext));
		return outputString;
	
	}
	
	private static String inputFile(Scanner in) {
		Boolean fileExists = true;
		String filename = "";
		do{	
			System.out.println("Please enter the name of the csv ledger input file you wish to evaluate");
			if(in.hasNext()){
				filename = in.next(); 
				File maybeExists = new File(filename);
				if (maybeExists.exists() == true){
					System.out.println("file has been found");
					filename = maybeExists.getName();
					fileExists = true;
				}else{
					System.out.println("file " + filename + " has been not found try again");
					fileExists = false;
				}
			}
		}while (!fileExists);
			return filename;
	}
}