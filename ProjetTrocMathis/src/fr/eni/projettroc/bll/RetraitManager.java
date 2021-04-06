package fr.eni.projettroc.bll;

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

	public void modifierRetrait(Retrait retrait) throws BusinessException {
		retraitDAO.updateRetrait(retrait);
	}

	public Retrait retraitParArticle(int no_article) throws BusinessException {
		return retraitDAO.getByArticleId(no_article);

	}

}
