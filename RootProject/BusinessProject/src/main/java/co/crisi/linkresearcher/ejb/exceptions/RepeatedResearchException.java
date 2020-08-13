package co.crisi.linkresearcher.ejb.exceptions;

public class RepeatedResearchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RepeatedResearchException(String errorMessage) {
		super(errorMessage);
	}

}
