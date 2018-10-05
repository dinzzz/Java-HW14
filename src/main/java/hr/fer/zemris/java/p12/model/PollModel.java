package hr.fer.zemris.java.p12.model;

/**
 * Class that represents a poll. This class is used to store the poll data into
 * the web application's database and is also used to do the logic of the web
 * app.
 * 
 * @author Dinz
 *
 */
public class PollModel {

	/**
	 * ID of the poll.
	 */
	long id;

	/**
	 * Title of the poll.
	 */
	String title;

	/**
	 * Poll message.
	 */
	String message;

	/**
	 * Gets the poll ID.
	 * 
	 * @return Poll ID.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the poll ID.
	 * 
	 * @param id
	 *            ID to be set.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the poll title.
	 * 
	 * @return Poll title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the poll title.
	 * 
	 * @param title
	 *            Title to be set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the poll message.
	 * 
	 * @return Poll message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the poll message.
	 * 
	 * @param message
	 *            Message to be set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
