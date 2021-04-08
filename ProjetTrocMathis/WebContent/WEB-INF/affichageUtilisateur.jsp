<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="template/head.html" %>
</head>
<body>

<%@ include file="template/navBarNavigation.html" %>

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
	
	
	
 <div class="card-body" >
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-4 col-md-6 col-sm-6 portfolio-item">
			

					<h1 class="h1personne">Profil de ${users.pseudo}</h1>


					<table class="table table-hover">
					
						<tr>
							<th scope="row"></th>
							<td>Nom :</td>
							<td>${users.nom}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Prénom :</td>
							<td>${users.prenom}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Email :</td>
							<td>${users.email}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Téléphone :</td>
							<td>${users.telephone}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Rue :</td>
							<td>${users.rue}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Code Postal :</td>
							<td>${users.code_postal}</td>
						</tr>
						<tr>
							<th scope="row"></th>
							<td>Ville :</td>
							<td>${users.ville}</td>
						</tr>
						
					</table>


				</div>
			</div>
		</div>
	</div>


</body>
  
  
  <script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</html>