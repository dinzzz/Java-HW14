package hr.fer.zemris.java.p12.servleti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.p12.dao.DAO;
import hr.fer.zemris.java.p12.dao.DAOProvider;
import hr.fer.zemris.java.p12.model.PollOptionModel;

/**
 * This class represents a servlet responsible for showing of voting results on
 * the web app. This servlet extracts the desired information about the voting
 * options and their vote results and stores them into appropriate structures
 * which are forward to the webpage.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/servleti/glasanje-rezultati" })
public class GlasanjeRezultati extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2610438894597902268L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DAO sql = DAOProvider.getDao();
		String pollId = req.getParameter("id");
		long pID = 0;
		try {
			pID = Long.parseLong(pollId);
		} catch (NumberFormatException ex) {
			req.getRequestDispatcher("/WEB-INF/pages/errorpage.jsp").forward(req, resp);
		}

		if (pID > sql.getPolls().size() || pID < 1) {
			req.getRequestDispatcher("/WEB-INF/pages/errorpage.jsp").forward(req, resp);
		}
		List<PollOptionModel> options = sql.getPollOptions(pID);
		options.sort((o2, o1) -> Long.compare(o1.getVotes(), o2.getVotes()));

		long max = 0;
		for (PollOptionModel opt : options) {
			if (opt.getVotes() > max) {
				max = opt.getVotes();
			}
		}

		List<PollOptionModel> maxes = new ArrayList<>();
		for (PollOptionModel opt : options) {
			if (opt.getVotes() == max) {
				maxes.add(opt);
			}
		}

		req.setAttribute("options", options);
		req.setAttribute("max", maxes);
		req.setAttribute("poll", pollId);

		req.getRequestDispatcher("/WEB-INF/pages/glasanjeRez.jsp").forward(req, resp);
	}

}
