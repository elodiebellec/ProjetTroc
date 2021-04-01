package fr.eni.projettroc.bll;



import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.dao.ArticleVenduDAO;
import fr.eni.projettroc.dao.DAOFactory;

import fr.eni.projettroc.exception.BusinessException;

public class ArticleVenduManager {
	
	//Attribut pour représenter la couche DAL
	private ArticleVenduDAO articleVenduDAO;


   private static ArticleVenduManager instance;
   
   private ArticleVenduManager() {
	   articleVenduDAO = DAOFactory.getArticleVenduDAO();
   }
   
   public static ArticleVenduManager getArticleVenduManager() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
   
   public void insererArticle(ArticleVendu articleVendu) throws BusinessException {
	   articleVenduDAO.insert(articleVendu);
   }
   
   public List<ArticleVendu> tousLesArticles() throws BusinessException{
		
		return articleVenduDAO.getListArticle();
	}	   
   
  
   
  
   


}



