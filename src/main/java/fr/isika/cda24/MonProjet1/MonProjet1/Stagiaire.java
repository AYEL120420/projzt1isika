package fr.isika.cda24.MonProjet1.MonProjet1;


public class Stagiaire {
    private String nom;
    private String prenom;
    private String departement;
    private String cycle;
    private String annee;

    public Stagiaire(String nom, String prenom, String departement, String cycle, String annee) {
        this.nom = nom;
        this.prenom = prenom;
        this.departement = departement;
        this.cycle = cycle;
        this.annee = annee;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDepartement() {
        return departement;
    }

    public String getPromotion() {
        return cycle;
    }

    public String getAnnee() {
        return annee;
    }

    @Override
    public String toString() {
        return "Stagiaire{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", departement=" + departement +
                ", promotion='" + cycle + '\'' +
                ", anneeDebut='" + annee + '\'' +
                '}';
    }
    
}
