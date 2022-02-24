package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur login(String username, String password) throws DALException;
	
	public Utilisateur select(int id) throws DALException;
	
	
	public boolean registreUser(Utilisateur user) throws DALException;
}
