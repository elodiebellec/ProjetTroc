package fr.eni.projettroc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.exception.BusinessException;




public class EnchereJDBCImpl implements EnchereDAO{
	
	private static final String SELECT_ALL = "SELECT `no_enchere`,`date_enchere`,`montant_enchere`,`no_article`,`no_utilisateur` FROM `encheres`;";

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
	
	
	
}
