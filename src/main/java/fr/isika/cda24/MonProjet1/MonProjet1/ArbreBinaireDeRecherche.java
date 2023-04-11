package fr.isika.cda24.MonProjet1.MonProjet1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe ArbreBinaireDeRecherche permet de gerer le fichier binaire
 * 
 * @author 33665
 *
 */
public class ArbreBinaireDeRecherche {

	private Noeud racine;
	private RandomAccessFile raf;
	Annuaire annuaire = new Annuaire();

	/**
	 * Le constructeur intialise un arbre binaire de recherche par sa racine
	 * 
	 * @throws IOException
	 */
	
	// Constructeur
	public ArbreBinaireDeRecherche() throws IOException {

		this.racine = new Noeud(null, 1, -1, -1);
		raf = new RandomAccessFile("src/main/java/annuaireTxt/fBinaireStagiaire.bin", "rw");
	}
	
	
	//Methodes
	/**
	 * La methode permet d'ecrire une liste de stagiaire dans le fichier binaire à
	 * partir d'un fichier texte
	 * 
	 * @throws IOException
	 */
	
	public void ecrireDansFichierBinaire() throws IOException {

		List<Stagiaire> stagiaires = annuaire.lireFichierTxt();
		raf.seek(0);

		for (Stagiaire s : stagiaires) {
			ajouterDansArbre(s, raf);
		}
	}
	

	/**
	 * La methode lireFichierBinaire permet de lire un fichier binaire et retourne une liste des stagiaires
	 * @return
	 * @throws IOException
	 */
	
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
	
	
	/**
	 * La methode ajouterDansArbre permet d'ajouter un Noeud dans l'arbre binaire de recherche
	 * @param stagiaire
	 * @param raf
	 * @throws IOException
	 */
	
	public void ajouterDansArbre(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {

		if (raf.length() == 0) {
			racine = new Noeud(stagiaire, -1, -1, -1);
			raf.seek(0);
			racine.ecrireStagiaire(racine, raf);

		} else {
			System.out.println(stagiaire);
			raf.seek(0);
			racine = racine.lireNoeud(raf);
			racine.ajouterNoeud(stagiaire, raf);
		}
	}
	
	
	/**
	 * La methode arbreAffichageInfix permet d'afficher les stagiaires dans un ordre alphabetique et retourne la liste des stagiaires dans l'ordre
	 * @param raf
	 * @return
	 */
	
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
	
	
	/**
	 * la methode resultatRecherche permet rechercher un stagaire dans l'arbre binaire et retourne une liste de stagiaire et fait appel à la methode rechercherStagiaire de la classe Noeud
	 * @param stagRecherche
	 * @param raf
	 * @return
	 * @throws IOException
	 */
	
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
	
	
	/**
	 * la methode supprimerStagaire permet la suppression su stagiaire de l'arbre binaire et fait appel à la methode rechercherStagiaireASupprime de la classe Noeud
	 * @param stagSuppression
	 * @param raf
	 * @throws IOException
	 */ 
	
	public void supprimerStagiaire(Stagiaire stagSuppression, RandomAccessFile raf) throws IOException {
		if(raf.length() == 0 ) {
			System.out.println("l'arbre est vide");
		} else {
			raf.seek(0);
			racine = racine.lireNoeud(raf);
			racine.rechercherStagiaireASupprime(stagSuppression, raf, 0);
		}
	}
	/**
	 * la mehtode modifierStagaire met à jour les informations d'un stagiaire
	 * @param stagMAJ
	 * @param stagAmodifie
	 * @param raf
	 */
	public void modifierStagiaire(Stagiaire stagMAJ,Stagiaire stagAmodifie, RandomAccessFile raf) {
		try {
		supprimerStagiaire(stagAmodifie, raf);
		ajouterDansArbre(stagMAJ, raf);

		} catch (IOException e) {
				// 
				e.printStackTrace();
			}

}
}

			