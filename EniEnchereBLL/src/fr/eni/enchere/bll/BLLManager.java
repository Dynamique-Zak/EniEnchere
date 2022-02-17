package fr.eni.enchere.bll;

public class BLLManager {

	private static BLLManager instance;
	
	private UtilisateurBLL utilisateurManager;
	
	public static BLLManager getInstance() {
		if (instance == null) {
			instance = new BLLManager();
		}
		
		return instance;
	}

	public UtilisateurBLL getUtilisaterManager() {
		if (utilisateurManager == null) {
			utilisateurManager = new UtilisateurBLL();
		}
		return utilisateurManager;
	}
}
