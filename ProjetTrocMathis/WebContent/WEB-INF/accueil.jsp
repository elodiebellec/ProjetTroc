<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" isErrorPage="false" %>


<!DOCTYPE html>
<html>

<%@ include file="template/head.html" %>

<body>
>
						
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
					<li class="nav-item active"><a class="nav-link" href="./Inscription">S'inscrire
					</a><li class="nav-item active"><a class="nav-link" href="./Connexion">Se connecter
					</a></li>
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
			 <input type="text" name="barre" id="barre" value= "recherche..." />
			
			<br> <br>		
			
			<select class="form-select" aria-label="Default select example">
			 
			 	 <c:forEach var="c" items="${listeCategories}">
					 <option>${c.libelle}</option>
				</c:forEach>
			</select>
			<br> <br>		
			 <input type="button" onclick="rechercher() " value="Rechercher" />
			
		</form>
			<br>
			
			
			<c:forEach var="c" items="${listeArticleVendu}">
				<div class="card h-100">
					 <p>${c.nom_article}</p>
					 <p>${c.prix_initial}</p>
					 <p>${c.date_fin_encheres}</p>
					 <p>${c.utilisateur}</p>
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
