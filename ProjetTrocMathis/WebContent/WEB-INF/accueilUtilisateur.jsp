<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" isErrorPage="false" %>
<!DOCTYPE html>
<html>

<%@ include file="template/head.html" %>

<body>
>
						
<!-- Navigation -->
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
					<li class="nav-item active"><a class="nav-link" href="./MonCompte">Mon compte
					</a><li class="nav-item active"><a class="nav-link" href="./Deconnexion">Se deconnecter
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-2">Liste des enchères</h1>
		<form action="./AffichageUtilisateur" method="post">
		<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">
			<div class="card h-100">
			
			<div class="form-group">
			</div>	
			</div>
			<div class="form-group">
							<label for="password">Pseudo </label> <input
								class="form-control" id="psd" required name="psd"
								type="text">	
		<button type="submit" class="btn btn-primary btn-lg btn-block" id="belodie" name="belodie">Rechercher</button>	
		
		</div>
		
		</div>
		</form>
	</div>
	<!-- /.container -->

	

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>