package Main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * UserInputUtil class is used to userInput so it can be manipulated
 * @author Philip Evans
 *
 */
public class UserInputUtil 
{	
	/**
	 * These class variables are Strings that was be passed onto other methods
	 * in main.
	 * m_Name = customer first name
	 * m_Surname = customer surname
	 * m_InputCSVFile the filename of the input file
	 * m_OutputFile the filename of the output file
	 */
	private String m_Name;
	private String m_Surname;
	private String m_InputCSVFile;
	private String m_OutputCSVFile;
	
	public UserInputUtil(Scanner input){
	
		
		System.out.println("Welcome to Philip Moses Evans' Backbase application"
		+ " task");
		this.m_Name = UserInputUtil.InputString(input, "firstname");
		this.m_Surname = UserInputUtil.InputString(input, "surname");
		this.m_InputCSVFile = UserInputUtil.InputFile(input);
		this.m_OutputCSVFile = "OutPutFileOf"+ m_InputCSVFile;
		
	}
	
	/**
	 * Accessor to get the m_Name class variable
	 * @return the first name of the customer
	 */
	public String getName() {
		return m_Name;
	}
	
	/**
	 * Accessor to get the m_Surname class variable
	 * @return the surname of the customer
	 */
	public String getSurname() {
		return m_Surname;
	}
	
	/**
	 * Accessor to get the m_InputCSVFile class variable
	 * @return the name of the inputFile
	 */
	public String getInputCSVFile() {
		return m_InputCSVFile;
	}
	
	/**
	 * Accessor to get the m_OutputCSVFile class variable
	 * @return the name of the outputFile
	 */
	public String getOutputCSVFile() {
		return m_OutputCSVFile;
	}

	/**
	 * Method which generates the userinput to get the firstname or surname of
	 * a customer
	 * @param in the scanner used in the constructor
	 * @param type helps differentiate which type of user input to show 
	 * appropriate prompt message
	 * @return
	 */
	private static String InputString(Scanner in, String type){
		String outputString = "";
		boolean hasNext = true;
		do{
			if (type.equals("firstname")){
				System.out.println("Please enter the firstname of the customer "
				+ "you wish to evaluate");
			}else{
				System.out.println("Please enter the surname of the customer "
				+ "you wish to evaluate");
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
	
	/**
	 * User response used in main
	 * @param in scanner
	 * @return response of user
	 */
	public static String userContinue(Scanner in){

		
		System.out.println("Do you wish to evaluate another csv file? Y for yes anything else for no");
	
		String response = in.next();
		
		return response;
	}
	/**
	 * Method which generates the user input to get the inputFile name
	 * @param in the scanner used in the constructor
	 * @return returns the filename
	 */
	private static String InputFile(Scanner in) {
		Boolean fileExists = true;
		String filename = "";
		do{	
			System.out.println("Please enter the name of the csv ledger input "
			+ "file you wish to evaluate");
			if(in.hasNext()){
				filename = in.next(); 
				File maybeExists = new File(filename);
				if (maybeExists.exists() == true){
					System.out.println("file has been found");
					filename = maybeExists.getName();
					fileExists = true;
				}else{
					System.out.println("file " + filename + " has been not "
					+ "found try again");
					fileExists = false;
				}
			}
		}while (!fileExists);
			return filename;
	}
}