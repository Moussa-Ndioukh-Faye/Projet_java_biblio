package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Classe centrale représentant la bibliothèque municipale.
 * Agrège tous les éléments du système.
 */
public class Bibliotheque {
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private final List<Membre>              membres;
    private final List<Document>            documents;
    private final List<Emprunt>             emprunts;
    private final List<Stock>               stocks;
    private final List<Vehicule>            vehicules;
    private final List<CapteurConservation> capteurs;
    private Caisse caisse;

    public Bibliotheque() {
        this.membres   = new ArrayList<>();
        this.documents = new ArrayList<>();
        this.emprunts  = new ArrayList<>();
        this.stocks    = new ArrayList<>();
        this.vehicules = new ArrayList<>();
        this.capteurs  = new ArrayList<>();
        this.caisse    = new Caisse();
    }

    public Bibliotheque(String nom, String adresse, String telephone) {
        this();
        setNom(nom);
        setAdresse(adresse);
        this.telephone = telephone;
    }

    // ─── Getters ───────────────────────────────────────────────────────────
    public int getId()    { return id; }
    public String getNom()       { return nom; }
    public String getAdresse()   { return adresse; }
    public String getTelephone() { return telephone; }
    public Caisse getCaisse()    { return caisse; }

    public List<Membre>              getMembres()   { return Collections.unmodifiableList(membres); }
    public List<Document>            getDocuments() { return Collections.unmodifiableList(documents); }
    public List<Emprunt>             getEmprunts()  { return Collections.unmodifiableList(emprunts); }
    public List<Stock>               getStocks()    { return Collections.unmodifiableList(stocks); }
    public List<Vehicule>            getVehicules() { return Collections.unmodifiableList(vehicules); }
    public List<CapteurConservation> getCapteurs()  { return Collections.unmodifiableList(capteurs); }

    // ─── Setters ───────────────────────────────────────────────────────────
    public void setId(int id)           { this.id = id; }
    public void setNom(String nom)      {
        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("Le nom de la bibliothèque est obligatoire.");
        this.nom = nom.trim();
    }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setTelephone(String t)     { this.telephone = t; }
    public void setCaisse(Caisse caisse)   {
        if (caisse == null) throw new IllegalArgumentException("Caisse invalide.");
        this.caisse = caisse;
    }

    // ─── Gestion des membres ───────────────────────────────────────────────
    public void ajouterMembre(Membre m) {
        if (m == null) throw new IllegalArgumentException("Membre null.");
        membres.add(m);
    }

    public Optional<Membre> trouverMembreParId(int id) {
        return membres.stream().filter(m -> m.getId() == id).findFirst();
    }

    public Optional<Membre> trouverMembreParEmail(String email) {
        return membres.stream()
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // ─── Gestion des documents ────────────────────────────────────────────
    public void ajouterDocument(Document d) {
        if (d == null) throw new IllegalArgumentException("Document null.");
        documents.add(d);
    }

    public Optional<Document> trouverDocumentParId(int id) {
        return documents.stream().filter(d -> d.getId() == id).findFirst();
    }

    public List<Document> rechercherParTitre(String motCle) {
        if (motCle == null || motCle.trim().isEmpty()) return Collections.emptyList();
        String mc = motCle.trim().toLowerCase();
        return documents.stream()
                .filter(d -> d.getTitre().toLowerCase().contains(mc))
                .toList();
    }

    // ─── Gestion des stocks ───────────────────────────────────────────────
    public void ajouterStock(Stock s) {
        if (s == null) throw new IllegalArgumentException("Stock null.");
        stocks.add(s);
    }

    public Optional<Stock> trouverStockParDocument(int documentId) {
    return Optional.empty();
}
   

    // ─── Gestion des emprunts ─────────────────────────────────────────────
    public void ajouterEmprunt(Emprunt e) {
        if (e == null) throw new IllegalArgumentException("Emprunt null.");
        emprunts.add(e);
    }

    public List<Emprunt> getEmpruntsEnCours() {
        return emprunts.stream()
                .filter(e -> e.getStatut() == Emprunt.Statut.EN_COURS
                          || e.getStatut() == Emprunt.Statut.EN_RETARD)
                .toList();
    }

    public List<Emprunt> getEmpruntsEnRetard() {
        emprunts.forEach(Emprunt::verifierRetard);
        return emprunts.stream()
                .filter(e -> e.getStatut() == Emprunt.Statut.EN_RETARD)
                .toList();
    }

    public List<Emprunt> getEmpruntsMembre(int membreId) {
        return emprunts.stream()
                .filter(e -> e.getMembre().getId() == membreId)
                .toList();
    }

    // ─── Gestion des véhicules ────────────────────────────────────────────
    public void ajouterVehicule(Vehicule v) {
        if (v == null) throw new IllegalArgumentException("Véhicule null.");
        vehicules.add(v);
    }

    // ─── Gestion des capteurs ─────────────────────────────────────────────
    public void ajouterCapteur(CapteurConservation c) {
        if (c == null) throw new IllegalArgumentException("Capteur null.");
        capteurs.add(c);
    }

    public List<CapteurConservation> getCapteursEnAlerte() {
        return capteurs.stream().filter(CapteurConservation::estHorsNorme).toList();
    }

    @Override
    public String toString() {
        return String.format("Bibliothèque[%s | %s | Membres: %d | Documents: %d | Emprunts actifs: %d]",
                nom, adresse, membres.size(), documents.size(), getEmpruntsEnCours().size());
    }
}
