package fr.eni.projettroc.dao;

import fr.eni.projettroc.bo.Categorie;
import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.exception.BusinessException;

public interface CategorieDAO {

	public List<Categorie> getListCategories() throws BusinessException;
	public Categorie selectByNoCategorie (int no_categorie) throws BusinessException;

	}


