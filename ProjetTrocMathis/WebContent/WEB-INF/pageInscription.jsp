<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<%@ include file="template/head.html"%>
<title>Page Inscription</title>
</head>

<body>

	<!-- Navigation -->



<%@ include file="template/navBarDeconnecteeSans.html"%>
	


	<c:if test="${!empty errors}">
		<div class="col-lg-6 col-md-4 col-sm-6 portfolio-item ">
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
	<header>
		<div class="card-body">
			<div class="container">
				<div class="row">
				<div class="titre">
			<h1 class="my-2">Mon Profil</h1>
		</div>
					
				</div>
			</div>
		</div>
		<form action="./Inscription" method="post">
			<div class="card-body">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 portfolio-item">

							<div class="form-group">
								<label for="pseudo">Pseudo</label> <input type="text"
									class="form-control" id="pseudo" name="pseudo"
									aria-describedby="pseudoHelp" placeholder="Entrer votre pseudo">
							</div>

						<div class="form-group">
							<label for="prenom">Pr&eacute;nom</label> <input type="text"
								class="form-control" id="identite" name="prenom"
								aria-describedby="prenomHelp" placeholder="Entrer votre Prenom">
						</div>

						<div class="form-group">
							<label for="telephone">T&eacute;l&eacute;phone</label> <input
								type="text" class="form-control" id="telephone" name="telephone"
								placeholder="Entrer votre telephone">
						</div>

						<div class="form-group">
							<label for="codepostal">Code Postal</label> <input type="text"
								class="form-control" id="codepostal" name="codepostal"
								placeholder="Entrer votre CP">
						</div>

						<div class="form-group">
							<label for="password">Mot De Passe</label> <input type="password"
								class="form-control" id="motdepasse" name="motdepasse"
								placeholder="Mot de passe">
						</div>

					</div>

					<div class="col-lg-6 col-md-6 col-sm-6 portfolio-item">

						<div class="form-group">
							<label for="nom">Nom</label> <input type="text"
								class="form-control" id="identite" name="nom"
								aria-describedby="nomHelp" placeholder="Entrer votre Nom">
						</div>

						<div class="form-group">
							<label for="email">Email</label> <input type="email"
								class="form-control" id="email" name="email"
								aria-describedby="emailHelp" placeholder="Entrer votre Email">
						</div>

						<div class="form-group">
							<label for="rue">Rue</label> <input type="text"
								class="form-control" id="rue" name="rue"
								placeholder="Entrer votre rue">
						</div>

						<div class="form-group">
							<label for="codepostal">Ville</label> <input type="text"
								class="form-control" id="ville" name="ville"
								placeholder="Entrer votre ville">
						</div>
						<div class="form-group">
							<label for="password2">Confirmation</label> <input
								type="password" class="form-control" id="motdepasseconfirmation"
								name="motdepasseconfirmation" placeholder="Mot de passe">
						</div>

					</div>
				</div>
			</div>

			<div class="userSubmit">
				<button type="submit" class="btn btn-primary boutonsubmit">Cr&eacute;er</button>
				<a href="${pageContext.servletContext.contextPath}/Accueil"><input
					type="button" class="btn btn-primary boutonsubmit" value="Annuler"></a>

			</div>
		</form>

	</header>





</body>
</html>