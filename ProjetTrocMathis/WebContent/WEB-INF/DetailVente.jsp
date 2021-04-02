<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page errorPage="error.jsp" isErrorPage="false"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<%@ include file="template/head.html"%>

<head>
<title>Detail Vente</title>
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
		<h1 class="my-2">Détail vente</h1>

		<div class="form-group">
			<div class="form-row">

				<input class="form-control" id="nomArticle" type="text" required
					name="nomArticle"
				<!--   value="${sessionScope.detailVente.nomArticle}"-->
				>
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
					<select class="form-select" aria-label="Default select example"
						id="categorie" required name="categorie">
						<c:forEach var="c" items="${listeCategories}">
							<option value="${c.no_categorie}">${c.libelle}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="meilleureOffre"> Meilleure
					Offre : </label>
				<div class="col-md-8">

					<!-- <input class="form-control" id="nomArticle" type="text" required
							name="nomArticle"> -->

				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="miseAPrix"> Mise à prix : </label>
				<div class="col-md-8">

					<!-- <input class="form-control" id="nomArticle" type="text" required
							name="nomArticle"> -->

				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="finEnchere"> Fin de l'enchère:
				</label>
				<div class="col-md-8">

					<!-- <input class="form-control" id="nomArticle" type="text" required
							name="nomArticle"> -->

				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="retrait"> Retrait : </label>
				<div class="col-md-8">

					<!-- <input class="form-control" id="nomArticle" type="text" required
							name="nomArticle"> -->

				</div>
			</div>
		</div>
		
		<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="vendeur"> Vendeur : </label>
					<div class="col-md-8">

					<!-- <input class="form-control" id="nomArticle" type="text" required
							name="nomArticle"> -->	

					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="maProposition"> Ma proposition : </label>
					<div class="col-md-8">

					<!-- <input class="form-control" id="nomArticle" type="text" required
							name="nomArticle"> -->	

					</div>
				</div>
			</div>
			
			
</body>
</html>