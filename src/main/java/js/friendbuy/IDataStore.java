package js.friendbuy;

public interface IDataStore extends IDataValueCommandHandler {
	
	/**
	 * This method copies all data in this data store into another data store. Data values in the target data store may be overwritten if there is a conflict. 
	 * 
	 * @param targetDataStore the data store to which all data should be copied
	 */
	void copyData(IDataStore targetDataStore);

}
