package fr.lajotsarthou.cavalier.modele;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.lajotsarthou.cavalier.EquideDBConnection;

public class Equide {
    private String nomComplet;
    private String nomEcurie;
    private int id;
    private int age;
    private String sexe;
    private String robe;
    private String race;
    private String proprietaire;
    private String ecurie;

    public Equide(){

    }

    public Equide(int id, String nomComplet, String nomEcurie, int age,
                  String sexe, String race, String robe, String proprietaire,
                  String ecurie){
        this.id = id;
        this.nomComplet = nomComplet;
        this.nomEcurie = nomEcurie;
        this.age = age;
        this.sexe = sexe;
        this.race = race;
        this.robe = robe;
        this.proprietaire = proprietaire;
        this.ecurie = ecurie;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNomEcurie() {
        return nomEcurie;
    }

    public void setNomEcurie(String nomEcurie) {
        this.nomEcurie = nomEcurie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRobe() {
        return robe;
    }

    public void setRobe(String robe) {
        this.robe = robe;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getEcurie() {
        return ecurie;
    }

    public void setEcurie(String ecurie) {
        this.ecurie = ecurie;
    }

    public static List<Equide> getAllEquide(){
        List<Equide> equideList = new ArrayList<>();
        String req = "select * from equide";
        Connection connection = EquideDBConnection.getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(req);

            while (rs.next()){
                equideList.add(new Equide(
                        rs.getInt(0),
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            EquideDBConnection.close(connection);
        }
        return equideList;
    }

    public static Equide getByName(String nomEcurie){
        List<Equide> equides = getAllEquide();
        Equide res = null;
        for(Equide equide : equides){
            if (equide.getNomEcurie().equals(nomEcurie)){
                res = new Equide();
                res.setId(equide.getId());
                res.setNomComplet(equide.getNomComplet());
                res.setNomEcurie(equide.getNomEcurie());
                res.setAge(equide.getAge());
                res.setSexe(equide.getSexe());
                res.setRace(equide.getRace());
                res.setRobe(equide.getRobe());
                res.setProprietaire(equide.getProprietaire());
                res.setEcurie(equide.getEcurie());
                break;
            }
        }
        if (res == null){
            Log.d("NULL", "Réponse nulle");
            return null;
        }else {
            return res;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "nomComplet='" + nomComplet + '\'' +
                ", nomEcurie='" + nomEcurie + '\'' +
                '}';
    }

}
