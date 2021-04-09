<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="template/head.html"%>
</head>

<body>


	<!-- Navigation -->

	<%@ include file="template/navBarDeconnectee.html"%>


	<!-- Page Content -->
	<div class="container portfolio-item accueilContainer">

		<!-- Page Heading -->

		<div class="titre">
			<h1 class="my-2">Liste des enchères</h1>
		</div>



		<div class="filtres">
			<h2>Filtres :</h2>
			<form action="./Accueil" method="post" class="form">
				<div class="flitresForm">
					<div class="loupeForm">
						<button type="submit" value="Rechercher" class="search-button">
							<img src="image/search.png">
						</button>
						<input type="search" placeholder="Le nom de l'article contient"
							class="search-field" name="nomSelect" id="nomSelect" value="" />
					</div>
					<div class="categoriesForm">
						<p class="formCategoriesItem">Catégories :</p>
						<select name="categorieSelect" class="formCategoriesItem"
							aria-label="Default select example">
							<c:forEach var="c" items="${listeCategories}">
								<option value="${c.no_categorie}">${c.libelle}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<input type="submit" value="Rechercher"
					class="flitresForm btn $gray-400 btn-lg" />
			</form>
		</div>

		<div class="articles">
			<c:forEach var="c" items="${listeArticleEnCours}">
				<div class="card h-100 articleCard">

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
