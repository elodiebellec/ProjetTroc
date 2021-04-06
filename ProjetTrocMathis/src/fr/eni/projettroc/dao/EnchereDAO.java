package fr.eni.projettroc.dao;

import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.exception.BusinessException;

public interface EnchereDAO {

	public List<Enchere> getListEnchere() throws BusinessException;

	List<Enchere> getListByNoUtilisateur(int no_utilisateur) throws BusinessException;
	

	public void insert(Enchere enchere) throws BusinessException;
	}


