<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="fr.eni.enchere.bo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.84.0">
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
  <%@include file='WEB-INF/include/header.jsp'%>
  <div class="col-lg-8 mx-auto p-3 py-md-5">
    <!-- Alert Message -->
    <c:if test="${not empty sessionScope.successMessage}">
      <div class="alert alert-success" role="alert">
        ${sessionScope.successMessage}
      </div>
    </c:if>
    <!-- Main -->
    <main>
      <h1>Accueil</h1>
      	<p class="fs-5 col-md-8">Bienvenue sur le site !
      </p>

      <hr class="col-3 col-md-2 mb-5">

      <!-- Filtre Title -->
      <h3>Filtres</h3>
      <!-- Filtre -->
      <div>
        <form action="" method="post">
          <ul>
            <!--List Step-3 -->
            <li>
              <input type="checkbox" class="custom-control-input" id="all-2-1" name="filter_01" <c:if test="${filterSelected.get(0) == true}">checked</c:if> >
              <label class="custom-control-label" for="all-2-1">enchères ouvertes</label>
            </li>
            <li>
              <input type="checkbox" class="custom-control-input" id="all-2-2" name="filter_02" <c:if test="${filterSelected.get(1)}">checked</c:if> >
              <label class="custom-control-label" for="all-2-2" >mes enchères en cours</label>
            </li>
            <li>
              <input type="checkbox" class="custom-control-input" id="all-2-3" name="filter_03" <c:if test="${filterSelected.get(2)}">checked</c:if> >
              <label class="custom-control-label" for="all-2-3">mes enchères remportées</label>
            </li>
            <!--List Step-(n) -->
          </ul>
          <!-- Rechercher -->
          <div>
            <input type="submit" value="Rechercher" class="form-control btn btn-primary" />
          </div>
        </form>
      </div>

      <div>
        <!-- Pagination -->
        <div class="eench-pagination-wrapper">
          <nav aria-label="Page">
            <ul class="pagination">
              <li class="page-item"><a class="page-link" href="#">Previous</a></li>
              <c:forEach var="i" begin="0" end="${ nbPage }" step="1">
                <li class="${i == pageIndex ? 'page-item active' : 'page-item'}">
                  <a class="page-link" href="?page=${ i }">${ i + 1 }</a>
                </li>
              </c:forEach>
              <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
          </nav>
        </div>
        <!-- For each article -->
        <c:forEach items="${articleList}" var="article">
          <div class="eench-card-article card">
            <div class="card-body">
              <h5 class="card-title">
              	<a href="DetailArticleServlet?id=${ article.getNoArticle() }">${article.getNomArticle()}</a>
              </h5>
              <p class="card-text">${article.getDescription()}</p>
            </div>
          </div>
        </c:forEach>
      </div>

    </main>
  </div>
  <!-- Footer JSP -->
  <%@include file='WEB-INF/include/footer.jsp'%>

</body>

</html>