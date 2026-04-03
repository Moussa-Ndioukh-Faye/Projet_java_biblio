package model;

public class CapteurConservation {

    private double temperature;
    private double humidite;
    private String localisation;

    public CapteurConservation(String localisation) {
        this.localisation = localisation;
    }

    public void lireConditions(double temp, double hum) {

        this.temperature = temp;
        this.humidite = hum;

    }

    public boolean estHorsNorme() {

        return temperature > 25 || humidite > 60;

    }

    public void declencherAlarme() {

        if (estHorsNorme()) {

            System.out.println("⚠ Alerte conditions anormales à " + localisation);

        }

    }
}