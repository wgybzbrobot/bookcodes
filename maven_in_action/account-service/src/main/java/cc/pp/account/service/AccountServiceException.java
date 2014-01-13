package cc.pp.account.service;

public class AccountServiceException extends Exception {

	private static final long serialVersionUID = -4401096869326658112L;

	public AccountServiceException(String message, Throwable e) {
		super(message, e);
	}

	public AccountServiceException(String message) {
		super(message);
	}

}
