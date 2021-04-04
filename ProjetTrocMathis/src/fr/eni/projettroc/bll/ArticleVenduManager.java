package fr.eni.projettroc.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.dao.ArticleVenduDAO;
import fr.eni.projettroc.dao.DAOFactory;

import fr.eni.projettroc.exception.BusinessException;

public class ArticleVenduManager {

	// Attribut pour représenter la couche DAL
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

	public List<ArticleVendu> listeArticles() throws BusinessException {

		return articleVenduDAO.getListArticle();
	}

	public void modifierArticle(ArticleVendu articleVendu) throws BusinessException {
		articleVenduDAO.update(articleVendu);
	}

	public void deleteArticle(int no_article) throws BusinessException {
		articleVenduDAO.deleteArticle(no_article);
	}
	
	/*--------------Méthodes pour les filtres de la page d'accueil --------------------------*/
	
	public List<ArticleVendu> listeArticlesParNoUtilisateur(int noUtilisateur) throws BusinessException {
		return articleVenduDAO.getListByNoUtilisateur(noUtilisateur);
	}
	
	
	public List<ArticleVendu> listeArticlesParCategorie(List<ArticleVendu> listeArticle, int no_categorie) throws BusinessException {
		List<ArticleVendu> listeParCategorie = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : listeArticle) {
			if(no_categorie == articleVendu.getCategorie().getNo_categorie()) {

				listeParCategorie.add(articleVendu);
			}
		}
		return listeParCategorie;
	}
	

	public List<ArticleVendu> listeArticlesParNom(List<ArticleVendu> listeArticle, String nom)
			throws BusinessException {
		List<ArticleVendu> listeParNom = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : listeArticle) {

			if(articleVendu.getNom_article().toUpperCase().contains(nom.toUpperCase())) {

				listeParNom.add(articleVendu);
			}
		}
		return listeParNom;
	}
	
	public List<ArticleVendu> listeArticleParPeriode(List<ArticleVendu> listeArticles, String periode) throws BusinessException{
		   List<ArticleVendu> listeArticlesParPeriode = new ArrayList<ArticleVendu>();
		   LocalDate today = LocalDate.now();
		   switch (periode) {
				case "AVANT_DEBUT":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_debut_encheres()) == 1) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;
				case "APRES_FIN":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_fin_encheres()) == 1) {
							listeArticlesParPeriode.add(article);
						}	
					}
					break;
				case "EN_COURS":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_debut_encheres()) != 1 && today.compareTo(article.getDate_fin_encheres()) != 1) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;		
				default:
					listeArticlesParPeriode = listeArticles;
					break;
			}
		   
		   return listeArticlesParPeriode;
	}
		   
	   
	/*--------------Fin méthodes pour les filtres de la page d'accueil --------------------------*/  

	
	public ArticleVendu articleParNumero(int noArticle)  throws BusinessException {
		return articleVenduDAO.selectByNoArticle(noArticle);
	}
	

	private boolean validerNomArticle(String nom_article, BusinessException be) {
		nom_article = nom_article.trim();
		if (nom_article == null) {
			be.addError("Le nom de l'article est obligatoire");
			return false;
		}
		if (nom_article.trim().isEmpty() || nom_article.trim().length() > 30) {
			be.addError("Le nom de l'article ne doit pas dépasser 30 caractères");
			return false;
		}

		return false;
	}

	private boolean validerDescription(String description, BusinessException be) {
		description = description.trim();
		if (description == null) {
			be.addError("La description est obligatoire");
			return false;
		}
		if (description.trim().isEmpty() || description.trim().length() > 30) {
			be.addError("La description ne doit pas dépasser 300 caractères");
			return false;
		}

		return false;
	}

	private boolean validerPrixInitial(int prix_initial, BusinessException be) {
		if (prix_initial < 0) {
			be.addError("La mise à prix doit être supérieure à zéro");
			return false;
		}
		return false;
	}

	private boolean validerDate(LocalDate date_debut_encheres, LocalDate date_fin_encheres, BusinessException be) {
		if (date_debut_encheres == null || date_fin_encheres == null) {
			be.addError("La date est obligatoire");
			return false;
		}

		if (date_debut_encheres.isBefore(LocalDate.now())) {
			be.addError("La date ne peut pas être antérieure à la date du jour");
			return false;
		}

		if (date_fin_encheres.isBefore(date_debut_encheres)) {
			be.addError("La date de fin doit être supérieure à la date de début des enchères");
			return false;
		}

		return false;
	}

	/* public ArticleVendu validetAjoutArticle(String nom_article, String description, 
			LocalDate date_debut_encheres, LocalDate date_fin_encheres, int prix_initial, int prix_vente, String categorie)
			throws BusinessException {
		BusinessException be = new BusinessException();
		ArticleVendu articleVendu = null;
		boolean isValiderNomArticle = validerNomArticle(nom_article, be);
		boolean isValiderDescription = validerDescription(description, be);
		boolean isValiderPrixInitial = validerPrixInitial(prix_initial, be);
		boolean isValiderDate = validerDate(date_debut_encheres, date_fin_encheres, be);

		if (isValiderPrixInitial && isValiderDescription && isValiderPrixInitial && isValiderDate) {
			articleVendu = new ArticleVendu();
			articleVendu.setNom_article(nom_article);
			articleVendu.setDescription(description);
			articleVendu.setPrix_initial(prix_initial);
			articleVendu.setDate_debut_encheres(date_debut_encheres);
			articleVendu.setDate_fin_encheres(date_fin_encheres);
			articleVenduDAO.insert(articleVendu);
			
			return articleVendu;
		} else {
			throw be;
		}

		
		 * validerNomArticle(nom_article, be); validerDescription(description, be);
		 * validerPrixInitial(prix_initial, be); validerDate(date_debut_encheres,
		 * date_fin_encheres, be);
		 * 
		 * ArticleVendu articleVendu = null;
		 * 
		 * if (!be.hasErreurs()) { articleVendu = new ArticleVendu();
		 * articleVendu.setNom_article(nom_article);
		 * articleVendu.setDescription(description);
		 * articleVendu.setDate_debut_encheres(date_debut_encheres);
		 * articleVendu.setDate_fin_encheres(date_fin_encheres);
		 * articleVendu.setPrix_initial(prix_initial);
		 * articleVenduDAO.insert(articleVendu); return articleVendu; } else { throw be;
		 * }
		 */

	}

