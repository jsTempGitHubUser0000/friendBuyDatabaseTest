package js.friendbuy;

public interface IDatabaseCommandParser {
	
	/**
	 * This method parses database commands, executes them, and returns any results. In the case of an invalid command, it should throw an exception that explains what is wrong with the command.
	 * 
	 * @param commandName name of the database command to be parsed and executed
	 * @param arguments any arguments supplied for the database command
	 * @return a string containing the data returned by the command, or null if no data was returned
	 * @throws InvalidCommandException if the supplied command and/or arguments could not be parsed as a valid database command
	 * @throws NoCurrentTransactionException if the specified command could not be executed due to a lack of any active transaction
	 */
	public String parseAndExecuteCommand(String commandName, String[] arguments) throws InvalidCommandException, NoCurrentTransactionException;

}
