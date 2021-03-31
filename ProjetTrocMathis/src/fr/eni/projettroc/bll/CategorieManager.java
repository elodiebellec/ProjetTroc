package fr.eni.projettroc.bll;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.CategorieDAO;
import fr.eni.projettroc.dao.DAOFactory;
import fr.eni.projettroc.dao.UtilisateurDAO;





import fr.eni.projettroc.exception.BusinessException;

public class CategorieManager {
	//Attribut pour représenter la couche DAL

	private CategorieDAO categorieDAO;


   private static CategorieManager instance;
   
   private CategorieManager() {
	   categorieDAO = DAOFactory.getCategorieDAO();
   }
   
   public static CategorieManager getCategorieManager() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
   
  
   


}



