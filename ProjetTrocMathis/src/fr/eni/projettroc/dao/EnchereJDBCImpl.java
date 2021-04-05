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
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;




public class EnchereJDBCImpl implements EnchereDAO{
	
	private static final String SELECT_ALL = "SELECT * FROM `encheres`";
	private static final String SELECT_ALL_BY_UTILISATEUR = "SELECT * FROM `encheres` WHERE `no_utilisateur`=?";

	private Enchere enchereBuilder(ResultSet rs) throws SQLException, BusinessException {
		Enchere enchere = new Enchere();
		
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		Utilisateur utilisateur = new Utilisateur();
		
		ArticleVenduDAO articleDAO =DAOFactory.getArticleVenduDAO();
		ArticleVendu articleVendu = new ArticleVendu();
		
		enchere.setNo_enchere(rs.getInt("no_enchere"));
		enchere.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
		enchere.setMontant_enchere(rs.getInt("montant_enchere"));
		
		if(rs.getInt("no_article") != 0) {
			//L'instance utilisateur r�cup�re les donn�es de utilisateurDAO
			articleVendu = articleDAO.selectByNoArticle(rs.getInt("no_article"));
			enchere.setArticle(articleVendu);
		}
		
		if(rs.getInt("no_utilisateur") != 0) {
			//L'instance utilisateur r�cup�re les donn�es de utilisateurDAO
			utilisateur = utilisateurDAO.selectByNoUtilisateur(rs.getInt("no_utilisateur"));
			enchere.setUtilisateur(utilisateur);
		}
				
		return enchere;
	}

	
	@Override
	public List<Enchere> getListEnchere() throws BusinessException {
		List<Enchere> listeEcheres = new ArrayList<Enchere>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

		
			while (rs.next()) {
				listeEcheres.add(enchereBuilder(rs));
				}
				

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur getListEnchere DAO");
			throw be;
		}

		return listeEcheres;
	}
	
	
	
	@Override
	public List<Enchere> getListByNoUtilisateur(int no_utilisateur) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL_BY_UTILISATEUR);
			stmt.setInt(1, no_utilisateur);;
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				listeEnchere.add(enchereBuilder(rs));
				}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur getListByNoUtilisateur DAO");
			throw be;
		}

		return listeEnchere;
	}
	
	
	
}
