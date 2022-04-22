<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="fr.eni.enchere.bo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Starter Template - Bootstrap v5.0</title>

  <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/starter-template/">

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

  <!-- Eni Enchere style -->
  <link href="vendor/eench/eench-style.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="vendor/starter-template.css" rel="stylesheet">
  
</head>

<body>
  <!-- Header JSP -->
  <%@include file='include/header.jsp'%>
  <div class="col-lg-8 mx-auto p-3 py-md-5">
    <!-- Alert Message -->
    <c:if test="${not empty sessionScope.successMessage}">
      <div class="alert alert-success" role="alert">
        ${sessionScope.successMessage}
      </div>
    </c:if>
    <!-- Main -->
    <main>
      <!-- Title  -->
      <h1 class="eench-h-bold">Article</h1>
      <h2>${ article.getNomArticle() }</h2>
      <hr>

      <img src="img/article_mock.jpg" class="img-fluid"/>

      <!-- Cat -->
      <div class="row eench-label-group">
        <div class="col-6"><label>Catégorie</label></div>
        <div class="col-6">${ article.getCategory().getLibelle() }</div>
      </div>

       <!-- Meilleur offre -->
       <div class="row eench-label-group">
        <div class="col-6"><label>Meilleure offre</label></div>
        <div class="col-6">
          <c:choose>
            <c:when test="${not empty article.getTopEnchere() }">
              ${ article.getTopEnchere().getMontantEnchere() } points
            </c:when>
            <c:otherwise>
              Aucune enchère
            </c:otherwise>
          </c:choose>
        </div>
      </div>

       <!-- Mis à prix -->
       <div class="row eench-label-group">
        <div class="col-6"><label>Mis à prix</label></div>
        <div class="col-6">${ article.getMiseAPrix() } points</div>
      </div>

       <!-- date fin enchère -->
       <div class="row eench-label-group">
        <div class="col-6"><label>Fin de l'enchère</label></div>
        <div class="col-6">${ article.getDateFinEncheresString() }</div>
    	</div>

       <!-- Retrait  -->
       <div class="row eench-label-group">
        <div class="col-6"><label>Retrait</label></div>
        <div class="col-6">
          <c:choose>
            <c:when test="${not empty article.getRetrait() }">
              ${ article.getRetrait().getRue() } 
              <br> ${ article.getRetrait().getCodePostal() } ${ article.getRetrait().getVille() } 
            </c:when>
            <c:otherwise>
              Aucun retrait
            </c:otherwise>
          </c:choose>
        </div>
      </div>

      <!-- Vendeur -->
      <div class="row eench-label-group">
        <div class="col-6"><label>Vendeur</label></div>
        <div class="col-6">${ article.getVendeur().getPseudo() }</div>
      </div>

      <!-- Enchérir -->
      <div class="row eench-label-group">
        <div class="col-6"><label>Ma proposition</label></div>
        <div class="col-6">
          <form method="post" action="DetailArticleServlet?id=${ article.getNoArticle() }">
            <input type="text" class="eench-encherir-input" value="0" name="montant_enchere" />
            <input type="submit" value="Enchérir" />
          </form>
        </div>
      </div>

    </main>
  </div>
  <!-- Footer JSP -->
  <%@include file='include/footer.jsp'%>

</body>

</html>