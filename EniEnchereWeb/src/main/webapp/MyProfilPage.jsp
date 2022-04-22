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
        	<!-- Info User -->
           	<div>
				<!-- Label Info : Pseudo -->
				  <div class="row eech-label-wrapper">
				      <label class="form-label col-4">Pseudo : </label>
				      <div class="col-8">${user.pseudo}</div>
				  </div>
				  <!-- ./Label Info : Pseudo -->
				
				  <!-- Label Info : Nom -->
					  <div class="row eech-label-wrapper">
				      <label for="eech-nom" class="form-label col-4">Nom</label>
				      <div class="col-8">${user.nom}</div>
				  </div>
				  <!-- ./Label Info : Nom -->
				
				  <!-- Label Info : Prenom -->
	  			  <div class="row eech-label-wrapper">
				      <label for="" class="form-label col-4">Prenom</label>
				      <div class="col-8">${user.prenom}</div>
				  </div>
				  <!-- ./Label Info : Prenom -->
				
				  <!-- Label Info : Email -->
				  <div class="row eech-label-wrapper">
				      <label for="" class="form-label col-4">Email</label>
				      <div class="col-8">${user.email}</div>
				  </div>
				  <!-- ./Label Info : Email -->
				
				  <!-- Label Info : Telephone -->
				  <div class="row eech-label-wrapper">
				      <label for="" class="form-label col-4">Telephone</label>
				      <div class="col-8">${user.telephone}</div>
				  </div>
				  <!-- ./Label Info : Telephone -->
				
				  <!-- Label Info : Rue -->
				  <div class="row eech-label-wrapper">
				      <label for="" class="form-label col-4">Rue</label>
				      <div class="col-8">${user.rue}</div>
				  </div>
				  <!-- ./Label Info : Rue -->
				
				  <!-- Label Info : Code postale -->
				  <div class="row eech-label-wrapper">
				      <label for="" class="form-label col-4">Code postale</label>
				      <div class="col-8">${user.codePostal}</div>
				  </div>
				  <!-- ./Label Info : Code postale -->
				
				  <!-- Label Info : City -->
				  <div class="row eech-label-wrapper">
				      <label for="eech-citycode" class="form-label col-4">Ville</label>
				      <div class="col-8">${user.ville}</div>
				  </div>
				  <!-- ./Label Info : City Code -->
			
				</div>
               <!-- Buttons  -->
               <div class="d-grid gap-2 d-flex">
                   <a href="edit-profil" class="btn btn-primary w-100">Modifier</a>
               </div>

        </div>
    </body>

    </html>