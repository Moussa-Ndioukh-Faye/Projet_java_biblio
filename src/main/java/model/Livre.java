package model;

/**
 * Représente un livre dans la bibliothèque.
 * Hérite de Document.
 */
public class Livre extends Document {
    private String isbn;
    private String genre;
    private int nombrePages;
    private String editeur;

    public Livre() { super(); }

    public Livre(String titre, String auteur, int anneePublication,
                 String description, String isbn, String genre,
                 int nombrePages, String editeur) {
        super(titre, auteur, anneePublication, description);
        setIsbn(isbn);
        setGenre(genre);
        setNombrePages(nombrePages);
        this.editeur = (editeur != null) ? editeur.trim() : "";
    }

    @Override
    public String getType() { return "Livre"; }

    // ─── Getters ───────────────────────────────────────────────────────────
    public String getIsbn()       { return isbn; }
    public String getGenre()      { return genre; }
    public int getNombrePages()   { return nombrePages; }
    public String getEditeur()    { return editeur; }

    // ─── Setters ───────────────────────────────────────────────────────────
    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty())
            throw new IllegalArgumentException("L'ISBN ne peut pas être vide.");
        this.isbn = isbn.trim();
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty())
            throw new IllegalArgumentException("Le genre ne peut pas être vide.");
        this.genre = genre.trim();
    }

    public void setNombrePages(int nombrePages) {
        if (nombrePages <= 0)
            throw new IllegalArgumentException("Le nombre de pages doit être positif.");
        this.nombrePages = nombrePages;
    }

    public void setEditeur(String editeur) {
        this.editeur = (editeur != null) ? editeur.trim() : "";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | ISBN: %s | Genre: %s | Pages: %d | Éditeur: %s",
                isbn, genre, nombrePages, editeur);
    }
}
