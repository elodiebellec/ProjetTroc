package fr.eni.projettroc.bll;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.dao.CategorieDAO;
import fr.eni.projettroc.dao.DAOFactory;
import fr.eni.projettroc.dao.EnchereDAO;
import fr.eni.projettroc.exception.BusinessException;

public class EnchereManager {
	//Attribut pour représenter la couche DAL

	private EnchereDAO enchereDAO;


   private static EnchereManager instance;
   
   private EnchereManager() {
	   enchereDAO = DAOFactory.getEnchereDAO();
   }
   
   public static EnchereManager getEnchereManager() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
   
   public List<Enchere> toutesLesEncheres() throws BusinessException{
		
		return enchereDAO.getListEnchere();
	}

   public List<Enchere> toutesLesEncheresEnCours() throws BusinessException{
	   List<Enchere> listeEncheres = enchereDAO.getListEnchere();
	   List<Enchere> listeEnchereEnCours = new ArrayList<Enchere>();
	   LocalDate today = LocalDate.now();
	   for (Enchere enchere : listeEncheres) {
		if(today.compareTo(enchere.getDate_enchere()) != 1) {
			listeEnchereEnCours.add(enchere);
		}
	}
	   
		return listeEnchereEnCours;
	}
   
  
   


}



