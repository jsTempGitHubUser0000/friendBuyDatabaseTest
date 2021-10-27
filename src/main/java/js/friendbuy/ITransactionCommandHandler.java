package js.friendbuy;

public interface ITransactionCommandHandler {
	
	void beginTransaction();
	
	void rollbackCurrentTransaction() throws NoCurrentTransactionException;
	
	void commitTransaction() throws NoCurrentTransactionException;

}
