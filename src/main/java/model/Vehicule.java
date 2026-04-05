package model;

import java.util.List;

public class Vehicule {

    private String immatriculation;
    private double kilometrage;
    private boolean enService;

    public Vehicule(String immatriculation) {
        this.immatriculation = immatriculation;
        this.kilometrage = 0;
        this.enService = true;
    }
    public Vehicule(String immatriculation, double kilometrage, boolean enService) {
    this.immatriculation = immatriculation;
    this.kilometrage = kilometrage;
    this.enService = enService;
}

public String getImmatriculation() { return immatriculation; }
public double getKilometrage() { return kilometrage; }
public boolean isEnService() { return enService; }

    public void effectuerLivraison(String destination, List<Document> docs) {

        if (!enService) {
            System.out.println("Vehicule hors service !");
            return;
        }

        if (docs == null || docs.isEmpty()) {
            System.out.println("Aucun document à livrer");
            return;
        }

        System.out.println("Livraison vers " + destination);

        kilometrage += 10;
    }

    public void mettreEnRevision() {

        enService = false;

        System.out.println("Vehicule en revision.");

    }
}