package js.friendbuy;

public interface IDataValueCommandHandler {
	
	public void setValue(String name, String value);
	
	public String getValue(String name);
	
	public int countValueOccurrences(String value);
	
}
