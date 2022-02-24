package fr.eni.enchere.dao.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EniDAOFilter {

	private static final String sqlSelectEnchereFilter= "SELECT * FROM ENCHERES INNER JOIN ARTICLES_VENDUS ON (ARTICLES_VENDUS.no_article = ENCHERES.no_article) ";
	
	public List<String> conditionsFiltre;
	
	private EniDAOFilter instance;
	
	public EniDAOFilter() {
		conditionsFiltre = new ArrayList<String>();
		
		conditionsFiltre.add("date_enchere < :date_enchere");
		conditionsFiltre.add("ENCHERES.no_utilisateur = :no_utilisateur");
		conditionsFiltre.add("ARTICLES_VENDUS.prix_vente = ENCHERES.montant_enchere");
	}
	
	public static EniDAOFilter getInstance() {
		return new EniDAOFilter();
	}
	
	public String checkFilter(HashMap<Integer, Boolean> filterSelected) {
		String sqlFinal = "" + sqlSelectEnchereFilter;
		
		int currentCheck = 0;
		
		// Si le 1er filtre est coché
		if (filterSelected.get(1) == true) {
			// Check si Where ou And
			sqlFinal = EniDAOFilter.addCondition(currentCheck, sqlFinal);
			
			// Ajoute la condition
			sqlFinal += conditionsFiltre.get(0);
			
			// increment
			currentCheck++;
		}
		
		// Si le 2eme filtre est coché
		if (filterSelected.get(2) == true) {
			// Check si Where ou And
			sqlFinal = EniDAOFilter.addCondition(currentCheck, sqlFinal);
			
			// Ajoute la condition
			sqlFinal += conditionsFiltre.get(1);
			
			// increment
			currentCheck++;
		}
		
		
		// Si le 3eme filtre est coché
		if (filterSelected.get(3) == true) {
			// Check si Where ou And
			sqlFinal = EniDAOFilter.addCondition(currentCheck, sqlFinal);
			
			// Ajoute la condition
			sqlFinal += conditionsFiltre.get(2);
			
			// increment
			currentCheck++;
		}

		// Return
		return sqlFinal;
	}
	
	/**
	 * 
	 * @param currentCheck
	 * @param sqlQuery
	 * @return
	 */
	public static String addCondition(int currentCheck, String sqlQuery) {
		if (currentCheck == 0) {
			sqlQuery += "WHERE ";
		}
		else if (currentCheck >= 1) {
			sqlQuery += " AND ";
		}
		
		return sqlQuery;
	}
	
}
