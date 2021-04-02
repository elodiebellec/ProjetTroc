<%@page import="fr.eni.projettroc.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="template/head.html"%>
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
						href="./VendreUnArticle">Nouvelle Vente </a></li>
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



	<div class="card-body" >
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-4 col-md-6 col-sm-6 portfolio-item">
			

					<h1 class="h1profil">Votre profil</h1>


					<table class="table table-hover">
						<tr>
							<th scope="row"></th>
							<td>Pseudo :</td>
							<td>${user.pseudo}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Nom :</td>
							<td>${user.nom}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Prenom :</td>
							<td>${user.prenom}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Email :</td>
							<td>${user.email}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Telephone :</td>
							<td>${user.telephone}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Rue :</td>
							<td>${user.rue}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Code Postal :</td>
							<td>${user.code_postal}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Ville :</td>
							<td>${user.ville}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Crédit :</td>
							<td>${user.credit}</td>
						</tr>
					</table>

					<form action="./ModificationUtilisateur" method="get" >
						<input type="submit" value="Modifier" class="btn btn-primary boutonmodif">
					</form>

				</div>
			</div>
		</div>
	</div>


</body>


<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</html>