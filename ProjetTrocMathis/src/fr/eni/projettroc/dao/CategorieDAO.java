package fr.eni.projettroc.dao;

import fr.eni.projettroc.bo.Categorie;
import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.exception.BusinessException;

public interface CategorieDAO {

	static Categorie selectByNoCategorie(int int1) {
		// TODO Auto-generated method stub
		return null;
	}

	List<Categorie> getListCategories() throws BusinessException;

	}


