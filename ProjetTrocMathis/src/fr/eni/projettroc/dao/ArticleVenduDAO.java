package fr.eni.projettroc.dao;

import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.exception.BusinessException;



public interface ArticleVenduDAO {
	
	public void insert(ArticleVendu article) throws BusinessException;
	
	public void update(ArticleVendu article) throws BusinessException;
	
	public void deleteAll(int no_article) throws BusinessException;
	
	public void deleteArticle(int no_article) throws BusinessException;
	
	public List<ArticleVendu> getListByNom (String nom_article) throws BusinessException;

	public List<ArticleVendu> getListArticle() throws BusinessException;
	
	

	

	

	

}
