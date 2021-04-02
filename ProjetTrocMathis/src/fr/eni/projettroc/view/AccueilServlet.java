package fr.eni.projettroc.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.CastExpression;

import fr.eni.projettroc.bll.ArticleVenduManager;
import fr.eni.projettroc.bll.CategorieManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class accueil
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public List<Categorie> listeCategories; 
	public List<ArticleVendu> listeArticleVendu;
	public List<ArticleVendu> listeArticleFiltree;
	public List<ArticleVendu> listeArticleParNom;
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
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
		
		try {
			
			//Afficher la liste des catégorie
			listeCategories = CategorieManager.getCategorieManager().toutesLesCategorie();
			session.setAttribute("listeCategories", listeCategories);
			//Afficher articles vendus
			listeArticleVendu = ArticleVenduManager.getArticleVenduManager().listeArticles();
			session.setAttribute("listeArticleVendu", listeArticleVendu);
			//Récupérer une liste des articles par catégorie				
			//listeArticleVendu = ArticleVenduManager.getArticleVenduManager().listeArticlesParCategorie(1);				
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
		//Récupérer le numéro de catégorie sélectionnée
		String categorieSelect = request.getParameter("categorieSelect");
		int numCategorie = Integer.parseInt(categorieSelect);
		System.out.println(numCategorie);
		//Récupérer le nom de l'article filtré
		String nomSelect = request.getParameter("nomSelect");
		System.out.println(nomSelect);
		//Afficher les articles vendus par catégorie
		try {
			listeArticleFiltree = ArticleVenduManager.getArticleVenduManager().listeArticlesParCategorie(numCategorie);
			listeArticleParNom = ArticleVenduManager.getArticleVenduManager().listeArticlesParNom(listeArticleFiltree, nomSelect);
			session.setAttribute("listeArticleVendu", listeArticleParNom);
			listeArticleVendu = ArticleVenduManager.getArticleVenduManager().listeArticles();
			session.setAttribute("listeCategories", listeCategories);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
		
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		
	
	}

}
