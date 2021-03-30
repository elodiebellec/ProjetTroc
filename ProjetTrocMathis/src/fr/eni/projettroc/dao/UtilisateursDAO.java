package fr.eni.projettroc.dao;


import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;

public interface UtilisateursDAO {

	
	Utilisateur find(String pseudo, String mot_de_passe, String email)throws BusinessException;
	
    public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;


}