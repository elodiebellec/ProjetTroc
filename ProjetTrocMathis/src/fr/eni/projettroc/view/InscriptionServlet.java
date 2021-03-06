package fr.eni.projettroc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projettroc.bll.UtilisateurManager;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		request.getRequestDispatcher("/WEB-INF/pageInscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
			    String pseudo = request.getParameter("pseudo");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String code_postal = request.getParameter("codepostal");
				String ville = request.getParameter("ville");
				String mot_de_passe = request.getParameter("motdepasse");
				String mot_de_passe_confirmation = request.getParameter("motdepasseconfirmation");
				int credit =100;
	try {
		Utilisateur u = UtilisateurManager.getUtilisateursManager().validerAjoutPersonne( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,mot_de_passe_confirmation, credit);
		 int userId = u.getNo_utilisateur();
		 String mdpId = mot_de_passe;
		 System.out.println(userId);
		HttpSession session = request.getSession();
		    session.setAttribute("idMdp", mdpId);
		   session.setAttribute("idUser", userId);
		   session.setAttribute("user",u);
		request.getRequestDispatcher("/WEB-INF/accueilUtilisateur.jsp").forward(request, response);
		   } catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("errors", e.getErrors());
			request.getRequestDispatcher("/WEB-INF/pageInscription.jsp").forward(request, response);
		}
	}
	
}


