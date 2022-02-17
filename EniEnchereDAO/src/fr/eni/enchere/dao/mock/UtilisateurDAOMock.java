package fr.eni.enchere.dao.mock;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.UtilisateurDAO;

public class UtilisateurDAOMock implements UtilisateurDAO{
	
	@Override
	public boolean login(String username, String password) throws DALException {
		return (username.equals("test@mail.com") && password.equals("password"));
	}

	@Override
	public boolean registreUser(Utilisateur user) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

}
