package fr.eni.projettroc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.exception.BusinessException;
import fr.eni.projettroc.exception.Errors;




public class EnchereJDBCImpl implements EnchereDAO{
	
	private static final String SELECT_ALL = "SELECT `no_enchere`,`date_enchere`,`montant_enchere`,`no_article`,`no_utilisateur` FROM `encheres`;";
    private static final String INSERT = "insert into encheres (no_enchere, date_enchere, montant_enchere, no_article, no_utlisateur) values(?,?,?,?,?)";
	
    
    private Enchere enchereBuilder(ResultSet rs) throws SQLException {
		Enchere enchere = new Enchere();
		enchere.setNo_enchere(rs.getInt("no_enchere"));
		enchere.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
		enchere.setMontant_enchere(rs.getInt("montant_enchere"));
		enchere.setNoArticle(rs.getInt("no_article"));		
		//enchere.setUtilisateur(rs.getInt("no_utilisateur"));
		return enchere;
	}
	
	@Override
	public List<Enchere> getListEnchere() throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			// Parcours la liste des enregistrements, et rassembler par id du repas
			Enchere enchereCourante = new Enchere();
			while (rs.next()) {
					enchereCourante = enchereBuilder(rs);
					listeEncheres.add(enchereCourante);
				}
				

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Connexion impossible");
			throw be;
		}

		return listeEncheres;
	}
	
	
	public void insert(Enchere enchere) throws BusinessException{
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setDate(1,java.sql.Date.valueOf(enchere.getDate_enchere()));
			requete.setInt(2, enchere.getMontant_enchere());
			requete.setInt(3, enchere.getArticle().getNo_article());
			requete.setInt(4, enchere.getUtilisateur().getNo_utilisateur());
			requete.executeUpdate();
		ResultSet rs = requete.getGeneratedKeys();
		if (rs.next()) {
			enchere.setNo_enchere(rs.getInt(1));
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
	
}
