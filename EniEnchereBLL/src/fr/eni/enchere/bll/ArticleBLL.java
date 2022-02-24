package fr.eni.enchere.bll;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.DAOFactory;

public class ArticleBLL {

	
	public List<Article> getAllArticle(){
		try {
			return DAOFactory.getInstance().getArticleDAO().selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return null;
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
		// Re r�cup�re l'user connect� depuis la bdd
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
		// Si le prix est plus grand que le meilleur enchere r�cup�r�
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
			// Je pr�pare l'encher eque je vais inserer dans la base
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
