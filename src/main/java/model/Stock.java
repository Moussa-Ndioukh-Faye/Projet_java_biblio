package model;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private List<Document> catalogue;

    public Stock() {
        catalogue = new ArrayList<>();
    }

    public void ajouterDocument(Document doc) {
        try {
            if (doc == null) {
                throw new Exception("Document invalide");
            }

            catalogue.add(doc);
            System.out.println("Document ajouté au stock.");

        } catch (Exception e) {
            System.out.println("Erreur ajout document : " + e.getMessage());
        }
    }

    public void retirerDocument(int id) {
        try {

            for (Document d : catalogue) {

                if (d.getId() == id) {

                    if (!d.isDisponible()) {
                        throw new Exception("Document emprunté !");
                    }

                    catalogue.remove(d);
                    System.out.println("Document retiré.");
                    return;
                }
            }

            throw new Exception("Document introuvable.");

        } catch (Exception e) {
            System.out.println("Erreur retrait : " + e.getMessage());
        }
    }

    public Document rechercherParTitre(String titre) {

        for (Document d : catalogue) {

            if (d.getTitre().equalsIgnoreCase(titre)) {
                return d;
            }

        }

        return null;
    }

    public List<Document> getDocumentsDisponibles() {

        List<Document> disponibles = new ArrayList<>();

        for (Document d : catalogue) {

            if (d.isDisponible()) {
                disponibles.add(d);
            }

        }

        return disponibles;
    }
}
