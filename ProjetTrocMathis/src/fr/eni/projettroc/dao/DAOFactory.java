package fr.eni.projettroc.dao;



public class DAOFactory {
	public static UtilisateurDAO getUtilisateursDAO() {
		return new UtilisateurJDBCImpl();
	}
}

		
