package fr.eni.enchere.dao;

public class DAOFactory {
	
	private static DAOFactory instance;
	
	private String implMode = "jdbc"; // or jdbc | mocl
	private IUtilisateurDAO utilisateurDAO;
	private IEnchereDAO enchereDAO;
	private IArticleDAO articleDAO;
	private ICategoryDAO categoryDAO;
	
	/**
	 * Get current instance
	 * @return
	 */
	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		
		return instance;
	}
	
	/**
	 * Get package prefix
	 * @param className
	 * @return
	 */
	private String getPackagePrefix(String className) {
		return "fr.eni.enchere.dao." +  implMode + "." + className + capitalizeMode(implMode);
	}
	
	private String capitalizeMode(String value) {
		String s1 = value.substring(0, 1).toUpperCase();
	    return s1 + value.substring(1);
	}
	/**
	 * Get use DAO
	 * @return
	 */
	public IUtilisateurDAO getUtilisateurDAO()  {
		if (utilisateurDAO == null) {
			try {
				utilisateurDAO = (IUtilisateurDAO) Class.forName(getPackagePrefix("UtilisateurDAO")).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return utilisateurDAO; 
	}
	
	/**
	 * Get enchere DAO
	 * @return
	 */
	public IArticleDAO getArticleDAO()  {
		if (articleDAO == null) {
			try {
				articleDAO = (IArticleDAO) Class.forName(getPackagePrefix("ArticleDAO")).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return articleDAO; 
	}
	
	/**
	 * Get category DAO
	 * @return
	 */
	public ICategoryDAO getCategoryDAO()  {
		if (categoryDAO == null) {
			try {
				categoryDAO = (ICategoryDAO) Class.forName(getPackagePrefix("CategoryDAO")).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categoryDAO; 
	}
	
	/**
	 * Get enchere DAO
	 * @return
	 */
	public IEnchereDAO getEnchereDAO()  {
		if (enchereDAO == null) {
			try {
				enchereDAO = (IEnchereDAO) Class.forName(getPackagePrefix("EnchereDAO")).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return enchereDAO; 
	}
}
