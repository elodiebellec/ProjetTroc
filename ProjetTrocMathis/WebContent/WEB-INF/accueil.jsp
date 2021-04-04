<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>

<head>
<%@ include file="template/head.html" %>
</head>

<body>

						
<!-- Navigation -->

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">ENI Encheres</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="./Inscription">S'inscrire </a>
					<li class="nav-item active"><a class="nav-link"
						href="./Connexion">Se connecter </a></li>
				</ul>
			</div>
		</div>
	</nav>


	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-2">Liste des enchères</h1>
		<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">

			
				<h2>Filtres :</h2>
				<br>
			

		<form action="./Accueil" method="post">
			<div class="form-group fg--search" class="form">
				<button type="submit" value="Rechercher" class="search-button"><img src="image/search.png"></button>			
				<input type="search" placeholder="Le nom de l'article contient"  class="search-field" name="nomSelect" id="nomSelect" value= "" />
			</div>		
			<br> <br>				
			<select name="categorieSelect" class="form-select" aria-label="Default select example">			 
			 	 <c:forEach var="c" items="${listeCategories}">
					 <option value="${c.no_categorie}">${c.libelle}</option>
				</c:forEach>
			</select>
			<br> <br>		
			 <input type="submit" value="Rechercher" />			
		</form>
			<br>
			<c:forEach var="c" items="${listeArticleEnCours}">
				<div class="card h-100">	
	
					 <p>${c.nom_article}</p>
					 <p>Prix : ${c.prix_initial} points</p>				
       				 <p>Fin de l'enchère : <tags:localDate date="${c.date_fin_encheres}"/></p>	       				
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
