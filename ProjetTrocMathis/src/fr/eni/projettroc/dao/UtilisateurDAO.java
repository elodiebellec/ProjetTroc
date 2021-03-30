package fr.eni.projettroc.dao;


import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.bo.Utilisateur;

public interface UtilisateurDAO {

	
	Utilisateur find(String pseudo, String mot_de_passe, String email)throws BusinessException;
	
    public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;



}