package js.friendbuy;

public class DefaultTransactionFactory implements ITransactionFactory {

	public ITransaction createTransaction() {
		return new DefaultTransaction();
	}

}
