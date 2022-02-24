package fr.eni.enchere.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BLLManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.utils.EniCommunUtils;
import fr.eni.enchere.servlet.utils.EniWebUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected HashMap<Integer, Boolean> defaultSelection() {
		// Construction la map de selection
		HashMap<Integer, Boolean> filterSelected = new HashMap<Integer, Boolean>();
		
		filterSelected.put(1, true);
		filterSelected.put(2, false);
		filterSelected.put(3, true);
		
		return filterSelected;
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Construction la map de selection
		HashMap<Integer, Boolean> filterSelected = defaultSelection();
		// Get all articles
		List<Article> articleList = BLLManager.getInstance().getArticleManager().getAllArticle();
		
		// Get logged user
		//Utilisateur user = EniWebUtils.getLoggedUser(request);
				
		// Je le put dans la vue
		request.setAttribute("articleList", articleList);
		
		// envoyer ce qu'on a coché
		List<Boolean> selectedHtml = new ArrayList<Boolean>();
		
		
		for (Boolean value : filterSelected.values()) {
			selectedHtml.add(value);
		}
		request.setAttribute("filterSelected", selectedHtml);
		
		request.getSession().setAttribute("loggedTest", true);
	
		// getAllByFilter
		
		// j'affiche la vue
		getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupère les selections
		boolean filter1 = "on".equals(request.getParameter("filter_01"));
		boolean filter2 = "on".equals(request.getParameter("filter_02"));
		boolean filter3 = "on".equals(request.getParameter("filter_03"));
		
		// Construction la map de selection
		HashMap<Integer, Boolean> filterSelected = new HashMap<Integer, Boolean>();
		
		filterSelected.put(1, filter1);
		filterSelected.put(2, filter2);
		filterSelected.put(3, filter3);
		
		// Get logged user
		Utilisateur user = EniWebUtils.getLoggedUser(request);
		
		// Les encheres associés avec les articles
		List<Enchere> listEnchere = BLLManager.getInstance().getArticleManager().getAllByFilter(user, filterSelected);
		
		List<Article> listArticle = EniCommunUtils.getArticleListFromEncheresList(listEnchere);
		
		// Je le put dans la vue
		request.setAttribute("articleList", listArticle);
		
		// envoyer ce qu'on a coché
		List<Boolean> selectedHtml = new ArrayList<Boolean>();
		
		
		for (Boolean value : filterSelected.values()) {
			selectedHtml.add(value);
		}
		request.setAttribute("filterSelected", selectedHtml);
		
		request.getSession().setAttribute("loggedTest", true);
	
		// j'affiche la vue
		getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
	}

}
