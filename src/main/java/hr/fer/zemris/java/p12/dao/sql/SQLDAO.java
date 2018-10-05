package hr.fer.zemris.java.p12.dao.sql;

import hr.fer.zemris.java.p12.dao.DAO;
import hr.fer.zemris.java.p12.dao.DAOException;
import hr.fer.zemris.java.p12.model.PollModel;
import hr.fer.zemris.java.p12.model.PollOptionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an implementation for the support of the data operation
 * with the web app's database.
 * 
 * @author Dinz
 *
 */
public class SQLDAO implements DAO {

	@Override
	public List<PollModel> getPolls() throws DAOException {
		List<PollModel> polls = new ArrayList<>();
		Connection con = SQLConnectionProvider.getConnection();

		try {
			System.out.println(con);
			PreparedStatement pst = con.prepareStatement("select id, title, message from Polls order by id");
			ResultSet rs = pst.executeQuery();

			while (rs != null && rs.next()) {
				PollModel poll = new PollModel();
				poll.setId(rs.getLong(1));
				poll.setTitle(rs.getString(2));
				poll.setMessage(rs.getString(3));
				polls.add(poll);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("Error while getting the list of polls.");
		}
		return polls;
	}

	@Override
	public PollModel getPoll(long id) throws DAOException {
		PollModel poll = null;
		Connection con = SQLConnectionProvider.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select id, title, message from Polls where id=?");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs != null && rs.next()) {
				poll = new PollModel();
				poll.setId(rs.getLong(1));
				poll.setTitle(rs.getString(2));
				poll.setMessage(rs.getString(3));
			}

		} catch (Exception ex) {
			throw new DAOException("Error while getting a poll.");
		}

		return poll;
	}

	@Override
	public long insertPoll(PollModel poll) throws DAOException {
		Connection con = SQLConnectionProvider.getConnection();
		long key = 0;
		try {
			PreparedStatement pst = con.prepareStatement("INSERT INTO POLLS (title, message) values (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, poll.getTitle());
			pst.setString(2, poll.getMessage());

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			while (rs != null && rs.next()) {
				key = rs.getLong(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("Error while inserting a poll.");
		}
		return key;
	}

	@Override
	public List<PollOptionModel> getPollOptions(long id) throws DAOException {
		List<PollOptionModel> options = new ArrayList<>();
		Connection con = SQLConnectionProvider.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(
					"select id, optiontitle, optionlink, pollid, votescount from PollOptions where pollId=? order by id");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs != null && rs.next()) {
				PollOptionModel opt = new PollOptionModel();
				opt.setId(rs.getLong(1));
				opt.setTitle(rs.getString(2));
				opt.setLink(rs.getString(3));
				opt.setPollID(rs.getLong(4));
				opt.setVotes(rs.getLong(5));
				options.add(opt);
			}

		} catch (Exception ex) {
			throw new DAOException("Error while getting the list of poll options.");
		}
		return options;
	}

	@Override
	public PollOptionModel getPollOption(long id) throws DAOException {
		PollOptionModel opt = null;
		Connection con = SQLConnectionProvider.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(
					"select id, optiontitle, optionlink, pollid, votescount from PollOptions where Id=? order by id");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs != null && rs.next()) {
				opt = new PollOptionModel();
				opt.setId(rs.getLong(1));
				opt.setTitle(rs.getString(2));
				opt.setLink(rs.getString(3));
				opt.setPollID(rs.getLong(4));
				opt.setVotes(rs.getLong(5));

			}

		} catch (Exception ex) {
			throw new DAOException("Error while getting the poll option.");
		}
		return opt;
	}

	@Override
	public long insertPollOption(PollOptionModel pollOption) throws DAOException {
		Connection con = SQLConnectionProvider.getConnection();
		long key = 0;
		try {
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO POLLOPTIONS (optiontitle, optionlink, pollId, votescount) values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, pollOption.getTitle());
			pst.setString(2, pollOption.getLink());
			pst.setLong(3, pollOption.getPollID());
			pst.setLong(4, pollOption.getVotes());

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			while (rs != null && rs.next()) {
				key = rs.getLong(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("Error while inserting a poll option.");
		}
		return key;
	}

	@Override
	public void updateVote(long id, long count) throws DAOException {
		Connection con = SQLConnectionProvider.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("UPDATE POLLOPTIONS set votescount=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, count);
			pst.setLong(2, id);
			pst.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("Error while inserting a poll option.");
		}
	}

	@Override
	public List<PollOptionModel> getAllPollOptions() throws DAOException {
		List<PollOptionModel> pollOptions = new ArrayList<>();
		Connection con = SQLConnectionProvider.getConnection();

		try {
			System.out.println(con);
			PreparedStatement pst = con.prepareStatement(
					"select id, optiontitle, optionlink, pollid, votescount from PollOptions order by id");
			ResultSet rs = pst.executeQuery();

			while (rs != null && rs.next()) {
				PollOptionModel poll = new PollOptionModel();
				poll.setId(rs.getLong(1));
				poll.setTitle(rs.getString(2));
				poll.setLink(rs.getString(3));
				poll.setPollID(rs.getLong(4));
				poll.setVotes(rs.getLong(5));
				pollOptions.add(poll);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("Error while getting the list of polls.");
		}
		return pollOptions;
	}

}