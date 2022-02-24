package fr.eni.enchere.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Category;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DALException;
import fr.eni.enchere.dao.UtilisateurDAO;
import fr.eni.enchere.dao.utils.EniDAOMapping;

public class UtilisateurDAOJdbc implements UtilisateurDAO{

	private static final String sqlSelectByLogin = "SELECT *" +
			" FROM UTILISATEURS WHERE email = ? AND mot_de_passe = ?";
	
	private static final String sqlSelectById = "SELECT *" +
			" FROM UTILISATEURS WHERE no_utilisateur = ?";
	
	private static final String sqlInsertUser = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	
	@Override
	public Utilisateur login(String username, String password) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur user = null;
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByLogin);
			rqt.setString(1, username);
			rqt.setString(2, password);
			
			rs = rqt.executeQuery();
			
			if (rs.next()){
				// map l'user
				user = EniDAOMapping.mappingUser(rs);
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
		return user;
	}

	@Override
	public boolean registreUser(Utilisateur user) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean success = false;
		
		try {
			
			// Prepare la requete
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsertUser, Statement.RETURN_GENERATED_KEYS);
			
			rqt.setString(1, user.getPseudo());
			rqt.setString(2, user.getNom());
			rqt.setString(3, user.getPrenom());
			rqt.setString(4, user.getEmail());
			rqt.setString(5, user.getTelephone());
			rqt.setString(6, user.getRue());
			rqt.setString(7, user.getCodePostal());
			rqt.setString(8, user.getVille());
			rqt.setString(9, user.getMotDePasse());
			rqt.setInt(10, user.getCredit());
			rqt.setInt(11, user.getAdministrateur());
			
			// Execute la requete 
			int result = rqt.executeUpdate();
			
			// --
			if (result == 1){
				ResultSet resultSet = rqt.getGeneratedKeys();
				if (resultSet.next()) {
					user.setNoUtilisateur(resultSet.getInt(1));
				}
				//resultSet.get
				success = true;
			}

		} catch (SQLException e) {
			throw new DALException("sqlInsertUser failed - id" , e);
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
		return success;
	}

	@Override
	public Utilisateur select(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur user = null;
		
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);
			
			rs = rqt.executeQuery();

			
			if (rs.next()){
				// map l'user
				user = EniDAOMapping.mappingUser(rs);
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
		return user;
	}

}
