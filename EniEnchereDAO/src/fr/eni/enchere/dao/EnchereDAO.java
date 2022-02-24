package fr.eni.enchere.dao;

import java.util.HashMap;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public interface  EnchereDAO {

	public List<Enchere> selectAllByFilter(Utilisateur user, HashMap<Integer, Boolean> filterSelected) throws DALException;
	
	public List<Enchere> selectAll() throws DALException;
	
	public Enchere selectBestEnchere(Article article) throws DALException;
	
	public boolean insert(Enchere enchere) throws DALException;
}
