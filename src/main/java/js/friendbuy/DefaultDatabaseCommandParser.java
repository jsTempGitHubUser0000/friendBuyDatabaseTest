package js.friendbuy;

public class DefaultDatabaseCommandParser implements IDatabaseCommandParser {
	
	private IDatabaseManager databaseManager;
	
	public DefaultDatabaseCommandParser(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}
	

	public String parseAndExecuteCommand(String commandName, String[] arguments) throws InvalidCommandException, NoCurrentTransactionException {
		
		switch(commandName) {
			case "SET":
				checkArguments("SET", arguments, 2);				
				databaseManager.setValue(arguments[0], arguments[1]);
				return null;
			
			case "UNSET":
				checkArguments("UNSET", arguments, 1);
				databaseManager.setValue(arguments[0], null);	
				return null;
				
			case "GET":
				checkArguments("GET", arguments, 1);
				return databaseManager.getValue(arguments[0]);						
				
			case "NUMEQUALTO":
				checkArguments("NUMEQUALTO", arguments, 1);
				return Integer.toString(databaseManager.countValueOccurrences(arguments[0]));
				
			case "BEGIN":
				checkArguments("BEGIN", arguments, 0);
				databaseManager.beginTransaction();
				return null;
				
			case "ROLLBACK":
				checkArguments("ROLLBACK", arguments, 0);
				databaseManager.rollbackCurrentTopLevelTransaction();
				return null;
				
			case "COMMIT":
				checkArguments("COMMIT", arguments, 0);
				databaseManager.commitAllCurrentTransactions();				
				return null;
				
			default:
				throw new InvalidCommandException("UNRECOGNIZED COMMAND: " + commandName);
		}
		
	}
	
	private void checkArguments(String commandName, String[] arguments, int expectedLength) throws InvalidCommandException {
		if (arguments.length != expectedLength) {
			throw new InvalidCommandException("INVALID ARGUMENTS FOR " + commandName + " COMMAND (" + expectedLength + " ARGUMENTS EXPECTED, " + arguments.length + " FOUND)");
		}
	}

}
