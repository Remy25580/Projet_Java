package school.coda.remy_axel_ethan.projet_java.events;

import java.sql.*;

public class DataBase {
    String databaseUrl= "jdbc:sqlite:events.db";

    public void createDb(){
        try (Connection connection = DriverManager.getConnection(databaseUrl)){
            try(Statement stmt = connection.createStatement()){

                String sql = "CREATE TABLE IF NOT EXISTS result (" +
                             "  id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "  winner VARCHAR(10) NOT NULL, " +
                             "  nbToursPlayer INTEGER NOT NULL, " +
                             "  nbToursIa INTEGER NOT NULL" +
                             ")";

                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }


    public void putAResult(String winner, int nbPlayer, int nbIa){
        String sql = "INSERT INTO result (winner, nbToursPlayer, nbToursIa) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(databaseUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, winner);
            pstmt.setInt(2, nbPlayer);
            pstmt.setInt(3, nbIa);

            pstmt.executeUpdate();
            System.out.println("Résultat enregistré : Vainqueur = " + winner);

        } catch(SQLException e){
            System.err.println("Erreur lors de l'insertion du résultat : " + e.getMessage());
        }
    }

    public int gamesCounter(){
        String sql = "SELECT COUNT(*) AS total FROM result";
        return counter(databaseUrl, sql);
    }

    public int victoriesCounter() {
        String sql = "SELECT COUNT(*) AS total FROM result WHERE winner = 'player'";
        return counter(databaseUrl, sql);
    }

    private int counter(String databaseUrl, String sql){
        int count = 0;

        try (Connection connection = DriverManager.getConnection(databaseUrl);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors du comptage des victoires : " + e.getMessage());
        }
        return count;
    }
}


