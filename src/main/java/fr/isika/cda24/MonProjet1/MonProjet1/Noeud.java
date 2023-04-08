package fr.isika.cda24.MonProjet1.MonProjet1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Noeud {

	private Stagiaire stagiaire;
	protected int indexNoeudGauche;
	protected int indexNoeudDroit;
	protected int doublon;
	public static final int INDEX_FILS_NUL = -1;
	public static final int tailleNoeud = 128; // ( taille d'un noeud en octet = taille stagiaire + taille
												// indexNoeudGauche +taille index noeud droit+taille doublon)

// Constructeurs

	public Noeud(Stagiaire stagiaire, int indexNoeudGauche, int indexNoeudDroit, int doublon) {

		this.stagiaire = stagiaire;
		this.indexNoeudGauche = indexNoeudGauche;
		this.indexNoeudDroit = indexNoeudDroit;
		this.doublon = doublon;
	}

	// methode pour ecrire un Stagiaire dans le fichier binaire
	public void ecrireStagiaire(Noeud noeud, RandomAccessFile raf) {
		try {

			raf.writeChars(noeud.stagiaire.getNomLong());
			raf.writeChars(noeud.stagiaire.getPrenomLong());
			raf.writeChars(noeud.stagiaire.getDepartementLong());
			raf.writeChars(noeud.stagiaire.getCycleLong());
			raf.writeChars(noeud.stagiaire.getAnneeLong());
			raf.writeInt(noeud.indexNoeudGauche);
			raf.writeInt(noeud.indexNoeudDroit);
			raf.writeInt(noeud.doublon);
		}

		catch (IOException e) {

			e.printStackTrace();
		}
	}

	// methode pour lire un stagiaire dans fichier binaire
	public Noeud lireStagiaire(RandomAccessFile raf) {

		int filsGauche = INDEX_FILS_NUL;
		int filsDroit = INDEX_FILS_NUL;
		int doublon = INDEX_FILS_NUL;
		Stagiaire stag = null;
		try {
			String nom = "";
			for (int n = 0; n < Stagiaire.TAILLE_NOM_MAX; n++) {
				nom += raf.readChar();
			}
			String prenom = "";
			for (int p = 0; p < Stagiaire.TAILLE_PRENOM_MAX; p++) {
				prenom += raf.readChar();
			}
			String departement = "";
			for (int d = 0; d < Stagiaire.TAILLE_DEPARTEMENT_MAX; d++) {
				departement += raf.readChar();
			}
			String cycle = "";
			for (int c = 0; c < Stagiaire.TAILLE_CYCLE_MAX; c++) {
				cycle += raf.readChar();
			}
			String annee = "";
			for (int a = 0; a < Stagiaire.TAILLE_ANNEE_MAX; a++) {
				annee += raf.readChar();
			}
			filsGauche = raf.readInt();
			filsDroit = raf.readInt();
			doublon = raf.readInt();
			stag = new Stagiaire(nom.trim(), prenom.trim(), departement.trim(), cycle.trim(), annee.trim());

			// System.out.println("Stagiaire: " + stag);

			// }
		} catch (IOException e) {

			e.printStackTrace();
		}

		return new Noeud(stag, filsGauche, filsDroit, doublon);

	}

	// methode pour ajouter un noeud
	public void ajouterNoeud(Stagiaire nvStagiaire, RandomAccessFile raf) {
		int comparaison = this.stagiaire.getNom().compareTo(nvStagiaire.getNom());
		try {

			if (comparaison > 0) {
				// nouveauStagiaire doit être placé à gauche
				if (this.indexNoeudGauche == INDEX_FILS_NUL) { // cas de terminaison

					// remonte de 8 octet le seek pour etre pret à ecrire à index gauche
					int positionEcritureNoeudGauche = (int) (raf.getFilePointer() - 12);
					raf.seek(positionEcritureNoeudGauche);

					// tu ecris l'index du noeud que tu ajoute (tailles du fichier / taille d'un
					// noeud en octet)
					indexNoeudGauche = (int) (raf.length() / tailleNoeud);
					raf.writeInt(indexNoeudGauche);

					// tu te mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudGauche
					Noeud nouveauNoeudGauche = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauNoeudGauche.ecrireStagiaire(nouveauNoeudGauche, raf);

				} else {
					// deplace le seek à FG*taille d'un noeud
					raf.seek(this.indexNoeudGauche * tailleNoeud);

					// lit le noeud et tu le stockes dans une variable (nouveauNoeudGauche)

					Noeud nouveauNoeudGauche = lireStagiaire(raf);
					nouveauNoeudGauche.ajouterNoeud(nvStagiaire, raf);

				}
			}else if (comparaison < 0) {
				if (this.indexNoeudDroit == INDEX_FILS_NUL) { // cas de terminaison

					// remonte de 8 octet le seek pour etre pret à ecrire à index gauche
					int positionEcrireNoeudDroit = (int) raf.getFilePointer() - 8;
					raf.seek(positionEcrireNoeudDroit);

					// tu ecris l'index du noeud que tu ajoute (tailles du fichier / taille d'un
					// noeud en octet)
					indexNoeudDroit = (int) (raf.length() / tailleNoeud);
					raf.writeInt(indexNoeudDroit);

					// tu te mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudDroit
					Noeud nouveauNoeudDroit = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauNoeudDroit.ecrireStagiaire(nouveauNoeudDroit, raf);

				} else {
					// deplace le seek à FD*taille d'un noeud
					raf.seek(indexNoeudDroit * tailleNoeud);

					// lit le noeud et tu le stockes dans une variable (nouveauNoeudDroit)
					Noeud nouveauNoeudDroit = lireStagiaire(raf);
					nouveauNoeudDroit.ajouterNoeud(nvStagiaire, raf);
				}
			} else if (comparaison == 0) {
				// faire le doublon
				if (this.doublon == INDEX_FILS_NUL) { // cas de terminaison

					// remonte de 4 octet le seek pour etre pret à ecrire à index gauche
					int positionEcrireDoublon = (int) raf.getFilePointer() - 4;
					raf.seek(positionEcrireDoublon);

					// tu eceris l'index du noeud que tu ajoute (tailles du fichier / taille d'un
					// noeud en octet)
					doublon = (int) (raf.length() / tailleNoeud);
					raf.writeInt(doublon);

					// tu te mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudDroit
					Noeud nouveauDoublon = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauDoublon.ecrireStagiaire(nouveauDoublon, raf);

				} else {
					// deplace le seek à FD*taille d'un noeud
					raf.seek(doublon * tailleNoeud);

					// lit le noeud et tu le stockes dans une variable (nouveauNoeudDroit)
					Noeud nouveauDNoeud = lireStagiaire(raf);
					nouveauDNoeud.ajouterNoeud(nvStagiaire, raf);
				}

			} 
		}

		catch (IOException e) {

			e.printStackTrace();
		}
	}

	// methode pour avoir un affichage par ordre
	public void affichageInfixeNoeud(RandomAccessFile raf, List<Stagiaire> stagiaires) {
		try {
			// on met notre pointeur à la fin d un noeud

			if (indexNoeudGauche != INDEX_FILS_NUL) {
				raf.seek(this.getIndexNoeudGauche() * tailleNoeud);
				Noeud noeudCourant = lireStagiaire(raf);
				noeudCourant.affichageInfixeNoeud(raf, stagiaires);
			}
			 System.out.println(this);
			stagiaires.add(this.stagiaire);
			if (doublon != INDEX_FILS_NUL) {

				raf.seek(this.getDoublon() * tailleNoeud);
				Noeud noeudCourant = lireStagiaire(raf);
				noeudCourant.affichageInfixeNoeud(raf, stagiaires);

			}
			if (indexNoeudDroit != INDEX_FILS_NUL) {

				raf.seek(this.getIndexNoeudDroit() * tailleNoeud);
				Noeud noeudCourant = lireStagiaire(raf);
				noeudCourant.affichageInfixeNoeud(raf, stagiaires);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void rechercherStagiaire(Stagiaire stagRecherche, RandomAccessFile raf, List<Stagiaire>resultat) throws IOException {
		//System.out.println(this);
		int comparaison = this.stagiaire.getNom().trim().compareTo(stagRecherche.getNom().trim());
		// sinon on compare le nom du stagiaire présent avec le nom du stagiaire
		// recherché
		if (comparaison == 0) {// Stagiaire avec meme nom
			resultat.add(this.stagiaire);
		} else {
			if (comparaison > 0) { // on cherche sans la sous arbre gauche
				if (this.indexNoeudGauche != INDEX_FILS_NUL ) {
					//&& this.doublon !=INDEX_FILS_NUL) {
					raf.seek(this.getIndexNoeudGauche() * (tailleNoeud ));// je mets mon pointeur avant le
					Noeud gauche = lireStagiaire(raf);															// FilsGauche
					// lire le noeud // pour commncer la comparaison
					gauche.rechercherStagiaire(stagRecherche, raf, resultat);// on fé appel à la methode recursive
				}
				if (comparaison < 0) { // si comparaison négative on continue la recherche dans la sous arbre droit
					if (this.indexNoeudDroit != INDEX_FILS_NUL) { // si le noeud droit existe
						raf.seek(this.getIndexNoeudDroit() * (tailleNoeud ));
						Noeud droite = lireStagiaire(raf);													
						droite.rechercherStagiaire(stagRecherche, raf, resultat);// on fé appel à la methode
																						// recursive
					} else {
						System.out.println("Ce nom n'existe pas!");
					}
				}
			}
		}

	}

	/*public void supprimerNoeud(Stagiaire stagiaireSupp, RandomAccessFile raf) {

	int comparaison = this.stagiaire.getNom().compareTo(stagiaireSupp.getNom());
	rechercherStagiaire(stagiaireSupp, raf, List<Stagiaire>resultat);
	this.stagiaire = null;
	}*/
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getIndexNoeudGauche() {
		return indexNoeudGauche;
	}

	public void setIndexNoeudGauche(int indexNoeudGauche) {
		this.indexNoeudGauche = indexNoeudGauche;
	}

	public int getIndexNoeudDroit() {
		return indexNoeudDroit;
	}

	public void setIndexNoeudDroit(int indexNoeudDroit) {
		this.indexNoeudDroit = indexNoeudDroit;
	}

	public int getDoublon() {
		return doublon;
	}

	public void setDoublon(int doublon) {
		this.doublon = doublon;
	}

	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", indexNoeudGauche=" + indexNoeudGauche + ", indexNoeudDroit="
				+ indexNoeudDroit + ", doublon=" + doublon + "]";
	}

}