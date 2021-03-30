package fr.eni.projettroc.dao;

import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.exception.BusinessException;



public interface ArticleVenduDAO {
	
	public void insert(ArticleVendu article) throws BusinessException;
	
	public void update(ArticleVendu article) throws BusinessException;
	
	public void deleteAll(int no_article) throws BusinessException;
	
	public void deleteArticle(int no_article) throws BusinessException;
	
	public ArticleVendu selectByNoArticle (int no_article) throws BusinessException;
	
	//public List<ListeArticleVendu> selectAllByIdListe(int no_article) throws BusinessException;

	

	

	

}
