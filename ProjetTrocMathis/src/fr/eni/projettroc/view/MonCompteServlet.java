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
 * Servlet implementation class MonCompte
 */
@WebServlet("/MonCompte")
public class MonCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int no_utilisateur;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonCompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/profilUtilisateur.jsp").forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	}


