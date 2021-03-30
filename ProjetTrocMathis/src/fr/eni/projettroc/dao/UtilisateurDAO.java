package fr.eni.projettroc.dao;

<<<<<<< HEAD
=======

>>>>>>> branch 'enchere' of https://github.com/elodiebellec/ProjetTroc.git
import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.bo.Utilisateur;

public interface UtilisateurDAO {
<<<<<<< HEAD
	
public Utilisateur find(String pseudo, String mot_de_passe, String email)throws BusinessException;
=======
>>>>>>> branch 'enchere' of https://github.com/elodiebellec/ProjetTroc.git

<<<<<<< HEAD
public Utilisateur selectByNoUtilisateur(int no_utilisateur)throws BusinessException;
	
}

 



=======
	
	Utilisateur find(String pseudo, String mot_de_passe, String email)throws BusinessException;
	
    public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;



}
>>>>>>> branch 'enchere' of https://github.com/elodiebellec/ProjetTroc.git
