package fr.eni.projettroc.view;

import java.io.IOException;
import java.time.LocalDate;
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
			// Afficher la liste des catÃ©gories
			listeCategories = CategorieManager.getCategorieManager().toutesLesCategorie();
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
        String nomArticle = request.getParameter("nomArticle");
        System.out.println(nomArticle);
        String description = request.getParameter("description");
        System.out.println(description);
      //  String categorie = request.getParameter("categorie");
      //  System.out.println(categorie);
         int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
        System.out.println(prixInitial);
        LocalDate dateDebutEncheres = (LocalDate) request.getAttribute("dateDebutEncheres");
        System.out.println(dateDebutEncheres);
        LocalDate dateFinEncheres = (LocalDate) request.getAttribute("dateFinEncheres");
        System.out.println(dateFinEncheres);
        
        
        ArticleVendu articleVendu = new ArticleVendu();
        articleVendu.setNom_article(nomArticle);
        articleVendu.setDescription(description);
       // articleVendu.setCategorie(categorie);
        articleVendu.setPrix_initial(prixInitial);
        articleVendu.setDate_debut_encheres(dateDebutEncheres);
        articleVendu.setDate_fin_encheres(dateFinEncheres);
        
       		
	//	ArticleVendu articleVendu = ArticleVenduManager.getArticleVenduManager().insererArticle(articleVendu);
		
		
		//doGet(request, response);
	}

}
