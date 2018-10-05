package hr.fer.zemris.java.p12.dao;

import hr.fer.zemris.java.p12.model.PollModel;
import hr.fer.zemris.java.p12.model.PollOptionModel;

import java.util.List;

/**
 * Interface which describes data operations with the web app's database.
 * 
 * @author Dinz
 *
 */
public interface DAO {

	/**
	 * Method which gets the list of polls from the database.
	 * 
	 * @return List of polls.
	 * @throws DAOException
	 */
	public List<PollModel> getPolls() throws DAOException;

	/**
	 * Method which gets the poll from the database with the given ID.
	 * 
	 * @param id
	 *            Poll ID.
	 * @return
	 * @throws DAOException
	 */
	public PollModel getPoll(long id) throws DAOException;

	/**
	 * Method which gets all the poll options from the database.
	 * 
	 * @return List of all poll options from the database.
	 * @throws DAOException
	 */
	public List<PollOptionModel> getAllPollOptions() throws DAOException;

	/**
	 * Method which inserts a poll into the database table.
	 * 
	 * @param poll
	 *            Poll to be inserted.
	 * @return Key by which is poll stored in the database table.
	 * @throws DAOException
	 */
	public long insertPoll(PollModel poll) throws DAOException;

	/**
	 * Method which gets a list of poll options for the desired poll from the
	 * database.
	 * 
	 * @param id
	 *            ID of the poll.
	 * @return List of poll options.
	 * @throws DAOException
	 */
	public List<PollOptionModel> getPollOptions(long id) throws DAOException;

	/**
	 * Method which gets a poll option with the given ID from the database.
	 * 
	 * @param id
	 *            ID of the poll option.
	 * @return Poll option.
	 * @throws DAOException
	 */
	public PollOptionModel getPollOption(long id) throws DAOException;

	/**
	 * Method which inserts a poll option into the database table.
	 * 
	 * @param pollOption
	 *            Poll option.
	 * @return Key by which the option is stored in the database table.
	 * @throws DAOException
	 */
	public long insertPollOption(PollOptionModel pollOption) throws DAOException;

	/**
	 * Method which updates the vote into the option model stored in the database.
	 * 
	 * @param id
	 *            ID of the option.
	 * @param count
	 *            Vote count.
	 * @throws DAOException
	 */
	public void updateVote(long id, long count) throws DAOException;

}