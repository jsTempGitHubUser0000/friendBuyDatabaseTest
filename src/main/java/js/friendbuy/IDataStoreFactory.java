package js.friendbuy;

public interface IDataStoreFactory {
	IDataStore createDataStore(IDataStore baseStore);
}
