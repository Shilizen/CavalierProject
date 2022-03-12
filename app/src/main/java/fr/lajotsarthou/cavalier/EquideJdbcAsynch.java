package fr.lajotsarthou.cavalier;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.lajotsarthou.cavalier.modele.Equide;

public class EquideJdbcAsynch extends AsyncTask<Connection, Integer, Connection> {
    public EquideActivity equideActivity;


    protected Connection doInBackground(Connection... connections) {
        connections = null;
        Connection connection = connections[0];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mysql-lajot-sarthou.alwaysdata.net:3306/lajot-sarthou_cavalier"  // Lien vers la base de données
                    + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            String login = "260195";
            String pwd = "cavalier56%";
            Log.d("DBCONNEXION", "Début de l'instance de connexion");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            connection = DriverManager.getConnection(url, login, pwd);
            Log.d("DBCONNEXION", "getConnection: vérification si c'est pas null.");

        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trouvée");
        } catch (SQLException throwables) {
            System.err.println("Driver non connecté");
        }
        return connections[0];
    }


    protected void onPostExecute(Connection aCo) {
       super.onPostExecute(aCo);
    }



}