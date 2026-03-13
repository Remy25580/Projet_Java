package school.coda.remy_axel_ethan.projet_java.events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public void createDb(){
        String databaseUrl= "jdbc:sqlite:events.db";
        try (Connection connection = DriverManager.getConnection(databaseUrl)){
            try(Statement stmt = connection.createStatement()){

                String sql = "CREATE TABLE IF NOT EXISTS result\n" +
                             "(\n" +
                             "	id   integer      not null \n" +
                             "			constraint customer_pk\n" +
                             "			primary key autoincrement,\n" +
                             "	winner varchar(6) not null\n" +
                             "    nbToursPlayer integer not null\n" +
                             "    nbToursIa integer not null\n" +
                             ")";

                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }


    public void putAResult(String winner, int nbPlayer, int nbIa){
        String databaseUrl= "jdbc:sqlite:events.db";

        try (Connection connection = DriverManager.getConnection(databaseUrl)){
            try(Statement stmt = connection.createStatement()){
                String sql = "INSERT INTO result (winner, nbToursPlayer, nbToursIa)" +
                             "VALUES ("+winner+", "+nbPlayer+", "+nbIa+");";

                stmt.execute(sql);
            }
        }catch(SQLException e){
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }


}
