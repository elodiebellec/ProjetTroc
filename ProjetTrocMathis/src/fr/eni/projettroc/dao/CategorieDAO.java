package fr.eni.projettroc.dao;

import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.exception.BusinessException;

public interface CategorieDAO {

	List<Categorie> getListCategories() throws BusinessException;

	}


