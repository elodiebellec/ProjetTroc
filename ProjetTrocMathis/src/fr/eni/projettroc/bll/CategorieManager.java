package fr.eni.projettroc.bll;



import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.dao.CategorieDAO;
import fr.eni.projettroc.dao.DAOFactory;

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
   
   public List<Categorie> toutesLesCategorie() throws BusinessException{
		//Aucune vérification
		//Seulement l'appel à la couche DAL
		return categorieDAO.getListCategories();
	}

   public Categorie categorieParNumero(int no_categorie)throws BusinessException {	
	   return categorieDAO.selectByNoCategorie(no_categorie);
	}	
   
    
   


}



