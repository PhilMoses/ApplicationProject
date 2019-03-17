package Main;
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
import java.util.Scanner;

public class UserInputUtil 
{
	private String m_Name;
	private String m_Surname;
	private String m_InputCSVFile;
	private String m_OutputCSVFile;
	
	public UserInputUtil(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Philip Moses Evans' Backbase application"
		+ " task");
		this.m_Name = UserInputUtil.InputString(input, "firstname");
		this.m_Surname = UserInputUtil.InputString(input, "surname");
		this.m_InputCSVFile = UserInputUtil.InputFile(input);
		this.m_OutputCSVFile = UserInputUtil.InputString(input, "outputFile");
		
		input.close();
	}
	

	public String getName() {
		return m_Name;
	}

	public String getSurname() {
		return m_Surname;
	}

	public String getInputCSVFile() {
		return m_InputCSVFile;
	}

	public String getOutputCSVFile() {
		return m_OutputCSVFile;
	}


	private static String InputString(Scanner in, String type){
		String outputString = "";
		boolean hasNext = true;
		do{
			if (type.equals("firstname")){
				System.out.println("Please enter the firstname of the customer "
				+ "you wish to evaluate");
			}else if(type.equals("surname")){
				System.out.println("Please enter the surname of the customer "
				+ "you wish to evaluate");
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