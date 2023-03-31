package fr.isika.cda24.projet1.lanceur;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.isika.cda24.MonProjet1.MonProjet1.Annuaire;
import fr.isika.cda24.MonProjet1.MonProjet1.ArbreBinaireDeRecherche;
import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;

public class Lanceur {

	public static void main(String[] args) {
		
		Annuaire annuaire = new Annuaire();
		annuaire.creerFichierTxt();
		List<Stagiaire> stagiaires = annuaire.lireFichierTxt();
		
		
		ArbreBinaireDeRecherche abr = new ArbreBinaireDeRecherche();
		
		for (Stagiaire s: stagiaires ) {
			abr.ajouterDansArbre(s);
			
		} 
		abr.affichageInfixe();
		
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/annuaireTxt/fBinaireStagiaire.bin" , "rw");
			
			for(Stagiaire s : stagiaires) {
				raf.writeChars(s.getNomLong());
				raf.writeChars(s.getPrenomLong());
				raf.writeChars(s.getCycleLong());
				raf.writeChars(s.getDepartementLong());
				raf.writeChars(s.getAnneeLong());
				
			
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}