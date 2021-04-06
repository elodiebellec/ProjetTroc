package fr.eni.projettroc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			
			// rÃ©cupÃ©rer l'utisateur de l'article
			Utilisateur utilisateurArticle = article.getUtilisateur();
			
			//rÃ©cupÃ©rer l'utilisateur connectÃ© (en session)
			Utilisateur utilisateurConnecte =(Utilisateur) request.getSession().getAttribute("user");
			
			// faire test : boolean , Si true = proprietaire, si false=connecte
			
			boolean isProprietaireArticle = utilisateurArticle.getNo_utilisateur() == utilisateurConnecte.getNo_utilisateur();
	
			// on le passe Ã  la jsp
			
			request.setAttribute("isProprietaireArticle",  isProprietaireArticle);
			
			
			// Comparaison des dates d'enchères
			
			LocalDate date_debut_encheres = article.getDate_debut_encheres();
			LocalDate date_jour = LocalDate.now();
			
			
			boolean isDateOk = date_debut_encheres.isAfter(date_jour);
				
			
			request.setAttribute("isDateModifiable", isDateOk );
			
			
			
			
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