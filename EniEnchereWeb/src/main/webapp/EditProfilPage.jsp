<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Mon Profil</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <!-- Eni Enchere style -->
        <link href="vendor/eench/eench-style.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>

    <body>
   	  	<!-- Header JSP -->
  		<%@include file='WEB-INF/include/header.jsp'%>
  		<!-- Page Wrapper -->
        <div class="eech-page-wrapper">
            <h2 class="eench-h-title">ENI Enchères</h2>

            <h3 class="text-center eench-h-subtitle">Mon Profil</h3>
            <form method="post" action="edit-profil">
            	<!-- Form User -->
    			<%@include file='WEB-INF/include/form-user.jsp'%>
                <!-- Buttons  -->
                <div class="d-grid gap-2 d-flex">
                    <input type="submit" class="btn btn-primary col-4" value="Enregistrer" />
                    <a href="delete-profil" class="btn btn-primary col-4">Supprimer mon Compte</a>
                    <a href="my-profil" class="btn btn-primary col-4">Retour</a>
                </div>

            </form>
        </div>
    </body>

    </html>