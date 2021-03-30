package fr.eni.projettroc.dao;

import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.bo.Utilisateur;

public interface UtilisateurDAO {
	
public Utilisateur find(String pseudo, String mot_de_passe, String email)throws BusinessException;

public Utilisateur selectByNoUtilisateur(int no_utilisateur)throws BusinessException;
	
}

 



