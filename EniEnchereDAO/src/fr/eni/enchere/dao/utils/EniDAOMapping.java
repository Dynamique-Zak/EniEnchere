package fr.eni.enchere.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.utils.EniCommunUtils;

public class EniDAOMapping {

	/**
	 * Mapping User
	 * @param rs
	 * @return
	 */
	public static Utilisateur mappingUser(ResultSet rs) {
		Utilisateur user = null;
		try {
			user = new Utilisateur(rs.getInt("no_utilisateur"),
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("telephone"),
					rs.getString("rue"),
					rs.getString("code_postal"),
					rs.getString("ville"),
					rs.getString("mot_de_passe"),
					Integer.parseInt(rs.getString("credit")),
					Integer.parseInt(rs.getString("administrateur")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * Mapping Article
	 * @param rs
	 * @return
	 */
	public static Article mappingArticle(ResultSet rs) {
		Article article = null;
		try {
			article = new Article(rs.getInt("no_article"),
					rs.getString("nom_article"),
					rs.getString("description"),
					EniCommunUtils.convertDate(rs.getString("date_debut_encheres")),
					EniCommunUtils.convertDate(rs.getString("date_fin_encheres")),
					rs.getInt("prix_initial"),
					rs.getInt("prix_vente"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return article;
	}
	
	/**
	 * Mapping Enchere
	 * @param rs
	 * @return
	 */
	public static Enchere mappingEnchere(ResultSet rs) {
		Enchere enchere = null;
		try {
			
			enchere = new Enchere(EniCommunUtils.convertDate(rs.getString("date_enchere")), rs.getInt("montant_enchere"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}
}
