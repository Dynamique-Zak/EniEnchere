package fr.eni.enchere.bll.utils;

public class EniConstantes {

	public final static int CODE_SUCCESS = 200;
	public final static int CODE_ERROR_TECH = 600;
	
	/**
	 * Veirifer si 
	 * @param vendeur
	 * @param idUtilisateur
	 * @return
	 */
	public static boolean verifyIsVendeur(int vendeur, int idUtilisateur) {
		return (vendeur == idUtilisateur);
	}
}
