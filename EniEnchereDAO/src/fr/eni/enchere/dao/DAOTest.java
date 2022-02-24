package fr.eni.enchere.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sql2o.tools.NamedParameterStatement;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.utils.EniCommunUtils;
import fr.eni.enchere.dao.jdbc.JdbcTools;
import fr.eni.enchere.dao.utils.EniDAOFilter;
import fr.eni.enchere.dao.utils.EniDAOMapping;

public class DAOTest {


    
	public static void main(String[] args) {
		// J'ai coché que le premier
		HashMap<Integer, Boolean> filterSelected = new HashMap<Integer, Boolean>();
		
		filterSelected.put(1, true);
		filterSelected.put(2, true);
		filterSelected.put(3, true);
		
		// On génère la requete de filtrage selon les choix
		String sqlFinal = EniDAOFilter.getInstance().checkFilter(filterSelected);
		
		// Afficher la requete
		System.out.println(sqlFinal);
		
		// Les données date / utilisateur
		String maDate = "2022-02-25";
		int noUtilisateur = 12;
		
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		Connection cnx = null;
		ResultSet rs = null;
		
		// SQL named
		try {
			// Etablir connexion SQL en named paramaeter
			cnx = JdbcTools.getConnection();
			
			NamedParameterStatement rqt = new NamedParameterStatement(cnx, sqlFinal, false);
			rqt.setDate("date_enchere", java.sql.Date.valueOf(maDate));
			rqt.setInt("no_utilisateur", noUtilisateur);
			
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
				
				listEnchere.add(enchere);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Affichfer tout les encheres
		for (Enchere enchere : listEnchere) {
			System.out.println(enchere);
		}
	
		
	}

}
