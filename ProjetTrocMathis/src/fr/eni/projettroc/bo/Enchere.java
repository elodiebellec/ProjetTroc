package fr.eni.projettroc.bo;

import java.time.LocalDate;

	public class Enchere {
	private int no_enchere;
	private LocalDate date_enchere;
	private int montant_enchere;
	private ArticleVendu article;
	private Utilisateur utilisateur;
	
	public Enchere(int no_enchere, LocalDate date_enchere, int montant_enchere, ArticleVendu article,
			Utilisateur utilisateur) {
		super();
		this.no_enchere = no_enchere;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}

	public Enchere() {
		super();
		
	}

	public int getNo_enchere() {
		return no_enchere;
	}

	public void setNo_enchere(int no_enchere) {
		this.no_enchere = no_enchere;
	}

	public LocalDate getDate_enchere() {
		return date_enchere;
	}

	public void setDate_enchere(LocalDate date_enchere) {
		this.date_enchere = date_enchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public ArticleVendu getArticle() {
		return article;
	}


	
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	



	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
	

	

}
