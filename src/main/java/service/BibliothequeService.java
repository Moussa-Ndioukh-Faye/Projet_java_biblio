package service;

import dao.*;
import model.*;
import java.time.LocalDate;
import java.util.List;

public class BibliothequeService {

    private Stock stock;
    private Caisse caisse;

    public BibliothequeService() {
        stock = new Stock();
        caisse = new Caisse();
    }

    // Inscrit un nouveau membre et le sauvegarde en base
    public void inscrireMembre(Membre m) {
        MembreDAO.save(m);
        System.out.println("Membre inscrit : " + m.getNom() + " " + m.getPrenom());
    }

    // Ajoute un document dans le stock
    public void ajouterDocument(Document doc) {
        try {
            stock.ajouterDocument(doc);
        } catch (Exception e) {
            System.out.println("Erreur ajout document : " + e.getMessage());
        }
    }

    // Retire un document du stock
    public void retirerDocument(int id) {
        try {
            stock.retirerDocument(id);
        } catch (Exception e) {
            System.out.println("Erreur retrait document : " + e.getMessage());
        }
    }

    // Recherche un document par titre
    public Document rechercherDocument(String titre) {
        try {
            return stock.rechercherParTitre(titre);
        } catch (Exception e) {
            System.out.println("Erreur recherche : " + e.getMessage());
        }
        return null;
    }

    // Emprunte un document pour un membre
    public void emprunterDocument(Membre membre, Document doc, int dureeJours) {
        try {
            if (!doc.isDisponible()) {
                throw new Exception("Document déjà emprunté");
            }
            Emprunt e = new Emprunt(membre, doc);
            e.setDateRetourPrevue(LocalDate.now().plusDays(dureeJours));
            e.setDateEmprunt(LocalDate.now());
           
            membre.ajouterEmprunt(e);
            doc.setDisponible(false);

            EmpruntDAO.save(e);
            DocumentDAO.updateDisponible(doc.getId(), false);

            System.out.println("Emprunt effectué : " + doc.getTitre()
                + " par " + membre.getNom());
        } catch (Exception e) {
            System.out.println("Erreur emprunt : " + e.getMessage());
        }
    }

    // Retourne un document et encaisse l'amende si retard
    public void retournerDocument(Emprunt e, Membre membre, Document doc) {
        try {
            e.effectuerRetour();
            EmpruntDAO.updateRetour(e.getId(), LocalDate.now());

            doc.setDisponible(true);
            DocumentDAO.updateDisponible(doc.getId(), true);

            double amende = membre.getAmendeDue();
            if (amende > 0) {
                caisse.encaisserAmende(amende, membre.getNom());
                CaisseDAO.save(membre.getNom(), amende);
                System.out.println("Amende encaissée : " + amende + " FCFA");
            }

            System.out.println("Document retourné : " + doc.getTitre());
        } catch (Exception ex) {
            System.out.println("Erreur retour : " + ex.getMessage());
        }
    }

    // Affiche l'état général de la bibliothèque
    public void afficherEtat() {
        System.out.println("=== ÉTAT DE LA BIBLIOTHÈQUE ===");
        System.out.println("-- Membres --");
        MembreDAO.findAll().forEach(m ->
            System.out.println(m.getNom() + " " + m.getPrenom()));
        System.out.println("-- Documents disponibles --");
        DocumentDAO.findDisponibles();
        System.out.println("-- Caisse --");
        System.out.println("Total encaissé : " + CaisseDAO.getTotalEncaisse() + " FCFA");
        System.out.println("===============================");
    }

    public List<Document> getDocumentsDisponibles() {
        return stock.getDocumentsDisponibles();
    }

    public double getSoldeCaisse() {
        return caisse.getSolde();
    }
}