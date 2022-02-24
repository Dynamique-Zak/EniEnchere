package fr.eni.enchere.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BLLManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.utils.EniCommunUtils;
import fr.eni.enchere.servlet.utils.EniWebUtils;

/**
 * Servlet implementation class DetailArticleServlet
 */
@WebServlet("/DetailArticleServlet")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer l'id de l'article
		int idArticle = Integer.parseInt(request.getParameter("id"));

		// Je recupère un article en base
		Article art = BLLManager.getInstance().getArticleManager().getArticle(idArticle);
		
		// je l'envoie dans la request (dans la jsp)
		request.setAttribute("article", art);
		
		// Afficherr la jsp
		getServletContext().getRequestDispatcher("/WEB-INF/DetailArticle.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer l'id de l'article
		int idArticle = Integer.parseInt(request.getParameter("id"));

		// Get logged user
		Utilisateur user = EniWebUtils.getLoggedUser(request);
		
		// Article
		Article art = BLLManager.getInstance().getArticleManager().getArticle(idArticle);
		
		// 
		int prix = Integer.parseInt(request.getParameter("montant_enchere"));
		// Appel a la BLL
		BLLManager.getInstance().getArticleManager().encherir(art, user, prix);
	}

}