<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@page import="fr.eni.projettroc.bo.Utilisateur"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="template/head.html" %>
</head>

<body>


	<!-- Navigation -->


	<%@ include file="template/navBarConnectee.html"%>

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->

		<h1 class="my-2">Liste des encheres</h1>

		<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">


			<h2>Filtres :</h2>
			<br>


			<form action="./AccueilUtilisateur" method="post">
				<div class="form-group fg--search" class="form">
					<button type="submit" value="Rechercher" class="search-button">
						<img src="image/search.png">
					</button>
					<input type="search" placeholder="Le nom de l'article contient"
						class="search-field" name="nomSelect" id="nomSelect" value="" />
				</div>
				<br> <select name="categorieSelect" class="form-select"
					aria-label="Default select example">
					<c:forEach var="c" items="${listeCategories}">
						<option value="${c.no_categorie}">${c.libelle}</option>
					</c:forEach>
				</select> <br>
				<br>

				<div>
					<input type="radio" name="typeTransaction" value="achat" checked onclick="disable()">

					<label for="achat"> Achats</label><br> 

						<input type="checkbox" name="encoursEnchere" value="encours" class="checkboxAchat" id="checkboxAchat1" checked> enchères ouvertes<br>      
        				<input type="checkbox" name="enchereUtilisateur" value="encours" class="checkboxAchat" id="checkboxAchat2"> mes enchères<br>      
        				<input type="checkbox" name="enchereRemportee" value="remportee" class="checkboxAchat" id="checkboxAchat3"> mes enchères remportées<br>      



				</div>
				<br>
				<div>


					<input type="radio" name="typeTransaction" value="vente" onclick="undisable()">
					<label for="vente"> Mes ventes</label><br> 
						<input type="checkbox" name="encoursVente" value="encours" class="checkboxVente" id="checkboxVente1" disabled> mes ventes en cours<br>      
        				<input type="checkbox" name="futureVente" value="future" class="checkboxVente" id="checkboxVente2" disabled> ventes non débutées<br>      
        				<input type="checkbox" name="venteTerminee" value="terminee" class="checkboxVente" id="checkboxVente3" disabled> ventes terminées<br>     

				</div>


				<br> <input type="submit" value="Rechercher" />
			</form>
			<br>
			<c:forEach var="c" items="${listeArticles}">
				<div class="card h-100">


					

					<p><a href="${pageContext.request.contextPath}/DetailVente?param=${c.no_article}"
				title="Article"><i>${c.nom_article}</i></a></p>


					<p>


					<p>Prix : ${c.prix_initial} points</p>
					<p>
						Fin de l'enchère :
						<tags:localDate date="${c.date_fin_encheres}" />
					</p>
					<p>
						Vendeur : <a
							href="${pageContext.request.contextPath}/AffichageUtilisateur?param=${c.utilisateur.no_utilisateur}"
							title="Article"><i>${c.utilisateur.pseudo}</i></a>
					</p>

				</div>
				<br>


			</c:forEach>


		</div>

	</div>
	<!-- /.container -->



	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script>
		function disable() {
		document.getElementById("checkboxVente1").checked = false;
		document.getElementById("checkboxVente2").checked = false;
		document.getElementById("checkboxVente3").checked = false;
 		document.getElementById("checkboxVente1").disabled = true;
 		document.getElementById("checkboxVente2").disabled = true;
 		document.getElementById("checkboxVente3").disabled = true;
 		document.getElementById("checkboxAchat1").disabled = false;
 		document.getElementById("checkboxAchat2").disabled = false;
 		document.getElementById("checkboxAchat3").disabled = false;
		}

		function undisable() {
		document.getElementById("checkboxAchat1").checked = false;
		document.getElementById("checkboxAchat2").checked = false;
		document.getElementById("checkboxAchat3").checked = false;
  		document.getElementById("checkboxVente1").disabled = false;
  		document.getElementById("checkboxVente2").disabled = false;
  		document.getElementById("checkboxVente3").disabled = false;
  		document.getElementById("checkboxAchat1").disabled = true;
 		document.getElementById("checkboxAchat2").disabled = true;
 		document.getElementById("checkboxAchat3").disabled = true;

		}
	</script>


</body>

</html>