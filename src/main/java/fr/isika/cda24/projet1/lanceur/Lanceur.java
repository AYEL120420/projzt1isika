package fr.isika.cda24.projet1.lanceur;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.isika.cda24.MonProjet1.MonProjet1.ArbreBinaireDeRecherche;
import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;

public class Lanceur {

	public static void main(String[] args) throws IOException {

		ArbreBinaireDeRecherche arbre = new ArbreBinaireDeRecherche();
		RandomAccessFile raf = new RandomAccessFile("src/main/java/annuaire/fBinaireStagiaire.bin", "rw");

		Stagiaire st = new Stagiaire("AKHIAD", "Brahim", "80", "AI 60", "2003");
		// arbre.ajouterDansArbre(st, raf);

		arbre.ecrireDansFichierBinaire();

		arbre.lireFichierBinaire();
		List<Stagiaire> stagiaires = arbre.arbreAffichageInfix(raf);

		stagiaires = arbre.resultatRecherche(st, raf);
		for (Stagiaire stag : stagiaires) {
			System.out.println(stag);
		}
	}
}
