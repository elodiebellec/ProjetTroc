package fr.eni.projettroc.bll;



import java.util.List;

import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.DAOFactory;
import fr.eni.projettroc.dao.UtilisateurDAO;





import fr.eni.projettroc.exception.BusinessException;

public class UtilisateurManager {
	//Attribut pour repr�senter la couche DAL

	private UtilisateurDAO utilisateurDAO;


   private static UtilisateurManager instance;
   
   private UtilisateurManager() {
	   utilisateurDAO = DAOFactory.getUtilisateurDAO();
   }
   
   
   public static UtilisateurManager getUtilisateursManager() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
   
   
   public Utilisateur modifierUnUtilisateur(String pseudo,String nom,String prenom,String email,String telephone,
   String rue,String code_postal,String ville,String mot_de_passe,int no_utilisateur) throws BusinessException{
	   BusinessException be = new BusinessException();
	Utilisateur u = null;
	boolean isValidPseudo = validatePseudo(pseudo, be);
	boolean isValidPwd = validatePassword(mot_de_passe, be);
	boolean isValidIdentite = validateIdentite(nom, prenom, email, rue, ville, code_postal, telephone, be);

	if(isValidPseudo && isValidPwd && isValidIdentite) {
		u =new Utilisateur();
		
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
		}else {
			throw be;
}
}
   
   public Utilisateur validerLaConnection(String pseudo, String mot_de_passe, String email)throws BusinessException{
	   BusinessException be = new BusinessException();
		boolean isValidLogin = validateLogin(pseudo,email, be);
		boolean isValidPwd = validatePassword(mot_de_passe, be);
		if (isValidLogin && isValidPwd) {
			//Appelle de la couche DAL
			return utilisateurDAO.find(pseudo, mot_de_passe, email);
		} else {
			throw be;
		}
	}
   
 public Utilisateur rechercherParPseudo(String utilisateur) throws BusinessException{
	 return utilisateurDAO.selectByPseudo(utilisateur);
 }

       public Utilisateur validerAjoutPersonne( String pseudo,String nom,String prenom,String email,String telephone,
		   String rue,String code_postal,String ville,String mot_de_passe,String mot_de_passe_confirmation ,int credit) throws BusinessException{
	   BusinessException be = new BusinessException();
	   Utilisateur u = null;
	   boolean isPseudoInexistant = pseudoIdentique(pseudo, be);
	   boolean isValidPseudo = validatePseudo(pseudo, be);
	   boolean isValidPwd = validatePassword(mot_de_passe, be);
	   boolean isValidIdentite = validateIdentite(nom, prenom, email, rue, ville, code_postal, telephone, be);
	   boolean isValidSecondPassword = validateSecondPassword(mot_de_passe, mot_de_passe_confirmation, be);
	   if(isValidPseudo && isValidPwd && isValidIdentite && isValidSecondPassword && isPseudoInexistant) {
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
	   int id =  utilisateurDAO.insertUtilisateur(u);
	   u.setNo_utilisateur(id);
	   return u;
   }else {
	   throw be;
   }
  }
     
     public Utilisateur afficherPersonne(String pseudo) throws BusinessException{
    	 return utilisateurDAO.selectByPseudo(pseudo);
     }
     
   
     
     
     public void supprimerUtilisateur(int no_utilsateur)throws BusinessException {
 		utilisateurDAO.delete(no_utilsateur);
 		
 	}
    
     
     
     private boolean pseudoIdentique(String pseudo, BusinessException be)  {
     List<Utilisateur> utilisateur = null;
	try {
		utilisateur = utilisateurDAO.getListeUtilisateur();
	} catch (BusinessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     for(Utilisateur u : utilisateur) {
    	 if(pseudo.equals(u.getPseudo())) {
    		 be.addError("Pseudo deja utiliser");
    		 return false; 
    	 }
    }
    
    
     return true; 
}
   

     private boolean validatePseudo(String pseudo, BusinessException be) {
 		if (pseudo == null) {
 			be.addError("Login est obligatoire");
 			return false;
 		}
 		pseudo = pseudo.trim();
 		if (pseudo.isEmpty() || pseudo.length() < 6) {
 			be.addError("Login doit contenir au moins 6 caract�res");
 			return false;
 		}
 		if (pseudo.length() > 30 ) {
 			be.addError("Login doit contenir au plus 30 caract�res");
 			return false;
 		}

 		return true;
 	}
     private boolean validateIdentite(String nom,String prenom,String email, String rue, String ville, String code_postal, String telephone ,BusinessException be) {
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
 			be.addError(
 					"Le Code Postal ne doit contenir que des chiffres");
 			return false;
    		}
    	if (!telephone.matches("[0-9]{10}")) {
 			be.addError(
 					"Le numero de telephone ne doit contenir que des chiffres");
 			return false;
    		}
  		return true;
  	}

     
     
     
     private boolean validateLogin(String pseudo,String email, BusinessException be) {
		if (pseudo == null && email == null) {
			be.addError("Login est obligatoire");
			return false;
		}
		pseudo = pseudo.trim();
		if (pseudo.isEmpty() || email.isEmpty() || pseudo.length() < 6) {
			be.addError("Login doit contenir au moins 6 caract�res");
			return false;
		}
		if (pseudo.length() > 30 || email.length() >30) {
			be.addError("Login doit contenir au plus 30 caract�res");
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
					"Mot de passe doit contenir entre 6 et 12 caract�res (1 chiffre, 1 majuscule, 1 caract�re sp�cial)");
			return false;
		}
		
		return true;
	}


		private boolean validateSecondPassword(String mot_de_passe,String mot_de_passe_confirmation, BusinessException be){	
			if(!mot_de_passe.equals( mot_de_passe_confirmation)){
				be.addError(
						"Les deux mot de passe doivent etre identique");
				return false;
			}

		return true;
	}
}








