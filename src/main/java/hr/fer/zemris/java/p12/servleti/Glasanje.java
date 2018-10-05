package hr.fer.zemris.java.p12.servleti;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.p12.dao.DAO;
import hr.fer.zemris.java.p12.dao.DAOProvider;
import hr.fer.zemris.java.p12.model.PollModel;
import hr.fer.zemris.java.p12.model.PollOptionModel;

/**
 * This class represents a voting servlet. This servlet showcases the user a
 * list of the data to choose from and when the favorite option is selected, the
 * user is redirected to the page with voting results.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/servleti/glasanje" })
public class Glasanje extends HttpServlet {

	/**
	 * Serial for the voting servlet.
	 */
	private static final long serialVersionUID = 3371767972485888786L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAO sql = DAOProvider.getDao();
		
		String pollId = req.getParameter("pollID");
		long pID = 0;
		try {
			pID = Long.parseLong(pollId);
		} catch (NumberFormatException ex) {
			req.getRequestDispatcher("/WEB-INF/pages/errorpage.jsp").forward(req, resp);
		}		
		
		if(pID > sql.getPolls().size() || pID < 1) {
			req.getRequestDispatcher("/WEB-INF/pages/errorpage.jsp").forward(req, resp);
		}
		
		List<PollOptionModel> options = sql.getPollOptions(pID);
		PollModel poll = sql.getPoll(pID);

		req.setAttribute("options", options);
		req.setAttribute("poll", poll);
		req.getRequestDispatcher("/WEB-INF/pages/glasanjeIndex.jsp").forward(req, resp);
	}


}
