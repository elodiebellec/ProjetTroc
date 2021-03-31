<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" isErrorPage="false" %>
<!DOCTYPE html>
<html>

<%@ include file="template/head.html" %>

<head>

<title>Nouvelle vente </title>
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

<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-2">Nouvelle vente </h1>
		

	
	<label for="article">Article : </label> 
	<input class="form-control" id="article" required name="article">
	
	<label for="description">Description : </label> <textarea
								class="form-control" id="description" required name="description" rows="5" cols="30"
								 ></textarea>
	
	<label for="categorie">Categorie : </label>	  
	
	<label for="photoArticle">Photo de l'article : </label>
	
	<label for="miseAPrix">Mise à prix : </label> 
		
	<label for="debutEnchere">Début de l'enchère : </label> <input class="form-control" type="date"
								id="debutEnchere" required name="debutEnchere">
	
	<label for="finEnchere">Fin de l'enchère  : </label> <input class="form-control" type="date"
								id="finEnchere" required name="finEnchere" >							
								
</body>
</html>