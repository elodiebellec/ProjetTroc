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


	<%@ include file="template/navBarDeconnectee.html" %>


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
		<h1 class="my-2">${usermax} a remport&eacute; l'ench&egrave;re</h1>


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
				<label class="col-md-2" for="meilleureOffre"> Meilleure
					Offre : </label>

				<div class="col-md-8">${montantmaximum} pts par ${usermax}</div>

			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="miseAPrix"> Mise &agrave; prix : </label>
				<div class="col-md-8">${articlejsp.prix_initial} points</div>
			</div>
		</div>
		
		<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="finEnchere"> Fin de l'ench&egrave;re:
					</label>
					<div class="col-md-8">
						<tags:localDate date="${articlejsp.date_fin_encheres}" />
					</div>
				</div>
			</div>
			
			

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="retrait"> Retrait : </label>
				<div class="col-md-8">

					${articlejsp.utilisateur.rue } <br>
					${articlejsp.utilisateur.code_postal} 
					${articlejsp.utilisateur.ville }

				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-row">
				<label class="col-md-2" for="vendeur"> Vendeur : </label>
				<div class="col-md-8">${articlejsp.utilisateur.pseudo}</div>
			</div>
		</div>



		<div class="col-md-1"></div>
						<button type="submit" class="btn btn-light">Retrait effectué</button>
					</div>
		

		
</body>
</html>