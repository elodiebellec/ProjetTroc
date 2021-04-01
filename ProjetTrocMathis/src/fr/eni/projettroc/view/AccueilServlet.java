package fr.eni.projettroc.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projettroc.bll.ArticleVenduManager;
import fr.eni.projettroc.bll.CategorieManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;

/**
 * Servlet implementation class accueil
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			List<Categorie> listeCategories;
			List<ArticleVendu> listeArticleVendu;
			ArticleVendu articleAffiche;
			try {
				//Afficher la liste des catégorie
				listeCategories = CategorieManager.getCategorieManager().toutesLesCategorie();				
				request.setAttribute("listeCategories", listeCategories);
				//Récupérer une liste des articles en vente
				listeArticleVendu = ArticleVenduManager.getArticleVenduManager().tousLesArticles();				
				request.setAttribute("listeArticleVendu", listeArticleVendu);
				//Afficher les articles vendus
				
			} catch (fr.eni.projettroc.exception.BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
