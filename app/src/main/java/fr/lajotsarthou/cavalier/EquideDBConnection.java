package fr.lajotsarthou.cavalier;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EquideDBConnection {
    public static Connection getConnection() {
        Connection  connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql-lajot-sarthou.alwaysdata.net:3306/lajot-sarthou_cavalier"  // Lien vers la base de données
                    + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            String login = "260195";
            String pwd = "cavalier56%";
            Log.d("DBCONNEXION", "Début de l'instance de connexion");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            connection= DriverManager.getConnection(url,login,pwd);
            Log.d("DBCONNEXION", "getConnection: vérification si c'est pas null.");
        }catch (ClassNotFoundException | SQLException ex){
            System.err.println("Driver non chargé !");
            ex.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        try {
            Log.d("DBCONNEXION", "début méthode close");
            assert connection != null;
            connection.close();
        } catch (SQLException |AssertionError e) {
            e.printStackTrace();
        }
    }

    public static String dateToSQLFormat(String date) {
        String[] tab = date.split("-");
        return tab[2]+'-'+tab[1]+'-'+tab[0];
    }
}
