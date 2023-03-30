package fr.isika.cda24.MonProjet1.MonProjet1;

public class ArbreBinaireDeRecherche {

private Noeud premierStagiaire;
	//Constructeurs

	public ArbreBinaireDeRecherche() {
		this.premierStagiaire = null;
	}
	
	public ArbreBinaireDeRecherche(Noeud premierStagiaire) {
		this.premierStagiaire = premierStagiaire;
	}
	//methodes
	
	public void affichageInfixe() {
		if(premierStagiaire == null) {
			System.out.println("arbre vide");
		} else {
			this.premierStagiaire.affichageInfixeNoeud();
		}
	}
	public void ajouterDansArbre(Stagiaire nvStagiaire) {
		
		if(premierStagiaire == null ) {
			premierStagiaire = new Noeud(nvStagiaire);
		} else {
			this.premierStagiaire.ajouterNoeud(nvStagiaire);
		}
	}
	
}