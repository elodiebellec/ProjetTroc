package fr.eni.projettroc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projettroc.bll.ArticleVenduManager;
import fr.eni.projettroc.bll.RetraitManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Retrait;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class ModifierUnArticle
 */
@WebServlet("/ModifierUnArticle")
public class ModifierUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUnArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int numerodelarticle = Integer.valueOf(request.getParameter("iddelarticle"));
		
		HttpSession session = request.getSession();
		session.setAttribute("iddelarticle", numerodelarticle);
		request.getRequestDispatcher("WEB-INF/modifierUnArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.getAttribute("iddelarticle");	
		int no_article = (int) session.getAttribute("iddelarticle");	
        int no_user = (int) session.getAttribute("idUser");
        String nom_article = request.getParameter("nomArticle");
        String description = request.getParameter("description"); 
        int  no_categorie = Integer.parseInt(request.getParameter("categorie")); 
        int prix_initial = Integer.parseInt(request.getParameter("prixInitial"));
        String dateDebutEncheres = request.getParameter("dateDebutEncheres");
        String dateFinEncheres = request.getParameter("dateFinEncheres");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date_debut_encheres = LocalDate.parse(dateDebutEncheres, formatter);
        LocalDate date_fin_encheres  = LocalDate.parse(dateFinEncheres, formatter);
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("codePostal");
        String ville = request.getParameter("ville");
    
        try {
        	
        	//injecter l'article dans la base de données
			ArticleVenduManager.getArticleVenduManager().modifierArticles(nom_article, description, date_debut_encheres, 
					date_fin_encheres, prix_initial, no_categorie, no_article);
			
			//injecter le retrait dans la base de données
			/*Retrait retrait = RetraitManager.getRetraitManager().valideAjoutRetrait(articleVendu, rue, codePostal, ville);*/

			
			 request.getRequestDispatcher("/WEB-INF/accueilUtilisateur.jsp").forward(request, response);
			
		//	request.getRequestDispatcher("/DetailVente?param=" + articleVendu.getNo_article()).forward(request, response);
		
			
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errors", e.getErrors());
			request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
		}

	}

}