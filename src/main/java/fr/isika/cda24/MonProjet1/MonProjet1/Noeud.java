package fr.isika.cda24.MonProjet1.MonProjet1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
/**
 * le classe noeud represente l'objet noeud qui permet la construction de
 * l'arbre binaire de recherche
 * 
 * @author 33665
 *
 */

public class Noeud {

	private Stagiaire stagiaire;
	protected int indexNoeudGauche;
	protected int indexNoeudDroit;
	protected int doublon;
	public static final int INDEX_FILS_NUL = -1;
	public static final int TAILLE_NOEUD = 128; // ( taille d'un noeud en octet = taille stagiaire + taille
												// indexNoeudGauche +taille index noeud droit+taille doublon)
	/**
	 * le constructeur initialise un noeud à travers 4 attributs
	 * 
	 * @param stagiaire
	 * @param indexNoeudGauche
	 * @param indexNoeudDroit
	 * @param doublon
	 */
	
// Constructeurs

	public Noeud(Stagiaire stagiaire, int indexNoeudGauche, int indexNoeudDroit, int doublon) {

		this.stagiaire = stagiaire;
		this.indexNoeudGauche = indexNoeudGauche;
		this.indexNoeudDroit = indexNoeudDroit;
		this.doublon = doublon;
	}
	
//Methodes
	/**
	 * La methode ecrireStagiaire permet d'ecrire un Stagiaire dans le fichier
	 * binaire
	 * 
	 * @param noeud
	 * @param raf
	 */
	
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
	
	/**
	 * methode lireNoeud permet de lire un noeud dans le fichier binaire et retourne
	 * le noeud lu
	 * 
	 * @param raf
	 * @return
	 */
	
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

		} catch (IOException e) {

			e.printStackTrace();
		}

		return new Noeud(stag, filsGauche, filsDroit, doublon);

	}

	/**
	 * methode ajouterNoeud permet d'ajouter nouveau noeud contenant un stagiaire à
	 * l'arbre binaire de recherche
	 * 
	 * @param nvStagiaire
	 * @param raf
	 */
	
	public void ajouterNoeud(Stagiaire nvStagiaire, RandomAccessFile raf) {
		int comparaison = this.stagiaire.getNom().compareTo(nvStagiaire.getNom());
		try {

			if (comparaison > 0) {
				// nouveauStagiaire doit être placé à gauche
				if (this.indexNoeudGauche == INDEX_FILS_NUL) { // cas de terminaison

					// remonte de 12 octet le seek pour etre pret à ecrire à index gauche
					int positionEcritureNoeudGauche = (int) (raf.getFilePointer() - 12);
					raf.seek(positionEcritureNoeudGauche);

					// j ecris l'index du noeud que j ajoute (tailles du fichier / taille d'un
					// noeud en octet)
					indexNoeudGauche = (int) (raf.length() / TAILLE_NOEUD);
					raf.writeInt(indexNoeudGauche);

					// je me mets à la fin du fichier
					raf.seek(raf.length());

					// j'ecris le nouveauNoeudGauche
					Noeud nouveauNoeudGauche = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauNoeudGauche.ecrireStagiaire(nouveauNoeudGauche, raf);

				} else {
					// je deplace le seek à FG*taille d'un noeud
					raf.seek(this.indexNoeudGauche * TAILLE_NOEUD);

					// lis le noeud et je le stockes dans une variable (nouveauNoeudGauche)

					Noeud nouveauNoeudGauche = lireNoeud(raf);
					nouveauNoeudGauche.ajouterNoeud(nvStagiaire, raf);

				}
			} else if (comparaison < 0) {
				if (this.indexNoeudDroit == INDEX_FILS_NUL) { // cas de terminaison
					// remonte de 8 octet le seek pour etre pret à ecrire à index droit
					int positionEcrireNoeudDroit = (int) raf.getFilePointer() - 8;
					raf.seek(positionEcrireNoeudDroit);

					// j ecris l'index du noeud que j ajoute (tailles du fichier / taille d'un
					// noeud en octet)
					indexNoeudDroit = (int) (raf.length() / TAILLE_NOEUD);
					raf.writeInt(indexNoeudDroit);

					// je me mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudDroit
					Noeud nouveauNoeudDroit = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauNoeudDroit.ecrireStagiaire(nouveauNoeudDroit, raf);

				} else {
					// deplace le seek à FD*taille d'un noeud
					raf.seek(indexNoeudDroit * TAILLE_NOEUD);

					// lis le noeud et je le stockes dans une variable (nouveauNoeudDroit)
					Noeud nouveauNoeudDroit = lireNoeud(raf);
					nouveauNoeudDroit.ajouterNoeud(nvStagiaire, raf);
				}
			} else if (comparaison == 0) {
				// faire le doublon
				if (this.doublon == INDEX_FILS_NUL) { // cas de terminaison

					// remonte de 4 octet le seek pour etre pret à ecrire 
					int positionEcrireDoublon = (int) raf.getFilePointer() - 4;
					raf.seek(positionEcrireDoublon);

					// j ecris l'index du noeud que j ajoute (tailles du fichier / taille d'un
					// noeud en octet)
					doublon = (int) (raf.length() / TAILLE_NOEUD);
					raf.writeInt(doublon);

					// je me mets à la fin du fichier
					raf.seek(raf.length());

					// ecris le nouveauNoeudDroit
					Noeud nouveauDoublon = new Noeud(nvStagiaire, INDEX_FILS_NUL, INDEX_FILS_NUL, INDEX_FILS_NUL);
					nouveauDoublon.ecrireStagiaire(nouveauDoublon, raf);

				} else {
					// deplace le seek à doublon*taille d'un noeud
					raf.seek(doublon * TAILLE_NOEUD);

					// lis le noeud et je le stockes dans une variable (nouveauNoeudDroit)
					Noeud nouveauDNoeud = lireNoeud(raf);
					nouveauDNoeud.ajouterNoeud(nvStagiaire, raf);
				}

			}
		}

		catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * methode affichage infixe permet d'avoir un affichage par ordre alphabetique
	 * 
	 * @param raf
	 * @param stagiaires
	 */

	public void affichageInfixeNoeud(RandomAccessFile raf, List<Stagiaire> stagiaires) {
		try {
		
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
	
	/**
	 * La methode rechercherStagiaire permet de recherchrer un objet stagiarie dans
	 * l'arbre binaire et retourne une liste de stagiaire avec le meme nom
	 * 
	 * @param StagRecherche
	 * @param raf
	 * @param resultat
	 * @throws IOException
	 */

	public void rechercherStagiaire(Stagiaire StagRecherche, RandomAccessFile raf, List<Stagiaire> resultat)
			throws IOException {

		int comparaison = this.stagiaire.getNom().trim().compareTo(StagRecherche.getNom().trim());
		// on compare le nom du stagiaire présent avec le nom du stagiaire
		// recherché
		if (comparaison == 0) {// Stagiaire avec meme nom
			resultat.add(this.stagiaire);
			if (doublon != INDEX_FILS_NUL) {
				raf.seek(this.doublon * TAILLE_NOEUD);
				Noeud doublon = lireNoeud(raf);
				doublon.rechercherStagiaire(StagRecherche, raf, resultat);
			}
		} else {
			if (comparaison > 0) { // on cherche dans le sous arbre gauche
				if (this.indexNoeudGauche != INDEX_FILS_NUL) {
					raf.seek(this.getIndexNoeudGauche() * (TAILLE_NOEUD));// je mets mon pointeur avant le
					Noeud gauche = lireNoeud(raf); // FilsGauche
					// lire le noeud // pour commncer la comparaison
					gauche.rechercherStagiaire(StagRecherche, raf, resultat);// on fait appel à la methode recursive
				}
				if (comparaison < 0) { // si comparaison négative on continue la recherche dans le sous arbre droit
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
	
	/**
	 * La methode rachercherStagiaireASupprime permet de trouver le noeud qu'on veut supprimer
	 * de l'arbre binaire de recherche
	 * 
	 * @param StagRechercheASupp
	 * @param raf
	 * @param indexParent
	 * @throws IOException
	 */

	public void rechercherStagiaireASupprime(Stagiaire stagRechercheASupp, RandomAccessFile raf, int indexParent)
			throws IOException {
		// je stock la position du noeud sur lequel je suis
		int position = (int) (raf.getFilePointer() / TAILLE_NOEUD - 1);
		// je fais ma comparaison
		int comparaison = this.getStagiaire().comparer(stagRechercheASupp);
		System.out.println("Comparaison :" + this.getStagiaire().getNom() + " " +stagRechercheASupp.getNom() + " = "+ comparaison);
		if (comparaison == 0) {// Stagiaire recherché a été trouvé
			// appeler la méthode suppression du noeud
			supprimerNoeud(this, indexParent, raf);
		} else if (comparaison > 0) { // on cherche dans le sous arbre gauche
			if (this.indexNoeudGauche != INDEX_FILS_NUL) {
				raf.seek(this.getIndexNoeudGauche() * (TAILLE_NOEUD));// je mets mon pointeur avant le

				Noeud gauche = lireNoeud(raf); // FilsGauche

				// lire le noeud // pour commncer la comparaison
				gauche.rechercherStagiaireASupprime(stagRechercheASupp, raf, position);// on fé appel à la methode
																						// recursive
			} else {
				System.out.println("Ce nom n'existe pas!");
			}

		} else if (comparaison < 0) { // si comparaison négative on continue la recherche dans le sous arbre droit
			if (this.indexNoeudDroit != INDEX_FILS_NUL) { // si le noeud droit existe
				raf.seek(this.getIndexNoeudDroit() * (TAILLE_NOEUD));
				Noeud droit = lireNoeud(raf);
				droit.rechercherStagiaireASupprime(stagRechercheASupp, raf, position);// on fait appel à la methode
																						// recursive
			} else {
				System.out.println("Ce nom n'existe pas!");
			}
		}
	}

	/**
	 * La methode supprimerNoeud permet de supprimer un noeud de l'arbre binaire
	 * 
	 * @param noeudSupp
	 * @param indexParent
	 * @param raf
	 * @throws IOException
	 */
	public void supprimerNoeud(Noeud noeudSupp, int indexParent, RandomAccessFile raf) throws IOException {

//-----------------------------Cas 1 = mon noeudSupp est une feuille-------------------------------------------		
		if (indexNoeudDroit == INDEX_FILS_NUL && indexNoeudGauche == INDEX_FILS_NUL) {
			raf.seek(indexParent * TAILLE_NOEUD);// pointeur->position parent
			Noeud noeudParent = lireNoeud(raf);// je lis le noeud
			int comparaison = (noeudParent.getStagiaire().getNom().trim())
					.compareTo(noeudSupp.getStagiaire().getNom().trim());
			if (comparaison > 0) {
				raf.seek(raf.getFilePointer() - 12);// pointeur->position fils gauche
				raf.writeInt(-1);// modifié indexFilsGauche en -1 = filsGauche n'existe plus
			} else if (comparaison < 0) {
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
				Noeud noeudParent = lireNoeud(raf);
				int comparaison = (noeudParent.getStagiaire().getNom().trim())
						.compareTo(noeudSupp.getStagiaire().getNom().trim());
				if (comparaison > 0) {
					raf.seek(raf.getFilePointer() - 12);// pointeur->position fils gauche
					raf.writeInt(noeudSupp.indexNoeudGauche);
				} else if (comparaison < 0) {
					raf.seek(raf.getFilePointer() - 8);// pointeur->position fils Droit
					raf.writeInt(noeudSupp.indexNoeudGauche);
				} else {
					raf.seek(raf.getFilePointer() - 4);// pointeur->position doublon
					raf.writeInt(-1);// modifié doublon en -1 = doublon n'existe plus }

				}
			} else {
				raf.seek(indexParent * TAILLE_NOEUD);

				Noeud noeudParent = lireNoeud(raf);
				int comparaison = (noeudParent.getStagiaire().getNom().trim())
						.compareTo(noeudSupp.getStagiaire().getNom().trim());
				if (comparaison > 0) {
					raf.seek(raf.getFilePointer() - 12);// pointeur->position fils gauche
					raf.writeInt(noeudSupp.indexNoeudDroit);// modifié indexFilsGauche en -1 = filsGauche n'existe plus
				} else if (comparaison < 0) {
					raf.seek(raf.getFilePointer() - 8);// pointeur->position fils Droit
					raf.writeInt(noeudSupp.indexNoeudDroit);// modifié indexFilsDroit en -1 = filsDroit n'existe plus
				} else {
					raf.seek(raf.getFilePointer() - 4);// pointeur->position doublon
					raf.writeInt(-1);// modifié doublon en -1 = doublon n'existe plus }
				}
			}
//----------------------------Cas 3 = mon noeudSupp a deux fils-----------------------------------------------------
		} else if (indexNoeudDroit != INDEX_FILS_NUL && indexNoeudGauche != INDEX_FILS_NUL) {
			int position = (int) raf.getFilePointer() / TAILLE_NOEUD - 1;// je calcule la position du noeud à supp
			raf.seek(this.indexNoeudDroit * TAILLE_NOEUD);// on se positionne sur le fils droit du noeud à supprimer
			Noeud noeudSuppFilsDroit = lireNoeud(raf);// je lis le fils droit
			Noeud descendant = trouverSuccesseur(noeudSuppFilsDroit, raf);// trouver le successeur du nœud supprimé

			raf.seek(position * TAILLE_NOEUD);
			raf.writeChars(descendant.stagiaire.getNomLong());
			raf.writeChars(descendant.stagiaire.getPrenomLong());
			raf.writeChars(descendant.stagiaire.getDepartementLong());
			raf.writeChars(descendant.stagiaire.getCycleLong());
			raf.writeChars(descendant.stagiaire.getAnneeLong());
			raf.seek(raf.getFilePointer() + 8);
			raf.writeInt(descendant.doublon);
			raf.seek(position * TAILLE_NOEUD);
			Noeud noeudEcrit = lireNoeud(raf);
			System.out.println(noeudEcrit);

			raf.seek(this.indexNoeudDroit * TAILLE_NOEUD);
			Noeud noeudDroit = lireNoeud(raf);
			noeudDroit.rechercherStagiaireASupprime(descendant.getStagiaire(), raf, position);
		}
	}
	
	/**
	 * La methode trouverSuccesseur permet de trouver le successeur dans le cas d'un
	 * noeud qui a un fils gauche et un fils droit
	 * 
	 * @param noeudSupp
	 * @param raf
	 * @return
	 * @throws IOException
	 */
	public Noeud trouverSuccesseur(Noeud noeudSupp, RandomAccessFile raf) throws IOException {
		// si le noeudSupp n a pas de fils gauche = remplacant
		if (noeudSupp.getIndexNoeudGauche() == INDEX_FILS_NUL) {
			return noeudSupp;
		} else {
			// si le noeud a un fils gauche on continue de chercher le plus à gauche
			raf.seek(noeudSupp.getIndexNoeudGauche() * TAILLE_NOEUD);

			// on lit le noeud courant
			Noeud courant = lireNoeud(raf);
			return trouverSuccesseur(courant, raf);
		}
	}
/**
 * getters & setters
 * @return
 */
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