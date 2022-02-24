package fr.eni.enchere.bll;

import fr.eni.enchere.bll.utils.EniConstantes;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.DAOFactory;

public class UtilisateurBLL {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws DALException
	 */
	public EniResponse login(String username, String password){
		Utilisateur user = null;
		try {
			user = DAOFactory.getInstance().getUtilisateurDAO().login(username, password);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2 : Reponse metier
		// -- Reponse par défaut
		EniResponse response = new EniResponse(EniConstantes.CODE_SUCCESS, "Login avec succès");
		
		// si false
		if (user == null) {
			response.setResponse(EniConstantes.CODE_ERROR_TECH, "Impossible de se connecter");
		}
		
		// Store the user in the response
		response.setDataObject(user);
		
		return response;
	}
	
	/**
	 * Register
	 * @param user
	 * @return
	 */
	public EniResponse registerUser(Utilisateur user) {
		boolean success = false;
		
		// 1 : DAO
		try {
			// Appel DAO
			success = DAOFactory.getInstance().getUtilisateurDAO().registreUser(user);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2 : Reponse metier
		// -- Reponse par défaut
		EniResponse response = new EniResponse(EniConstantes.CODE_SUCCESS, "Inscription avec succès");
		
		// si false
		if (!success) {
			response.setResponse(EniConstantes.CODE_ERROR_TECH, "Impossible d'insérer un utilisateur");
		}
		
		return response;
	}
	
	public EniResponse select(int id){
		Utilisateur user = null;
		try {
			user = DAOFactory.getInstance().getUtilisateurDAO().select(id);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2 : Reponse metier
		// -- Reponse par défaut
		EniResponse response = new EniResponse(EniConstantes.CODE_SUCCESS, "Login avec succès");
		
		// si false
		if (user == null) {
			response.setResponse(EniConstantes.CODE_ERROR_TECH, "Impossible de se connecter");
		}
		
		// Store the user in the response
		response.setDataObject(user);
		
		return response;
	}
}
