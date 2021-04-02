
package fr.eni.projettroc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.exception.Errors;




public class ArticleVenduJDBCImpl implements ArticleVenduDAO {
	
	private static final String INSERT = "INSERT INTO `articles_vendus`(`nom_article`, `description`, `date_debut_encheres`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (?,?,?,?,?,?,?,?)";
	private static final String DELETE_BY_NO = "DELETE FROM `articles_vendus` WHERE `no_article`=?";
	private static final String UPDATE = "UPDATE `articles_vendus` SET `no_article`=?,`nom_article`=?,`description`=?,`date_debut_encheres`=?,`date_fin_encheres`=?,`prix_initial`=?,`prix_vente`=?,`no_utilisateur`=?,`no_categorie`=? WHERE `no_article`=?";
	private static final String SELECT_ALL = "SELECT `no_article`,`nom_article`,`description`,`date_debut_encheres`,`date_fin_encheres`,`prix_initial`,`prix_vente`,`no_utilisateur`,`no_categorie` FROM `articles_vendus`";
	private static final String SELECT_BY_UTILISATEUR = "SELECT `no_article`, `nom_article`, `description`, `date_debut_encheres`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie` FROM `articles_vendus` WHERE `no_utilisateur`=?";
	private static final String SELECT_ALL_BY_CATEGORIE = "SELECT * FROM `articles_vendus` WHERE `no_categorie`=?";
	
	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getNom_article());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3,java.sql.Date.valueOf(article.getDate_debut_encheres()));
			stmt.setDate(4,java.sql.Date.valueOf(article.getDate_fin_encheres()));
			stmt.setInt(5, article.getPrix_initial());
			stmt.setInt(6, article.getPrix_vente());
			stmt.setInt(7, article.getUtilisateur().getNo_utilisateur());
			stmt.setInt(8, article.getCategorie().getNo_categorie());
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
			PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_NO);
			stmt.setInt(1, no_Article);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError(Errors.SUPPRESSION_ARTICLE_ERREUR);
			throw be;
		}

	}
	
	

	public ArticleVendu selectByUtilisateur (int no_utilisateur) throws BusinessException {
		ArticleVendu av = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR);
			stmt.setInt(1, no_utilisateur);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				av = articleBuilder(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("error dans selectByUtilisateur");
			throw be;
		}

		return av;
	}
	
	
	
	
	@Override
	public List<ArticleVendu> getListByCategorie(int no_categorie) throws BusinessException {
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL_BY_CATEGORIE);
			stmt.setInt(1, no_categorie);
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				listeArticle.add(articleBuilder(rs));
				}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur getListByCategorie DAO");
			throw be;
		}

		return listeArticle;
	}
		
	
	@Override
	public List<ArticleVendu> getListArticle() throws BusinessException {
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
		
			while (rs.next()) {
				listeArticle.add(articleBuilder(rs));
				}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur getListArticleVendu DAO");
			throw be;
		}

		return listeArticle;
	}
		
	
	
	private ArticleVendu articleBuilder(ResultSet rs) throws SQLException, BusinessException {
		ArticleVendu articleVendu = new ArticleVendu();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		Utilisateur utilisateur = new Utilisateur();
		
		CategorieDAO categorieDAO =DAOFactory.getCategorieDAO();
		Categorie categorie = new Categorie();
		
		articleVendu.setNo_article(rs.getInt("no_article"));
		articleVendu.setNom_article(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
		articleVendu.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
		articleVendu.setPrix_initial(rs.getInt("prix_initial"));
		articleVendu.setPrix_vente(rs.getInt("prix_vente"));
		if(rs.getInt("no_utilisateur") != 0) {
			//L'instance utilisateur r�cup�re les donn�es de utilisateurDAO
			utilisateur = utilisateurDAO.selectByNoUtilisateur(rs.getInt("no_utilisateur"));
			articleVendu.setUtilisateur(utilisateur);
		}
		
		if(rs.getInt("no_categorie") != 0) {
			//L'instance utilisateur r�cup�re les donn�es de utilisateurDAO
			categorie = categorieDAO.selectByNoCategorie(rs.getInt("no_categorie"));
			articleVendu.setCategorie(categorie);
		}

		
		return articleVendu;
	}

	
	
	

	@Override
	public void update(ArticleVendu article) throws BusinessException {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		CategorieDAO categorieDAO =DAOFactory.getCategorieDAO();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(UPDATE);
			requete.setInt(1, article.getNo_article()); 	
			requete.setString(2, article.getNom_article());
			requete.setString(3, article.getDescription());
			requete.setDate(4,java.sql.Date.valueOf(article.getDate_debut_encheres()));
			requete.setDate(5,java.sql.Date.valueOf(article.getDate_fin_encheres()));
			requete.setInt(6, article.getPrix_initial());
			requete.setInt(7,article.getPrix_vente());
			requete.setInt(8,article.getCategorie().getNo_categorie());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		
	
	


	
	

	
	

}
