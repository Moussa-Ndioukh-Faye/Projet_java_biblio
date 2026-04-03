package service;

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

