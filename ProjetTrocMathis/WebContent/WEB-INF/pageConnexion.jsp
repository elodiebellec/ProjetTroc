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
<link href="css/pagelogin.css" rel="stylesheet">
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

	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-6 col-sm-6 portfolio-item">
			<div class="card-body">
				<div class="position-relative">
					<form action="./Connexion" method="post">
						<div class="form-group">
							<label for="login">Identifiant : </label> <input
								class="form-control" id="login" required name="login">
						</div>
						<div class="form-group">
							<label for="password">Mot de passe : </label> <input
								class="form-control" id="mdp" required name="mdp"
								type="password">
						</div>

						<div class="form-group">
							<div class="groupecoco">
								<input type="submit" value="Connexion"
									class="btn btn-primary btn-lg"> <a
									href="Mot de passe oublié">Mot de passe oublié</a>
							</div>
						</div>


					</form>
					<div class="boutoncreate">
						<form action="./Inscription" method="get">
							<input type="submit" value="Créer un compte"
								class="btn btn-primary btn-lg btn-block">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>