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
      <h1 class="eench-h-bold">Nouvelle vente</h1>
      <hr>

	  <!-- Form -->
      <%@include file='include/form-article.jsp'%>
      
      <!-- Buttons  -->
      <div class="d-grid gap-2 d-flex">
      	<input type="submit" class="btn btn-primary col-6" value="Enregistrer" />
      	<a href="home" class="btn btn-primary col-6">Annuler</a>
      </div>
                
    </main>
  </div>
  <!-- Footer JSP -->
  <%@include file='include/footer.jsp'%>

</body>

</html>