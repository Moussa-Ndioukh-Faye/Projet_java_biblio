package dao;

import model.Document;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {

    // Met à jour la disponibilité d'un document (true = disponible, false = emprunté)
    public static void updateDisponible(int id, boolean disponible) {
        String sql = "UPDATE documents SET disponible = ? WHERE id = ?";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, disponible);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Document mis à jour : id = " + id);
        } catch (SQLException e) {
            System.out.println("Erreur updateDisponible : " + e.getMessage());
        }
    }

    // Récupère tous les documents disponibles
    public static List<Document> findDisponibles() {
        List<Document> liste = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE disponible = TRUE";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // On affiche juste les infos de base
                System.out.println("Document disponible : " + rs.getString("titre"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur findDisponibles : " + e.getMessage());
        }
        return liste;
    }

    // Récupère un document par son id
    public static void findById(int id) {
        String sql = "SELECT * FROM documents WHERE id = ?";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Document trouvé : " + rs.getString("titre"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur findById document : " + e.getMessage());
        }
    }
}
