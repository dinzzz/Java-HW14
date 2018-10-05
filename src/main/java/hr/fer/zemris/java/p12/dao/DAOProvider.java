package hr.fer.zemris.java.p12.dao;

import hr.fer.zemris.java.p12.dao.sql.SQLDAO;

/**
 * A singleton lass that represents a provider for the implementation of the
 * data operation handling with the database.
 * 
 * @author Dinz
 *
 */
public class DAOProvider {

	/**
	 * Instance of the model which provides the support for data operation with the
	 * database data.
	 */
	private static DAO dao = new SQLDAO();

	/**
	 * Method which gets the support for data operation with the database data.
	 * 
	 * @return Support for database data operations.
	 */
	public static DAO getDao() {
		return dao;
	}

}