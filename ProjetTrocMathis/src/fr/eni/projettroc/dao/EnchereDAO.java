package fr.eni.projettroc.dao;

import java.util.List;

import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.exception.BusinessException;

public interface EnchereDAO {

	List<Enchere> getListEnchere() throws BusinessException;

	}


