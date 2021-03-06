package fr.eni.projettroc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.projettroc.bll.ArticleVenduManager;
import fr.eni.projettroc.bll.CategorieManager;
import fr.eni.projettroc.bll.RetraitManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Retrait;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class VendreUnArticleServlet
 */
@WebServlet("/VendreUnArticle")
public class VendreUnArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Categorie> listeCategories;
		
		
		try {
			// Afficher la liste des catégories
			listeCategories = CategorieManager.getCategorieManager().toutesLesCategories();
			request.setAttribute("listeCategories", listeCategories);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 //Récupérer les données du formulaire
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
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
			ArticleVendu articleVendu = ArticleVenduManager.getArticleVenduManager().valideAjoutArticle(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,  no_categorie, no_user);

			//injecter le retrait dans la base de données
			Retrait retrait = RetraitManager.getRetraitManager().valideAjoutRetrait(articleVendu, rue, codePostal, ville);

			
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
