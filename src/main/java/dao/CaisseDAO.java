package dao;

import java.sql.*;

public class CaisseDAO {

    // Enregistre un encaissement d'amende dans la base de données
    public static void save(String membre, double montant) {
        String sql = "INSERT INTO caisse_historique (membre, montant, dateTransaction) VALUES (?, ?, ?)";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, membre);
            ps.setDouble(2, montant);
            ps.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            ps.executeUpdate();
            System.out.println("Amende encaissée pour : " + membre + " — montant : " + montant);
        } catch (SQLException e) {
            System.out.println("Erreur save caisse : " + e.getMessage());
        }
    }

    // Affiche tout l'historique des encaissements
    public static void findAll() {
        String sql = "SELECT * FROM caisse_historique";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("=== Historique de la caisse ===");
            while (rs.next()) {
                System.out.println("Membre : " + rs.getString("membre")
                    + " | Montant : " + rs.getDouble("montant")
                    + " | Date : " + rs.getDate("dateTransaction"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur findAll caisse : " + e.getMessage());
        }
    }

    // Calcule le total encaissé
    public static double getTotalEncaisse() {
        String sql = "SELECT SUM(montant) FROM caisse_historique";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur getTotalEncaisse : " + e.getMessage());
        }
        return 0;
    }
}
