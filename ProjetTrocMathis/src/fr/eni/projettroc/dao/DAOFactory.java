package fr.eni.projettroc.dao;



public class DAOFactory {
	public static UtilisateursDAO getUtilisateursDAO() {
		return new UtilisateursJDBCImpl();
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

		
