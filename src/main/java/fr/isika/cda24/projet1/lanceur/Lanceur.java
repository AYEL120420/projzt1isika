package fr.isika.cda24.projet1.lanceur;
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
		
		
	}

}