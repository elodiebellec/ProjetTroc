package fr.eni.projettroc.dao;

import fr.eni.projettroc.bo.Retrait;
import fr.eni.projettroc.exception.BusinessException;

public interface RetraitDAO {
	
	public void insertRetrait(Retrait retrait) throws BusinessException;	
	
	public void deleteRetrait(int no_article) throws BusinessException;
	
	public void updateRetrait(Retrait retrait) throws BusinessException;

	public Retrait getByArticleId(int no_article) throws BusinessException;

}


