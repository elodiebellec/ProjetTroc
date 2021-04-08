package fr.eni.projettroc.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projettroc.bll.ArticleVenduManager;
import fr.eni.projettroc.bll.CategorieManager;
import fr.eni.projettroc.bll.EnchereManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Categorie;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class AccueilUtilisateur
 */
@WebServlet("/AccueilUtilisateur")
public class AccueilUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public List<Categorie> listeCategories;
	public List<ArticleVendu> listeArticlesUtilisateur;
	public List<ArticleVendu> listeArticleCategorie;
	public List<ArticleVendu> listeArticleParNom;
	public List<ArticleVendu> listeArticle;
	public List<ArticleVendu> listeArticleFiltree;
	public List<Enchere> listeEnchereFiltree;
	public List<Enchere> listeEnchereUtilisateur;
	public List<Enchere> listeEnchereRemportee;
	public List<ArticleVendu> listeArticleRemportes;

	public int idUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilUtilisateurServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {

			// Afficher la liste des catégorie
			listeCategories = CategorieManager.getCategorieManager().toutesLesCategories();
			session.setAttribute("listeCategories", listeCategories);
			// Répérer la liste de par défaut de toutes les enchères ouvertes
			listeArticle = ArticleVenduManager.getArticleVenduManager().listeArticles();
			listeArticleFiltree = ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(listeArticle, "");


			session.setAttribute("listeArticles", listeArticleFiltree);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/accueilUtilisateur.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();


		// Récupérer l'ID de l'utilisateur connecté (si déconnecté on le renvoie sur la
		// page d'accueil)
		if (session.getAttribute("idUser") != null) {
			idUser = (int) session.getAttribute("idUser");
			System.out.println("idUser : " + idUser);
		} else {
			idUser = 0;
			System.out.println("Utilisateur déconnecté");
		}


		// Récupérer le type de transaction à afficher

		String typeTransaction = request.getParameter("typeTransaction");
		request.setAttribute("typeTransaction", typeTransaction);
		System.out.println("type transaction : " + typeTransaction);


		// Récupérer les valeurs des ckeckbox

		// Checkbox achats
		String checkboxAchat = (request.getParameter("encoursEnchere") + "_"
				+ request.getParameter("enchereUtilisateur") + "_" + request.getParameter("enchereRemportee"))
						.toUpperCase();
		System.out.println("checkboxAchat : " + checkboxAchat);
		// Checkbox ventes
		String checkboxVente = (request.getParameter("encoursVente") + "_" + request.getParameter("futureVente") + "_"
				+ request.getParameter("venteTerminee")).toUpperCase();
		System.out.println("checkboxVente : " + checkboxVente);

		// Afficher les articles vendus par catégorie

		// Récupérer le numéro de catégories sélectionnées


		String categorieSelect = request.getParameter("categorieSelect");
		int numCategorie = Integer.parseInt(categorieSelect);
		System.out.println("catégorie : " + categorieSelect);


		// Récupérer le nom de l'article filtré

		String nomSelect = request.getParameter("nomSelect");
		System.out.println("nom article : " + nomSelect);

		try {

			// -------------- filtre par type de transaction----------------//
			// ---------------------ACHATS-----------------------------------//
			if ("achat".equals(typeTransaction)) {

				// filtrer les enchères par période (checkbox)
				if ("ENCOURS_NULL_NULL".equals(checkboxAchat) || "ENCOURS_ENCOURS_NULL".equals(checkboxAchat)) {
					//Afficher tous les articles en cours
					listeArticleFiltree =  ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(ArticleVenduManager.getArticleVenduManager().listeArticles(), checkboxAchat);
					
				} else if ("ENCOURS_NULL_REMPORTEE".equals(checkboxAchat) || "ENCOURS_ENCOURS_REMPORTEE".equals(checkboxAchat) || "NULL_NULL_NULL".equals(checkboxAchat)) {
					//Afficher tous les articles en cours
					listeArticleFiltree =  ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(ArticleVenduManager.getArticleVenduManager().listeArticles(), "ENCOURS_NULL_NULL");
					// Toutes les encheres remportées
					listeEnchereRemportee = EnchereManager.getEnchereManager().encheresRemporteesParUtilisateur(idUser);
					listeArticlesUtilisateur = EnchereManager.getEnchereManager().ArticlesdeListeEncheres(listeEnchereRemportee);
					// Additionner les deux listes
					listeArticleFiltree.addAll(listeArticlesUtilisateur);
				} else if ("NULL_ENCOURS_REMPORTEE".equals(checkboxAchat)) {
					// les encheres encours de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager().toutesLesEncheresUniquesParUtilisateur(idUser);
					// Récupérer la liste des articles correspondants à la liste des enchères
					listeArticleFiltree = ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(EnchereManager.getEnchereManager().ArticlesdeListeEncheres(listeEnchereFiltree), "NULL_ENCOURS_NULL");
					// Toutes les encheres remportées
					listeEnchereRemportee = EnchereManager.getEnchereManager().encheresRemporteesParUtilisateur(idUser);
					listeArticlesUtilisateur = EnchereManager.getEnchereManager().ArticlesdeListeEncheres(listeEnchereRemportee);
					// Additionner les deux listes
					listeArticleFiltree.addAll(listeArticlesUtilisateur);
				} else if ("NULL_NULL_REMPORTEE".equals(checkboxAchat)) {
					// toutes les encheres encours + les encheres passées de l'utilisateur
					listeEnchereRemportee = EnchereManager.getEnchereManager().encheresRemporteesParUtilisateur(idUser);
					listeArticleFiltree = EnchereManager.getEnchereManager().ArticlesdeListeEncheres(listeEnchereRemportee);
				} else {
					// les encheres encours de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager().toutesLesEncheresUniquesParUtilisateur(idUser);
					// Récupérer la liste des articles correspondants à la liste des enchères
					listeArticleFiltree = ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(EnchereManager.getEnchereManager().ArticlesdeListeEncheres(listeEnchereFiltree), checkboxAchat);
				} 

				

			} else {
				// ---------------------VENTES-----------------------------------//
				listeArticlesUtilisateur = ArticleVenduManager.getArticleVenduManager()
						.listeArticlesParNoUtilisateur(idUser);
				// filtrer les articles par période (checkbox)
				listeArticleFiltree =  ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(listeArticlesUtilisateur, checkboxVente);

			}

			// -------------- filtre par catégorie -------------------------//
			session.getAttribute("listeCategories");
			// On récupère le libellé de la catégorie avec le numéro de catégorie du
			// formulaire.
			// Si l'utilisateur sélectionne toutes les catégories, pas de filtre par
			// catégorie.
			if ("Toutes".equals(CategorieManager.getCategorieManager().categorieParNumero(numCategorie).getLibelle())) {
				listeArticleCategorie = listeArticleFiltree;
			} else {
				listeArticleCategorie = ArticleVenduManager.getArticleVenduManager()
						.listeArticlesParCategorie(listeArticleFiltree, numCategorie);
			}
			// -------------- filtre par npm d'objet -------------------------//
			listeArticleParNom = ArticleVenduManager.getArticleVenduManager().listeArticlesParNom(listeArticleCategorie,
					nomSelect);
			session.setAttribute("listeArticles", listeArticleParNom);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/accueilUtilisateur.jsp").forward(request, response);

	}

}
