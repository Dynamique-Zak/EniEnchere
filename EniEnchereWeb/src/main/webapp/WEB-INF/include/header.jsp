<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <div class="navbar-brand">
        <c:if test="${sessionScope.logged}">
         Bonjour, vous êtes connecté !
       	</c:if>
     </div>
     <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
       aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarNav">
       <ul class="navbar-nav ms-md-auto gap-2">
         <li class="nav-item rounded">
           <a class="nav-link active" aria-current="page" href="#"><i class="bi bi-house-fill me-2"></i>Home</a>
         </li>
         <!-- Mon profil -->
         <c:if test="${sessionScope.logged}">
	         <li class="nav-item rounded">
	           <a class="nav-link" href="my-profil"><i class="bi bi-people-fill me-2"></i>Mon Profil</a>
	         </li>
         </c:if>
       </ul>
     </div>
   </div>
 </nav>