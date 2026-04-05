package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un membre inscrit à la bibliothèque.
 */
public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateInscription;
    private double amendeTotal;
    private boolean actif;
    private List<Emprunt> empruntsActifs = new ArrayList<>();

    private static final int MAX_EMPRUNTS_SIMULTANES = 5;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Membre() {}

    public Membre(String nom, String prenom, String email, String telephone) {
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setTelephone(telephone);
        this.dateInscription = LocalDate.now();
        this.amendeTotal = 0.0;
        this.actif = true;
    }

    // ─── Getters ───────────────────────────────────────────────────────────
    public int getId()                   { return id; }
    public String getNom()               { return nom; }
    public String getPrenom()            { return prenom; }
    public String getNomComplet()        { return prenom + " " + nom; }
    public String getEmail()             { return email; }
    public String getTelephone()         { return telephone; }
    public LocalDate getDateInscription(){ return dateInscription; }
    public double getAmendeTotal()       { return amendeTotal; }
    public boolean isActif()             { return actif; }
    public int getMaxEmprunts()          { return MAX_EMPRUNTS_SIMULTANES; }
    public List<Emprunt> getEmpruntsActifs() { return empruntsActifs; }
    public double getAmendeDue() { return amendeTotal; }

    // ─── Setters ───────────────────────────────────────────────────────────
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID invalide.");
        this.id = id;
    }

    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("Le nom ne peut pas être vide.");
        this.nom = nom.trim().toUpperCase();
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.trim().isEmpty())
            throw new IllegalArgumentException("Le prénom ne peut pas être vide.");
        this.prenom = prenom.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Email invalide : " + email);
        this.email = email.trim().toLowerCase();
    }

    public void setTelephone(String telephone) {
        if (telephone == null || telephone.trim().length() < 8)
            throw new IllegalArgumentException("Numéro de téléphone invalide.");
        this.telephone = telephone.trim();
    }

    public void setDateInscription(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Date invalide.");
        this.dateInscription = date;
    }

    public void setAmendeTotal(double amendeTotal) {
        if (amendeTotal < 0)
            throw new IllegalArgumentException("L'amende ne peut pas être négative.");
        this.amendeTotal = amendeTotal;
    }

    public void setActif(boolean actif) { this.actif = actif; }

    /**
     * Ajoute une amende au total du membre.
     */
    public void ajouterAmende(double montant) {
        if (montant < 0)
            throw new IllegalArgumentException("Le montant de l'amende doit être positif.");
        this.amendeTotal += montant;
    }

    /**
     * Acquitte une partie ou totalité de l'amende.
     */
    public void payerAmende(double montant) {
        if (montant <= 0)
            throw new IllegalArgumentException("Montant de paiement invalide.");
        if (montant > this.amendeTotal)
            throw new IllegalArgumentException("Montant supérieur à l'amende due (" + this.amendeTotal + " FCFA).");
        this.amendeTotal -= montant;
    }

    @Override
    public String toString() {
        return String.format("Membre[ID=%d | %s | Email: %s | Tél: %s | Inscrit: %s | Amende: %.0f FCFA | Actif: %s]",
                id, getNomComplet(), email, telephone,
                dateInscription != null ? dateInscription.format(FORMATTER) : "N/A",
                amendeTotal, actif ? "Oui" : "Non");
    }
    public void ajouterEmprunt(Emprunt e) {
    if (empruntsActifs.size() >= MAX_EMPRUNTS_SIMULTANES)
        throw new IllegalStateException("Nombre maximum d'emprunts atteint.");
    empruntsActifs.add(e);
}
}
