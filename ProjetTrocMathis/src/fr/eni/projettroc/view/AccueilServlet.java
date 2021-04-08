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
import fr.eni.projettroc.bll.EnchereManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class accueil
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public List<Categorie> listeCategories;
	public List<ArticleVendu> listeArticles;
	public List<ArticleVendu> listeArticleEnCours;
	public List<ArticleVendu> listeArticleCategorie;
	public List<ArticleVendu> listeArticleParNom;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {

			// Afficher la liste des cat�gorie
			listeCategories = CategorieManager.getCategorieManager().toutesLesCategories();
			session.setAttribute("listeCategories", listeCategories);
			//R�cup�rer la liste des article en cours de vente
			listeArticles = ArticleVenduManager.getArticleVenduManager().listeArticles();
			listeArticleEnCours = ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(listeArticles, "");
			session.setAttribute("listeArticleEnCours", listeArticleEnCours);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// R�cup�rer le num�ro de cat�gorie s�lectionn�e
		String categorieSelect = request.getParameter("categorieSelect");
		int numCategorie = Integer.parseInt(categorieSelect);
		System.out.println(categorieSelect);

		// R�cup�rer le nom de l'article filtr�
		String nomSelect = request.getParameter("nomSelect");
				
		System.out.println(nomSelect);
		// Afficher les articles vendus par cat�gorie
		try {
			listeArticleEnCours = ArticleVenduManager.getArticleVenduManager().listeArticles();
			// Si l'utilisateur s�lectionne toutes les cat�gories, pas de filtre par
			// cat�gorie
			// On r�cup�re le libell� de la cat�gorie avec le num�ro de cat�gorie du
			// formulaire
			if ("Toutes".equals(CategorieManager.getCategorieManager().categorieParNumero(numCategorie).getLibelle())) {				
				listeArticleCategorie = listeArticleEnCours;
			} else {
				listeArticleCategorie = ArticleVenduManager.getArticleVenduManager()
						.listeArticlesParCategorie(ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(listeArticleEnCours, ""), numCategorie);
			}
			listeArticleParNom = ArticleVenduManager.getArticleVenduManager().listeArticlesParNom(listeArticleCategorie,
					nomSelect);
			session.setAttribute("listeArticleEnCours", listeArticleParNom);
			
			session.getAttribute("listeCategories");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

	}

}
