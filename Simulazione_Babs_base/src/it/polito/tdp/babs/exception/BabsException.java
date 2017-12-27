package it.polito.tdp.babs.exception;

public class BabsException extends Exception{
	private static final long serialVersionUID = -7319051610168716689L;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BabsException(String arg0, Exception arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public BabsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}
