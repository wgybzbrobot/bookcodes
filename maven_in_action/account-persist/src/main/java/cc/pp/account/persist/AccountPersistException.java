package cc.pp.account.persist;

public class AccountPersistException extends Exception {

	private static final long serialVersionUID = 6831370029476266744L;

	public AccountPersistException(String message) {
		super(message);
	}

	public AccountPersistException(String message, Throwable e) {
		super(message, e);
	}

}
