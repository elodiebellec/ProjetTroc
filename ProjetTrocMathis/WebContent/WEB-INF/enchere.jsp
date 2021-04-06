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
						href="./MonCompte">Mon compte </a>
					<li class="nav-item active"><a class="nav-link"
						href="./Deconnexion">Se deconnecter </a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<form action="./Enchere" method="post">
			<div class="card-body">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 portfolio-item">
		<!-- Page Heading -->
		<h1 class="my-2">Liste des enchères</h1>
		<div class="col-lg-6 col-md-4 col-sm-6 portfolio-item">

		<h1>PC Gamer pour travailler</h1>
		
		<p>Categorie : Informatique</p>
		
		<p> Mise a prix  100 points </p>
			
			<div class="form-group">
				<div class="form-row">
					<label class="col-md-4" for="prixInitial">Mise à prix : </label>
					<div class="col-md-8">
						<input type="number" class="form-control" id="prixenchere" required
							name="prixenchere">
					</div>
					<button type="submit" class="btn btn-primary">Enchérir</button>
					
				</div>
			</div>
	</div>
	</div>
	</div>
	</div>
	

		</div>
</form>
	</div>
	<!-- /.container -->



	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>