package hr.fer.zemris.java.p12.model;

/**
 * Class that represents a poll option in the context of the web application.
 * The instances of this class are stored in the web app's database and also
 * used in the context of the logic of the same web app.
 * 
 * @author Dinz
 *
 */
public class PollOptionModel {

	/**
	 * Id of the poll option.
	 */
	long id;

	/**
	 * Title of the poll option.
	 */
	String title;

	/**
	 * Poll option link.
	 */
	String link;

	/**
	 * ID of the poll where the option belongs to.
	 */
	long pollID;

	/**
	 * Number of votes this option received.
	 */
	long votes;

	/**
	 * Gets the poll option ID.
	 * 
	 * @return Poll option ID.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the poll option ID.
	 * 
	 * @param id
	 *            ID to be set.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the poll option title.
	 * 
	 * @return Poll option title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the poll option title.
	 * 
	 * @param title
	 *            Title to be set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the poll option link.
	 * 
	 * @return Poll option link.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the poll option link.
	 * 
	 * @param link
	 *            Link to be set.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the ID of the poll which the option belongs to.
	 * 
	 * @return Poll ID.
	 */
	public long getPollID() {
		return pollID;
	}

	/**
	 * Sets the ID of the poll which the option belongs to.
	 * 
	 * @param pollID
	 *            ID to be set.
	 */
	public void setPollID(long pollID) {
		this.pollID = pollID;
	}

	/**
	 * Gets the number of votes the option received.
	 * 
	 * @return Number of votes the option received.
	 */
	public long getVotes() {
		return votes;
	}

	/**
	 * Sets the number of votes the option received.
	 * 
	 * @param votes
	 *            Number of votes.
	 */
	public void setVotes(long votes) {
		this.votes = votes;
	}

}
