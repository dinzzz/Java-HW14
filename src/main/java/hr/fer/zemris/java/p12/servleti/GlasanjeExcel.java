package hr.fer.zemris.java.p12.servleti;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import hr.fer.zemris.java.p12.dao.DAO;
import hr.fer.zemris.java.p12.dao.DAOProvider;
import hr.fer.zemris.java.p12.model.PollOptionModel;

/**
 * This class represents a servlet responsible for creating an excel file with
 * voting results. This servlet fetches appropriate data and extracts the option
 * info and number of votes for each option in the survey. Then, it fills the
 * excel files with each row representing an option and the number of votes it
 * got.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/servleti/glasanje-xls" })
public class GlasanjeExcel extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -407911594728171999L;

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

		createExcelFile(options, resp);

	}

	/**
	 * Method which creates an excel file from the given list of poll options.
	 * 
	 * @param options
	 *            Poll options.
	 * @param resp
	 *            Servlet response.
	 * @throws IOException
	 */
	private void createExcelFile(List<PollOptionModel> options, HttpServletResponse resp) throws IOException {
		HSSFWorkbook hwb = new HSSFWorkbook();

		HSSFSheet sheet = hwb.createSheet("Vote results");
		for (int i = 0; i < options.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(options.get(i).getTitle());
			row.createCell(1).setCellValue(options.get(i).getVotes());
		}
		resp.setHeader("Content-Disposition", "attachment; filename=\"results.xls\"");
		hwb.write(resp.getOutputStream());
		hwb.close();

	}

}
