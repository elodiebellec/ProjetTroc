package fr.eni.projettroc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.exception.Errors;




public class ArticleVenduJdbcImpl implements ArticleVenduDAO {
	
	//private static final String INSERT = "INSERT INTO Articles_vendus(no_article,nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, utilisateur, categorie) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT = "INSERT INTO Articles_vendus VALUES(null,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_BY_No = "DELETE FROM Articles_vendus where no_article=?";
	//private static final String DELETE_ALL = "delete from Articles_vendus where id_liste=?";
	private static final String SELECT_BY_No = "SELECT no_article FROM Articles_vendus WHERE no_article=?";
	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT);
			stmt.setInt(1, article.getNo_article());
			stmt.setString(2, article.getNom_article());
			stmt.setString(3, article.getDescription());
			stmt.setDate(4,java.sql.Date.valueOf(article.getDate_debut_encheres()));
			stmt.setDate(4,java.sql.Date.valueOf(article.getDate_fin_encheres()));
			stmt.setInt(6, article.getPrix_initial());
			stmt.setInt(7, article.getPrix_vente());
			stmt.setInt(8, article.getUtilisateur().getNo_utilisateur());
			stmt.setInt(9, article.getCategorie().getNo_categorie());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNo_article(rs.getInt(1));
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
			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_No);
			stmt.setInt(1, no_Article);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError(Errors.SUPPRESSION_ARTICLE_ERREUR);
			throw be;
		}

	}
	
	public ArticleVendu selectByNoArticle (int no_article) throws BusinessException {
		ArticleVendu av = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_No);
			stmt.setInt(1, no_article);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				av = builderArticle(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError(Errors.LECTURE_LISTE_ECHEC);
			throw be;
		}

		return av;
	}
		
	private ArticleVendu builderArticle(ResultSet rs) throws SQLException {
		ArticleVendu av = new ArticleVendu();
		av.setNo_article(rs.getInt("no_article"));
		av.setNom_article(rs.getString("nom_article"));
		av.setDescription(rs.getString("description"));
		av.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
		av.setDate_fin_encheres(rs.getDate("date_fin-encheres").toLocalDate());
		av.setPrix_initial(rs.getInt("prix_initial"));
		av.setPrix_vente(rs.getInt("prix_vente"));
		av.setUtilisateur(UtilisateurDAO.selectByNoUtilisateur(rs.getInt("utilisateur")));
		av.setCategorie(CategorieDAO.selectByNoCategorie(rs.getInt("categorie")));
	 
		return null;
	}

	
	
	

	@Override
	public void update(ArticleVendu article) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void deleteAll(int no_article) throws BusinessException {
		
		
	}
	

}
