package js.friendbuy;

import java.util.List;

public interface ITransaction {
	void addSetValueCommand(String name, String value);
	List<IDataValueCommand> getCommands();
}
