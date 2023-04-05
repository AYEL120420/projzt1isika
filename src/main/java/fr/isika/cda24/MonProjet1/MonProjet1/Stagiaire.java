package fr.isika.cda24.MonProjet1.MonProjet1;


public class Stagiaire {
//mon objet stagiaire fera au max (21*2 +20*2 +11*2 +2*2 +4*2)= 116 octets 
	// On fixe une taille max pour chaque attribut
	public final static int TAILLE_NOM_MAX = 21;
	public final static int TAILLE_PRENOM_MAX = 20;
	public final static int TAILLE_CYCLE_MAX = 11;
	public final static int TAILLE_DEPARTEMENT_MAX = 2;
	public final static int TAILLE_ANNEE_MAX = 4;
	public final static int TAILLE_STAGIAIRE_OCTET = 116;

	// Attributs de la classe stagiaire
	String nom, prenom, cycle, annee, departement;

	// Constructeur
	public Stagiaire(String nom, String prenom, String departement, String cycle, String annee) {

		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.cycle = cycle;
		this.annee = annee;

	}

	// getters & setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	// methode toString

	@Override
	public String toString() {
		return "nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", cycle=" + cycle
				+ ", annee= " + annee ;
	}

	// methodes specifiques
	public String getNomLong() {
		String nomLong = this.nom;

		for (int i = this.nom.length(); i < TAILLE_NOM_MAX; i++) {
			nomLong += " ";
		}
		return nomLong;
	}

	public String getPrenomLong() {
		String prenomLong = this.prenom;

		for (int i = this.prenom.length(); i < TAILLE_PRENOM_MAX; i++) {
			prenomLong += " ";
		}
		return prenomLong;
	}

	public String getCycleLong() {
		String cycleLong = this.cycle;

		for (int i = this.cycle.length(); i < TAILLE_CYCLE_MAX; i++) {
			cycleLong += " ";
		}
		return cycleLong;
	}

	public String getDepartementLong() {
		String departementLong = this.departement;

		for (int i = this.prenom.length(); i < TAILLE_DEPARTEMENT_MAX; i++) {
			departementLong += " ";
		}
		return departementLong;

	}

	public String getAnneeLong() {
		String anneeLong = this.annee;

		for (int i = this.prenom.length(); i < TAILLE_ANNEE_MAX; i++) {
			anneeLong += " ";
		}
		return anneeLong;
	}
}
