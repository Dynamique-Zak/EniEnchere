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
         <li class="nav-item rounded">
           <a class="nav-link" href="#"><i class="bi bi-people-fill me-2"></i>About</a>
         </li>
         <li class="nav-item rounded">
           <a class="nav-link" href="#"><i class="bi bi-telephone-fill me-2"></i>Contact</a>
         </li>
       </ul>
     </div>
   </div>
 </nav>