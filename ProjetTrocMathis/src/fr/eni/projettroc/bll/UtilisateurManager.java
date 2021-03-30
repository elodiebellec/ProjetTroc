package fr.eni.projettroc.bll;

import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.DAOFactory;

import javax.print.event.PrintJobAttributeEvent;

import fr.eni.projettroc.dao.UtilisateursDAO;
import fr.eni.projettroc.exception.BusinessException;

public class UtilisateurManager {
	//Attribut pour représenter la couche DAL
	private UtilisateursDAO utilisateursDAO;

   private static UtilisateurManager instance;
   
   private UtilisateurManager() {
	   utilisateursDAO = DAOFactory.getUtilisateursDAO();
   }
   
   public static UtilisateurManager getUtilisateursManager() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
   
   public Utilisateur validerLaConnection(String pseudo, String mot_de_passe, String email)throws BusinessException{
	   BusinessException be = new BusinessException();
		boolean isValidLogin = validateLogin(pseudo,email, be);
		boolean isValidPwd = validatePassword(mot_de_passe, be);
		if (isValidLogin && isValidPwd) {
			//Appelle de la couche DAL
			return utilisateursDAO.find(pseudo, mot_de_passe, email);
		} else {
			throw be;
		}
	}
   
   public void ajouterUtlisateur(Utilisateur utilisateur) throws BusinessException{
	   utilisateursDAO.insertUtilisateur(utilisateur);
   }
   
   
   public Utilisateur ajouterUnUtilisateur(String pseudo,String nom,String prenom,String email,String telephone,
		   String rue,String code_postal,String ville,String mot_de_passe) throws BusinessException{
	   BusinessException be = new BusinessException();
	   Utilisateur u = null;
	   
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
	   utilisateursDAO.insertUtilisateur(u);
	   return u;
   }
   
 /*  public ListeCourse ajouterListeEtArticle(String nomListe, String nomArticle) throws BusinessException {
		BusinessException be = new BusinessException();
		validerNomListe(nomListe, be);
		validerNomArticle(nomArticle, be);

		ListeCourse listeCourse = null;

		if (!be.hasErreurs()) {
			listeCourse = new ListeCourse();
			listeCourse.setNom(nomListe.trim());
			listeCourseDAO.insert(listeCourse);

			Article article = new Article();
			article.setNom(nomArticle.trim());
			articleDAO.insert(article, listeCourse.getId());
			listeCourse.getArticles().add(article);
			return listeCourse;
		} else {
			throw be;
		}*/
   
   
   private boolean validateLogin(String pseudo,String email, BusinessException be) {
		if (pseudo == null && email == null) {
			be.addError("Login est obligatoire");
			return false;
		}
		pseudo = pseudo.trim();
		if (pseudo.isEmpty() || email.isEmpty() || pseudo.length() < 6) {
			be.addError("Login doit contenir au moins 6 caractères");
			return false;
		}
		if (pseudo.length() > 30 || email.length() >30) {
			be.addError("Login doit contenir au plus 30 caractères");
			return false;
		}

		return true;
	}
     /**
	 * Vérifier que le password n'est pas null, pas vide et qu'il respecte un nombre
	 * de caractères entre 8 et 12 Le mot de passe doit contenir au moins un
	 * chiffre, 1 majuscule, 1 caractère spécial
	 * 
	 * @param pwd
	 * @param be
	 * @return
	 */
	private boolean validatePassword(String mot_de_passe, BusinessException be) {
		if (mot_de_passe == null) {
			be.addError("Mot de passe est obligatoire");
			return false;
		}
		if (!mot_de_passe.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,12}")) {
			be.addError(
					"Mot de passe doit contenir entre 8 et 12 caractères (1 chiffre, 1 majuscule, 1 caractère spécial)");
			return false;
		}

		return true;
	}



}



