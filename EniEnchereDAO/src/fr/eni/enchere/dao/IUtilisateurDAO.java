package fr.eni.enchere.dao;

import fr.eni.enchere.bo.Utilisateur;

public interface IUtilisateurDAO {

	public Utilisateur login(String username, String password) throws DALException;
	
	public Utilisateur select(int id) throws DALException;
	
	public boolean registreUser(Utilisateur user) throws DALException;
}
