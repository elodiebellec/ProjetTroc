package fr.eni.projettroc.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.ConnectionProvider;
import fr.eni.projettroc.exception.BusinessException;


public class UtilisateurJDBCImpl implements UtilisateurDAO{
	
	private static final String CONNECTION = "select pseudo, mot_de_passe, nom, prenom, email, telephone,"
			+ " rue, code_postal, ville, credit from utilisateurs where pseudo=? and mot_de_passe=? or email=? and mot_de_passe=?";
	private static final String INSERT = "insert into utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,administrateur) values(?,?,?,?,?,?,?,?,?,?)";
	public Utilisateur find(String pseudo, String mot_de_passe, String email) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(CONNECTION);
			pstmt.setString(1,pseudo);
			pstmt.setString(2,mot_de_passe);
			pstmt.setString(3, email);
			pstmt.setString(4, mot_de_passe);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Utilisateur u = new Utilisateur();
				u.setPseudo(rs.getString("pseudo"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				u.setTelephone(rs.getString("telephone"));
				u.setRue(rs.getString("rue"));
				u.setCode_postal(rs.getString("code_postal"));
				u.setVille(rs.getString("ville"));
				u.setCredit(rs.getInt("credit"));
				return u;
			}else {
				//Utilisateur non trouvé
				BusinessException be = new BusinessException();
				be.addError("Pseudo ou Mot de passe inconnu");
				throw be;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERROR DB - " + e.getMessage());
			throw be;
		}

	}
	
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException{
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement requete = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, utilisateur.getPseudo());
			requete.setString(2, utilisateur.getNom());
			requete.setString(3, utilisateur.getPrenom());
			requete.setString(4, utilisateur.getEmail());
			requete.setString(5, utilisateur.getTelephone());
			requete.setString(6, utilisateur.getRue());
			requete.setString(7, utilisateur.getCode_postal());
			requete.setString(8, utilisateur.getVille());
			requete.setString(9, utilisateur.getMot_de_passe());
			requete.setInt(10, 1);
			requete.executeUpdate();
			ResultSet rs = requete.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setNo_utilisateur(rs.getInt(1));
			}
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.addError("erreur");
				throw be;
			}

	}
 }

