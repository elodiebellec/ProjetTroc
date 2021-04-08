package fr.eni.projettroc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Retrait;
import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.exception.Errors;

public class RetraitJDBCImpl implements RetraitDAO {

	private static final String SELECT_BY_NO_ARTICLE = "SELECT * FROM `retraits` WHERE `no_article`=?";
	private static final String INSERT = "INSERT INTO `retraits`(`no_article`, `rue`, `code_postal`, `ville`) VALUES (?,?,?,?)";
	private static final String DELETE_RETRAIT = "DELETE FROM `retraits` WHERE `no_article` =?";
	private static final String UPDATE_RETRAIT = "UPDATE `retraits` SET `rue`=?,`code_postal`=?,`ville`=? WHERE `no_article` =?";

	@Override
	public void insertRetrait(Retrait retrait) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(INSERT);
			
			requete.setInt(1, retrait.getArticle().getNo_article());
			requete.setString(2, retrait.getRue());
			requete.setString(3, retrait.getCode_postal());
			requete.setString(4, retrait.getVille());
			requete.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("erreur");
			throw be;
		}
	
	}

	
	@Override
	public void deleteRetrait(int no_article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(DELETE_RETRAIT);
			stmt.setInt(1, no_article);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError(Errors.SUPPRESSION_RETRAIT_ERREUR);
			throw be;
		}
		
	}
	
	
	
	@Override
	public void updateRetrait(Retrait retrait) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement requete = cnx.prepareStatement(UPDATE_RETRAIT);       	
			requete.setString(1, retrait.getRue());
			requete.setString(2, retrait.getCode_postal());
			requete.setString(3, retrait.getVille());
			requete.setInt(4, retrait.getArticle().getNo_article());
            
            requete.executeUpdate();
        }catch (Exception e){
  
            throw new BusinessException();
        }
	
	}
	

	@Override
	public Retrait getByArticleId(int no_article) throws BusinessException {

		Retrait retrait = null;
		// Connexion � la base de donn�es et try pour que la connexion se ferme
		// automatiquement.
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// requete
			PreparedStatement requete = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
			// on s�lectionne le retrait dont le num�ro est renseign� dans la requ�te
			requete.setInt(1, no_article);
			ResultSet rs = requete.executeQuery();

			if (rs.next()) {
				retrait = retraitBuilder(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur getByArticleId");
			throw be;
		}
		// Je retourne le retrait s�lectionn�
		return retrait;

	}

	private Retrait retraitBuilder(ResultSet rs) throws SQLException, BusinessException {
		Retrait retrait = new Retrait();

		ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
		ArticleVendu articleVendu = new ArticleVendu();

		if (rs.getInt("no_article") != 0) {
			articleVendu = articleDAO.selectByNoArticle(rs.getInt("no_article"));
			retrait.setArticle(articleVendu);

			retrait.setRue(rs.getString("rue"));
			retrait.setCode_postal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
		}
		return retrait;
	}

	

}
