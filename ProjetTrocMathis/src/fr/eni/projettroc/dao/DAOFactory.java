package fr.eni.projettroc.dao;



public class DAOFactory {
	public static UtilisateurDAO getUtilisateursDAO() {
		return new UtilisateurJDBCImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitJDBCImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereJDBCImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieJDBCImpl();
	}
	
	
}

		
