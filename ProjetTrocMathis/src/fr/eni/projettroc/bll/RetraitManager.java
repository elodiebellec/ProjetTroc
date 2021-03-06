package fr.eni.projettroc.bll;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Retrait;
import fr.eni.projettroc.dao.DAOFactory;
import fr.eni.projettroc.dao.RetraitDAO;
import fr.eni.projettroc.exception.BusinessException;

public class RetraitManager {

	private RetraitDAO retraitDAO;

	private static RetraitManager instance;

	private RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}

	public static RetraitManager getRetraitManager() {

		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}

	public void creerRetrait(Retrait retrait) throws BusinessException {
		retraitDAO.insertRetrait(retrait);
	}

	public void supprimerRetrait(int no_article) throws BusinessException {
		retraitDAO.deleteRetrait(no_article);
	}

	

	public Retrait retraitParArticle(int no_article) throws BusinessException {
		return retraitDAO.getByArticleId(no_article);

	}

	public Retrait valideAjoutRetrait(ArticleVendu article, String rue, String code_postal, String ville)
			throws BusinessException {
		BusinessException be = new BusinessException();
		Retrait retrait = null;

		boolean isValiderRue = validerRue(rue, be);
		boolean isValiderCodePostal = validerCodePostal(code_postal, be);
		boolean isValiderVille = validerVille(ville, be);
		if (isValiderRue && isValiderCodePostal && isValiderVille) {

			retrait = new Retrait();
			retrait.setArticle(article);
			retrait.setCode_postal(code_postal);
			retrait.setRue(rue);
			retrait.setVille(ville);

			retraitDAO.insertRetrait(retrait);

			return retrait;
		} else {
			throw be;

		}
	}
	
	public Retrait modifierRetrait( String rue, String codePostal, String ville, int no_article) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean isValiderRue = validerRue(rue, be);
		boolean isValiderCodePostal = validerCodePostal(codePostal, be);
		boolean isValiderVille = validerVille(ville, be);
		if (isValiderRue && isValiderCodePostal && isValiderVille) {
		
		ArticleVendu article = new ArticleVendu();
		 article.setNo_article(no_article);
		
		Retrait retrait = null;
		
		retrait = new Retrait();
		retrait.setRue(rue);
		retrait.setCode_postal(codePostal);
		retrait.setVille(ville);
		retrait.setArticle(article);

		retraitDAO.updateRetrait(retrait);

		return retrait;
		} else {
			throw be;

		}
	}

	private boolean validerVille(String ville, BusinessException be) {
		ville = ville.trim();
		if (ville == null) {
			be.addError("La ville est obligatoire");
			return false;
		}
		if (ville.trim().isEmpty() || ville.trim().length() > 50) {

			be.addError("La ville ne doit pas d??passer 50 caract??res");

			return false;
		}

		return true;
	}

	private boolean validerCodePostal(String code_postal, BusinessException be) {
		code_postal = code_postal.trim();
		if (code_postal == null) {
			be.addError("Le code postale est obligatoire");
			return false;
		}
		if (code_postal.trim().isEmpty() || code_postal.trim().length() != 5) {

			be.addError("Le code postale doit ??tre compos?? de 5 caract??res");

			return false;
		}

		return true;
	}

	private boolean validerRue(String rue, BusinessException be) {
		rue = rue.trim();
		if (rue == null) {
			be.addError("La rue est obligatoire");
			return false;
		}
		if (rue.trim().isEmpty() || rue.trim().length() > 200) {

			be.addError("La rue ne doit pas d??passer 200 caract??res");

			return false;
		}
		return true;
	}



}
