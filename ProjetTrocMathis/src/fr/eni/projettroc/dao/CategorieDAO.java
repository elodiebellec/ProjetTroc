package fr.eni.projettroc.dao;

import fr.eni.projettroc.bo.Categorie;
import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.exception.BusinessException;

public interface CategorieDAO {

	public Categorie selectByNoCategorie(int int1) throws BusinessException;

	List<Categorie> getListCategories() throws BusinessException;

	}


