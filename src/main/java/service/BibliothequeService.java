
package service;

<<<<<<< HEAD
import dao.*;
import model.*;
import java.time.LocalDate;

public class BibliothequeService {

    // Inscrit un nouveau membre et le sauvegarde en base
    public static void inscrireMembre(Membre m) {
        MembreDAO.save(m);
        System.out.println("Membre inscrit : " + m.getNom() + " " + m.getPrenom());
    }

    // Emprunte un document pour un membre
    public static Emprunt emprunterDocument(Membre membre, Document doc, int dureeJours) {
        if (!doc.isDisponible()) {
            System.out.println("Document non disponible : " + doc.getTitre());
            return null;
        }
        // Créer l'emprunt
        Emprunt e = new Emprunt();
        e.setMembreId(membre.getId());
        e.setDocumentId(doc.getId());
        e.setDateEmprunt(LocalDate.now());
        e.setDateRetourPrevu(LocalDate.now().plusDays(dureeJours));

        // Sauvegarder en base
        EmpruntDAO.save(e);

        // Marquer le document comme non disponible
        doc.setDisponible(false);
        DocumentDAO.updateDisponible(doc.getId(), false);

        System.out.println("Emprunt effectué : " + doc.getTitre() 
            + " par " + membre.getNom());
        return e;
    }

    // Retourne un document et calcule l'amende si retard
    public static void retournerDocument(Emprunt e, Membre membre, Document doc) {
        // Enregistrer la date de retour
        e.effectuerRetour();
        EmpruntDAO.updateRetour(e.getId(), LocalDate.now());

        // Marquer le document comme disponible
        doc.setDisponible(true);
        DocumentDAO.updateDisponible(doc.getId(), true);

        // Calculer et encaisser l'amende si retard
        double amende = e.calculerAmende();
        if (amende > 0) {
            CaisseDAO.save(membre.getNom(), amende);
            System.out.println("Amende encaissée : " + amende + " FCFA");
        }

        System.out.println("Document retourné : " + doc.getTitre());
    }

    // Affiche l'état général de la bibliothèque
    public static void afficherEtat() {
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
}
=======
import model.Stock;
import model.Caisse;
import model.Document;
import model.Membre;
import model.Emprunt;
>>>>>>> b26709ce7e499b94e0a69f9e3691509328ad8b22

import java.util.List;

public class BibliothequeService {

    private Stock stock;
    private Caisse caisse;

    public BibliothequeService() {
        stock = new Stock();
        caisse = new Caisse();
    }

    // ajouter un document dans le stock
    public void ajouterDocument(Document doc) {
        try {
            stock.ajouterDocument(doc);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du document : " + e.getMessage());
        }
    }

    // retirer un document
    public void retirerDocument(int id) {
        try {
            stock.retirerDocument(id);
        } catch (Exception e) {
            System.out.println("Erreur retrait document : " + e.getMessage());
        }
    }

    // rechercher document
    public Document rechercherDocument(String titre) {
        try {
            return stock.rechercherParTitre(titre);
        } catch (Exception e) {
            System.out.println("Erreur recherche document : " + e.getMessage());
        }

        return null;
    }

    // emprunter un document
    public void emprunterDocument(Membre membre, Document doc) {
        try {
            if (!doc.isDisponible()) {
                throw new Exception("Document déjà emprunté");
            }

            Emprunt emprunt = new Emprunt(membre, doc);
            membre.ajouterEmprunt(emprunt);

            doc.setDisponible(false);

        } catch (Exception e) {
            System.out.println("Erreur emprunt : " + e.getMessage());
        }
    }

    // retourner document
    public void retournerDocument(Membre membre, Document doc) {
        try {

            doc.setDisponible(true);

            double amende = membre.getAmendeDue();

            if (amende > 0) {
                caisse.encaisserAmende(amende, membre.getNom());
            }

        } catch (Exception e) {
            System.out.println("Erreur retour document : " + e.getMessage());
        }
    }

    public List<Document> getDocumentsDisponibles() {
        return stock.getDocumentsDisponibles();
    }

    public double getSoldeCaisse() {
        return caisse.getSolde();
    }
}