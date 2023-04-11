package fr.isika.cda24.MonProjet1.MonProjet1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaireDeRecherche {

	private Noeud racine;
	private RandomAccessFile raf;
	Annuaire annuaire = new Annuaire();

	// Constructeur

	public ArbreBinaireDeRecherche() throws IOException {

		this.racine = new Noeud(null, 1, -1, -1);
		raf = new RandomAccessFile("src/main/java/annuaireTxt/fBinaireStagiaire.bin", "rw");
	}

	// Methode pour ecrire dans un fichier binaire a partir du fichier texte 
	public void ecrireDansFichierBinaire() throws IOException {

		List<Stagiaire> stagiaires = annuaire.lireFichierTxt();
		raf.seek(0);

		for (Stagiaire s : stagiaires) {
			ajouterDansArbre(s, raf);
		}
	}

	// methodes pour lire dans le fichier binaire et retourne la liste des
	// stagiaires
	public List<Stagiaire> lireFichierBinaire() throws IOException {
		raf.seek(0);
		int nbStagiaire = (int) raf.length() / Stagiaire.TAILLE_STAGIAIRE_OCTET;
		List<Stagiaire> stagiaires = annuaire.lireFichierTxt();

		for (int i = 0; i < nbStagiaire-1; i++) {

			String nom = " ";
			for (int j = 0; j < Stagiaire.TAILLE_NOM_MAX; j++) {
				nom += raf.readChar();
			}

			String prenom = " ";
			for (int j = 0; j < Stagiaire.TAILLE_PRENOM_MAX; j++) {
				prenom += raf.readChar();
			}

			String departement = " ";
			for (int j = 0; j < Stagiaire.TAILLE_DEPARTEMENT_MAX; j++) {
				departement += raf.readChar();
			}

			String cycle = " ";
			for (int j = 0; j < Stagiaire.TAILLE_CYCLE_MAX; j++) {
				cycle += raf.readChar();
			}

			String annee = " ";
			for (int j = 0; j < Stagiaire.TAILLE_ANNEE_MAX; j++) {
				annee += raf.readChar();
			}
			System.out.println("Stagiaire : " + "Nom: " + nom + "  " + "Prenom: " + prenom + "  " + "departement : "
					+ departement + "  " + "cycle: " + cycle + "annee : " + annee + " FG: " + raf.readInt() + " FD: "
					+ raf.readInt() + " dbl: " + raf.readInt());
		}

		return stagiaires;

	}

	// methode pour ecrire un stagiaire dans un arbre binaire
	public void ajouterDansArbre(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {

		if (raf.length() == 0) {
			racine = new Noeud(stagiaire, -1, -1, -1);
			raf.seek(0);
			racine.ecrireStagiaire(racine, raf);

		} else {
			raf.seek(0);
			racine = racine.lireNoeud(raf);
			racine.ajouterNoeud(stagiaire, raf);
		}
	}

	// methode pour un affichage de l'arbre en ordre
	public List<Stagiaire> arbreAffichageInfix(RandomAccessFile raf) {
		List<Stagiaire> stagiaires = new ArrayList<>();
		try {
			if (raf.length() == 0) {
				System.out.println("arbre vi/"
						+ "de");
			} else {
				raf.seek(0);
				Noeud noeudCourant = racine.lireNoeud(raf);
				noeudCourant.affichageInfixeNoeud(raf, stagiaires);

			}
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
		return stagiaires;
	}
	// methode pour la recherche dans l arbre 
	public List<Stagiaire> resultatRecherche(Stagiaire stagRecherche, RandomAccessFile raf) throws IOException {
		List<Stagiaire> resultat = new ArrayList<>();
		if (raf.length() == 0) {
		System.out.println("Liste des stagiaires est vide!");
		
	}else {
		raf.seek(0);
		racine = racine.lireNoeud(raf);
		racine.rechercherStagiaire(stagRecherche, raf, resultat);
	}
	return resultat;
	}
	//methode pour supprimer un stagiaire de l'arbre
	public void supprimerStagiaire(Stagiaire stagSuppression, RandomAccessFile raf) throws IOException {
		if(raf.length() == 0 ) {
			System.out.println("l'arbre est vide");
		} else {
			raf.seek(0);
			racine = racine.lireNoeud(raf);
			racine.rechercherStagiaireASupprime(stagSuppression, raf, 0);
		}
	}
	
	public Stagiaire modifierStagiaire(Stagiaire stagMAJ, RandomAccessFile raf) {
		try {
			if (raf.length() == 0) {
				System.out.println("arbre vi/"
						+ "de");
			} else {
				raf.seek(0);
				Noeud noeudAModifie = racine.lireNoeud(raf);
				noeudAModifie.modifierStagiaire(stagMAJ, raf);
				
			}} catch (IOException e) {
				// 
				e.printStackTrace();
			}
		return stagMAJ;
}
	
}

			