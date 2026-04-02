package bibliotheque.membres;

import java.util.ArrayList;
import java.util.List;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private List<Emprunt> empruntsActifs;

    public Membre(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.empruntsActifs = new ArrayList<>();
    }

    public void ajouterEmprunt(Emprunt e) {
        this.empruntsActifs.add(e);
    }

    public double getAmendeDue() {
        double total = 0;
        for (Emprunt e : empruntsActifs) {
            total += e.calculerAmende();
        }
        return total;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
}
