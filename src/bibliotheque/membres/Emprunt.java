package bibliotheque.membres;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {
    private int id;
    private Document document;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;
    private LocalDate dateRetourEffectif;

    public Emprunt(int id, Document doc) {
        this.id = id;
        this.document = doc;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevu = this.dateEmprunt.plusDays(14);
    }

    public boolean estEnRetard() {
        if (dateRetourEffectif != null) return false;
        return LocalDate.now().isAfter(dateRetourPrevu);
    }

    public double calculerAmende() {
        if (!estEnRetard()) return 0;
        long jours = ChronoUnit.DAYS.between(dateRetourPrevu, LocalDate.now());
        return jours * 500;
    }

    public void effectuerRetour() {
        this.dateRetourEffectif = LocalDate.now();
        this.document.setDisponible(true);
    }

    public int getId() { return id; }
    public Document getDocument() { return document; }
}
