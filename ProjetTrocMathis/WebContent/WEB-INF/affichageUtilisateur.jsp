<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
					<li class="nav-item active"><a class="nav-link" href="./AccueilUtilisateur">Accueil
					</a></li>
					<li class="nav-item active"><a class="nav-link" href="./Deconnexion">Se deconnecter
					</a>
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
	
	
	
    <div class="container">
      <h1>Profil de Elodie</h1>
      <table class="table table-dark">
        <thead>
          <tr>
            <th>Pseudo</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Email</th>
            <th>Telephone</th>
            <th>Rue</th>
            <th>Code Postal</th>
            <th>Ville</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>${users.pseudo}</td>
            <td>${users.nom}</td>
            <td>${users.prenom}</td>
            <td>${users.email}</td>
            <td>${users.telephone}</td>
            <td>${users.rue}</td>
            <td>${users.code_postal}</td>
             <td>${users.ville}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </body>
  <footer>
  </footer>
  
  
  <script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</html>