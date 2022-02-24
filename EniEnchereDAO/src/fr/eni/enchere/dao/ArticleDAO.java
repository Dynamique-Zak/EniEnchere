package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.Article;

public interface ArticleDAO {

	public List<Article> selectAll() throws DALException;
	public Article select(int id) throws DALException;
}
