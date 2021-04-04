<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page errorPage="error.jsp" isErrorPage="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>

<%@ include file="template/head.html"%>

<body>


	<!-- Navigation -->

<%@ include file="template/navBarConnectee.html"%>


	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-2">Liste des enchères</h1>
		<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">


			<h2>Filtres :</h2>
			<br>


			<form action="./AccueilUtilisateur" method="post">
				<div class="form-group fg--search" class="form">
					<button type="submit" value="Rechercher" class="search-button">
						<img src="image/search.png">
					</button>
					<input type="search" placeholder="Le nom de l'article contient"
						class="search-field" name="nomSelect" id="nomSelect" value="" />
				</div>
				<br> <br> <select name="categorieSelect"
					class="form-select" aria-label="Default select example">
					<c:forEach var="c" items="${listeCategories}">
						<option value="${c.no_categorie}">${c.libelle}</option>
					</c:forEach>
				</select> <br> <br> <input type="submit" value="Rechercher" />

				<div>
					<input type="radio" name="typeAffichage" value="achat">
					<label for="achat"> Achats</label><br> 
						<input type="checkbox" name="typeEnchere" value="encours"> enchères ouvertes<br>      
        				<input type="checkbox" name="typeEnchere" value="enchereUtilisateur"> mes enchères<br>      
        				<input type="checkbox" name="typeEnchere" value="remportee"> mes enchères remportées<br>      
				</div>

				<div>
					<input type="radio" name="typeAffichage" value="vente">
					<label for="vente"> Mes ventes</label><br> 
						<input type="checkbox" name="typeAchat" value="encours"> mes ventes en cours<br>      
        				<input type="checkbox" name="typeAchat" value="futur"> ventes non débutées<br>      
        				<input type="checkbox" name="typeAchat" value="termine"> ventes terminées<br>     
				</div>
			</form>
			<br>
			<c:forEach var="c" items="${listeArticleEnCours}">
				<div class="card h-100">

					<p>${c.nom_article}</p>
					<p>Prix : ${c.prix_initial} points</p>
					<p>
						Fin de l'enchère :
						<tags:localDate date="${c.date_fin_encheres}" />
					</p>
					<p>Vendeur : ${c.utilisateur.pseudo}</p>

				</div>
				<br>
			</c:forEach>


		</div>

	</div>
	<!-- /.container -->



	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>