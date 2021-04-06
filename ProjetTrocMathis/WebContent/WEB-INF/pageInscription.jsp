<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page errorPage="error.jsp" isErrorPage="false"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="ENI Ecole">
<meta name="author" content="ENI Ecole">

<title>Projet TROC</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/pageinscription.css" rel="stylesheet">
<link rel="icon" href="images/favicon.ico">

</head>

<body>

	<!-- Navigation -->


<%@ include file="template/navBarDeconnectee.html"%>
	

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
	<header>
		<h1>Mon profil</h1>

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
								<label for="password">Mot De Passe</label> <input
									type="password" class="form-control" id="motdepasse"
									name="motdepasse" placeholder="Mot de passe">
							</div>
							<div class="form-group">
								<label for="password2">Confirmation du Mot De Passe</label> <input
									type="password" class="form-control"
									id="motdepasseconfirmation" name="motdepasseconfirmation"
									placeholder="Mot de passe">
							</div>
							<div class="form-group">
								<label for="nom">Nom</label> <input type="text"
									class="form-control" id="identite" name="nom"
									aria-describedby="nomHelp" placeholder="Entrer votre Nom">
							</div>
							<div class="form-group">
								<label for="prenom">Prenom</label> <input type="text"
									class="form-control" id="identite" name="prenom"
									aria-describedby="prenomHelp" placeholder="Entrer votre Prenom">
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 portfolio-item">
							<div class="form-group">
								<label for="email">Email</label> <input type="email"
									class="form-control" id="email" name="email"
									aria-describedby="emailHelp" placeholder="Entrer votre Email">
							</div>

							<div class="form-group">
								<label for="telephone">Telephone</label> <input type="text"
									class="form-control" id="telephone" name="telephone"
									placeholder="Entrer votre telephone">
							</div>

							<div class="form-group">
								<label for="rue">Rue</label> <input type="text"
									class="form-control" id="rue" name="rue"
									placeholder="Entrer votre rue">
							</div>
							<div class="form-group">
								<label for="codepostal">Code Postal</label> <input type="text"
									class="form-control" id="codepostal" name="codepostal"
									placeholder="Entrer votre CP">
							</div>
							<div class="form-group">
								<label for="codepostal">Ville</label> <input type="text"
									class="form-control" id="ville" name="ville"
									placeholder="Entrer votre ville">
							</div>


						</div>
					</div>
				</div>
			</div>
			<div class="boutonsubmit">
				<button type="submit" class="btn btn-primary ">Créer</button>
				<a href="${pageContext.servletContext.contextPath}/Accueil"><input
					type="button" class="btn btn-primary  " value="Annuler"></a>

			</div>
		</form>
		
</header>





</body>
</html>