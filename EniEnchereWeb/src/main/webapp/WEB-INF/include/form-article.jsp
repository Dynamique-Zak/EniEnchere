<div>
<!-- Input : Article name -->
  <div class="mb-3">
      <label for="eech-nomArticle" class="form-label">Article</label>
      <input type="text" id="eech-nomArticle" name="nomArticle" class="form-control" value="${article.nomArticle}">
  </div>
  <!-- ./Input : name -->

  <!-- Input : Categorie -->
  <div class="mb-3">
      <label for="eech-nom" class="form-label">Catégorie</label>
      <select class="form-select" aria-label="Selectionner une catégorie" name="category">
		  <option selected>Selectionner une catégorie</option>
		  <c:forEach items="${categoryList}" var="cat">
		  	<option value="${cat.no_category}">${cat.libelle}</option>
		  </c:forEach>
		</select>
  </div>
  <!-- ./Input : Categorie -->

  <!-- Input : Description -->
  <div class="mb-3">
      <label for="" class="form-label">Description</label>
      <textarea name="description" class="form-control" rows="3">${article.description}</textarea>
  </div>
  <!-- ./Input : Description -->

  <!-- Photo TODO -->
  
  <!-- Input : Prix initial -->
  <div class="mb-3">
      <label for="" class="form-label">Prix initial</label>
      <input type="number" name="miseAPrix" class="form-control" value="${article.miseAPrix}">
  </div>
  <!-- ./Input : Prix initial -->

  <!-- Input : Début de l'enchère -->
  <div class="mb-3">
      <label for="" class="form-label">Début de l'enchère</label>
      <input type="date" name="dateDebutEncheres" class="form-control" value="${article.dateDebutEncheres}">
  </div>
  <!-- ./Input : Début de l'enchère -->

   <!-- Input : Fin de l'enchère -->
  <div class="mb-3">
      <label for="" class="form-label">Fin de l'enchère</label>
      <input type="date" name="dateFinEncheres" class="form-control" value="${article.dateFinEncheres}">
  </div>
  <!-- ./Input : Fin de l'enchère -->

</div>