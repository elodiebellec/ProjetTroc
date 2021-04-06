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

	<%@ include file="template/navBarConnectee.html" %>

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