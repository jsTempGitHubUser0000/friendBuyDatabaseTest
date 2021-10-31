package js.friendbuy;

public interface IDataValueCommand {
	/**
	 * This method applies a data value change command to the targeted database or manager.
	 * 
	 * @param commandHandler the command handler to which the data change should be applied. 
	 */
	
	void applyCommand(IDataValueCommandHandler commandHandler);
}
