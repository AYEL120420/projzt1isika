package fr.isika.cda24.projet1.lanceur;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.isika.cda24.MonProjet1.MonProjet1.ArbreBinaireDeRecherche;
import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;

public class Lanceur {

	public static void main(String[] args) throws IOException {
	
		//Annuaire annuaire = new Annuaire();
				//annuaire.creerFichierTxt();
				
				ArbreBinaireDeRecherche arbre = new ArbreBinaireDeRecherche();
				RandomAccessFile raf = new RandomAccessFile("src/main/java/annuaireTxt/fBinaireStagiaire.bin", "rw");
				
				//---------------------------Feuilles-----------------------------
				
				//Stagiaire st = new Stagiaire("AKHIAD", "Brahim", "92","AI 60" , "2003");//ça ne marche pas
				//Stagiaire st1 = new Stagiaire("NOUAR", "Adel", "94", "ATOD 5", "2009");//ça ne marche pas
				//Stagiaire st2 = new Stagiaire("CHONE", "Martin", "92","ATOD 24 CP" , "2015");//ça marche 
				//Stagiaire st3 = new Stagiaire("ROIGNANT", "Pierre-Yves", "77", "ATOD 26 CP", "2015");//ça ne marche pas 
				
				//----------------------------SeulFils----------------------------

				//Stagiaire st = new Stagiaire("GARIJO", "Rosie", "75", "AI 79", "2011");
				//Stagiaire st1 = new Stagiaire("AUGEREAU", "Kévin", "76", "AI 78", "2010");
				//Stagiaire st2 = new Stagiaire("UNG", "Jet-Ming", "75", "ATOD 16 CP", "2012");
				
				//----------------------------deuxFils----------------------------

				//Stagiaire st = new Stagiaire("POTIN", "Thomas", "75", "ATOD 21", "2014");
				//Stagiaire st1 = new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008");
				//Stagiaire st2 = new Stagiaire("CHAVENEAU", "Kim Anh", "92", "ATOD 22", "2014");
				//arbre.ajouterDansArbre(st, raf);

				arbre.ecrireDansFichierBinaire();

				//arbre.lireFichierBinaire();
				List<Stagiaire> stagiaires = arbre.arbreAffichageInfix(raf);
				
				//arbre.supprimerStagiaire(st1, raf);
				//arbre.arbreAffichageInfix(raf);
				//arbre.supprimerStagiaire(st, raf);
				//arbre.arbreAffichageInfix(raf);
				//arbre.supprimerStagiaire(st3, raf);
				//arbre.supprimerStagiaire(st2, raf);
				
				//arbre.modifierStagiaire(st, raf);
				//System.out.println("Ma liste après suppression");
				//arbre.arbreAffichageInfix(raf);
//				stagiaires = arbre.resultatRecherche(st, raf);
//				for (Stagiaire stag : stagiaires) {
//					System.out.println(stag);
//				}
			}
		}