package js.friendbuy;

import java.util.Scanner;

public class MainClass {

	public static void  main(String[] args) {
		boolean keepRunnning = true;
		
		Scanner inputScanner = new Scanner(System.in);
		
		DefaultTransactionFactory transactionFactory = new DefaultTransactionFactory();
		
		DefaultDataStoreFactory dataStoreFactory = new DefaultDataStoreFactory();
		
		DefaultDatabaseManager defaultDatabaseManager = new DefaultDatabaseManager(dataStoreFactory, 
				transactionFactory);
		
		DefaultDatabaseCommandParser commandParser = new DefaultDatabaseCommandParser(defaultDatabaseManager);
		
		System.out.println("IN MEMORY DATABASE EXAMPLE:");
		
		while(keepRunnning) {
			
			String commandString = inputScanner.nextLine();
			
			//Split the input string by whitespace
			//Todo: Determine how input values containing whitespace or control characters such as endlines should be handled (values might need to be placed in quotes in this case)
			String[] commandArray = commandString.split("\\s+");
			
			String output = null;
			
			if (commandArray.length > 0) {	
				if (commandArray[0].equals("END")) {
					//Quit the program shell
					keepRunnning = false;					
				} else {					
					String[] argumentsArray = new String[commandArray.length - 1];
					
					System.arraycopy(commandArray, 1, argumentsArray, 0, commandArray.length - 1);
					
					try {
						//This parser handles actual database command in order to separate them from commands that control the program "shell" itself, allowing more thorough testing of database commands.
						output = commandParser.parseAndExecuteCommand(commandArray[0], argumentsArray);
					} catch (NoCurrentTransactionException | InvalidCommandException ex) {
						output = ex.getMessage();
					}					
				}
				
			} else {
				output = "PLEASE ENTER A COMMAND";
			}
			
			if (output != null) {
				System.out.println(output);
			} else {
				System.out.println();
			}				
		}
	}
	
	
}
