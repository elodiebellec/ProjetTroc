package fr.eni.projettroc.dao;



public class DAOFactory {
	public static UtilisateursDAO getUtilisateursDAO() {
		return new UtilisateursJDBCImpl();
	}
}

		
