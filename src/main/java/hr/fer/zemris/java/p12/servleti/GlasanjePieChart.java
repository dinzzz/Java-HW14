package hr.fer.zemris.java.p12.servleti;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import hr.fer.zemris.java.p12.dao.DAO;
import hr.fer.zemris.java.p12.dao.DAOProvider;
import hr.fer.zemris.java.p12.model.PollOptionModel;

/**
 * This class represents a servlet which creates a pie chart with the results of
 * user's voting for their favorite options. The servlet takes appropriate data
 * from the database and extracts the desired data for the creation of the pie
 * chart. Afterwards, the chart is created and can be fetched to the pages with
 * an appropriate source link.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/servleti/glasanje-grafika" })
public class GlasanjePieChart extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 7411944468006490150L;

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

		resp.setContentType("image/png");
		PieDataset dataset = createDataset(options);
		JFreeChart chart = createChart(dataset, "Query results");
		BufferedImage bim = chart.createBufferedImage(400, 400);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bim, "png", bos);

		resp.getOutputStream().write(bos.toByteArray());
	}

	/**
	 * Creates the dataset for the piechart from the given list of options.
	 * 
	 * @param options
	 *            Options to be present in the chart.
	 * @return Generated dataset.
	 */
	private PieDataset createDataset(List<PollOptionModel> options) {

		DefaultPieDataset result = new DefaultPieDataset();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getVotes() != 0) {
				result.setValue(options.get(i).getTitle(), options.get(i).getVotes());
			}
		}
		return result;

	}

	/**
	 * Creates a new pie chart from the given dataset and chart title.
	 * 
	 * @param dataset
	 *            Dataset.
	 * @param title
	 *            Title.
	 * @return New pie chart object.
	 */
	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}
}
