package fr.isika.cda24.MonProjet1.MonProjet1;

public class Noeud {
	private Stagiaire stagiaire;
	private Noeud noeudGauche;
	private Noeud noeudDroit;

	// Constructeurs
//La clé du noeud (racine) est le stagiaire 
	public Noeud(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		this.noeudGauche = null;
		this.noeudDroit = null;
	}
//La clé du constructeur 
	public Noeud(Stagiaire stagiaire, Noeud noeudGauche, Noeud noeudDroit) {
		super();
		this.stagiaire = stagiaire;
		this.noeudGauche = noeudGauche;
		this.noeudDroit = noeudDroit;
	}
	
	public void ajouterNoeud(Stagiaire nouveauStagiaire) {
	    int comparaison = this.stagiaire.getNom().compareTo(nouveauStagiaire.getNom());

	    if (comparaison > 0) {
	        // nouveauStagiaire doit être placé à gauche
	        if (this.noeudGauche == null) { // cas de terminaison
	            this.noeudGauche = new Noeud(nouveauStagiaire);
	        } else {
	            // cas de base
	            this.noeudGauche.ajouterNoeud(nouveauStagiaire);
	        }
	    } else /* if (comparaison < 0) */ {
	        // nouveauStagiaire doit être placé à droite
	        if (this.noeudDroit == null) {
	            this.noeudDroit = new Noeud(nouveauStagiaire);
	        } else {
	            // cas de base
	            this.noeudDroit.ajouterNoeud(nouveauStagiaire);
	        }
	    }
	}

	public void affichageInfixeNoeud() {
		if (this.noeudGauche != null) { //si le noeud gauche n'est pas vide 
			this.noeudDroit.affichageInfixeNoeud(); 
		}
		System.out.println(this.stagiaire);
		if (this.noeudDroit != null) {
			this.noeudDroit.affichageInfixeNoeud();
		}
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Noeud getNoeudGauche() {
		return noeudGauche;
	}

	public void setNoeudGauche(Noeud noeudGauche) {
		this.noeudGauche = noeudGauche;
	}

	public Noeud getNoeudDroit() {
		return noeudDroit;
	}

	public void setNoeudDroit(Noeud noeudDroit) {
		this.noeudDroit = noeudDroit;
	}
}