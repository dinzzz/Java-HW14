package hr.fer.zemris.java.p12;

import hr.fer.zemris.java.p12.dao.DAO;
import hr.fer.zemris.java.p12.dao.DAOProvider;
import hr.fer.zemris.java.p12.dao.sql.SQLConnectionProvider;
import hr.fer.zemris.java.p12.model.PollModel;
import hr.fer.zemris.java.p12.model.PollOptionModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.sql.DataSource;

/**
 * Class that represents a connection setter filter. This class checks the
 * required table availability in the database and handles it appropriately.
 * This occurs when the URL pattern of the webpage matches pre-set one in the
 * filter.
 * 
 * @author Dinz
 *
 */
@WebFilter(filterName = "f1", urlPatterns = { "/servleti/*" })
public class ConnectionSetterFilter implements Filter {

	/**
	 * Key of the bands poll in the table.
	 */
	private long bandsKey;

	/**
	 * Key of the cars poll in the table.
	 */
	private long carsKey;

	/**
	 * Key of the football poll in the table.
	 */
	private long footballKey;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("hr.fer.zemris.dbpool");
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			throw new IOException("Baza podataka nije dostupna.", e);
		}

		SQLConnectionProvider.setConnection(con);

		try {
			DAO sql = DAOProvider.getDao();
			DatabaseMetaData metadata = null;
			metadata = con.getMetaData();
			String[] names = { "TABLE" };
			ResultSet tableNames = metadata.getTables(null, null, null, names);
			boolean foundPolls = false;
			boolean foundPollOpts = false;

			while (tableNames.next()) {
				String table = tableNames.getString("TABLE_NAME");
				if (table.toUpperCase().equals("POLLS")) {
					foundPolls = true;
				} else if (table.toUpperCase().equals("POLLOPTIONS")) {
					foundPollOpts = true;
				}
			}

			if (!foundPolls) {
				PreparedStatement pst = con.prepareStatement(
						"CREATE TABLE Polls\r\n" + " (id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\r\n"
								+ " title VARCHAR(150) NOT NULL,\r\n" + " message CLOB(2048) NOT NULL\r\n" + ")");
				pst.execute();
			}

			if (!foundPollOpts) {
				PreparedStatement pst = con.prepareStatement(
						"CREATE TABLE PollOptions\r\n" + " (id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\r\n"
								+ " optionTitle VARCHAR(100) NOT NULL,\r\n" + " optionLink VARCHAR(150) NOT NULL,\r\n"
								+ " pollID BIGINT,\r\n" + " votesCount BIGINT,\r\n"
								+ " FOREIGN KEY (pollID) REFERENCES Polls(id)\r\n" + ")");
				pst.execute();
			}
			
			if(sql.getPolls().isEmpty()) {
				insertPollsData(con);
			}
			
			if(sql.getAllPollOptions().isEmpty()) {
				insertPollOptionsData(con);
			}
		
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		SQLConnectionProvider.setConnection(con);
		try {
			chain.doFilter(request, response);
		} finally {
			SQLConnectionProvider.setConnection(null);
			try {
				con.close();
			} catch (SQLException ignorable) {
			}
		}
	}

	/**
	 * Method which inserts polls data into the database.
	 * 
	 * @param con
	 *            Connection
	 * @throws SQLException
	 */
	private void insertPollsData(Connection con) throws SQLException {
		DAO sql = DAOProvider.getDao();

		PollModel bands = new PollModel();
		bands.setTitle("Voting for your favorite band");
		bands.setMessage(
				"Select your favorite band from those who are provided. Click on the link to submit your vote!");
		bandsKey = sql.insertPoll(bands);

		PollModel cars = new PollModel();
		cars.setTitle("Voting for your favorite car manufacturer");
		cars.setMessage("Select your favorite!");
		carsKey = sql.insertPoll(cars);

		PollModel fbal = new PollModel();
		fbal.setTitle("Ballon d'Or poll");
		fbal.setMessage("Select the best football player on the planet!");
		footballKey = sql.insertPoll(fbal);
	}

	/**
	 * Method which inserts poll options data into the database table.
	 * 
	 * @param con
	 *            Connection
	 * @throws SQLException
	 */
	private void insertPollOptionsData(Connection con) throws SQLException {
		DAO sql = DAOProvider.getDao();

		if (bandsKey == 0 && carsKey == 0 && footballKey == 0) {			
			List<PollModel> polls = sql.getPolls();
			for(PollModel poll : polls) {
				if(poll.getTitle().equals("Voting for your favorite band")) {
					bandsKey = poll.getId();
				}
				if(poll.getTitle().equals("Voting for your favorite car manufacturer")) {
					carsKey = poll.getId();
				}
				if(poll.getTitle().equals("Ballon d'Or poll")) {
					footballKey = poll.getId();
				}
			}
		}

		PollOptionModel blur = new PollOptionModel();
		blur.setTitle("Blur");
		blur.setLink("https://www.youtube.com/watch?v=SSbBvKaM6sk");
		blur.setPollID(bandsKey);
		blur.setVotes(0);
		sql.insertPollOption(blur);

		PollOptionModel jet = new PollOptionModel();
		jet.setTitle("Jet");
		jet.setLink("https://www.youtube.com/watch?v=tuK6n2Lkza0");
		jet.setPollID(bandsKey);
		jet.setVotes(0);
		sql.insertPollOption(jet);

		PollOptionModel ff = new PollOptionModel();
		ff.setTitle("Franz Ferdinand");
		ff.setLink("https://www.youtube.com/watch?v=GhCXAiNz9Jo");
		ff.setPollID(bandsKey);
		ff.setVotes(0);
		sql.insertPollOption(ff);

		PollOptionModel oasis = new PollOptionModel();
		oasis.setTitle("Oasis");
		oasis.setLink("https://www.youtube.com/watch?v=bx1Bh8ZvH84");
		oasis.setPollID(bandsKey);
		oasis.setVotes(0);
		sql.insertPollOption(oasis);

		PollOptionModel arcMon = new PollOptionModel();
		arcMon.setTitle("Arctic Monkeys");
		arcMon.setLink("https://www.youtube.com/watch?v=bpOSxM0rNPM");
		arcMon.setPollID(bandsKey);
		arcMon.setVotes(0);
		sql.insertPollOption(arcMon);

		PollOptionModel merc = new PollOptionModel();
		merc.setTitle("Mercedes");
		merc.setLink("https://www.mercedes-benz.com/");
		merc.setPollID(carsKey);
		merc.setVotes(0);
		sql.insertPollOption(merc);

		PollOptionModel bmw = new PollOptionModel();
		bmw.setTitle("BMW");
		bmw.setLink("https://www.bmw.com/en/index.html");
		bmw.setPollID(carsKey);
		bmw.setVotes(0);
		sql.insertPollOption(bmw);

		PollOptionModel nissan = new PollOptionModel();
		nissan.setTitle("Nissan");
		nissan.setLink("https://www.nissan-global.com/");
		nissan.setPollID(carsKey);
		nissan.setVotes(0);
		sql.insertPollOption(nissan);

		PollOptionModel chevy = new PollOptionModel();
		chevy.setTitle("Chevrolet");
		chevy.setLink("http://www.chevrolet.com/");
		chevy.setPollID(carsKey);
		chevy.setVotes(0);
		sql.insertPollOption(chevy);

		PollOptionModel ferrari = new PollOptionModel();
		ferrari.setTitle("Ferrari");
		ferrari.setLink("https://www.ferrari.com/en-US");
		ferrari.setPollID(carsKey);
		ferrari.setVotes(0);
		sql.insertPollOption(ferrari);

		PollOptionModel cr = new PollOptionModel();
		cr.setTitle("Cristiano Ronaldo");
		cr.setLink("http://www.cristianoronaldo.com/");
		cr.setPollID(footballKey);
		cr.setVotes(0);
		sql.insertPollOption(cr);

		PollOptionModel messi = new PollOptionModel();
		messi.setTitle("Lionel Messi");
		messi.setLink("https://messi.com/");
		messi.setPollID(footballKey);
		messi.setVotes(0);
		sql.insertPollOption(messi);

		PollOptionModel salah = new PollOptionModel();
		salah.setTitle("Mohamed Salah");
		salah.setLink("https://www.liverpoolfc.com/team/first-team/player/mohamed-salah");
		salah.setPollID(footballKey);
		salah.setVotes(0);
		sql.insertPollOption(salah);

	}

}