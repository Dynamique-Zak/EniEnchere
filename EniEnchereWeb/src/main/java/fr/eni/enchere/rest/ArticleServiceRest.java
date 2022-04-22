package fr.eni.enchere.rest;

import java.util.List;

import fr.eni.enchere.bll.BLLManager;
import fr.eni.enchere.bo.Article;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/article-rest")
public class ArticleServiceRest {

	@GET
	@Path("/all")
	public List<Article> getAllArticles() {

		return BLLManager.getInstance().getArticleManager().getAllArticle();
	}
	
}
