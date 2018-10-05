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

/**
 * This class represents a servlet responsible for creating a starting webpage
 * of the web application. This servlet lists all of the polls got from the
 * database to the page and allows the voting of these polls to be possible.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/servleti/index.html" })
public class PollsServlet extends HttpServlet {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 4970236728189088592L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAO sql = DAOProvider.getDao();
		List<PollModel> polls = sql.getPolls();

		resp.getWriter().write("<html>\n");
		resp.getWriter().write("<body>\n");
		resp.getWriter().write("<ul>\n");
		for (int i = 0; i < polls.size(); i++) {
			resp.getWriter().write("<li><a href=\"glasanje?pollID=" + polls.get(i).getId() + "\">"
					+ polls.get(i).getTitle() + "</a></li>\n");
		}
		resp.getWriter().write("</ul>\n");
		resp.getWriter().write("</body>\n");
		resp.getWriter().write("</html>\n");

	}

}
