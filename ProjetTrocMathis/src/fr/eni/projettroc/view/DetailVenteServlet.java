package fr.eni.projettroc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projettroc.bll.ArticleVenduManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no_article = Integer.parseInt(request.getParameter("param")) ;
		
		try {
			ArticleVendu article = ArticleVenduManager.getArticleVenduManager().recupererArticle(no_article);
			request.setAttribute("articlejsp", article);
			
			// récupérer l'utisateur de l'article
			Utilisateur utilisateurArticle = article.getUtilisateur();
			
			//récupérer l'utilisateur connecté (en session)
			Utilisateur utilisateurConnecte =(Utilisateur) request.getSession().getAttribute("user");
			
			// faire test : boolean , Si true = proprietaire, si false=connecte
			
			boolean isProprietaireArticle = utilisateurArticle.getNo_utilisateur() == utilisateurConnecte.getNo_utilisateur();
	
			// on le passe à la jsp
			
			request.setAttribute("isProprietaireArticle",  isProprietaireArticle);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		
	        
	        
	        
	        doGet(request, response);
	}

}
