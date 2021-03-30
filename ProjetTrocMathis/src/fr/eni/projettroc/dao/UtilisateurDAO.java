package fr.eni.projettroc.dao;

<<<<<<< HEAD
import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.bo.Utilisateur;

public interface UtilisateurDAO {
	Utilisateur find(String pseudo, String mot_de_passe, String email)throws BusinessException;
	Utilisateur selectByNoUtilisateur(int int1)throws BusinessException;
}


=======

import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;

public interface UtilisateurDAO {

	
	Utilisateur find(String pseudo, String mot_de_passe, String email)throws BusinessException;
	
    public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;


}
>>>>>>> branch 'master' of https://github.com/elodiebellec/ProjetTroc.git
