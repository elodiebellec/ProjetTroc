package fr.eni.projettroc.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.gestionlistescourses.bll.ListeCourseManager;
import fr.eni.gestionlistescourses.bo.ListeCourse;
import fr.eni.gestionlistescourses.exception.BusinessException;
import fr.eni.gestionlistescourses.exception.Errors;

/**
 * Servlet implementation class accueil
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		try {
				ListeCourseManager.getInstance().supprimerListeCourse(idListe);
			}
			//Afficher la liste des Liste de courses
			//Rediriger vers la page courses.jsp			
			List<ListeCourse> lstCourses = ListeCourseManager.getInstance().selectionneListes();
			request.setAttribute("lstCourse", lstCourses);
		} catch (BusinessException e) {
			request.setAttribute("errors", e.getErrors());
		}catch (NumberFormatException e) {
			BusinessException be = new BusinessException();
			be.addError(Errors.FORMAT_ID_LISTE_ERREUR);
			request.setAttribute("errors", be.getErrors());
		}
		
		request.getRequestDispatcher("/WEB-INF/courses.jsp").forward(request, response);
	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
