package fr.eni.projettroc.dao;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.exception.BusinessException;




public class CategorieJDBCImpl implements CategorieDAO{
	
	private static final String SELECT_ALL = "SELECT `no_categorie`,`libelle` FROM `categories`;";

	private Categorie categorieBuilder(ResultSet rs) throws SQLException {
		Categorie categorie = new Categorie();
		categorie.setNo_categorie(rs.getInt("id_article"));
		categorie.setLibelle(rs.getString("date_enchere"));
		return categorie;
	}
	
	@Override
	public List<Categorie> getListCategories() throws BusinessException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			// Parcours la liste des enregistrements, et rassembler par id du repas
			Categorie categorieCourante = new Categorie();
			while (rs.next()) {
				categorieCourante = categorieBuilder(rs);
				listeCategorie.add(categorieCourante);
				}
				

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Connexion impossible");
			throw be;
		}

		return listeCategorie;
	}
	
	
	
}
