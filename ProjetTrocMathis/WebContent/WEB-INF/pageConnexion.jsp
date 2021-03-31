<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" isErrorPage="false" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="template/head.html" %>
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
					<li class="nav-item active"><a class="nav-link" href="./Accueil">Accueil
					</a></li>
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

<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-6 portfolio-item">
				<div class="card-body">
					<form action="./Connexion" method="post">
						<div class="form-group">
							<label for="login">Login : </label> <input class="form-control"
								id="login" required name="login">
						</div>
						<div class="form-group">
							<label for="password">Password : </label> <input
								class="form-control" id="mdp" required name="mdp"
								type="password">
						</div>
						<div class="form-group">
							<input type="submit" value="OK" class="btn btn-primary">
						</div>
					</form>
					<form action="./Inscription" method="get">
					<input type="submit" value="Créer un compte" class="btn btn-primary">
					</form>
				</div>
			</div>
		</div>
	

</body>
</html>