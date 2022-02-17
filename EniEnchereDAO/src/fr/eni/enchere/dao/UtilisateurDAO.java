package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	public boolean login(String username, String password) throws DALException;
	
	public boolean registreUser(Utilisateur user) throws DALException;
}
