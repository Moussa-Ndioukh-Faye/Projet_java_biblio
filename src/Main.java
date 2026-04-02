import bibliotheque.membres.*; // INDISPENSABLE pour lier tes fichiers

public class Main {
    public static void main(String[] args) {
        // 1. On crée un document (on lui donne un ID car ta classe en a un)
        Document livre = new Document();

        // 2. On crée le membre
        Membre m = new Membre(1, "Sow", "Moussa", "moussa@ucad.sn");

        // 3. On crée l'emprunt
        Emprunt e = new Emprunt(101, livre);

        // 4. On ajoute l'emprunt au membre et on affiche
        m.ajouterEmprunt(e);
        System.out.println("Test reussi pour : " + m.getPrenom() + " " + m.getNom());
        System.out.println("Amende actuelle : " + m.getAmendeDue() + " FCFA");
    }
}
