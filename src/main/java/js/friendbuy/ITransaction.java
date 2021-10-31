package js.friendbuy;

import java.util.List;

public interface ITransaction {
	
	/**
	 * This method adds a data value command to the list of commands tracked by this transaction.
	 * 
	 * @param name the identifier of the database value set by the command
	 * @param value the value assigned to the identifier by the command
	 */
	void addSetValueCommand(String name, String value);
	
	/**
	 * This method returns a list of data value commands included in the transaction.
	 * 
	 * @return an ordered list of the commands applied to this transaction
	 */
	List<IDataValueCommand> getCommands();
}
