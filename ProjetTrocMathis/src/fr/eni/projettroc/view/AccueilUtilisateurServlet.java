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
	public List<ArticleVendu> listeArticles;
	public List<ArticleVendu> listeArticleEnCours;
	public List<ArticleVendu> listeArticleCategorie;
	public List<ArticleVendu> listeArticleParNom;
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

			// Afficher la liste des catégorie
			listeCategories = CategorieManager.getCategorieManager().toutesLesCategories();
			session.setAttribute("listeCategories", listeCategories);
			// Récupérer la liste des enchères ouvertes de l'utilisateur
			listeArticles = ArticleVenduManager.getArticleVenduManager().listeArticles();
			listeArticleEnCours = ArticleVenduManager.getArticleVenduManager().listeArticleParPeriode(listeArticles,
					"");
			session.setAttribute("listeArticleEnCours", listeArticleEnCours);

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

		// Récupérer l'ID de l'utilisateur connecté (si déconnecté, on le renvoie sur la
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

		// Récupérer le numéro de catégorie sélectionnée
		String categorieSelect = request.getParameter("categorieSelect");
		int numCategorie = Integer.parseInt(categorieSelect);
		System.out.println("catégorie : " + categorieSelect);

		// Récupérer le nom de l'article filtré
		String nomSelect = request.getParameter("nomSelect");
		System.out.println("nom article : " + nomSelect);

		try {
			List<Enchere> listeEncheres = EnchereManager.getEnchereManager().toutesLesEncheres();
			List<Enchere> listeEncheresParUtilisateur = EnchereManager.getEnchereManager()
					.toutesLesEncheresParUtilisateur(idUser);
			// listeArticleEnCours =
			// ArticleVenduManager.getArticleVenduManager().listeArticles();
			// -------------- filtre par type de transaction----------------//
			if ("achat".equals(typeTransaction)) {
				// Liste d'enchères de l'utilisateur connecté
				// filtrer les enchères par période (checkbox)
				if ("ENCOURSENCHERE_NULL_NULL".equals(checkboxAchat)
						|| "ENCOURSENCHERE_ENCHEREUTILISATEUR_NULL".equals(checkboxAchat)) {
					// toutes les encheres encours
					listeEnchereFiltree = EnchereManager.getEnchereManager().listeEnchereParPeriode(listeEncheres,
							"EN_COURS");
				} else if ("NULL_ENCHEREUTILISATEUR_ENCHEREREMPORTEE".equals(checkboxAchat)) {
					// les encheres encours et passées de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager()
							.listeEnchereParPeriode(listeEncheresParUtilisateur, "TOUTES");
				} else if ("NULL_ENCHEREUTILISATEUR_NULL".equals(checkboxAchat)) {
					// les encheres encours de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager()
							.listeEnchereParPeriode(listeEncheresParUtilisateur, "EN_COURS");
				} else if ("NULL_NULL_ENCHEREREMPORTEE".equals(checkboxAchat)) {
					// les encheres passées de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager()
							.listeEnchereParPeriode(listeEncheresParUtilisateur, "APRES_FIN");
				} else {
					// toutes les encheres encours + les encheres passées de l'utilisateur
					listeEnchereFiltree = EnchereManager.getEnchereManager().listeEnchereParPeriode(listeEncheres,
							"EN_COURS");
					listeEnchereUtilisateur = EnchereManager.getEnchereManager()
							.listeEnchereParPeriode(listeEncheresParUtilisateur, "APRES_FIN");
					listeEnchereFiltree.addAll(listeEnchereUtilisateur);
				}

				// Récupérer la liste des articles correspondants à la liste des enchères
				listeArticles = EnchereManager.getEnchereManager().ArticlesdeListeEncheres(listeEnchereFiltree);
			}

			// -------------- filtre par catégorie -------------------------//
			session.getAttribute("listeCategories");
			// On récupère le libellé de la catégorie avec le numéro de catégorie du
			// formulaire.
			// Si l'utilisateur sélectionne toutes les catégories, pas de filtre par
			// catégorie.
			if ("Toutes".equals(CategorieManager.getCategorieManager().categorieParNumero(numCategorie).getLibelle())) {
				listeArticleCategorie = listeArticles;
			} else {
				listeArticleCategorie = ArticleVenduManager.getArticleVenduManager()
						.listeArticlesParCategorie(listeArticles, numCategorie);
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
