package fr.isika.cda24.MonProjet1.MonProjet1;

public class Utilisateur {
	
	public class Utitisateur {
		
		//Attributs
			String nom, prenom, poste;
			int numUtilisateur;
			
		//Constructeur
			public Utitisateur(String nom, String prenom, String poste, int numUtilisateur) {
				super();
				this.nom = nom;
				this.prenom = prenom;
				this.poste = poste;
				this.numUtilisateur = numUtilisateur;
			}

		//getters & setters
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

			public String getPoste() {
				return poste;
			}

			public void setPoste(String poste) {
				this.poste = poste;
			}

			public int getNumUtilisateur() {
				return numUtilisateur;
			}

			public void setNumUtilisateur(int numUtilisateur) {
				this.numUtilisateur = numUtilisateur;
			}
		// methode toString
			@Override
			public String toString() {
				return "Utitisateur [nom=" + nom + ", prenom=" + prenom + ", poste=" + poste + ", numUtilisateur="
						+ numUtilisateur + "]";
			}

		//methodes specifiques
			

		}

}
