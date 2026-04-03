package dao;

import model.Membre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {

    // Enregistre un nouveau membre dans la base de données
    public static void save(Membre m) {
        String sql = "INSERT INTO membres (nom, prenom, email) VALUES (?, ?, ?)";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setString(3, m.getEmail());
            ps.executeUpdate();
            System.out.println("Membre sauvegardé : " + m.getNom());
        } catch (SQLException e) {
            System.out.println("Erreur save membre : " + e.getMessage());
        }
    }

    // Récupère un membre par son id
    public static Membre findById(int id) {
        String sql = "SELECT * FROM membres WHERE id = ?";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Membre m = new Membre();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setEmail(rs.getString("email"));
                return m;
            }
        } catch (SQLException e) {
            System.out.println("Erreur findById membre : " + e.getMessage());
        }
        return null;
    }

    // Récupère tous les membres
    public static List<Membre> findAll() {
        List<Membre> liste = new ArrayList<>();
        String sql = "SELECT * FROM membres";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Membre m = new Membre();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setEmail(rs.getString("email"));
                liste.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erreur findAll membres : " + e.getMessage());
        }
        return liste;
    }

    // Supprime un membre par son id
    public static void delete(int id) {
        String sql = "DELETE FROM membres WHERE id = ?";
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Membre supprimé : id = " + id);
        } catch (SQLException e) {
            System.out.println("Erreur delete membre : " + e.getMessage());
        }
    }
}
