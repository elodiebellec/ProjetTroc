/**
 * 
 */
package fr.eni.projettroc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.exception.Errors;




public class ArticleVenduJdbcImpl implements ArticleVenduDAO {
	
	//private static final String INSERT = "INSERT INTO Articles_vendus(no_article,nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, utilisateur, categorie) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT = "INSERT INTO Articles_vendus VALUES(null,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_BY_ID = "DELETE FROM Articles_vendus where no_article=?";
	
	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT);
			stmt.setInt(1, article.getNo_article());
			stmt.setString(2, article.getNom_article());
			stmt.setString(3, article.getDescription());
			stmt.setDate_debut_encheres(4, article.getDate_debut_encheres());
			stmt.setDate_fin_encheres(5, article.getDate_fin_encheres());
			stmt.setInt(6, article.getPrix_initial());
			stmt.setInt(7, article.getPrix_vente());
			stmt.setUtilisateur(8, article.getUtilisateur());
			stmt.setCategorie(9, article.getCategorie());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				article.setInt(rs.getInt(1));
			} else {
				BusinessException be = new BusinessException();
				be.addError(Errors.INSERT_OBJET_ECHEC);
				throw be;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError(Errors.INSERT_OBJET_ECHEC);
			throw be;
		}

	}
	
	@Override
	public void deleteArticle(int no_Article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_ID);
			stmt.setInt(1, no_Article);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError(Errors.SUPPRESSION_ARTICLE_ERREUR);
			throw be;
		}

	}
	

}
