package co.crisi.linkresearcher.ejb.exceptions;

public class RepeatedSearchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatedSearchException(String errorMessage) {
		super(errorMessage);
	}

}
