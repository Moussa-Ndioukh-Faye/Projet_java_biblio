
package service;

import model.Stock;
import model.Caisse;
import model.Document;
import model.Membre;
import model.Emprunt;

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