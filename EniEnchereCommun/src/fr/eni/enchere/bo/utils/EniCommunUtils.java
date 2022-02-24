package fr.eni.enchere.bo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;

public class EniCommunUtils {

	/**
	 * Convertir une date string en Date
	 * @param maDateSQLStr
	 * @return
	 */
	public static Date convertDate(String maDateSQLStr) {
		// Outil formatteur
		SimpleDateFormat simpleDateFormat =
		        new SimpleDateFormat("yyyy-MM-dd", new Locale("EN"));
		
		try {
			return simpleDateFormat.parse(maDateSQLStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Convertir une Date en String
	 * @param maDateSQLStr
	 * @return
	 */
	public static String convertDateToString(Date maDate) {
		// Outil formatteur
		SimpleDateFormat simpleDateFormat =
		        new SimpleDateFormat("yyyy-MM-dd", new Locale("EN"));
		
		return simpleDateFormat.format(maDate);
	}
	
	public static String todayStr() {
		return EniCommunUtils.convertDateToString(new Date());
	}
	
	/**
	 * Convertir une Date en String
	 * @param maDateSQLStr
	 * @return
	 */
	public static String displayDateToFrench(Date maDate) {
		// Outil formatteur
		SimpleDateFormat simpleDateFormat =
		        new SimpleDateFormat("dd/MM/yyyy", new Locale("FR"));
		
		return simpleDateFormat.format(maDate);
	}
	
	public static List<Article> getArticleListFromEncheresList(List<Enchere> listEnchere){
		
		List<Article> listArticle = new ArrayList<Article>();
		
		for (Enchere enchere : listEnchere) {
			if (!listArticle.contains(enchere.getArticle())) {
				listArticle.add(enchere.getArticle());
			}
		}
		
		return listArticle;
	}
}
