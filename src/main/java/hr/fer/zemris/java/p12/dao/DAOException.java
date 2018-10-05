package hr.fer.zemris.java.p12.dao;

/**
 * Class that represents an exception which is thrown when there is a problem
 * with handling data operation with the database.
 * 
 * @author Dinz
 *
 */
public class DAOException extends RuntimeException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new DAOException.
	 */
	public DAOException() {
	}

	/**
	 * Constructs a new DAOException.
	 * 
	 * @param message
	 *            Message of the exception.
	 * @param cause
	 *            Cause of the exception.
	 * @param enableSuppression
	 *            Flag which determines if the suppression is enabled.
	 * @param writableStackTrace
	 *            Flag which determines if the stack trace is writable.
	 */
	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructs a new DAOException.
	 * 
	 * @param message
	 *            Message of the exception.
	 * @param cause
	 *            Cause of the exception.
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new DAOException.
	 * 
	 * @param message
	 *            Message of the exception.
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Constructs a new DAOException.
	 * 
	 * @param cause
	 *            Cause of the exception.
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}