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

	<c:if test="${!empty errors}">
		<div class="col-lg-6 col-md-4 col-sm-6 portfolio-item">
			<div class="card h-100 alert">
				<div class="card-body alert alert-danger">
					<h4 class="card-title">Erreurs</h4>
					<c:forEach var="msg" items="${errors}">
						<p class="card-text">${msg}</p>
					</c:forEach>
				</div>
			</div>
		</div>

	</c:if>

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-2">Nouvelle vente</h1>


		<form action="./VendreUnArticle" method="post">

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="nomArticle">Article : </label>
					<div class="col-md-8">

						<input class="form-control" id="nomArticle" type="text" required
							name="nomArticle">

					</div>
				</div>
			</div>

			

			 	<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="description">Description : </label>
					<div class="col-md-8">
						<textarea class="form-control" id="description" required
							name="description" rows="5" cols="30"
							></textarea>
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="categorie">Categorie </label>
					<div class="col-md-8">
						<select class="form-select" aria-label="Default select example"
							id="categorie" required name="categorie">
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

						<input type="file" id="image" required name="image"
							accept="image/png, image/jpeg">
					</div>

				</div>
			</div>



			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="prixInitial">Mise à prix : </label>
					<div class="col-md-8">
						<input type="number" class="form-control" id="prixInitial" required
							name="prixInitial">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="dateDebutEncheres">Début de
						l'enchère : </label>
					<div class="col-md-8">
						<input class="form-control" type="date" id="dateDebutEncheres" required
							name="dateDebutEncheres">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">

					<label class="col-md-2" for="dateFinEncheres">Fin de l'enchère :
					</label>
					<div class="col-md-8">
						<input class="form-control" type="date" id="dateFinEncheres" required
							name="dateFinEncheres">
					</div>
				</div>
			</div>

			<div class="card h-100">
				<div class="card-body">

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

				</div>
			</div>



			<div class="bouttons">
				<input type="submit" class="col-md-2"
					class="btn btn-outline-secondary mb-2" value="Enregistrer">
				<button type="button" class="col-md-2"
					class="btn btn-outline-secondary mb-2">Annuler</button>
			</div>  
		</form>
	</div>


</body>
</html>