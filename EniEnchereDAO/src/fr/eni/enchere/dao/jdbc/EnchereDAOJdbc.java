package fr.eni.enchere.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sql2o.tools.NamedParameterStatement;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.utils.EniCommunUtils;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.IEnchereDAO;
import fr.eni.enchere.dao.utils.EniDAOFilter;
import fr.eni.enchere.dao.utils.EniDAOMapping;

public class EnchereDAOJdbc implements IEnchereDAO {

	private static final String sqlSelectBestEnchere = "SELECT TOP 1 *"
			+ " FROM ENCHERES WHERE no_article = ? ORDER BY montant_enchere DESC";

	private static final String sqlInsertEnchere = "INSERT INTO ENCHERES(no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?)";

	private static final String sqlSelectBAll = "SELECT * " + "FROM ENCHERES "
			+ "INNER JOIN ARTICLES_VENDUS ON (ENCHERES.no_article = ARTICLES_VENDUS.no_article)"
			+ "INNER JOIN UTILISATEURS ON (ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateur)";

	@Override
	public List<Enchere> selectAll() throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;

		List<Enchere> listEnchere = new ArrayList<Enchere>();

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectBAll);

			rs = rqt.executeQuery();

			// Si y'a un resultat
			while (rs.next()) {
				// Creer l'enchere
				Enchere enchere = EniDAOMapping.mappingEnchere(rs);

				// Creer l'article
				Article article = EniDAOMapping.mappingArticle(rs);

				// Creer un utilisateur
				Utilisateur user = EniDAOMapping.mappingUser(rs);

				// Associations
				enchere.setArticle(article);
				enchere.setUser(user);

				// Ajouter dans la liste des encheres
				listEnchere.add(enchere);
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rqt != null) {
					rqt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return listEnchere;
	}

	@Override
	public Enchere selectBestEnchere(Article article) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Enchere enchere = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectBestEnchere);
			rqt.setInt(1, article.getNoArticle());

			rs = rqt.executeQuery();

			// Si y'a un resultat
			if (rs.next()) {
				// Alimente enchere
				enchere = new Enchere(EniCommunUtils.convertDate(rs.getString("date_enchere")),
						rs.getInt("montant_enchere"));
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rqt != null) {
					rqt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return enchere;
	}

	@Override
	public boolean insert(Enchere enchere) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean success = false;

		try {
			// Prepare la requete
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsertEnchere, Statement.RETURN_GENERATED_KEYS);

			// Envoie les id des objet User et article pour les Foreign Key
			rqt.setInt(1, enchere.getUser().getNoUtilisateur());
			rqt.setInt(2, enchere.getArticle().getNoArticle());
			// date et montant
			rqt.setDate(3, java.sql.Date.valueOf(EniCommunUtils.convertDateToString(enchere.getDateEnchere())));
			rqt.setInt(4, enchere.getMontantEnchere());

			// Execute la requete
			int result = rqt.executeUpdate();

			// --
			if (result == 1) {
				ResultSet resultSet = rqt.getGeneratedKeys();
				// resultSet.get
				success = true;
			}

		} catch (SQLException e) {
			throw new DALException("sqlInsertUser failed - id", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rqt != null) {
					rqt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return success;
	}

	@Override
	public List<Enchere> selectAllByFilter(Utilisateur user, HashMap<Integer, Boolean> filterSelected)
	{
		// --
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		Connection cnx = null;
		NamedParameterStatement rqt = null;
		ResultSet rs = null;
	
		// On génère la requete de filtrage selon les choix
		String sqlFinal = EniDAOFilter.getInstance().checkFilter(filterSelected);
		
		// SQL named
		try {
			// Etablir connexion SQL en named paramaeter
			cnx = JdbcTools.getConnection();
			rqt = new NamedParameterStatement(cnx, sqlFinal, false);
			
			if (filterSelected.get(1)) {
				rqt.setDate("date_enchere", java.sql.Date.valueOf(EniCommunUtils.todayStr()));
			}
			
			if (filterSelected.get(2)) {
				rqt.setInt("no_utilisateur", user.getNoUtilisateur());
			}
		
			
			// Execute la requête
			rs = rqt.executeQuery();
	
			// Tant qu'on a des resultats
			while (rs.next()){
				// Map Enchere
				Enchere enchere = new Enchere(
						EniCommunUtils.convertDate(rs.getString("date_enchere")),
						rs.getInt("montant_enchere"));
				
				// Article 
				Article art = EniDAOMapping.mappingArticle(rs);
				enchere.setArticle(art);
				
				//
				listEnchere.add(enchere);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Affichfer tout les encheres
		for (Enchere enchere : listEnchere) {
			System.out.println(enchere);
		}
		
		// Afficher la requete
		System.out.println(sqlFinal);
		
		return listEnchere;
	}

}
