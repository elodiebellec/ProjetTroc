package fr.eni.projettroc.bll;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;



import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Utilisateur;

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

	/*
	 * public void insererArticle(ArticleVendu articleVendu) throws
	 * BusinessException { articleVenduDAO.insert(articleVendu); }
	 */

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

			if (articleVendu.getNom_article().toUpperCase().contains(nom.toUpperCase())) {

				listeParNom.add(articleVendu);
			}
		}
		return listeParNom;
	}
	
	public List<ArticleVendu> listeArticleParPeriode(List<ArticleVendu> listeArticles, String periode) throws BusinessException{
		   List<ArticleVendu> listeArticlesParPeriode = new ArrayList<ArticleVendu>();
		   LocalDate today = LocalDate.now();
		   switch (periode) {
				case "NULL_FUTURE_NULL":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_debut_encheres()) < 0) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;
				case "NULL_NULL_TERMINEE":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_fin_encheres()) > 0 || today.compareTo(article.getDate_fin_encheres()) == 0) {
							listeArticlesParPeriode.add(article);
						}	
					}
				
					break;
				case "ENCOURS_FUTURE_NULL":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_debut_encheres()) < 0 || ((today.compareTo(article.getDate_debut_encheres()) > 0 || today.compareTo(article.getDate_debut_encheres()) == 0) && today.compareTo(article.getDate_fin_encheres()) < 0)) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;
				case "NULL_FUTURE_TERMINEE":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_debut_encheres()) < 0 || (today.compareTo(article.getDate_fin_encheres()) > 0 || today.compareTo(article.getDate_fin_encheres()) == 0)) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;
				case "NULL_NULL_NULL":
				case "ENCOURS_FUTURE_TERMINEE":
					for (ArticleVendu article : listeArticles) {
						if(today.compareTo(article.getDate_debut_encheres()) < 0 || (today.compareTo(article.getDate_fin_encheres()) > 0 || today.compareTo(article.getDate_fin_encheres()) == 0) || ((today.compareTo(article.getDate_debut_encheres()) > 0 || today.compareTo(article.getDate_debut_encheres()) == 0) && today.compareTo(article.getDate_fin_encheres()) < 0)) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;
				case "ENCOURS_NULL_TERMINEE":
					for (ArticleVendu article : listeArticles) {
						if(((today.compareTo(article.getDate_debut_encheres()) > 0 || today.compareTo(article.getDate_debut_encheres()) == 0) && today.compareTo(article.getDate_fin_encheres()) < 0) || (today.compareTo(article.getDate_fin_encheres()) > 0 || today.compareTo(article.getDate_fin_encheres()) == 0)) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;
				default:
					for (ArticleVendu article : listeArticles) {
						if((today.compareTo(article.getDate_debut_encheres()) > 0 || today.compareTo(article.getDate_debut_encheres()) == 0) && today.compareTo(article.getDate_fin_encheres()) < 0) {
							listeArticlesParPeriode.add(article);
						}
					}
					break;
			}
		   
		   return listeArticlesParPeriode;
	}
		   
	   
	/*--------------Fin méthodes pour les filtres de la page d'accueil --------------------------*/  


	
	public ArticleVendu articleParNumero(int noArticle)  throws BusinessException {
		return articleVenduDAO.selectByNoArticle(noArticle);
	}
	
	/*public ArticleVendu modifierArticles() throws BusinessException{
		BusinessException be = new BusinessException();
		
		ArticleVendu articleVendu = null;
		
		articleVendu = new ArticleVendu();
		articleVendu.setNom_article(nom_article);
		articleVendu.setDescription(description);
		articleVendu.setPrix_initial(prix_initial);
		articleVendu.setDate_debut_encheres(date_debut_encheres);
		articleVendu.setDate_fin_encheres(date_fin_encheres);
		articleVendu.setCategorie(categorie);
		articleVendu.setUtilisateur(utilisateur);
		articleVenduDAO.update(articleVendu);

		return articleVendu;
	}*/


	public ArticleVendu valideAjoutArticle(String nom_article, String description, LocalDate date_debut_encheres,
			LocalDate date_fin_encheres, int prix_initial, int no_categorie, int no_user) throws BusinessException {
		BusinessException be = new BusinessException();
		ArticleVendu articleVendu = null;
		boolean isValiderNomArticle = validerNomArticle(nom_article, be);
		boolean isValiderDescription = validerDescription(description, be);
		boolean isValiderPrixInitial = validerPrixInitial(prix_initial, be);
		boolean isValiderDate = validerDate(date_debut_encheres, date_fin_encheres, be);
		if (isValiderNomArticle && isValiderPrixInitial && isValiderDate && isValiderDescription ) {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNo_utilisateur(no_user);

			Categorie categorie = new Categorie();
			categorie.setNo_categorie(no_categorie);

			articleVendu = new ArticleVendu();
			articleVendu.setNom_article(nom_article);
			articleVendu.setDescription(description);
			articleVendu.setPrix_initial(prix_initial);
			articleVendu.setDate_debut_encheres(date_debut_encheres);
			articleVendu.setDate_fin_encheres(date_fin_encheres);
			articleVendu.setCategorie(categorie);
			articleVendu.setUtilisateur(utilisateur);
			articleVenduDAO.insert(articleVendu);

			return articleVendu;
		} else {
			throw be;

		}
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

		return true;
	}

	private boolean validerDescription(String description, BusinessException be) {
		description = description.trim();
		if (description == null) {
			be.addError("La description est obligatoire");
			return false;
		}
		if (description.trim().isEmpty() || description.trim().length() > 300) {

			be.addError("La description ne doit pas dépasser 300 caractères");

			return false;
		}

		return true;
	}

	private boolean validerPrixInitial(int prix_initial, BusinessException be) {
		if (prix_initial < 0) {

			be.addError("La mise à prix doit être supérieure à zéro");

			return false;
		}
		return true;
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
		

		if (date_fin_encheres.isBefore(date_debut_encheres) || date_fin_encheres.isEqual(date_debut_encheres)) {

			be.addError("La date de fin doit être supérieure à la date de début des enchères");

			return false;
		}

		return true;
	}
	
	public ArticleVendu recupererArticle(int no_article) throws BusinessException{
			return articleVenduDAO.selectByNoArticle(no_article);
	}

}