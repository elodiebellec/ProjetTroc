package fr.eni.projettroc.bll;

import java.time.LocalDate;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.ArticleVenduDAO;
import fr.eni.projettroc.dao.DAOFactory;
import fr.eni.projettroc.dao.EnchereDAO;
import fr.eni.projettroc.exception.BusinessException;

public class EnchereManager {

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

	
	public Enchere validerEnchere(LocalDate date_enchere, int montant_enchere, int no_article, int no_utilisateur )throws BusinessException{
		BusinessException be = new BusinessException();
		Enchere enchere = new Enchere();
	
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNo_utilisateur(no_utilisateur);
		
		ArticleVendu article = new ArticleVendu();
		article.setNo_article(no_article);
		
		
		enchere.setDate_enchere(date_enchere);
		enchere.setMontant_enchere(montant_enchere);
		enchere.setArticle(article);
		enchere.setUtilisateur(utilisateur);
		
		enchereDAO.insert(enchere);
		
		return enchere;
	
	
}
	
}
