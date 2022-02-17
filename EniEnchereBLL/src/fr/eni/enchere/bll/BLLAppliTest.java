package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dao.DALException;

public class BLLAppliTest {

	public static void main(String[] args) {
		//boolean successLogin = BLLManager.getInstance().getUtilisaterManager().login("test@mail.com", "password");
		
		Utilisateur user = new Utilisateur(-1, "dsfdsf", "sdqsdqs", "qdqsd", "qsdsqdqs",
				"sdqsd", "qsdqsd", "qsqsd", "qsdqsdqsd", "sdfdf", 0, 0);
		
		BLLManager.getInstance().getUtilisaterManager().registerUser(user);
		String message = "";

		
		System.out.println(message);

	}

}
