package fr.eni.projettroc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projettroc.bll.UtilisateurManager;
import fr.eni.projettroc.bo.Utilisateur;
import fr.eni.projettroc.exception.BusinessException;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		request.getRequestDispatcher("/WEB-INF/pageInscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		UtilisateurManager um = UtilisateurManager.getUtilisateursManager();  
		try {  
		    String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String code_postal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");
			String mot_de_passe = request.getParameter("motdepasse");
			um.ajouterUnUtilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe);
			}catch(Exception e) {
		
	}
}
}


/*ListeCourse listeCourse = null;
HttpSession session = request.getSession();
ListeCourseManager lcm = ListeCourseManager.getInstance();

try {
	if (session.getAttribute("idListe") != null) {
		// ListeCourse existante - ajout d'un article uniquement
		int idListe = (Integer) session.getAttribute("idListe");
		String nomArticle = request.getParameter("nom_article");
		lcm.ajouterArticle(idListe, nomArticle);
		listeCourse = lcm.selectionneListe(idListe);
		request.setAttribute("listeCourse", listeCourse);
	} else {
		// Nouvelle liste
		String nomListe = request.getParameter("nom_liste");
		String nomArticle = request.getParameter("nom_article");

		listeCourse = lcm.ajouterListeEtArticle(nomListe, nomArticle);
		session.setAttribute("idListe", listeCourse.getId());
		request.setAttribute("listeCourse", listeCourse);
	}

} catch (BusinessException e) {
	request.setAttribute("errors", e.getErrors());
}

request.getRequestDispatcher("/WEB-INF/form.jsp").forward(request, response);

}*/
		/*String nomListe = request.getParameter("nom_liste");
		String nomArticle = request.getParameter("nom_article");

		listeCourse = lcm.ajouterListeEtArticle(nomListe, nomArticle);
		session.setAttribute("idListe", listeCourse.getId());
		request.setAttribute("listeCourse", listeCourse);*/
	/*requete.setString(1, utilisateur.getPseudo());
	requete.setString(2, utilisateur.getNom());
	requete.setString(3, utilisateur.getPrenom());
	requete.setString(4, utilisateur.getEmail());
	requete.setString(5, utilisateur.getTelephone());
	requete.setString(6, utilisateur.getRue());
	requete.setString(7, utilisateur.getCode_postal());
	requete.setString(8, utilisateur.getVille());
	requete.setString(9, utilisateur.getMot_de_passe());*/

