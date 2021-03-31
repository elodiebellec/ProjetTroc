package fr.eni.projettroc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projettroc.bll.UtilisateurManager;
import fr.eni.projettroc.bo.Utilisateur;

/**
 * Servlet implementation class AffichageUtilisateurServlet
 */
@WebServlet("/AffichageUtilisateur")
public class AffichageUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichageUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/accueilUtilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String personne = request.getParameter("psd");
		
		try {
			Utilisateur u = UtilisateurManager.getUtilisateursManager().afficherPersonne(personne);
		    request.setAttribute("users", u);
		    request.getRequestDispatcher("WEB-INF/affichageUtilisateur.jsp").forward(request, response);
		}catch(Exception e) {
			
		}
	}

}
