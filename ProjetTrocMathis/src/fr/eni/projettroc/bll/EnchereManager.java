package fr.eni.projettroc.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.bo.Utilisateur;

import fr.eni.projettroc.dao.DAOFactory;
import fr.eni.projettroc.dao.EnchereDAO;
import fr.eni.projettroc.dao.UtilisateurDAO;
import fr.eni.projettroc.exception.BusinessException;

public class EnchereManager {

	// Attribut pour représenter la couche DAL

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

	public List<Enchere> toutesLesEncheres() throws BusinessException {

		return enchereDAO.getListEnchere();
	}

	
	public List<Enchere> enchereParNum(int no_article) throws BusinessException {

		return enchereDAO.selectByNoArticle(no_article);
	}

	
	public boolean premiereEnchere(int no_article)throws BusinessException{
	List<Enchere> nombreEnchere = new ArrayList<Enchere>();
	nombreEnchere = enchereDAO.selectByNoArticle(no_article);	
	
	int lesenchere = nombreEnchere.size();
	if(lesenchere == 1) {
			return true;
	}
	
		return false;
}
	/*--------------MÃ©thodes pour les filtres de la page d'accueil --------------------------*/

	public List<Enchere> toutesLesEncheresParUtilisateur(int no_utilisateur) throws BusinessException {
		return enchereDAO.getListByNoUtilisateur(no_utilisateur);
	}

	public List<Enchere> listeEnchereParPeriode(List<Enchere> listeEnchere, String periode) throws BusinessException {
		List<Enchere> listeEncheresParPeriode = new ArrayList<Enchere>();
		LocalDate today = LocalDate.now();
		switch (periode) {
		case "APRES_FIN":
			for (Enchere enchere : listeEnchere) {
				if (today.compareTo(enchere.getDate_enchere()) == 1) {
					listeEncheresParPeriode.add(enchere);
				}
			}
			break;
		case "EN_COURS":
			for (Enchere enchere : listeEnchere) {
				if (today.compareTo(enchere.getDate_enchere()) != 1) {
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

	public List<ArticleVendu> ArticlesdeListeEncheres(List<Enchere> listeEnchere) throws BusinessException {
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		for (Enchere enchere : listeEnchere) {
			listeArticle.add(enchere.getArticle());
		}

		return listeArticle;

	}

	public Enchere validerEnchere(LocalDate date_enchere, int montant_enchere, int no_article, int no_utilisateur )throws BusinessException{

		BusinessException be = new BusinessException();
		boolean isEnchereSuperieur = verifEnchere(montant_enchere, no_article, be);
		boolean isVerifPrixreserve = verifPrixreserve(montant_enchere, no_article, be);
		if(isEnchereSuperieur && isVerifPrixreserve) {
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
		
		
		utilisateur.setCredit(montant_enchere);
		
		
		
		
		return enchere;
		
		
	}else {
		throw be;
	}
		
	}
	

	public boolean verifEnchere(int montant_enchere , int no_article, BusinessException be) {
	List<Enchere> enchere = null;
	try {
		enchere = enchereDAO.selectByNoArticle(no_article);
	} catch (BusinessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
     for(Enchere e : enchere) {
    	 if(montant_enchere < e.getMontant_enchere() ) {
    		 be.addError("Votre enchere est inferieur à l'enchere precedente");
				return false;	 
	}
	
   }
    return true;
 }
	
  public boolean verifPrixreserve(int montant_enchere, int no_article , BusinessException be) {
	
	 ArticleVendu article = null;
	try {
		article = ArticleVenduManager.getArticleVenduManager().articleParNumero(no_article);
	} catch (BusinessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 int prixreserve = article.getPrix_initial();
	 if(montant_enchere < prixreserve) {
		 be.addError("Votre enchère est inferieur à la reserve");
		return false; 
	 }
	 return true;
  }
  

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

