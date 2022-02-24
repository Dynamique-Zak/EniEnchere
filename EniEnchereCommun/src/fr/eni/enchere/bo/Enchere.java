package fr.eni.enchere.bo;

import java.util.Date;

import fr.eni.enchere.bo.utils.EniCommunUtils;

public class Enchere {

	private Date dateEnchere;
	private int montantEnchere;
	private Utilisateur user;
	private Article article;
	
	/**
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(Date dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	/**
	 * @return the dateEnchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}
	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	/**
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}
	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the user
	 */
	public Utilisateur getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Utilisateur user) {
		this.user = user;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return String.format("Enchere [dateEnchere=%s, montantEnchere=%d] \n %s", EniCommunUtils.displayDateToFrench(dateEnchere), montantEnchere, article);
	}
	
	
}
