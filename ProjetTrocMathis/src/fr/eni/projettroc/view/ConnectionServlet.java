package fr.eni.projettroc.view;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projettroc.bll.UtilisateurManager;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.ConnectionProvider;
import fr.eni.projettroc.exception.BusinessException;



/**
 * Servlet implementation class pageConnection
 */
@WebServlet("/Connexion")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//testPoolConnection();
		request.getRequestDispatcher("./WEB-INF/pageConnexion.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		//Récupération de la saisie
		request.setCharacterEncoding("UTF-8");
		String pseudo = request.getParameter("login");
		String mot_de_passe = request.getParameter("mdp");
		String email = request.getParameter("login");
		String no_user = "5";
		
		//Appelle a la BLL
		try {
			Utilisateur u = UtilisateurManager.getUtilisateursManager().validerLaConnection(pseudo, mot_de_passe , email);
	        int  userId =  u.getNo_utilisateur();
			//Transmettre les informations pour la page de welcome
			HttpSession session = request.getSession();
			 session.setAttribute("idUser", userId);
			session.setAttribute("iduser2", no_user );
			session.setAttribute("user", u);
			request.getRequestDispatcher("/WEB-INF/profilUtilisateur.jsp").forward(request, resp);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("errors", e.getErrors());
			request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, resp);
		}
		/*user user = bypseudo(pseudo)
			user=    	user.getID*/
				
	}

	/**
	 * Méthode pour valider la configuration de la base de données
	 */
	private void testPoolConnection() {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			System.out.println("La connexion est " + (cnx.isClosed() ? "FERMEE" : "OUVERTE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
