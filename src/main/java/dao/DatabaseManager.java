package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;
    
    public static Connection  getConnection(){
        if (connection == null){
            try{
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println(" Connexion à la base de données réussie !" );

            } catch(SQLException e){
                System.out.println("Erreur de connexion : " + e.getMessage());


            }
        }
        return connection;
        
    }
    public static void initDatabase(){
        System.out.println("Base de données initialisée.");
    }
}

