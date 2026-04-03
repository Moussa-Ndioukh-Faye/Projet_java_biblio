package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Représente un emprunt d'un document par un membre.
 */
public class Emprunt {

    public enum Statut {
        EN_COURS("En cours"),
        RETOURNE("Retourné"),
        EN_RETARD("En retard");

        private final String libelle;
        Statut(String libelle) { this.libelle = libelle; }
        public String getLibelle() { return libelle; }
    }

    /** Tarif de l'amende par jour de retard (en FCFA) */
    public static final double TARIF_AMENDE_PAR_JOUR = 100.0;
    /** Durée d'emprunt standard en jours */
    public static final int DUREE_EMPRUNT_JOURS = 14;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private Membre membre;
    private Document document;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;
    private Statut statut;
    private double amende;

    public Emprunt() {}

    public Emprunt(Membre membre, Document document) {
        if (membre == null)   throw new IllegalArgumentException("Le membre ne peut pas être null.");
        if (document == null) throw new IllegalArgumentException("Le document ne peut pas être null.");
        if (!membre.isActif()) throw new IllegalStateException("Le membre est inactif.");

        this.membre = membre;
        this.document = document;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevue = dateEmprunt.plusDays(DUREE_EMPRUNT_JOURS);
        this.statut = Statut.EN_COURS;
        this.amende = 0.0;
    }

    // ─── Getters ───────────────────────────────────────────────────────────
    public int getId()                          { return id; }
    public Membre getMembre()                   { return membre; }
    public Document getDocument()               { return document; }
    public LocalDate getDateEmprunt()           { return dateEmprunt; }
    public LocalDate getDateRetourPrevue()      { return dateRetourPrevue; }
    public LocalDate getDateRetourEffective()   { return dateRetourEffective; }
    public Statut getStatut()                   { return statut; }
    public double getAmende()                   { return amende; }

    // ─── Setters ───────────────────────────────────────────────────────────
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID invalide.");
        this.id = id;
    }

    public void setMembre(Membre membre) {
        if (membre == null) throw new IllegalArgumentException("Membre invalide.");
        this.membre = membre;
    }

    public void setDocument(Document document) {
        if (document == null) throw new IllegalArgumentException("Document invalide.");
        this.document = document;
    }

    public void setDateEmprunt(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Date invalide.");
        this.dateEmprunt = date;
    }

    public void setDateRetourPrevue(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Date invalide.");
        this.dateRetourPrevue = date;
    }

    public void setDateRetourEffective(LocalDate date) {
        this.dateRetourEffective = date;
    }

    public void setStatut(Statut statut) {
        if (statut == null) throw new IllegalArgumentException("Statut invalide.");
        this.statut = statut;
    }

    public void setAmende(double amende) {
        if (amende < 0) throw new IllegalArgumentException("L'amende ne peut pas être négative.");
        this.amende = amende;
    }

    /**
     * Calcule et enregistre le retour d'un document.
     * Calcule automatiquement l'amende si retour en retard.
     *
     * @return le montant de l'amende (0 si à temps)
     */
    public double effectuerRetour() {
        if (statut == Statut.RETOURNE)
            throw new IllegalStateException("Ce document a déjà été retourné.");

        this.dateRetourEffective = LocalDate.now();
        this.statut = Statut.RETOURNE;

        // Calcul de l'amende en cas de retard
        long joursRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
        if (joursRetard > 0) {
            this.amende = joursRetard * TARIF_AMENDE_PAR_JOUR;
            this.membre.ajouterAmende(this.amende);
        }
        return this.amende;
    }

    /**
     * Met à jour le statut si l'emprunt est en retard.
     */
    public void verifierRetard() {
        if (statut == Statut.EN_COURS && LocalDate.now().isAfter(dateRetourPrevue)) {
            this.statut = Statut.EN_RETARD;
        }
    }

    /**
     * Retourne le nombre de jours de retard actuels (0 si non en retard).
     */
    public long getJoursRetard() {
        LocalDate ref = (dateRetourEffective != null) ? dateRetourEffective : LocalDate.now();
        long jours = ChronoUnit.DAYS.between(dateRetourPrevue, ref);
        return Math.max(0, jours);
    }

    @Override
    public String toString() {
        return String.format(
                "Emprunt[ID=%d | Membre: %s | Doc: \"%s\" | Emprunté: %s | Retour prévu: %s | Statut: %s | Amende: %.0f FCFA]",
                id,
                membre != null ? membre.getNomComplet() : "N/A",
                document != null ? document.getTitre() : "N/A",
                dateEmprunt != null ? dateEmprunt.format(FORMATTER) : "N/A",
                dateRetourPrevue != null ? dateRetourPrevue.format(FORMATTER) : "N/A",
                statut.getLibelle(),
                amende);
    }
}
