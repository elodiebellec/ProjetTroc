package fr.eni.projettroc.dao;

import fr.eni.projettroc.exception.BusinessException;

import java.util.List;

import fr.eni.projettroc.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur find(String pseudo, String mot_de_passe, String email) throws BusinessException;

	public Utilisateur selectByNoUtilisateur(int no_utilisateur) throws BusinessException;

	public int insertUtilisateur(Utilisateur utilisateur) throws BusinessException;

	public Utilisateur selectByPseudo(String pseudo) throws BusinessException;

	public void update(Utilisateur utilisateur) throws BusinessException;

	public void delete(int id) throws BusinessException;

	public List<Utilisateur> getListeUtilisateur() throws BusinessException;
}
