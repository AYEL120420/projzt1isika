package fr.isika.cda24.MonProjet1.MonProjet1;


public class Stagiaire {

	//Attributs de la classe stagiaire
		String nom, prenom, cycle, annee, departement;

	//Constructeur
		public Stagiaire(String nom, String prenom, String cycle, String anneeFormation, String departement) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.cycle = cycle;
			this.annee = anneeFormation;
			this.departement = departement;
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

		public String getCycle() {
			return cycle;
		}

		public void setCycle(String cycle) {
			this.cycle = cycle;
		}

		public String getAnneeFormation() {
			return annee;
		}

		public void setAnneeFormation(String anneeFormation) {
			this.annee = anneeFormation;
		}

		public String getDepartement() {
			return departement;
		}

		public void setDepartement(String departement) {
			this.departement = departement;
		}

	//methode toString

		@Override
		public String toString() {
			return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", cycle=" + cycle + ", anneeFormation="
					+ annee + ", departement=" + departement + "]";
		}


	//methodes specifiques
	}