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
<title>Vente finie</title>
</head>

<body>

	<!-- Navigation -->

	<%@ include file="template/navBarNavigation.html"%>


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
		<div class="titre">
			<h1 class="my-2">${usermax}&nbsp;a&nbsp;remport&eacute;&nbsp;l'ench&egrave;re</h1>
		</div>

		<div class="container">
			<div class="row">
				<div  class="col-4"></div>
				<div class="container col-8">
					<div class="row">
						<h2 class="col-12">${articlejsp.nom_article}</h2>
					</div>
					<br>
					<div class="row">
						<label class="col-4" for="description">Description : </label>
						<div class="col-8">${articlejsp.description}</div>
					</div>
					<br>
					<div class="row">
						<label class="col-4" for="meilleureOffre"> Meilleure Offre
							: </label>
						<div class="col-8">${montantmaximum}&nbsp;pts&nbsp;par&nbsp;${usermax}</div>
					</div>
					<br>
					<div class="row">
						<label class="col-4" for="miseAPrix"> Mise&nbsp;&agrave;&nbsp;prix :
						</label>
						<div class="col-8">${articlejsp.prix_initial}&nbsp;points</div>
					</div>
					<br>
					<div class="row">
						<label class="col-4" for="finEnchere"> Fin de
							l'ench&egrave;re: </label>
						<div class="col-8">
							<tags:localDate date="${articlejsp.date_fin_encheres}" />
						</div>
					</div>
					<br>
					<div class="row">
						<label class="col-4" for="retrait"> Retrait : </label>
						<div class="col-8">
							${articlejsp.utilisateur.rue } <br>
							${articlejsp.utilisateur.code_postal}
							${articlejsp.utilisateur.ville }
						</div>
					</div>
					<br>
					<div class="row">
						<label class="col-4" for="vendeur"> Vendeur : </label>
						<div class="col-8">${articlejsp.utilisateur.pseudo}</div>
					</div>
					<br>
					<div class="row">
						<button class="col-3" type="submit" class="btn btn-light">Retrait
							effectué</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>