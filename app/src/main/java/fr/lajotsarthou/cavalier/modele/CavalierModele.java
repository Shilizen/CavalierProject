package fr.lajotsarthou.cavalier.modele;

import android.content.Context;
import android.util.Log;
import java.util.List;
import fr.lajotsarthou.cavalier.CavalierDbOpenHelper;

public class CavalierModele {
    private int id;
    private String prenom;
    private String nomFamille;
    private int age;
    private String sexe;
    private String niveau;
    private String numLicence;

    private CavalierDbOpenHelper myBase;

    public CavalierModele(){
    }

    public CavalierModele(int id, String nomFamille, String prenom, int age, String sexe, String niveau, String numLicence){
        this.id = id;
        this.nomFamille = nomFamille;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.niveau = niveau;
        this.numLicence = numLicence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(String numLicence) {
        this.numLicence = numLicence;
    }

    /*public CavalierModele getCavalierByName(String prenom) {
        List<CavalierModele> cavaliers = myBase.getAllCavalier();
        CavalierModele res = null;
        for (CavalierModele cavalier : cavaliers) {
            if (cavalier.getPrenom().equals(prenom)) {
                res = new CavalierModele();
                res.setId(cavalier.getId());
                res.setNomFamille(cavalier.getNomFamille());
                res.setPrenom(cavalier.getPrenom());
                res.setAge(cavalier.getAge());
                res.setSexe(cavalier.getSexe());
                res.setNiveau(cavalier.getNiveau());
                res.setNumLicence(cavalier.getNumLicence());
                break;
            }
        }
        if (res == null) {
            Log.d("NULL", "Réponse nulle");
            return null;
        } else {
            return res;
        }

    }*/

    @Override
    public String toString() {
        return "Cavalier{" +
                "id='" + id + '\'' +
                ", nomF='" + nomFamille + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age='" + age + '\'' +
                ", sexe='" + sexe + '\'' +
                ", niveau='" + niveau + '\'' +
                ", numL='" + numLicence + '\'' +
                '}';
    }
}
