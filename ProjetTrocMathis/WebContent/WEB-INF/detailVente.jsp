<%@page import="fr.eni.projettroc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>



<!DOCTYPE html>
<html>

<head>
<%@ include file="template/head.html"%>
<title>D&eacute;tail vente</title>


</head>


<body>

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

	<!-- Page Content -->
	<div class="container">
		<form action="./DetailVente" method="post">
			<!-- Page Heading -->
			<div class="titre">
				<h1 class="my-2">D&eacute;tail&nbsp;vente</h1>
			</div>
			<br>

			<div class="container">
				<div class="row">
					<div class="col-4"></div>
					<div class="container col-8">

						<div class="form-group">
							<div class="form-row">${articlejsp.nom_article}</div>
						</div>
					

						<div class="form-group">
							<div class="form-row">
								<label class="col-md-2" for="description">Description :
								</label>
								<div class="col-md-8" class="card">${articlejsp.description}</div>
							</div>
						</div>

						<div class="form-group">
							<div class="form-row">
								<label class="col-md-2" for="categorie">Cat&eacute;gorie
								</label>
								<div class="col-md-8">${articlejsp.categorie.libelle}</div>

							</div>
						</div>


						<div class="form-group">
							<div class="form-row">
								<label class="col-md-2" for="meilleureOffre">
									Meilleure&nbsp;Offre </label>

								<div class="col-md-8">${montantmaximum}&nbsp;Pts&nbsp;De&nbsp;${usermax}</div>

							</div>
						</div>

						<div class="form-group">
							<div class="form-row">
								<label class="col-md-2" for="miseAPrix">
									Mise&nbsp;&agrave;&nbsp;prix : </label>
								<div class="col-md-8">${articlejsp.prix_initial}</div>
							</div>
						</div>

						<div class="form-group">
							<div class="form-row">
								<label class="col-md-2" for="finEnchere">
									Fin&nbsp;de&nbsp;l'ench&egrave;re </label>
								<div class="col-md-8">
									<tags:localDate date="${articlejsp.date_fin_encheres}" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="form-row">
								<label class="col-md-2" for="retrait"> Retrait : </label>
								<div class="col-md-8">

									${articlejsp.utilisateur.rue } <br>
									${articlejsp.utilisateur.code_postal}
									${articlejsp.utilisateur.ville }

								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="form-row">

								<label class="col-md-2" for="vendeur"> Vendeur : </label>
								<div class="col-md-8">${articlejsp.utilisateur.nom }</div>

							</div>
						</div>



						<c:if test="${!isProprietaireArticle}">
							<div class="form-group">
								<div class="form-row">
									<label class="col-md-4" for="maProposition"> Ma
										&nbsp;proposition : </label> <input class="choixProposition col-3"
										type="number" class="form-control" id="prixenchere" required
										name="prixenchere">
									<div class="col-md-1"></div>
									<button type="submit" class="btn btn-primary col-md-4">Ench&eacute;rir</button>
								</div>
							</div>

						</c:if>

						<c:if test="${isProprietaireArticle && isDateModifiable}">
							<div class="form-group">
								<div class="form-row">

									<p>
										<button type="submit" class="btn btn-primary">
											<a
												href="${pageContext.request.contextPath}/ModifierUnArticle?iddelarticle=${articlejsp.no_article}"
												title="Modifier la vente">Modifier&nbsp; la&nbsp; Vente</a>
										</button>
									</p>



									<div class="col-md-8"></div>
								</div>
							</div>

						</c:if>



						<c:if test="${isProprietaireArticle && !isDateModifiable}">
							<div class="form-group">
								<div class="form-row">
									<div>L'ench&egrave;re&nbsp; a&nbsp; d&eacute;j&agrave;&nbsp;
										commenc&eacute;,&nbsp; vous&nbsp; ne&nbsp; pouvez&nbsp; pas&nbsp; la&nbsp; modifier</div>
									<div class="col-md-8"></div>
								</div>
							</div>


						</c:if>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>