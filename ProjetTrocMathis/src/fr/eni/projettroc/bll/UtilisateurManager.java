package fr.eni.projettroc.bll;

import java.util.ArrayList;
import java.util.List;


import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Retrait;


import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.ArticleVenduDAO;
import fr.eni.projettroc.dao.DAOFactory;
import fr.eni.projettroc.dao.EnchereDAO;
import fr.eni.projettroc.dao.RetraitDAO;
import fr.eni.projettroc.dao.UtilisateurDAO;

import fr.eni.projettroc.exception.BusinessException;

public class UtilisateurManager {
	// Attribut pour représenter la couche DAL

	private UtilisateurDAO utilisateurDAO;
	private RetraitDAO retraitDAO;
	private EnchereDAO enchereDAO;
	private ArticleVenduDAO articleVenduDAO;

	private static UtilisateurManager instance;

	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
		retraitDAO = DAOFactory.getRetraitDAO();
		enchereDAO = DAOFactory.getEnchereDAO();
		articleVenduDAO=DAOFactory.getArticleVenduDAO();
	}
	
	

	public static UtilisateurManager getUtilisateursManager() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public Utilisateur modifierUnUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mot_de_passe, String ancienmotdepasse,
			int no_utilisateur, String saisieancienmotdepasse) throws BusinessException {
		BusinessException be = new BusinessException();
		Utilisateur u = null;
		boolean isValidPseudo = validatePseudo(pseudo, be);
		boolean isValidPwd = validatePassword(mot_de_passe, be);
		boolean isValidIdentite = validateIdentite(nom, prenom, email, rue, ville, code_postal, telephone, be);
		boolean isValidAncienMdp = validateAncienMdp(saisieancienmotdepasse, ancienmotdepasse, be);

		if (isValidPseudo && isValidPwd && isValidIdentite && isValidAncienMdp) {
			u = new Utilisateur();

			u.setPseudo(pseudo);
			u.setNom(nom);
			u.setPrenom(prenom);
			u.setEmail(email);
			u.setTelephone(telephone);
			u.setRue(rue);
			u.setCode_postal(code_postal);
			u.setVille(ville);
			u.setMot_de_passe(mot_de_passe);
			u.setNo_utilisateur(no_utilisateur);
			utilisateurDAO.update(u);
			return u;
		} else {
			throw be;
		}
	}

	public Utilisateur validerLaConnection(String pseudo, String mot_de_passe, String email) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean isValidLogin = validateLogin(pseudo, email, be);
		boolean isValidPwd = validatePassword(mot_de_passe, be);
		if (isValidLogin && isValidPwd) {
			// Appelle de la couche DAL
			return utilisateurDAO.find(pseudo, mot_de_passe, email);
		} else {
			throw be;
		}
	}

	public Utilisateur rechercherParPseudo(String utilisateur) throws BusinessException {
		return utilisateurDAO.selectByPseudo(utilisateur);
	}

	public Utilisateur rechercherParNumero(int no_utilisateur) throws BusinessException {
		return utilisateurDAO.selectByNoUtilisateur(no_utilisateur);
	}

	public Utilisateur validerAjoutPersonne(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mot_de_passe, String mot_de_passe_confirmation,
			int credit) throws BusinessException {
		BusinessException be = new BusinessException();
		Utilisateur u = null;
		boolean isPseudoInexistant = pseudoIdentique(pseudo, be);
		boolean isValidPseudo = validatePseudo(pseudo, be);
		boolean isValidPwd = validatePassword(mot_de_passe, be);
		boolean isValidIdentite = validateIdentite(nom, prenom, email, rue, ville, code_postal, telephone, be);
		boolean isValidSecondPassword = validateSecondPassword(mot_de_passe, mot_de_passe_confirmation, be);
		if (isValidPseudo && isValidPwd && isValidIdentite && isValidSecondPassword && isPseudoInexistant) {
			u = new Utilisateur();
			u.setPseudo(pseudo);
			u.setNom(nom);
			u.setPrenom(prenom);
			u.setEmail(email);
			u.setTelephone(telephone);
			u.setRue(rue);
			u.setCode_postal(code_postal);
			u.setVille(ville);
			u.setMot_de_passe(mot_de_passe);
			u.setCredit(credit);
			int id = utilisateurDAO.insertUtilisateur(u);
			u.setNo_utilisateur(id);
			return u;
		} else {
			throw be;
		}
	}

	public Utilisateur verificationArgent(int credit, int no_utilisateur) throws BusinessException {
		BusinessException be = new BusinessException();
		Utilisateur user = null;
		user = utilisateurDAO.selectByNoUtilisateur(no_utilisateur);
		int argentuser = user.getCredit();
		boolean isAssezArgent = argentDisponible(credit, argentuser, no_utilisateur, be);
        if(isAssezArgent) {
		return user;
	
	} else {
		throw be;
	}
	}
	
	public Utilisateur validerArgentEnchere(int credit, int no_utilisateur) throws BusinessException {
		Utilisateur user = null;
		user = utilisateurDAO.selectByNoUtilisateur(no_utilisateur);
		int argentuser = user.getCredit();
		int argentdemander = credit;
		int argentreel = argentuser - argentdemander;
		user.setCredit(argentreel);
		user.setNo_utilisateur(no_utilisateur);
		utilisateurDAO.updateCredit(user);
		return user;
	}
	
	public Utilisateur rendreArgentEnchere(int credit, int no_utilisateur) throws BusinessException {
		Utilisateur user = null;
		user = utilisateurDAO.selectByNoUtilisateur(no_utilisateur);
		int argentuser = user.getCredit();
		int argentdemander = credit;
		int argentreel = argentuser + argentdemander;
		user.setCredit(argentreel);
		user.setNo_utilisateur(no_utilisateur);
		utilisateurDAO.updateCredit(user);
		return user;
	}

	public Utilisateur afficherPersonne(String pseudo) throws BusinessException {
		return utilisateurDAO.selectByPseudo(pseudo);
	}

	public void supprimerUtilisateur(int no_utilisateur) throws BusinessException {		
		
		// on vérifie que l'utilisateur ne fait pas d'enchère en cours
		if (utilisateurDAO.isEnchereEnCours(no_utilisateur)==true) {
			throw new BusinessException(" Des enchères sont en cours sur un de vos articles, il est impossible de supprimer le compte");
		}		
		//on récupère la liste des articles
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		listeArticles=articleVenduDAO.getListByNoUtilisateur(no_utilisateur);
				
		// Pour chaque article
		for(ArticleVendu articleVendu : listeArticles) {
			// on supprime le retrait
			retraitDAO.deleteRetrait(articleVendu.getNo_article());
			// on supprime l'article 
			articleVenduDAO.deleteArticle(articleVendu.getNo_article());
		}
		// on supprime les enchères que l'utilisateur fait
		enchereDAO.deleteEnchere(no_utilisateur);
		// on supprime l'utilisateur
		utilisateurDAO.delete(no_utilisateur);

	}

	private boolean pseudoIdentique(String pseudo, BusinessException be) {
		List<Utilisateur> utilisateur = null;
		try {
			utilisateur = utilisateurDAO.getListeUtilisateur();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Utilisateur u : utilisateur) {
			if (pseudo.equals(u.getPseudo())) {
				be.addError("Pseudo déjà utilisé");
				return false;
			}
			
		}

		return true;
	}

	private boolean argentDisponible(int credit, int argentuser, int no_utilsateur, BusinessException be) {
		if (argentuser < credit) {
			be.addError("Vous n'avez pas assez d'argent");
			return false;
			
		}
		return true;
	}

	private boolean validateAncienMdp(String saisieancienmotdepasse, String ancienmotdepasse, BusinessException be) {
		if (!saisieancienmotdepasse.equals(ancienmotdepasse)) {
			be.addError("Votre ancien mot de passe est incorrecte");
			return false;
		}
		return true;
	}

	private boolean validatePseudo(String pseudo, BusinessException be) {
		if (pseudo == null) {
			be.addError("Le Pseudo est obligatoire");
			return false;
		}
		pseudo = pseudo.trim();
		if (pseudo.isEmpty() || pseudo.length() < 6) {
			be.addError("Le Pseudo doit contenir au moins 6 caractères");
			return false;
		}
		if (pseudo.length() > 30) {
			be.addError("Le Pseudo doit contenir au maximum 30 caractères");
			return false;
		}

		return true;
	}

	private boolean validateIdentite(String nom, String prenom, String email, String rue, String ville,
			String code_postal, String telephone, BusinessException be) {
		if (nom == null) {
			be.addError("Nom obligatoire");
			return false;
		}
		if (prenom == null) {
			be.addError("Prenom obligatoire");
			return false;
		}
		if (email == null) {
			be.addError("Email obligatoire");
			return false;
		}
		if (rue == null) {
			be.addError("Rue obligatoire");
			return false;
		}
		if (ville == null) {
			be.addError("Ville obligatoire");
			return false;
		}
		if (code_postal == null) {
			be.addError("Code Postal obligatoire");
			return false;
		}
		if (telephone == null) {
			be.addError("Numero de telephone obligatoire");
			return false;
		}
		if (!code_postal.matches("[0-9]{5}")) {
			be.addError("Le Code Postal ne doit contenir que des chiffres");
			return false;
		}
		if (!telephone.matches("[0-9]{10}")) {
			be.addError("Le numero de telephone ne doit contenir que des chiffres");
			return false;
		}
		return true;
	}

	private boolean validateLogin(String pseudo, String email, BusinessException be) {
		if (pseudo == null && email == null) {
			be.addError("Login est obligatoire");
			return false;
		}
		pseudo = pseudo.trim();
		if (pseudo.isEmpty() || email.isEmpty() || pseudo.length() < 6) {
			be.addError("Login doit contenir au moins 6 caractères");
			return false;
		}
		if (pseudo.length() > 30 || email.length() > 30) {
			be.addError("Login doit contenir au plus 30 caractères");
			return false;
		}

		return true;
	}

	private boolean validatePassword(String mot_de_passe, BusinessException be) {
		if (mot_de_passe == null) {
			be.addError("Mot de passe est obligatoire");
			return false;
		}
		if (!mot_de_passe.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,12}")) {
			be.addError(
					"Mot de passe doit contenir entre 6 et 12 caractères (1 chiffre, 1 majuscule, 1 caractère spécial)");
			return false;
		}

		return true;
	}

	private boolean validateSecondPassword(String mot_de_passe, String mot_de_passe_confirmation,
			BusinessException be) {
		if (!mot_de_passe.equals(mot_de_passe_confirmation)) {
			be.addError("Les deux mots de passe doivent être identiques");
			return false;
		}

		return true;
	}
}
