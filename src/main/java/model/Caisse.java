package model;

import java.util.ArrayList;
import java.util.List;

public class Caisse {

    private double solde;
    private List<String> historique;

    public Caisse() {
        solde = 0;
        historique = new ArrayList<>();
    }

    public void encaisserAmende(double montant, String membre) {

        if (montant <= 0) {
            return;
        }

        solde += montant;

        historique.add("Amende payée par " + membre + " : " + montant);
    }

    public double getSolde() {
        return solde;
    }

    public void afficherHistorique() {

        for (String h : historique) {
            System.out.println(h);
        }

    }
}
