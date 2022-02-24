package fr.eni.enchere.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.SUCCESSFUL;

import fr.eni.enchere.bll.BLLManager;
import fr.eni.enchere.bll.EniResponse;
import fr.eni.enchere.bll.utils.EniConstantes;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.servlet.utils.EniWebUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si je suis connecté
		if (request.getSession().getAttribute("logged") != null && (boolean) request.getSession().getAttribute("logged")) {
		   // Redirect
		   response.sendRedirect("HomeServlet");
		   
		   // Flash message
		   request.getSession().setAttribute("successMessage", "Vous êtes déjà connecté !");
		}
		else {
			// Render page
			getServletContext().getRequestDispatcher("/LoginPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Prepare
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Utilisateur user = null;
	
		EniResponse eniRespose = BLLManager.getInstance().getUtilisaterManager().login(email, password);
	
		// Cast to utilisateur
		user = (Utilisateur) eniRespose.getDataObject();
		
		// Extract user from login

		if (eniRespose.getCodeResponse() == EniConstantes.CODE_SUCCESS) {
			// Save insession
		   HttpSession session = request.getSession();
		   
		   session.setAttribute("no_utilisateur", user.getNoUtilisateur());
		   session.setAttribute("nom", user.getNom());
		   session.setAttribute("prenom", user.getPrenom());
		   session.setAttribute("logged", true);
		   
		   // Example
		   List<Utilisateur> listUser = null; 
		   request.setAttribute("listeUtilisateur", listUser);
		   
		   request.setAttribute("utilisateur", user);
		   
		   // Flash message
		   session.setAttribute("successMessage", "Vous êtes connecté(e) !");
		   
		   // Redirect
		   response.sendRedirect("HomeServlet");
		}
		else {
			// sino nerreur mot de passe
			getServletContext().getRequestDispatcher("/LoginPage.jsp").forward(request, response);
		}
		
	}

}
