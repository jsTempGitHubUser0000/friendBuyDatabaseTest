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
		
		System.out.println("IN MEMORY DATABASE EXAMPLE:");
		
		while(keepRunnning) {
			
			String commandString = inputScanner.nextLine();
			
			String[] commandArray = commandString.split("\\s+");
			
			String output = null;
			
			if (commandArray.length > 0) {	
				if (commandArray[0].equals("END")) {
					keepRunnning = false;					
				} else if (commandArray[0].equals("SET")) {
					if (commandArray.length == 3) {
						defaultDatabaseManager.setValue(commandArray[1], commandArray[2]);
						
					} else {
						output = "INVALID ARGUMENTS FOR SET COMMAND";
					}
										
				}  else if (commandArray[0].equals("UNSET")) {
					if (commandArray.length == 2) {
						defaultDatabaseManager.setValue(commandArray[1], null);						
					} else {
						output = "INVALID ARGUMENTS FOR UNSET COMMAND";
					}									
				} else if (commandArray[0].equals("GET")) {
					if (commandArray.length == 2) {
						String value = defaultDatabaseManager.getValue(commandArray[1]);
						
						if (value == null) {
							output = "NULL";
						} else {
							output = value;
						}						
					} else {
						output = "INVALID ARGUMENTS FOR GET COMMAND";
					}									
				} else if (commandArray[0].equals("NUMEQUALTO")) {
					if (commandArray.length == 2) {
						int value = defaultDatabaseManager.countValueOccurrences(commandArray[1]);
						output = Integer.toString(value);						
					} else {
						output = "INVALID ARGUMENTS FOR GET COMMAND";
					}									
				} else if (commandArray[0].equals("BEGIN")) {
					if (commandArray.length == 1) {
						defaultDatabaseManager.beginTransaction();						
					} else {
						output = "INVALID ARGUMENTS FOR BEGIN COMMAND";
					}									
				} else if (commandArray[0].equals("ROLLBACK")) {
					if (commandArray.length == 1) {
						try {
							defaultDatabaseManager.rollbackCurrentTransaction();
						} catch(NoCurrentTransactionException exception) {
							output = "NO TRANSACTION";
						}
					} else {
						output = "INVALID ARGUMENTS FOR ROLLBACK COMMAND";
					}									
				} else if (commandArray[0].equals("COMMIT")) {
					if (commandArray.length == 1) {
						try {
							defaultDatabaseManager.commitTransaction();
						} catch(NoCurrentTransactionException exception) {
							output = "NO TRANSACTION";
						}
					} else {
						output = "INVALID ARGUMENTS FOR ROLLBACK COMMAND";
					}									
				} else {
					output = "UNRECOGNIZED COMMAND";
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
