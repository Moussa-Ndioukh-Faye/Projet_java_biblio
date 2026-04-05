package model;

public class CapteurConservation {

    private double temperature;
    private double humidite;
    private String localisation;

    public CapteurConservation(String localisation) {
        this.localisation = localisation;
    }
    public CapteurConservation(double temperature, double humidite, String localisation) {
    this.temperature = temperature;
    this.humidite = humidite;
    this.localisation = localisation;
}

public double getTemperature() { return temperature; }
public double getHumidite() { return humidite; }
public String getLocalisation() { return localisation; }

public void lireConditions() {
    System.out.println("Capteur [" + localisation + "] — Température: " 
        + temperature + "°C | Humidité: " + humidite + "%");
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