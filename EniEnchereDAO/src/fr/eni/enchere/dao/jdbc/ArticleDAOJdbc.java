package fr.eni.enchere.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.utils.EniCommunUtils;
import fr.eni.enchere.dao.ArticleDAO;
import fr.eni.enchere.dao.DALException;

public class ArticleDAOJdbc implements ArticleDAO{

	private static final String sqlSelectByALL = "SELECT *" +
			" FROM ARTICLES_VENDUS";
	
	private static final String sqlSelectById = "SELECT *" +
			" FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	

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
				article = new Article(rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						EniCommunUtils.convertDate(rs.getString("date_debut_encheres")),
						EniCommunUtils.convertDate(rs.getString("date_fin_encheres")),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"));
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
			rqt = cnx.prepareStatement(sqlSelectByALL);
			
			rs = rqt.executeQuery();

			while (rs.next()){
				// map article
				articleList.add(new Article(rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						EniCommunUtils.convertDate(rs.getString("date_debut_encheres")),
						EniCommunUtils.convertDate(rs.getString("date_fin_encheres")),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente")));
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

}
