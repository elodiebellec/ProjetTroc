package fr.eni.projettroc.dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.exception.BusinessException;




public class CategorieJDBCImpl implements CategorieDAO{
	
	private static final String SELECT_ALL = "SELECT `no_categorie`,`libelle` FROM `categories`";
	private static final String SELECT_BY_NO = "SELECT `no_categorie`,`libelle` FROM `categories` WHERE `no_categorie`=?";

	private Categorie categorieBuilder(ResultSet rs) throws SQLException {
		Categorie categorie = new Categorie();
		categorie.setNo_categorie(rs.getInt("no_categorie"));
		categorie.setLibelle(rs.getString("libelle"));
		return categorie;
	}
	
	@Override
	public List<Categorie> getListCategories() throws BusinessException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

		
			while (rs.next()) {
				listeCategorie.add(categorieBuilder(rs));
				}
				

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur getListCategories DAO");
			throw be;
		}

		return listeCategorie;
	}
	

	
	@Override
	public Categorie selectByNoCategorie (int no_categorie) throws BusinessException {
		Categorie categorie = null;
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try (Connection cnx = ConnectionProvider.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement(SELECT_BY_NO);
			// on sélectionne l'utilisateur dont le numéro est renseigné dans la requête
			requete.setInt(1, no_categorie);
			ResultSet rs = requete.executeQuery();

			if (rs.next()) {
				//appelle de la fonction utilisateurBuilder qui permet de récupérer toutes les colonnes de la table utilisateur
				categorie = categorieBuilder(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur selectByNoCategorie");
			throw be;
		}
		//Je retourne l'utilisateur sélectionné
		return categorie;
	}
	
	
	
}
