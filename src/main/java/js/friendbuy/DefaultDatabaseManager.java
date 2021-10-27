package js.friendbuy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class DefaultDatabaseManager implements IDatabaseManager {

	private ITransactionFactory transactionFactory;
	
	private IDataStoreFactory dataStoreFactory;

	//The current values with all current transactions applied
	private IDataStore topLevelTransactionDataStore;
	
	//The permanent set of values
	private IDataStore rootDataStore;
		
	//Current stack of valid transactions
	private List<ITransaction> transactionStack;
	
	
	public DefaultDatabaseManager(IDataStoreFactory dataStoreFactory, 
			ITransactionFactory transactionFactory) {
		
		this.transactionStack = new ArrayList<ITransaction>(0);
		
		this.dataStoreFactory = dataStoreFactory;
		this.transactionFactory = transactionFactory;
		
		this.rootDataStore = dataStoreFactory.createDataStore(null);		
		this.topLevelTransactionDataStore = rootDataStore;		
	}
	
	
	public void setValue(String name, String value) {
		// TODO Auto-generated method stub
		
		//Immediately apply the new value to the top-level cache
		topLevelTransactionDataStore.setValue(name, value);
		
		//If there is an active transaction, add the command to the transaction
		if (transactionStack.size() > 0) {
			transactionStack.get(transactionStack.size() - 1).addSetValueCommand(name, value);
		} 
	}

	public String getValue(String name) {		
		return topLevelTransactionDataStore.getValue(name);
	}

	public int countValueOccurrences(String value) {
		return topLevelTransactionDataStore.countValueOccurrences(value);
	}

	public void beginTransaction() {
		ITransaction newTransaction = transactionFactory.createTransaction();
		
		//Make a temporary cache for the current transaction stack if necessary
		if (topLevelTransactionDataStore == rootDataStore) {
			topLevelTransactionDataStore = dataStoreFactory.createDataStore(rootDataStore);
		}
				
		transactionStack.add(newTransaction);		
	}

	public void rollbackCurrentTransaction() throws NoCurrentTransactionException {		
		if (transactionStack.size() < 1) {
			throw new NoCurrentTransactionException("Unable to Roll Back - No Current Transaction");
		}
		
		//Remove the top-level transaction
		transactionStack.remove(transactionStack.size() - 1);
		
		
		if (transactionStack.size() > 0) {
			//Create a new cache to replace the current top-level cache starting with a copy of the permanent data store
			IDataStore newTopLevelTransactionDataStore = dataStoreFactory.createDataStore(rootDataStore);
			
			
			//Rebuild the current top-level cache by re-applying all currently valid transactions in order
			for (ITransaction transaction : transactionStack) {
				//Re-apply the list of commands in the transaction
				for (IDataValueCommand command : transaction.getCommands()) {
					command.applyCommand(newTopLevelTransactionDataStore);
				}
			}
			
			//Swap the new cache for the old one
			topLevelTransactionDataStore = newTopLevelTransactionDataStore;
		} else {
			//There is no longer an active transaction - the root is now the cache
			topLevelTransactionDataStore = rootDataStore;
		}
	}

	public void commitTransaction() throws NoCurrentTransactionException {
		if (transactionStack.size() < 1) {
			throw new NoCurrentTransactionException("Unable to Commit - No Current Transaction");
		}
		
		//Make the current cached transactional version of the data store the permanent version
		rootDataStore = topLevelTransactionDataStore;
		
		//Clear the transaction stack
		transactionStack.clear();
	}

}
