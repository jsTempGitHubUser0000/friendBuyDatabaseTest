package js.friendbuy;

public interface IDataValueCommandHandler {
	
	/**
	 * This method sets or removes the value assigned to a given data identifier in the database.
	 * 
	 * @param name the identifier of the data value to be set
	 * @param value the new value to assign to the specified key, or null to remove the currently assigned value
	 */
	public void setValue(String name, String value);
	
	/**
	 * This method retrieves the current value assigned to a given data identifier in the database, if it exists.
	 * 
	 * @param name the identifier for the data value to be retrieved
	 * @return the current value assigned to the specified name, or null if no value is currently assigned to that name
	 */
	public String getValue(String name);
	
	/**
	 * This method finds the number of instances in which a given value is assigned to any identifier in the database. 
	 * 
	 * @param value a non-null data value
	 * @return the number of distinct identifier to which the value is currently assigned
	 */
	public int countValueOccurrences(String value);
	
}
