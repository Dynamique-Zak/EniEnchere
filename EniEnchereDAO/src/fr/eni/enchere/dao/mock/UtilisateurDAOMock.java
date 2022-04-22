package fr.eni.enchere.dao.mock;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.IUtilisateurDAO;

public class UtilisateurDAOMock implements IUtilisateurDAO{
	
	@Override
	public Utilisateur login(String username, String password) throws DALException {
		return null;
	}

	@Override
	public boolean registreUser(Utilisateur user) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utilisateur select(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
