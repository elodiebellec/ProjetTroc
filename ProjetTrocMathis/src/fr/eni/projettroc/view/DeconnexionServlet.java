package fr.eni.projettroc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fr.eni.projettroc.bo.Utilisateur;

/**
 * Servlet implementation class DeconnexionServlet
 */
@WebServlet("/Deconnexion")
public class DeconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Invalider la Session utilisateur
		HttpSession session = request.getSession();
		
		//Tracer l'utilisateur en session avant l'invalidation
		System.out.println("BEFORE");
		if(session.getAttribute("user")!= null) {			
			Utilisateur u = (Utilisateur) session.getAttribute("user");
			System.out.println(u);
		}
		
		
		session.invalidate();
		
		HttpSession sessionAfter = request.getSession();
		
		//Vérifier qu'il n'est plus présent après invalidation
		System.out.println("AFTER");
		if(sessionAfter.getAttribute("user")!= null) {			
			Utilisateur u = (Utilisateur) sessionAfter.getAttribute("user");
			System.out.println(u);
		
	
	}
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
  }
}
