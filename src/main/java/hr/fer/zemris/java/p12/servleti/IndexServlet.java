package hr.fer.zemris.java.p12.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class represents a servlet which is responsible for redirecting the web
 * application when run towards an URL where the data and the logic is present.
 * This servlet is mapped by the starting page of the app.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/index.html" })
public class IndexServlet extends HttpServlet {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 2564155144453627189L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/servleti/index.html");
	}

}
