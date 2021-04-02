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

import org.apache.catalina.User;

import fr.eni.projettroc.bll.ArticleVenduManager;
import fr.eni.projettroc.bll.CategorieManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
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
		 //R�cup�rer les donn�es du formulaire
        request.setCharacterEncoding("UTF-8");
        String nomArticle = request.getParameter("nomArticle");
        System.out.println(nomArticle);
        String description = request.getParameter("description");
        System.out.println(description);
       int  no_categorie = Integer.parseInt(request.getParameter("categorie"));
        System.out.println(no_categorie);
        int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
        System.out.println(prixInitial);
        String dateDebutEncheres = request.getParameter("dateDebutEncheres");
        System.out.println(dateDebutEncheres);
        String dateFinEncheres = request.getParameter("dateFinEncheres");
        System.out.println(dateFinEncheres);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateDebutEncheres = LocalDate.parse(dateDebutEncheres, formatter);
        LocalDate localDateFinEncheres  = LocalDate.parse(dateFinEncheres, formatter);
        
        // Mettre dans la BDD
        ArticleVendu articleVendu = new ArticleVendu();
        Categorie categorie = new Categorie();
        categorie.setNo_categorie(no_categorie);
        
        articleVendu.setNom_article(nomArticle);
        articleVendu.setDescription(description);
        articleVendu.setCategorie(categorie);
        articleVendu.setPrix_initial(prixInitial);
        
        articleVendu.setDate_debut_encheres(localDateDebutEncheres);
        articleVendu.setDate_fin_encheres(localDateFinEncheres);
      
        
        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        articleVendu.setUtilisateur(user);
      
       
     
       	
		try {
			ArticleVenduManager.getArticleVenduManager().insererArticle(articleVendu);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
