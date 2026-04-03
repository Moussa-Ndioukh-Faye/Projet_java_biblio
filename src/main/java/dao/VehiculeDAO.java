package dao;

import model.Vehicule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDAO {

    // Enregistre un nouveau véhicule dans la base de données
    public static void save(Vehicule v) {
        String sql = "INSERT INTO vehicules (immatriculation, kilometrage, enService) VALUES (?, ?, ?)";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, v.getImmatriculation());
            ps.setDouble(2, v.getKilometrage());
            ps.setBoolean(3, v.isEnService());
            ps.executeUpdate();
            System.out.println("Véhicule sauvegardé : " + v.getImmatriculation());
        } catch (SQLException e) {
            System.out.println("Erreur save vehicule : " + e.getMessage());
        }
    }

    // Met à jour le statut du véhicule (en service ou en révision)
    public static void updateStatut(int id, boolean enService) {
        String sql = "UPDATE vehicules SET enService = ? WHERE id = ?";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, enService);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Statut véhicule mis à jour : id = " + id);
        } catch (SQLException e) {
            System.out.println("Erreur updateStatut vehicule : " + e.getMessage());
        }
    }

    // Récupère tous les véhicules
    public static List<Vehicule> findAll() {
        List<Vehicule> liste = new ArrayList<>();
        String sql = "SELECT * FROM vehicules";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Véhicule : " + rs.getString("immatriculation")
                    + " | En service : " + rs.getBoolean("enService"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur findAll vehicules : " + e.getMessage());
        }
        return liste;
    }
}
