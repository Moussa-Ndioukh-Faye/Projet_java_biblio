package dao;

import model.Emprunt;
import java.sql.*;

public class EmpruntDAO {

    // Enregistre un nouvel emprunt dans la base de données
    public static void save(Emprunt e) {
        String sql = "INSERT INTO emprunts (membreId, documentId, dateEmprunt, dateRetourPrevu) VALUES (?, ?, ?, ?)";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, e.getMembre().getId());
            ps.setInt(2, e.getDocument().getId());
            ps.setDate(3, Date.valueOf(e.getDateEmprunt()));
            ps.setDate(4, Date.valueOf(e.getDateRetourPrevue()));
            ps.executeUpdate();
            System.out.println("Emprunt sauvegardé !");
        } catch (SQLException ex) {
            System.out.println("Erreur save emprunt : " + ex.getMessage());
        }
    }

    // Met à jour la date de retour effectif quand le document est rendu
    public static void updateRetour(int id, java.time.LocalDate dateRetour) {
        String sql = "UPDATE emprunts SET dateRetourEffectif = ? WHERE id = ?";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(dateRetour));
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Retour enregistré pour emprunt id = " + id);
        } catch (SQLException ex) {
            System.out.println("Erreur updateRetour : " + ex.getMessage());
        }
    }

    // Récupère un emprunt par son id
    public static void findById(int id) {
        String sql = "SELECT * FROM emprunts WHERE id = ?";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Emprunt trouvé : membreId = "
                    + rs.getInt("membreId")
                    + ", documentId = " + rs.getInt("documentId"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur findById emprunt : " + ex.getMessage());
        }
    }
}