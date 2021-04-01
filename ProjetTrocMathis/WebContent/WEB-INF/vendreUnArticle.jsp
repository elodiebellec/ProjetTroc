<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page errorPage="error.jsp" isErrorPage="false"%>
<!DOCTYPE html>
<html>

<%@ include file="template/head.html"%>

<head>

<title>Nouvelle vente</title>
</head>
<body>
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
						href="./AccueilUtilisateur">Accueil </a></li>
					<li class="nav-item active"><a class="nav-link"
						href="./Deconnexion">Se deconnecter </a>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-2">Nouvelle vente</h1>


		<form action="./VendreUnArticle" method="post">

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="article">Article : </label>
					<div class="col-md-8">
						<input class="form-control" id="article" type="text" required
							name="article">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="description">Description : </label>
					<div class="col-md-8">
						<textarea class="form-control" id="description" required
							name="description" rows="5" cols="30"></textarea>
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="categorie">Categorie </label>
					<div class="col-md-8">
						<select class="form-select" aria-label="Default select example">
							<c:forEach var="c" items="${listeCategories}">
								<option>${c.libelle}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>


			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="photoArticle">Photo de
						l'article : </label>
					<div class="col-md-8">

						<label for="fichier">UPLOADER</label> <input type="file"
							class="form-control-file" id="fichier">
					</div>
					<a button type="button" class="btn btn-light"
						class="shadow-lg p-3 mb-5 bg-white" href="#">UPLOADER
						</button>
					</a>
				</div>
			</div>



			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="miseAPrix">Mise à prix : </label>
					<div class="col-md-8">
						<input type="number" class="form-control" id="miseAPrix">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="debutEnchere">Début de
						l'enchère : </label>
					<div class="col-md-8">
						<input class="form-control" type="date" id="debutEnchere" required
							name="debutEnchere">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">

					<label class="col-md-2" for="finEnchere">Fin de l'enchère :
					</label>
					<div class="col-md-8">
						<input class="form-control" type="date" id="finEnchere" required
							name="finEnchere">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="rue">Rue : </label>
					<div class="col-md-8">
						<input class="form-control" id="rue" type="text" required
							name="rue">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="codePostal">Code Postal : </label>
					<div class="col-md-8">
						<input class="form-control" id="codePostal" type="text" required
							name="codePostal">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="ville">Ville : </label>
					<div class="col-md-8">
						<input class="form-control" id="ville" type="text" required
							name="ville">
					</div>
				</div>
			</div>
</body>
</html>