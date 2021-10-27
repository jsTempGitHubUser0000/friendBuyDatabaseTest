package js.friendbuy;

public class DefaultDataStoreFactory implements IDataStoreFactory {

	public IDataStore createDataStore(IDataStore baseStore) {
		DefaultDataStore newStore = new DefaultDataStore();
		
		if (baseStore != null) {		
			baseStore.copyData(newStore);
		}		
		
		return newStore;
	}

}
