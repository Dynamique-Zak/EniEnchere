package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.jdbc.UtilisateurDAOJdbc;
import fr.eni.enchere.dao.mock.UtilisateurDAOMock;

public class DAOFactory {
	
	private static DAOFactory instance;
	
	private String implMode = "jdbc"; // or jdbc | mocl
	private UtilisateurDAO utilisateurDAO;
	
	/**
	 * Get current instance
	 * @return
	 */
	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		
		return instance;
	}
	
	/**
	 * Get package prefix
	 * @param className
	 * @return
	 */
	private String getPackagePrefix(String className) {
		return "fr.eni.enchere.dao." +  implMode + "." + className + capitalizeMode(implMode);
	}
	
	private String capitalizeMode(String value) {
		String s1 = value.substring(0, 1).toUpperCase();
	    return s1 + value.substring(1);
	}
	/**
	 * Get use DAO
	 * @return
	 */
	public UtilisateurDAO getUtilisateurDAO()  {
		if (utilisateurDAO == null) {
			try {
				utilisateurDAO = (UtilisateurDAO) Class.forName(getPackagePrefix("UtilisateurDAO")).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return utilisateurDAO; 
	}
}
