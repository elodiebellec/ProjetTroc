<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="template/head.html"%>
<title>Page Connexion</title>
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