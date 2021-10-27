package js.friendbuy;

public class SetValueCommand implements IDataValueCommand {
	private String name;
	private String value;
	
	public SetValueCommand(String name, String value) {
		this.name = name;
		this.value = value;
	}	
	
	
	public void applyCommand(IDataValueCommandHandler commandHandler) {
		commandHandler.setValue(name, value);
	}

}
