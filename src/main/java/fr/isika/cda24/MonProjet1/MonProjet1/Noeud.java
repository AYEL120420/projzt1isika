package fr.isika.cda24.MonProjet1.MonProjet1;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.RandomAccessFile;
import java.util.List;

public class Noeud {

	private Stagiaire stagiaire;
	protected int indexNoeudGauche;
	protected int indexNoeudDroit;
	protected int doublon;
	public static final int INDEX_FILS_NUL = -1;
	public static final int TAILLE_NOEUD = 128; // ( taille d'un noeud en octet = taille stagiaire + taille
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
	public Noeud lireNoeud(RandomAccessFile raf) {

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
					indexNoeudGauche = (int) (raf.length() / TAILLE_NOEUD);
					raf.writeInt(indexNoeudGauche);

					// tu te mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudGauche
					Noeud nouveauNoeudGauche = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauNoeudGauche.ecrireStagiaire(nouveauNoeudGauche, raf);

				} else {
					// deplace le seek à FG*taille d'un noeud
					raf.seek(this.indexNoeudGauche * TAILLE_NOEUD);

					// lit le noeud et tu le stockes dans une variable (nouveauNoeudGauche)

					Noeud nouveauNoeudGauche = lireNoeud(raf);
					nouveauNoeudGauche.ajouterNoeud(nvStagiaire, raf);

				}
			} else if (comparaison < 0) {
				if (this.indexNoeudDroit == INDEX_FILS_NUL) { // cas de terminaison

					// remonte de 8 octet le seek pour etre pret à ecrire à index gauche
					int positionEcrireNoeudDroit = (int) raf.getFilePointer() - 8;
					raf.seek(positionEcrireNoeudDroit);

					// tu ecris l'index du noeud que tu ajoute (tailles du fichier / taille d'un
					// noeud en octet)
					indexNoeudDroit = (int) (raf.length() / TAILLE_NOEUD);
					raf.writeInt(indexNoeudDroit);

					// tu te mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudDroit
					Noeud nouveauNoeudDroit = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauNoeudDroit.ecrireStagiaire(nouveauNoeudDroit, raf);

				} else {
					// deplace le seek à FD*taille d'un noeud
					raf.seek(indexNoeudDroit * TAILLE_NOEUD);

					// lit le noeud et tu le stockes dans une variable (nouveauNoeudDroit)
					Noeud nouveauNoeudDroit = lireNoeud(raf);
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
					doublon = (int) (raf.length() / TAILLE_NOEUD);
					raf.writeInt(doublon);

					// tu te mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudDroit
					Noeud nouveauDoublon = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauDoublon.ecrireStagiaire(nouveauDoublon, raf);

				} else {
					// deplace le seek à FD*taille d'un noeud
					raf.seek(doublon * TAILLE_NOEUD);

					// lit le noeud et tu le stockes dans une variable (nouveauNoeudDroit)
					Noeud nouveauDNoeud = lireNoeud(raf);
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
				raf.seek(this.getIndexNoeudGauche() * TAILLE_NOEUD);
				Noeud noeudCourant = lireNoeud(raf);
				noeudCourant.affichageInfixeNoeud(raf, stagiaires);
			}
			System.out.println(this);
			stagiaires.add(this.stagiaire);
			if (doublon != INDEX_FILS_NUL) {

				raf.seek(this.getDoublon() * TAILLE_NOEUD);
				Noeud noeudCourant = lireNoeud(raf);
				noeudCourant.affichageInfixeNoeud(raf, stagiaires);

			}
			if (indexNoeudDroit != INDEX_FILS_NUL) {

				raf.seek(this.getIndexNoeudDroit() * TAILLE_NOEUD);
				Noeud noeudCourant = lireNoeud(raf);
				noeudCourant.affichageInfixeNoeud(raf, stagiaires);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void rechercherStagiaire(Stagiaire StagRecherche, RandomAccessFile raf, List<Stagiaire> resultat)
			throws IOException {

		int comparaison = this.stagiaire.getNom().trim().compareTo(StagRecherche.getNom().trim());
		// sinon on compare le nom du stagiaire présent avec le nom du stagiaire
		// recherché
		if (comparaison == 0) {// Stagiaire avec meme nom
			resultat.add(this.stagiaire);
			if (doublon != INDEX_FILS_NUL) {
				raf.seek(this.doublon * TAILLE_NOEUD);
				Noeud doublon = lireNoeud(raf);
				doublon.rechercherStagiaire(StagRecherche, raf, resultat);
			}
		} else {
			if (comparaison > 0) { // on cherche sans la sous arbre gauche
				if (this.indexNoeudGauche != INDEX_FILS_NUL) {
					raf.seek(this.getIndexNoeudGauche() * (TAILLE_NOEUD));// je mets mon pointeur avant le
					Noeud gauche = lireNoeud(raf); // FilsGauche
					// lire le noeud // pour commncer la comparaison
					gauche.rechercherStagiaire(StagRecherche, raf, resultat);// on fé appel à la methode recursive
				}
				if (comparaison < 0) { // si comparaison négative on continue la recherche dans la sous arbre droit
					if (this.indexNoeudDroit != INDEX_FILS_NUL) { // si le noeud droit existe
						raf.seek(this.getIndexNoeudDroit() * (TAILLE_NOEUD));
						Noeud droite = lireNoeud(raf);
						droite.rechercherStagiaire(StagRecherche, raf, resultat);// on fé appel à la methode
																					// recursive
					} else {
						System.out.println("Ce nom n'existe pas!");
					}
				}
			}
		}
	}

	public void rechercherStagiaireASupprime(Stagiaire StagRechercheASupp, RandomAccessFile raf, int indexParent)
			throws IOException {
		// je stock la position du noeud sur lequel je suis
		int position = (int) (raf.getFilePointer() / TAILLE_NOEUD - 1);
		Noeud noeudCourant = lireNoeud(raf);
		System.out.println("Je commenece" + noeudCourant);
		// je fais ma comparaison
		int comparaison = this.getStagiaire().comparer(StagRechercheASupp);
		if (comparaison == 0) {// Stagiaire recherché a été trouvé
			// if je compare tout le stagiaire avec le stagiaire à supp)
			System.out.println("j'ai trouvé le stagiaire à supprimer");
			System.out.println(this);
			System.out.println("index du parent " + indexParent);
			// j'ai trouvé le stagiaire à supprimer et j'ai l'index de son parent
			// appeler la méthode suppression du noeud
			supprimerNoeud(noeudCourant, indexParent, raf);
		} else if (comparaison > 0) { // on cherche sans la sous arbre gauche
			System.out.println("je cherche à gauche");
			if (this.indexNoeudGauche != INDEX_FILS_NUL) {
				raf.seek(this.getIndexNoeudGauche() * (TAILLE_NOEUD));// je mets mon pointeur avant le
				System.out.println("noued courant" + noeudCourant);
				Noeud gauche = lireNoeud(raf); // FilsGauche
				System.out.println("noeud Gauche " + gauche);
				// lire le noeud // pour commncer la comparaison
				gauche.rechercherStagiaireASupprime(StagRechercheASupp, raf, position);// on fé appel à la methode
																					// recursive
			}

		} else if (comparaison < 0) { // si comparaison négative on continue la recherche dans la sous arbre droit
			System.out.println("je cherche à droite");
			if (this.indexNoeudDroit != INDEX_FILS_NUL) { // si le noeud droit existe
				raf.seek(this.getIndexNoeudDroit() * (TAILLE_NOEUD));
				Noeud droit = lireNoeud(raf);
				System.out.println("noeud Droit " + droit);
				droit.rechercherStagiaireASupprime(StagRechercheASupp, raf, position);// on fé appel à la methode
																					// recursive
			} else {
				System.out.println("Ce nom n'existe pas!");
			}
		}
	}

	
	public void supprimerNoeud(Noeud noeudSupp, int indexParent, RandomAccessFile raf) throws IOException {

		//-----------------------------Cas 1 = mon noeudSupp est une feuille-------------------------------------------		
				if (indexNoeudDroit == INDEX_FILS_NUL && indexNoeudGauche == INDEX_FILS_NUL ) {
					raf.seek(indexParent * TAILLE_NOEUD);// pointeur->position parent
					// System.out.println(" index parent " + indexParent);
					// System.out.println("index noeud supp " + indexNoeudSupp);
					Noeud noeudParent = lireNoeud(raf);// je lis le noeud
					// System.out.println(noeudParent);
					int comparaison = (noeudParent.getStagiaire().getNom().trim())
							.compareTo(noeudSupp.getStagiaire().getNom().trim());
					if (comparaison > 0) {
						System.out.println("Position gauche");
						raf.seek(raf.getFilePointer() - 12);// pointeur->position fils gauche
						raf.writeInt(-1);// modifié indexFilsGauche en -1 = filsGauche n'existe plus
					} else if (comparaison < 0) {
						System.out.println("Position droite");
						raf.seek(raf.getFilePointer() - 8);// pointeur->position fils Droit
						raf.writeInt(-1);// modifié indexFilsDroit en -1 = filsDroit n'existe plus
					} else {
						raf.seek(raf.getFilePointer() - 4);// pointeur->position doublon
						raf.writeInt(-1);// modifié doublon en -1 = doublon n'existe plus }
						
		//-----------------------------Cas 2 = mon noeudSupp a un fils gauche ou droit---------------------------------
					}
				} else if (indexNoeudGauche == INDEX_FILS_NUL || indexNoeudDroit == INDEX_FILS_NUL) {
					if (noeudSupp.indexNoeudGauche != INDEX_FILS_NUL) {
						raf.seek(indexParent * TAILLE_NOEUD);
						System.out.println(" index parent " + indexParent);
						// System.out.println("index noeud supp " + indexNoeudSupp);
						Noeud noeudParent = lireNoeud(raf);
						System.out.println("Parent : " + noeudParent);
						int comparaison = (noeudParent.getStagiaire().getNom().trim())
								.compareTo(noeudSupp.getStagiaire().getNom().trim());
						if (comparaison > 0) {
							raf.seek(raf.getFilePointer() - 12);// pointeur->position fils gauche
							raf.writeInt(noeudSupp.indexNoeudGauche);
						} else if (comparaison < 0) {
							raf.seek(raf.getFilePointer() - 8);// pointeur->position fils Droit
							raf.writeInt(noeudSupp.indexNoeudGauche);
						}

					} else {
						raf.seek(indexParent * TAILLE_NOEUD);
						System.out.println(" index parent " + indexParent);
						// System.out.println("index noeud supp " + indexNoeudSupp);
						Noeud noeudParent = lireNoeud(raf);
						System.out.println("Parent : " + noeudParent);
						int comparaison = (noeudParent.getStagiaire().getNom().trim())
								.compareTo(noeudSupp.getStagiaire().getNom().trim());
						if (comparaison > 0) {
							raf.seek(raf.getFilePointer() - 12);// pointeur->position fils gauche
							raf.writeInt(noeudSupp.indexNoeudDroit);// modifié indexFilsGauche en -1 = filsGauche n'existe plus
						} else if (comparaison < 0) {
							raf.seek(raf.getFilePointer() - 8);// pointeur->position fils Droit
							raf.writeInt(noeudSupp.indexNoeudDroit);// modifié indexFilsDroit en -1 = filsDroit n'existe plus
						}

					}
				} else if (indexNoeudDroit != INDEX_FILS_NUL && indexNoeudGauche != INDEX_FILS_NUL) {
					// Noeud noeudParent = lireNoeud(raf);// je lis mon noeoud parent
					// System.out.println(" noeud parent " + noeudParent;
					int position = (int) raf.getFilePointer() / TAILLE_NOEUD - 1;// je calcule la position du noeud parent
					raf.seek(this.indexNoeudDroit * TAILLE_NOEUD);// on se positionne sur le fils droit du noeud à
					System.out.println("Noeud courant" + this); // supprimer
					Noeud noeudSuppFilsDroit = lireNoeud(raf);// je lis le fils droit
					Noeud descendant = trouverSuccesseur(noeudSuppFilsDroit, raf);// trouver le successeur du nœud supprimé

					System.out.println("Noeud Successeur " + descendant);
					System.out.println(position);

					raf.seek(position * TAILLE_NOEUD);
					raf.writeChars(descendant.stagiaire.getNomLong());
					raf.writeChars(descendant.stagiaire.getPrenomLong());
					raf.writeChars(descendant.stagiaire.getDepartementLong());
					raf.writeChars(descendant.stagiaire.getCycleLong());
					raf.writeChars(descendant.stagiaire.getAnneeLong());
					;
					raf.seek(raf.getFilePointer() + 8);
					raf.writeInt(descendant.doublon);
					raf.seek(position);
					System.out.println("J'ai fini de copier le statgiaire");
					Noeud noeudEcrit = lireNoeud(raf);
					System.out.println(noeudEcrit);
					System.out.println("Début 2eme supression");
					raf.seek(noeudSupp.indexNoeudGauche * TAILLE_NOEUD);
					rechercherStagiaireASupprime(descendant.getStagiaire(), raf, position);
					System.out.println("fin 2eme supression");
				}
			}
	
	
		public Noeud trouverSuccesseur(Noeud noeudSupp, RandomAccessFile raf) throws IOException {
				// je declareun objet noeud successeur initialisé à null
				if (noeudSupp.getIndexNoeudDroit() == INDEX_FILS_NUL) {
					// si le noeud a un fils droit, on recherche le noeud ayant la clé minimale dans
					// le sous-arbre droit
					return noeudSupp;
				} else {
					raf.seek(noeudSupp.indexNoeudDroit * TAILLE_NOEUD);
					// int indexSuccesseur = chercherMinNoeud(getIndexNoeudDroit(), raf);
					// on lit le noeud successeur
					Noeud successeur = lireNoeud(raf);
					return trouverSuccesseur(successeur, raf);
				}
			}

	public void modifierStagiaire(Stagiaire stagAJour, RandomAccessFile raf) throws IOException {
		if (this.stagiaire.comparer(stagAJour) == 0) {
			rechercherStagiaireASupprime(stagAJour, raf, INDEX_FILS_NUL);
			ajouterNoeud(stagAJour, raf);
		} else {
			System.out.println("Stagiaire introuvable");
		}
	}
	// on appelle la methode supprimer pour supprimer l'ancien stagiaire

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