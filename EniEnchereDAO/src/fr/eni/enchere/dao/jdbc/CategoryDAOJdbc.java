package fr.eni.enchere.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Category;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.ICategoryDAO;
import fr.eni.enchere.dao.utils.EniDAOMapping;

public class CategoryDAOJdbc implements ICategoryDAO{

	private static final String sqlSelectALL = "SELECT *" +
			" FROM CATEGORIES";


	@Override
	public List<Category> selectAll() throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Category> categoryList = new ArrayList<Category>();
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectALL);
			
			rs = rqt.executeQuery();

			while (rs.next()){
				// map article
				Category category = EniDAOMapping.mappingCategory(rs);
				
				categoryList.add(category);
			}

		} catch (SQLException e) {
			throw new DALException("selectAll" , e);
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
		return categoryList;
	}


}
