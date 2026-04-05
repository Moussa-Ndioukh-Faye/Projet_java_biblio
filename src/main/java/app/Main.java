package app;

import dao.DatabaseManager;
import model.*;
import service.BibliothequeService;

public class Main {
    public static void main(String[] args) {

        // ─── 1. Initialisation ───────────────────────────────────────────
        System.out.println("=== Démarrage du système de la bibliothèque ===");
        DatabaseManager.initDatabase();
        BibliothequeService bibliotheque = new BibliothequeService();

        // ─── 2. Création des membres ─────────────────────────────────────
        System.out.println("\n--- Inscription des membres ---");
        Membre membre1 = new Membre("Diallo", "Amadou", "amadou@gmail.com", "771234567");
        Membre membre2 = new Membre("Ndiaye", "Fatou", "fatou@gmail.com", "778765432");
        bibliotheque.inscrireMembre(membre1);
        bibliotheque.inscrireMembre(membre2);
        System.out.println(membre1);
        System.out.println(membre2);

        // ─── 3. Création des documents ───────────────────────────────────
        System.out.println("\n--- Ajout des documents ---");
        Livre livre1 = new Livre(
            "Le Monde de Sophie", "Jostein Gaarder", 1991,
            "Roman philosophique", "978-2-07-051281-6",
            "Philosophie", 532, "Gallimard"
        );
        Livre livre2 = new Livre(
            "Clean Code", "Robert Martin", 2008,
            "Guide de programmation", "978-0-13-235088-4",
            "Informatique", 431, "Prentice Hall"
        );
        Revue revue1 = new Revue(
            "Science & Vie", "Collectif", 2024,
            "Revue scientifique", "ISSN-0036-8065",
            "Mensuelle", 42, "Janvier"
        );

        bibliotheque.ajouterDocument(livre1);
        bibliotheque.ajouterDocument(livre2);
        bibliotheque.ajouterDocument(revue1);
        System.out.println(livre1);
        System.out.println(livre2);
        System.out.println(revue1);

        // ─── 4. Emprunts ─────────────────────────────────────────────────
        System.out.println("\n--- Emprunts ---");
        bibliotheque.emprunterDocument(membre1, livre1, 14);
        bibliotheque.emprunterDocument(membre2, revue1, 7);
        System.out.println("Statut livre1 disponible : " + livre1.isDisponible());
        System.out.println("Statut revue1 disponible : " + revue1.isDisponible());

        // ─── 5. Tentative d'emprunt d'un doc non disponible ─────────────
        System.out.println("\n--- Tentative emprunt document déjà emprunté ---");
        bibliotheque.emprunterDocument(membre2, livre1, 14);

        // ─── 6. Retour d'un document ─────────────────────────────────────
        System.out.println("\n--- Retour de document ---");
        Emprunt emprunt1 = membre1.getEmpruntsActifs().get(0);
        bibliotheque.retournerDocument(emprunt1, membre1, livre1);
        System.out.println("Statut livre1 après retour : " + livre1.isDisponible());

        // ─── 7. Véhicule & Livraison ─────────────────────────────────────
        System.out.println("\n--- Livraison ---");
        Vehicule vehicule = new Vehicule("DK-1234-AB", 15000, true);
        vehicule.effectuerLivraison("Annexe Nord", null);

        // ─── 8. Capteur de conservation ──────────────────────────────────
        System.out.println("\n--- Surveillance conservation ---");
        CapteurConservation capteur = new CapteurConservation(22.5, 55.0, "Salle A");
        capteur.lireConditions();
        if (capteur.estHorsNorme()) {
            capteur.declencherAlarme();
        } else {
            System.out.println("Conditions de conservation normales.");
        }

        // ─── 9. État final ───────────────────────────────────────────────
        System.out.println();
        bibliotheque.afficherEtat();
    }
}
