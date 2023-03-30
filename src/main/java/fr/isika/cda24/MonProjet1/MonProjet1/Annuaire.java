package fr.isika.cda24.MonProjet1.MonProjet1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Annuaire {
	public ArrayList<Stagiaire>stagiaire;

//lecture du fichier text contenant la liste des stagiaires 
//on cree une liste chainée des stagiaires

//on cree un annuaire texte pour avoir un chemin relatif

	public void creerFichierTxt() {

		File annuaire = new File("src/main/java/annuaire");

		annuaire.mkdir();

		File annuaireTxt = new File("src/main/java/annuaire/Annuaire.txt");
		try {
			annuaireTxt.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public ArrayList<Stagiaire> lireFichierTxt() {
		
		ArrayList<Stagiaire> stagiaires = new ArrayList<>();
		
		try {
			FileReader fr = new FileReader("src/main/java/annuaire/annuaire.txt");

			BufferedReader br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {
				if (line.equals("*")) {
					// Si la ligne contient un astérisque, cela signifie que nous avons terminé de
					// lire les informations d'un stagiaire
					System.out.println("--------------------");
				} else {
					// Sinon, nous pouvons extraire les informations du stagiaire

					String nom = line;
					String prenom = br.readLine();
					String departement = br.readLine();
					String cycle = br.readLine();
					String annee = br.readLine();

					Stagiaire s = new Stagiaire(nom, prenom, departement, cycle, annee);

					System.out.println("Nom : " + nom);
					System.out.println("Prénom : " + prenom);
					System.out.println("Département : " + departement);
					System.out.println("Cycle : " + cycle);
					System.out.println("Année : " + annee);

					stagiaires.add(s);
				}
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stagiaires;

	}
}