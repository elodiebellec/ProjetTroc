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
	public List<ArticleVendu> listeArticleFiltree;
	public List<Enchere> listeEnchereFiltree;
	public List<Enchere> listeEnchereUtilisateur;

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

			// Afficher la liste des cat�gorie
			listeCategories = CategorieManager.getCategorieManager().toutesLesCategories();
			session.setAttribute("listeCategories", listeCategories);
			// Récupérer la liste des enchères ouvertes de l'utilisateur
			listeArticleFiltree = ArticleVenduManager.getArticleVenduManager()
					.listeArticleParPeriode(ArticleVenduManager.getArticleVenduManager().listeArticles(), "");
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

		// R�cup�rer l'ID de l'utilisateur connect� (si d�connect�, on le renvoie sur la
		// page d'accueil)
		if (session.getAttribute("idUser") != null) {
			idUser = (int) session.getAttribute("idUser");
			System.out.println("idUser : " + idUser);
		} else {
			idUser = 0;
			System.out.println("Utilisateur d�connect�");
		}

		// R�cup�rer le type de transaction � afficher
		String typeTransaction = request.getParameter("typeTransaction");
		request.setAttribute("typeTransaction", typeTransaction);
		System.out.println("type transaction : " + typeTransaction);

		// R�cup�rer les valeurs des ckeckbox
		// Checkbox achats
		String checkboxAchat = (request.getParameter("encoursEnchere") + "_"
				+ request.getParameter("enchereUtilisateur") + "_" + request.getParameter("enchereRemportee"))
						.toUpperCase();
		System.out.println("checkboxAchat : " + checkboxAchat);
		// Checkbox ventes
		String checkboxVente = (request.getParameter("encoursVente") + "_" + request.getParameter("futureVente") + "_"
				+ request.getParameter("venteTerminee")).toUpperCase();
		System.out.println("checkboxVente : " + checkboxVente);
		// Afficher les articles vendus par cat�gorie

		// R�cup�rer le num�ro de cat�gorie s�lectionn�e
		String categorieSelect = request.getParameter("categorieSelect");
		int numCategorie = Integer.parseInt(categorieSelect);
		System.out.println("cat�gorie : " + categorieSelect);

		// R�cup�rer le nom de l'article filtr�
		String nomSelect = request.getParameter("nomSelect");
		System.out.println("nom article : " + nomSelect);

		try {

			// -------------- filtre par type de transaction----------------//
			// ---------------------ACHATS-----------------------------------//
			if ("achat".equals(typeTransaction)) {
				// filtrer les ench�res par p�riode (checkbox)
				if ("ENCOURSENCHERE_NULL_NULL".equals(checkboxAchat)
						|| "ENCOURSENCHERE_ENCHEREUTILISATEUR_NULL".equals(checkboxAchat)) {
					// toutes les encheres encours
					listeEnchereFiltree = EnchereManager.getEnchereManager()
							.listeEnchereParPeriode(EnchereManager.getEnchereManager().toutesLesEncheres(), "EN_COURS");
				} else if ("NULL_ENCHEREUTILISATEUR_ENCHEREREMPORTEE".equals(checkboxAchat)) {
					// les encheres encours et pass�es de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager().listeEnchereParPeriode(
							EnchereManager.getEnchereManager().toutesLesEncheresParUtilisateur(idUser), "TOUTES");
				} else if ("NULL_ENCHEREUTILISATEUR_NULL".equals(checkboxAchat)) {
					// les encheres encours de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager().listeEnchereParPeriode(
							EnchereManager.getEnchereManager().toutesLesEncheresParUtilisateur(idUser), "EN_COURS");
				} else if ("NULL_NULL_ENCHEREREMPORTEE".equals(checkboxAchat)) {
					// les encheres pass�es de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager().listeEnchereParPeriode(
							EnchereManager.getEnchereManager().toutesLesEncheresParUtilisateur(idUser), "APRES_FIN");
				} else {
					// toutes les encheres encours + les encheres pass�es de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager()
							.listeEnchereParPeriode(EnchereManager.getEnchereManager().toutesLesEncheres(), "EN_COURS");
					listeEnchereUtilisateur = EnchereManager.getEnchereManager().listeEnchereParPeriode(
							EnchereManager.getEnchereManager().toutesLesEncheresParUtilisateur(idUser), "APRES_FIN");
					listeEnchereFiltree.addAll(listeEnchereUtilisateur);
				}

				// R�cup�rer la liste des articles correspondants � la liste des ench�res
				listeArticleFiltree = EnchereManager.getEnchereManager().ArticlesdeListeEncheres(listeEnchereFiltree);
			} else {
				// ---------------------VENTES-----------------------------------//
				listeArticlesUtilisateur = ArticleVenduManager.getArticleVenduManager()
						.listeArticlesParNoUtilisateur(idUser);
				// filtrer les articles par p�riode (checkbox)
				listeArticleFiltree =  ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(listeArticlesUtilisateur, checkboxVente);

			}

			// -------------- filtre par cat�gorie -------------------------//
			session.getAttribute("listeCategories");
			// On r�cup�re le libell� de la cat�gorie avec le num�ro de cat�gorie du
			// formulaire.
			// Si l'utilisateur s�lectionne toutes les cat�gories, pas de filtre par
			// cat�gorie.
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
