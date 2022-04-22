package fr.eni.enchere.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Category;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.utils.ArticlePaginateResult;
import fr.eni.enchere.dao.IArticleDAO;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.DAOFactory;
import fr.eni.enchere.dao.utils.EniDAOMapping;

public class ArticleDAOJdbc implements IArticleDAO{

	private static final String sqlSelectALL = "SELECT *" +
			" FROM ARTICLES_VENDUS";
	
	private static final String sqlSelectALLPaginate = "SELECT *" +
			" FROM ARTICLES_VENDUS" +
			" INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur" +
			" INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie" +
			" ORDER BY no_article OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
	private static final String sqlSelectById = "SELECT *" +
			" FROM ARTICLES_VENDUS " + 
			" INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur" +
			" INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie" + 
			" WHERE no_article = ?";
	
	private static final String sqlSelectRetraitById = "SELECT *" +
			" FROM RETRAITS " + 
			" WHERE no_article = ?";

	@Override
	public Article select(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Article article = null;
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);
			
			rs = rqt.executeQuery();
			
			if (rs.next()){
				// map article
				article = EniDAOMapping.mappingArticle(rs);
				
				// Mapping user
				Utilisateur user = EniDAOMapping.mappingUser(rs);
				Category category = EniDAOMapping.mappingCategory(rs);
				
				// Top Enchere
				Enchere enchere = DAOFactory.getInstance().getEnchereDAO().selectBestEnchere(article);
				
				// Retrait
				Retrait retrait = this.selectRetrait(id);
				
				// Association
				article.setCategory(category);
				article.setVendeur(user);
				article.setTopEnchere(enchere);
				article.setRetrait(retrait);
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id" , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return article;
	}


	@Override
	public List<Article> selectAll() throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> articleList = new ArrayList<Article>();
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectALL);
			
			rs = rqt.executeQuery();

			while (rs.next()){
				// map article
				Article article = EniDAOMapping.mappingArticle(rs);
				
				articleList.add(article);
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id" , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return articleList;
	}

	public int selectCountArticle() throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int count  = 0;
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement("SELECT COUNT(no_article) AS nbArticle FROM ARTICLES_VENDUS");
			rs = rqt.executeQuery();

			// Records
			while (rs.next()){
				count = rs.getInt("nbArticle");
			}
		
		} catch (SQLException e) {
			throw new DALException("selectCountArticle failed" , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return count;
	}

	
	@Override
	public ArticlePaginateResult selectAllPaginate(int index, int nbPerPage) throws DALException {
		// Paginate result
		ArticlePaginateResult resultData = new ArticlePaginateResult();
		
		// Param
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> articleList = new ArrayList<Article>();
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectALLPaginate);
			
			// Pagination calcul
			int offset = index * nbPerPage;
			rqt.setInt(1, offset);
			rqt.setInt(2, nbPerPage);
			
			rs = rqt.executeQuery();

			// Records
			while (rs.next()){
				// Default article
				Article article = EniDAOMapping.mappingArticle(rs);
				
				// Mapping user
				Utilisateur user = EniDAOMapping.mappingUser(rs);
				Category category = EniDAOMapping.mappingCategory(rs);
				
				// Association
				article.setCategory(category);
				article.setVendeur(user);
				
				// map article
				articleList.add(article);
			}
			
			// Save article in data
			resultData.articleList = articleList;
			
			// Save nb page in data
			resultData.nbArticle = selectCountArticle();

		} catch (SQLException e) {
			throw new DALException("selectByPaginate failed" , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return resultData;
	}


	@Override
	public Retrait selectRetrait(int articleId) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Retrait retrait = null;
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectRetraitById);
			rqt.setInt(1, articleId);
			
			rs = rqt.executeQuery();
			
			if (rs.next()){
				// map article
				retrait = EniDAOMapping.mappingRetrait(rs);
			}

		} catch (SQLException e) {
			throw new DALException("selectRetraitById failed - id" , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return retrait;
	}

}
