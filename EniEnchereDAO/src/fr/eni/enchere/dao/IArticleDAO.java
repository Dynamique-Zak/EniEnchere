package fr.eni.enchere.dao;

import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.utils.ArticlePaginateResult;

public interface IArticleDAO {

	public ArticlePaginateResult selectAllPaginate(int index, int nbPerPage) throws DALException;

	public List<Article> selectAll() throws DALException;

	public Article select(int id) throws DALException;

	public Retrait selectRetrait(int articleId) throws DALException;
}
