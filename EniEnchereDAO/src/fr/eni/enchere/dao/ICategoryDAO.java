package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.Category;

public interface ICategoryDAO {

	public List<Category> selectAll() throws DALException;
	
}
