package fr.eni.enchere.bll;

public class BLLManager {

	private static BLLManager instance;
	
	private UtilisateurBLL utilisateurManager;
	private ArticleBLL articleManager;
	
	/**
	 * 
	 * @return
	 */
	public static BLLManager getInstance() {
		if (instance == null) {
			instance = new BLLManager();
		}
		
		return instance;
	}

	/**
	 * Utilisater manager
	 * @return
	 */
	public UtilisateurBLL getUtilisaterManager() {
		if (utilisateurManager == null) {
			utilisateurManager = new UtilisateurBLL();
		}
		return utilisateurManager;
	}
	
	/**
	 * Article manager
	 * @return
	 */
	public ArticleBLL getArticleManager() {
		if (articleManager == null) {
			articleManager = new ArticleBLL();
		}
		return articleManager;
	}
}
