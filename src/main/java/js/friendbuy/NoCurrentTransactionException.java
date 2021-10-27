package js.friendbuy;

public class NoCurrentTransactionException extends Exception {
	
	public NoCurrentTransactionException(String message) {	
		super(message);	
	}
	
}
