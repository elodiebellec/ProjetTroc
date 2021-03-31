package fr.eni.projettroc.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.DALOAD;


import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.ConnectionProvider;
import fr.eni.projettroc.exception.BusinessException;
import sun.nio.ch.Util;


public class UtilisateurJDBCImpl implements UtilisateurDAO{
	
	private static final String CONNECTION = "select pseudo, mot_de_passe, nom, prenom, email, telephone,"
			+ " rue, code_postal, ville, credit from utilisateurs where pseudo=? and mot_de_passe=? or email=? and mot_de_passe=?";
	private static final String INSERT = "insert into utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT = "select * from utilisateurs where pseudo=?";
	
	
	public static Utilisateur utilisateurBuilder(ResultSet rs) throws Exception{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCode_postal(rs.getString("code_postal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setCredit(rs.getInt("credit"));
		return utilisateur;
		
	}

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
			requete.setInt(10, utilisateur.getCredit());
			requete.setInt(11, 1);
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
	
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException{
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
		PreparedStatement requete = cnx.prepareStatement(SELECT);
		requete.setString(1, pseudo);
		ResultSet rs = requete.executeQuery();
		
		while (rs.next()) {
			utilisateur = utilisateurBuilder(rs);
		}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("Erreur");
			throw be;
		}

		return utilisateur;
	}
}

