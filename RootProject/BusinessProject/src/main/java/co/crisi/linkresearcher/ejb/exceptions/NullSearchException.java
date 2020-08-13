package co.crisi.linkresearcher.ejb.exceptions;

public class NullSearchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullSearchException(String errorMessage) {
		super(errorMessage);
	}
}
