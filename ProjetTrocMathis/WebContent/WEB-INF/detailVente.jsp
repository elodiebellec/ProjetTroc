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
<title>D&eacute;tail vente</title>


</head>


<body>

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
		<form action="./DetailVente" method="post">
			<!-- Page Heading -->
			<h1 class="my-2">D&eacute;tail vente</h1>


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
					<label class="col-md-2" for="categorie">Cat&eacute;gorie </label>
					<div class="col-md-8">${articlejsp.categorie.libelle}</div>

				</div>
			</div>


			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="meilleureOffre"> Meilleure
						Offre : </label>

					<div class="col-md-8">${montantmaximum}De ${usermax}</div>

				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="miseAPrix"> Mise 	&agrave; prix : </label>
					<div class="col-md-8">${articlejsp.prix_initial}</div>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<label class="col-md-2" for="finEnchere"> Fin de l'ench	&egrave;re:
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
					<div class="col-md-8">${articlejsp.utilisateur.nom }</div>

				</div>
			</div>



			<c:if test="${!isProprietaireArticle}">
				<div class="form-group">
					<div class="form-row">
						<label class="col-md-2" for="maProposition"> Ma
							proposition : </label> <input class="choixProposition" type="number"
							class="form-control" id="prixenchere" required name="prixenchere">
						<div class="col-md-1"></div>
						<button type="submit" class="btn btn-light">Ench&eacute;rir</button>
					</div>
				</div>

			</c:if>
     
			<c:if test="${isProprietaireArticle && isDateModifiable}">
				<div class="form-group">
					<div class="form-row">
					
					
						<button type="button" class="btn btn-light">Modifier la
							vente</button>
						<div class="col-md-8"></div>
					</div>
				</div>

			</c:if>
			

     
			<c:if test="${isProprietaireArticle && !isDateModifiable}">
				<div class="form-group">
					<div class="form-row">
						<div>L'ench	&egrave;re a deja commenc&eacute;, vous ne pouvez pas la
							modifier</div>
						<div class="col-md-8"></div>
					</div>
				</div>


			</c:if>
		</form>
		</div>
</body>
</html>