<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>



<!DOCTYPE html>
<html>

<%@ include file="template/head.html"%>

<head>
<title>Detail Vente</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">

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
			<div class="form-row">${articlejsp.nom_article}</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="description">Description : </label>
				<div class="col-md-8" class="card">${articlejsp.description}</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="categorie">Categorie </label>
				<div class="col-md-8">${articlejsp.categorie.libelle}</div>

			</div>
		</div>


		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="meilleureOffre"> Meilleure
					Offre : </label>
				<div class="col-md-8">${articlejsp.prix_vente} pts par </div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="miseAPrix"> Mise à prix : </label>
				<div class="col-md-8">${articlejsp.prix_initial}</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="finEnchere"> Fin de l'enchère:
				</label>
				<div class="col-md-8"><tags:localDate date="${articlejsp.date_fin_encheres}" /></div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="retrait"> Retrait : </label>
				<div class="col-md-8">

					${articlejsp.utilisateur.rue } <br>
					${articlejsp.utilisateur.code_postal} &nbsp;
					${articlejsp.utilisateur.ville }

				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="vendeur"> Vendeur : </label>
				<div class="col-md-8">${articlejsp.utilisateur.nom }</div>
			</div>
		</div>



		<c:if test="${!isProprietaireArticle}">
			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="maProposition"> Ma proposition : </label>						
					<input class="choixProposition" type="number" class="form-control" id="prixInitial" required
							name="prixInitial">
					<div class="col-md-1"></div>		
					<button type="button" class="btn btn-light">Enchérir</button>
				</div>
			</div>

		</c:if>

		<c:if test="${isProprietaireArticle}">
			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="maProposition"> Je suis le
						vendeur </label>
					<div class="col-md-8"></div>
				</div> 
			</div>

		</c:if>
</body>
</html>