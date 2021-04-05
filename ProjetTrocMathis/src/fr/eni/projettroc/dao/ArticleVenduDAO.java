package fr.eni.projettroc.dao;

import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.exception.BusinessException;



public interface ArticleVenduDAO {
	
	public int insert(ArticleVendu article) throws BusinessException;
	
	public void update(ArticleVendu article) throws BusinessException;
	
	public void deleteArticle(int no_article) throws BusinessException;
	
	public List<ArticleVendu> getListByCategorie (int no_categorie) throws BusinessException;

	public List<ArticleVendu> getListArticle() throws BusinessException;
	
	public ArticleVendu selectByNumero(int numero) throws BusinessException;
	
	
	
	

	

	

	

}
