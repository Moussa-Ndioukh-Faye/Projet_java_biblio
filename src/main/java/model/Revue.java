package model;

/**
 * Représente une revue/magazine dans la bibliothèque.
 * Hérite de Document.
 */
public class Revue extends Document {
    private String issn;
    private String periodicite; // Mensuelle, Hebdomadaire, Trimestrielle...
    private int numero;
    private String moisParution;

    public Revue() { super(); }

    public Revue(String titre, String auteur, int anneePublication,
                 String description, String issn, String periodicite,
                 int numero, String moisParution) {
        super(titre, auteur, anneePublication, description);
        setIssn(issn);
        setPeriodicite(periodicite);
        setNumero(numero);
        this.moisParution = (moisParution != null) ? moisParution.trim() : "";
    }

    @Override
    public String getType() { return "Revue"; }

    // ─── Getters ───────────────────────────────────────────────────────────
    public String getIssn()         { return issn; }
    public String getPeriodicite()  { return periodicite; }
    public int getNumero()          { return numero; }
    public String getMoisParution() { return moisParution; }

    // ─── Setters ───────────────────────────────────────────────────────────
    public void setIssn(String issn) {
        if (issn == null || issn.trim().isEmpty())
            throw new IllegalArgumentException("L'ISSN ne peut pas être vide.");
        this.issn = issn.trim();
    }

    public void setPeriodicite(String periodicite) {
        if (periodicite == null || periodicite.trim().isEmpty())
            throw new IllegalArgumentException("La périodicité ne peut pas être vide.");
        this.periodicite = periodicite.trim();
    }

    public void setNumero(int numero) {
        if (numero <= 0)
            throw new IllegalArgumentException("Le numéro doit être positif.");
        this.numero = numero;
    }

    public void setMoisParution(String moisParution) {
        this.moisParution = (moisParution != null) ? moisParution.trim() : "";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | ISSN: %s | N°%d | Périodicité: %s | Mois: %s",
                issn, numero, periodicite, moisParution);
    }
}
