package fr.eni.projettroc.bll;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
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

   /*--------------Méthodes pour les filtres de la page d'accueil --------------------------*/
   
   public List<Enchere> toutesLesEncheresParUtilisateur (int no_utilisateur) throws BusinessException {
	   return enchereDAO.getListByNoUtilisateur(no_utilisateur);
   }
   
   public List<Enchere> listeEnchereParPeriode(List<Enchere> listeEnchere, String periode) throws BusinessException{
	   List<Enchere> listeEncheresParPeriode = new ArrayList<Enchere>();
	   LocalDate today = LocalDate.now();
	   switch (periode) {
			case "APRES_FIN":
				for (Enchere enchere : listeEnchere) {
					if(today.compareTo(enchere.getDate_enchere()) == 1) {
						listeEncheresParPeriode.add(enchere);
					}	
				}
				break;
			case "EN_COURS":
				for (Enchere enchere : listeEnchere) {
					if(today.compareTo(enchere.getDate_enchere()) != 1) {
						listeEncheresParPeriode.add(enchere);
					}
				}
				break;		
			default:
				listeEncheresParPeriode = listeEnchere;
				break;
		}
	   
	   return listeEncheresParPeriode;
}
   
   public List<ArticleVendu> ArticlesdeListeEncheres (List<Enchere> listeEnchere) throws BusinessException{
	   List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
	   for (Enchere enchere : listeEnchere) {
		   listeArticle.add(enchere.getArticle());
	   } 
	   
	   return listeArticle;
	   
   }
   
  /* public List<Enchere> toutesLesEncheresEnCours() throws BusinessException{
	   List<Enchere> listeEncheres = enchereDAO.getListEnchere();
	   List<Enchere> listeEnchereEnCours = new ArrayList<Enchere>();
	   LocalDate today = LocalDate.now();
	   for (Enchere enchere : listeEncheres) {
		if(today.compareTo(enchere.getDate_enchere()) != 1) {
			listeEnchereEnCours.add(enchere);
		}
	}
	   
		return listeEnchereEnCours;
	}*/
   
   
	/*--------------Fin méthodes pour les filtres de la page d'accueil --------------------------*/  

}



