package hr.fer.zemris.java.p12.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.p12.dao.DAO;
import hr.fer.zemris.java.p12.dao.DAOProvider;
import hr.fer.zemris.java.p12.model.PollOptionModel;

/**
 * This class represents a servlet responsible for the action of voting for the
 * selected favorite option. When the voting occurs, the servlet fetches the
 * data from the database, and increments the number of votes of the selected
 * option. After the voting, this servlet redirects the user to the results
 * page.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/servleti/glasanje-glasaj" })
public class GlasanjeGlasaj extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -8276437641245429771L;

	@Override
	protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DAO sql = DAOProvider.getDao();
		String pollOptionId = req.getParameter("id");
		PollOptionModel option = sql.getPollOption(Long.parseLong(pollOptionId));
		long voteCount = option.getVotes() + 1;
		sql.updateVote(Long.parseLong(pollOptionId), voteCount);

		resp.sendRedirect(req.getContextPath() + "/servleti/glasanje-rezultati?id=" + option.getPollID());

	}

}
