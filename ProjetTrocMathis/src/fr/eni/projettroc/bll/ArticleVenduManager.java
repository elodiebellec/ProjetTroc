package fr.eni.projettroc.bll;



import java.util.ArrayList;
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
   
   public List<ArticleVendu> listeArticles() throws BusinessException{
		
		return articleVenduDAO.getListArticle();
   }
   
   public void modifierArticle(ArticleVendu articleVendu) throws BusinessException{
	   articleVenduDAO.update(articleVendu);
   }
	
	
	public void deleteArticle(int no_article) throws BusinessException{
		articleVenduDAO.deleteArticle(no_article);
	}
	
	public List<ArticleVendu> listeArticlesParCategorie (int no_categorie) throws BusinessException {
		return articleVenduDAO.getListByCategorie(no_categorie);
	}
	
	public List<ArticleVendu> listeArticlesParNom (List<ArticleVendu> listeArticle, String nom) throws BusinessException {
		List<ArticleVendu> listeParNom = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : listeArticle) {
			if(articleVendu.getNom_article().toUpperCase().contains(nom.toUpperCase())) {
				listeParNom.add(articleVendu);
			}
		}
		return listeParNom;
	}
	
	
   


}



