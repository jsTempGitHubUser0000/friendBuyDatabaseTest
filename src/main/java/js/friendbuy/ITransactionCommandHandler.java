package js.friendbuy;

public interface ITransactionCommandHandler {
	
	/**
	 * This method begins a new transaction. The new transaction will become the new "top level" or active transaction for this handler. 
	 * 
	 * This transaction will nest with any other active transactions and include their changes.
	 */
	void beginTransaction();
	
	/**
	 * This method ends the current top level transaction. Any data value changes that are exclusive to this transaction will be nullified. If there was a previous top-level transaction active
	 * before the rolled-back transaction began, it will resume the position of top-level transaction.
	 * 
	 * @throws NoCurrentTransactionException if no top-level transaction currently exists
	 */
	void rollbackCurrentTopLevelTransaction() throws NoCurrentTransactionException;
	
	
	/**
	 * This method makes permanent the net data changes of all currently active transactions. After the transactions have been committed, it will no longer be possible to roll back their data
	 * value changes. After this method has been called, no top-level transaction will remain.
	 * 
	 * @throws NoCurrentTransactionException if no top-level transaction currently exists
	 */
	void commitAllCurrentTransactions() throws NoCurrentTransactionException;

}
