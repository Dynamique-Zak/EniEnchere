package fr.eni.enchere.bll;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Category;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.utils.ArticlePaginateResult;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.DAOFactory;

public class ArticleBLL {
	
	public ArticlePaginateResult getAllArticle(int pageIndex){
		try {
			return DAOFactory.getInstance().getArticleDAO().selectAllPaginate(pageIndex, 20);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Article> getAllArticle(){
		try {
			return DAOFactory.getInstance().getArticleDAO().selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Category> getAllCategory(){
		List<Category> categoryList = new ArrayList<Category>();
		
		try {
			categoryList = DAOFactory.getInstance().getCategoryDAO().selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return categoryList;
	}
	public Article getArticle(int id) {
		try {
			return DAOFactory.getInstance().getArticleDAO().select(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean encherir(Article article, Utilisateur user, int prix) {
		// Prepare test
		int nbError = 0;
		
		// -- DAO Appel 1
		// Re récupère l'user connecté depuis la bdd
		Utilisateur loggedUser = null;
		try {
			loggedUser = DAOFactory.getInstance().getUtilisateurDAO().select(user.getNoUtilisateur());
		} catch (DALException e) {
			e.printStackTrace();
		}
		// Si le prix est plus grand que le credit de l'utilisateur
		if (loggedUser.getCredit() > prix ) {
		}
		else {
			nbError++;
		}
	
		// -- DAO Appel 2
		// Appeler la meilleur enchere de l'article
		Enchere enchere = null;
		try {
			enchere = DAOFactory.getInstance().getEnchereDAO().selectBestEnchere(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
		// Si le prix est plus grand que le meilleur enchere récupéré
		if (enchere != null && prix > enchere.getMontantEnchere()) {
		}
		else if (enchere == null) {
			
		}
		else {
			nbError++;
		}
		
		
		// Si j'ai au moins une erreur c'est pas bon
		if (nbError > 0) {
			return false;
		}
		else {
			// -- DAO Appel 3
			// Je prépare l'encher eque je vais inserer dans la base
			Enchere newEnchere = new Enchere(new Date(), prix);
			newEnchere.setArticle(article);
			newEnchere.setUser(loggedUser);
			
			// J'appel dao pour l'insert
			try {
				DAOFactory.getInstance().getEnchereDAO().insert(newEnchere);
			} catch (DALException e) {
				e.printStackTrace();
			}
			
			return true;
		}
	}
	
	public List<Enchere> getAllByFilter(Utilisateur user, HashMap<Integer, Boolean> filterSelected)
	{
		List<Enchere> listEnchere = null;

		try {
			listEnchere = DAOFactory.getInstance().getEnchereDAO().selectAllByFilter(user, filterSelected);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return listEnchere;
	}
}
