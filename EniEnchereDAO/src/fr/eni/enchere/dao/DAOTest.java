package fr.eni.enchere.dao;

public class DAOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try 
		{
			boolean successLogin = DAOFactory.getInstance().getUtilisateurDAO().login("test@mail.com", "password");
			
			String message = "";
			
			System.out.println(successLogin);
		}
		catch (DALException e) 
		{
			e.printStackTrace();
		}
	}

}
