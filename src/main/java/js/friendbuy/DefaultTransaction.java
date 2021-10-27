package js.friendbuy;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DefaultTransaction implements ITransaction {
	private List<IDataValueCommand> commands;
	
	public DefaultTransaction() {
		commands = new ArrayList<IDataValueCommand>();
	}
	
	
	public void addSetValueCommand(String name, String value) {
		//Extract instantiation into a factory if more types of commands need to be tracked		
		commands.add(new SetValueCommand(name, value));
	}

	public List<IDataValueCommand> getCommands() {
		return Collections.unmodifiableList(commands);
	}

}
