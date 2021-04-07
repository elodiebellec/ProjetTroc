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
import fr.eni.projettroc.bll.EnchereManager;
import fr.eni.projettroc.bo.ArticleVendu;
import fr.eni.projettroc.bo.Enchere;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.dao.EnchereDAO;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*public int no_article;*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int no_article = Integer.parseInt(request.getParameter("param"));
		session.setAttribute("iddelarticle", no_article);
		ArticleVendu art = null;
		
		try {
			art = ArticleVenduManager.getArticleVenduManager().recupererArticle(no_article);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    LocalDate finDeLenchere =  art.getDate_fin_encheres();
		LocalDate dateActuelle  = LocalDate.now();
		Utilisateur utilisateurensession = (Utilisateur) request.getSession().getAttribute("user");
		int utilisateuren = utilisateurensession.getNo_utilisateur();
		
		
		
		List<Enchere> encheremax = null;
		try {
			encheremax = EnchereManager.getEnchereManager().enchereParNum(no_article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Enchere en : encheremax) {
			int montantmax = en.getMontant_enchere();
			String usermax = en.getUtilisateur().getPseudo();
			request.setAttribute("montantmaximum", montantmax);
			request.setAttribute("usermax", usermax);
		}
		
		if(finDeLenchere.compareTo(dateActuelle)>0) {
		try {
			ArticleVendu article = ArticleVenduManager.getArticleVenduManager().recupererArticle(no_article);
			session.setAttribute("articlejsp", article);
            System.out.println("date bonne");
            
 
			// rÃ©cupÃ©rer l'utisateur de l'article
			Utilisateur utilisateurArticle = article.getUtilisateur();

			// rÃ©cupÃ©rer l'utilisateur connectÃ© (en session)
			Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("user");

			// faire test : boolean , Si true = proprietaire, si false=connecte

			boolean isProprietaireArticle = utilisateurArticle.getNo_utilisateur() == utilisateurConnecte
					.getNo_utilisateur();

			// on le passe à la jsp

			request.setAttribute("isProprietaireArticle", isProprietaireArticle);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Enchere> enchere = null;
		try {
			enchere = EnchereManager.getEnchereManager().enchereParNum(no_article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Enchere en : enchere) {
		    en.getNo_enchere();
		    
			int montantmax = en.getMontant_enchere();
			String usermax = en.getUtilisateur().getPseudo();
			request.setAttribute("montantmaximum", montantmax);
			request.setAttribute("usermax", usermax);
		}
		
		request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
		
   }
		
		
		if(finDeLenchere.compareTo(dateActuelle)<0) {
		
		
		
		List<Enchere> enchereverif = null;
		try {
			enchereverif = EnchereManager.getEnchereManager().enchereParNum(no_article);
		} catch (BusinessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Object derniereElement = enchereverif.get(enchereverif.size() -1).getUtilisateur().getNo_utilisateur();
		int numerodudernieruser = (int) derniereElement;
		
		
		 if(finDeLenchere.compareTo(dateActuelle)<0 && utilisateuren == numerodudernieruser) {
			 ArticleVendu article = null;
			try {
				article = ArticleVenduManager.getArticleVenduManager().recupererArticle(no_article);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				session.setAttribute("articlejsp", article);
		
		request.getRequestDispatcher("/WEB-INF/ventegagner.jsp").forward(request, response);
		}
		 
		else {
			ArticleVendu article = null;
			try {
				article = ArticleVenduManager.getArticleVenduManager().recupererArticle(no_article);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("articlejsp", article);
			request.getRequestDispatcher("/WEB-INF/ventefini.jsp").forward(request, response);
		}
	}
	}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int no_user = (int) session.getAttribute("idUser");
		int no_article = (int) session.getAttribute("iddelarticle");

		LocalDate date_enchere = LocalDate.now();
		int montant = Integer.parseInt(request.getParameter("prixenchere"));

		try {
			EnchereManager.getEnchereManager().validerEnchere(date_enchere, montant, no_article, no_user);
			session.getAttribute("articlejsp");
			request.setAttribute("prixmax", montant);
			/* request.getRequestDispatcher("/").forward(request, response); */

			try {
				ArticleVendu article = ArticleVenduManager.getArticleVenduManager().recupererArticle(no_article);
				session.setAttribute("articlejsp", article);
				Utilisateur utilisateurArticle = article.getUtilisateur();

				Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("user");

				boolean isProprietaireArticle = utilisateurArticle.getNo_utilisateur() == utilisateurConnecte
						.getNo_utilisateur();

				request.setAttribute("isProprietaireArticle", isProprietaireArticle);

			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			
			
			List<Enchere> enchere = null;
			try {
				enchere = EnchereManager.getEnchereManager().enchereParNum(no_article);
		
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Enchere en : enchere) {
				int montantmax = en.getMontant_enchere();
				String usermax = en.getUtilisateur().getPseudo();
				request.setAttribute("montantmaximum", montantmax);
				request.setAttribute("usermax", usermax);
			}

			request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errors", e.getErrors());
			request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
		}

	}

}