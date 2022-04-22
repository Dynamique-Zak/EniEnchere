package fr.eni.enchere.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BLLManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.utils.ArticlePaginateResult;
import fr.eni.enchere.bo.utils.EniCommunUtils;
import fr.eni.enchere.servlet.utils.EniWebUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
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
		
		// Page 
		int pageIndex = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 0;
		
		// Get all articles
		ArticlePaginateResult resultBLL = BLLManager.getInstance().getArticleManager().getAllArticle(pageIndex);
		
		List<Article> articleList = resultBLL.articleList;
		int nbPage = (resultBLL.nbArticle / 20) - 1;
		// Get logged user
		//Utilisateur user = EniWebUtils.getLoggedUser(request);
				
		// Je le put dans la vue
		request.setAttribute("articleList", articleList);
		request.setAttribute("nbPage", nbPage);
		request.setAttribute("pageIndex", pageIndex);
		
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
		// Page 
		int pageIndex = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 0;
				
		// Récupère les selections
		boolean filter1 = "on".equals(request.getParameter("filter_01"));
		boolean filter2 = "on".equals(request.getParameter("filter_02"));
		boolean filter3 = "on".equals(request.getParameter("filter_03"));
		
		// Construction la map de selection
		HashMap<Integer, Boolean> filterSelected = new HashMap<Integer, Boolean>();
		
		filterSelected.put(1, filter1);
		filterSelected.put(2, filter2);
		filterSelected.put(3, filter3);
		
		// Si les filtres ont au moins 1 option selectionnée
		int countFilterOn = 0;
		for (boolean v : filterSelected.values()) {
		   if (v) {
            	countFilterOn++;
		   }
		}
		boolean useFilter = (countFilterOn > 0) ? true : false;
		
		// Get logged user
		Utilisateur user = EniWebUtils.getLoggedUser(request);
		
		// by default
		int nbPage = 1;
				
		// Les encheres associés avec les articles
		List<Article> articleList = null;
		if (useFilter) {
			List<Enchere> listEnchere = BLLManager.getInstance().getArticleManager().getAllByFilter(user, filterSelected);
			articleList = EniCommunUtils.getArticleListFromEncheresList(listEnchere);
			nbPage = (articleList.size()/ 20) - 1;
		}
		else {
			// Get all articles
			ArticlePaginateResult resultBLL = BLLManager.getInstance().getArticleManager().getAllArticle(pageIndex);
			
			articleList = resultBLL.articleList;
			nbPage = (resultBLL.nbArticle / 20) - 1;
		
		}
		
		// Je le put dans la vue
		request.setAttribute("articleList", articleList);
		request.setAttribute("nbPage", nbPage);
		request.setAttribute("pageIndex", pageIndex);
		
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
