<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@page import="fr.eni.projettroc.bo.Utilisateur"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="template/head.html"%>
</head>

<body>


	<!-- Navigation -->


	<%@ include file="template/navBarConnectee.html"%>

	<!-- Page Content -->
	<div class="container portfolio-item accueilContainer">

		<!-- Page Heading -->

		<div class="titre">
			<h1 class="my-2">Liste des enchères</h1>
		</div>

		

			<div class="filtres">
				<h2>Filtres :</h2>


				<form action="./AccueilUtilisateur" method="post" class="form">
					<div class="flitresForm">
						<div class="loupeForm">
							<button type="submit" value="Rechercher" class="search-button">
								<img src="image/search.png">
							</button>
							<input type="search" placeholder="Le nom de l'article contient"
								class="search-field" name="nomSelect" id="nomSelect" value="" />
						</div>
						<div class="categoriesForm">
							<p class="formCategoriesItem">Catégories :</p>
							<select name="categorieSelect" class="formCategoriesItem"
								aria-label="Default select example">
								<c:forEach var="c" items="${listeCategories}">
									<option value="${c.no_categorie}">${c.libelle}</option>
								</c:forEach>
							</select>
						</div>
						<div class="radioForm">
							<div class="radioFormItem">
								<input type="radio" name="typeTransaction" value="achat" checked
									onclick="disable()"> <label for="achat"> Achats</label><br>

								<input type="checkbox" name="encoursEnchere" value="encours"
									class="checkboxAchat" id="checkboxAchat1" checked>
								enchères ouvertes<br> <input type="checkbox"
									name="enchereUtilisateur" value="encours" class="checkboxAchat"
									id="checkboxAchat2"> mes enchères<br> <input
									type="checkbox" name="enchereRemportee" value="remportee"
									class="checkboxAchat" id="checkboxAchat3"> mes enchères
								remportées<br>



							</div>

							<div class="radioFormItem">


								<input type="radio" name="typeTransaction" value="vente"
									onclick="undisable()"> <label for="vente"> Mes
									ventes</label><br> <input type="checkbox" name="encoursVente"
									value="encours" class="checkboxVente" id="checkboxVente1"
									disabled> mes ventes en cours<br> <input
									type="checkbox" name="futureVente" value="future"
									class="checkboxVente" id="checkboxVente2" disabled>
								ventes non débutées<br> <input type="checkbox"
									name="venteTerminee" value="terminee" class="checkboxVente"
									id="checkboxVente3" disabled> ventes terminées<br>

							</div>
						</div>
					</div>


					<input type="submit" value="Rechercher"
						class="flitresForm btn $gray-400 btn-lg" />
				</form>
			</div>
			<div class="articles">
				<c:forEach var="c" items="${listeArticles}">
					<div class="card h-100 articleCard">
						<p><a href="${pageContext.request.contextPath}/DetailVente?param=${c.no_article}"
								title="Article"><i>${c.nom_article}</i></a>	</p>
						<p>Prix : ${c.prix_initial} points</p>
						<p>	Fin de l'enchère :
							<tags:localDate date="${c.date_fin_encheres}" />
						</p>
						<p>	Vendeur : <a href="${pageContext.request.contextPath}/AffichageUtilisateur?param=${c.utilisateur.no_utilisateur}"
								title="Article"><i>${c.utilisateur.pseudo}</i></a>
						</p>

					</div>
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