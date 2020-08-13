package co.crisi.linkresearcher.ejb.exceptions;

public class RepeatedRelevantResultException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatedRelevantResultException(String errorMessage) {
		super(errorMessage);
	}

}
