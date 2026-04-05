package model;

/**
 * Classe abstraite représentant un document de la bibliothèque.
 * Livre et Revue héritent de cette classe.
 */
public abstract class Document {
    private int id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private String description;
    private boolean disponible = true;

    public Document() {}

    public Document(String titre, String auteur, int anneePublication, String description) {
        if (titre == null || titre.trim().isEmpty())
            throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        if (auteur == null || auteur.trim().isEmpty())
            throw new IllegalArgumentException("L'auteur ne peut pas être vide.");
        if (anneePublication < 1000 || anneePublication > 2100)
            throw new IllegalArgumentException("Année de publication invalide : " + anneePublication);

        this.titre = titre.trim();
        this.auteur = auteur.trim();
        this.anneePublication = anneePublication;
        this.description = (description != null) ? description.trim() : "";
    }

    /** Retourne le type du document (ex: "Livre", "Revue") */
    public abstract String getType();

    // ─── Getters ───────────────────────────────────────────────────────────
    public int getId()               { return id; }
    public String getTitre()         { return titre; }
    public String getAuteur()        { return auteur; }
    public int getAnneePublication() { return anneePublication; }
    public String getDescription()   { return description; }
    public boolean isDisponible() { return disponible; }

    // ─── Setters ───────────────────────────────────────────────────────────
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("L'ID doit être positif.");
        this.id = id;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.trim().isEmpty())
            throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        this.titre = titre.trim();
    }

    public void setAuteur(String auteur) {
        if (auteur == null || auteur.trim().isEmpty())
            throw new IllegalArgumentException("L'auteur ne peut pas être vide.");
        this.auteur = auteur.trim();
    }

    public void setAnneePublication(int anneePublication) {
        if (anneePublication < 1000 || anneePublication > 2100)
            throw new IllegalArgumentException("Année invalide.");
        this.anneePublication = anneePublication;
    }

    public void setDescription(String description) {
        this.description = (description != null) ? description.trim() : "";

    }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return String.format("[%s] ID=%d | \"%s\" par %s (%d)",
                getType(), id, titre, auteur, anneePublication);
    }
}
