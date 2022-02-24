package fr.eni.enchere.servlet.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.BLLManager;
import fr.eni.enchere.bll.utils.EniConstantes;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public class EniWebUtils {

	public static Utilisateur getLoggedUser(HttpServletRequest request) {
		// A remplacer par l'user connecté quand ca marche
		int id = (int) request.getSession().getAttribute("no_utilisateur");
		
		Utilisateur user = (Utilisateur) BLLManager.getInstance().getUtilisaterManager().select(id).getDataObject();
		
		return user;
	}
	
	public static boolean verifyIsVendeur(HttpServletRequest request, Article article) {
		// Dépendances IHM
		HttpSession session = request.getSession();
        int idUtilisateur = (int) session.getAttribute("idUtilisateur");
        int vendeur = article.getUtilisateur().getNoUtilisateur();

        // Je compare les informations du vendeur et de l'article pour envoyer une valeur boolean pour afficher ou non certaines infos dans la JSP.
        return EniConstantes.verifyIsVendeur(vendeur, idUtilisateur);
	}
	
}
