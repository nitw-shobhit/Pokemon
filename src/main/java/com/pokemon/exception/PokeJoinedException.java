package com.pokemon.exception;

public class PokeJoinedException extends Exception {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -1669922807759141404L;

	public PokeJoinedException() {
        super();
    }

    public PokeJoinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PokeJoinedException(String message) {
        super(message);
    }

    public PokeJoinedException(Throwable cause) {
        super(cause);
    }
}
