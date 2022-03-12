package fr.lajotsarthou.cavalier.modele;

public class Engagement {
    private int id;
    private int numEngagement;
    private String lieu;
    private String discipline;
    private String categorie;
    private String nomConcours;
    private int classement;
    private String numLicence;
    private int idCheval;

    private CavalierModele cavalier;
    private Equide equide;

    public Engagement(){

    }

    public Engagement(int id, int numEngagement, String lieu, String discipline, String categorie, String nomConcours, int classement, String numLicence, int idCheval){
        this.id = id;
        this.numEngagement = numEngagement;
        this.lieu = lieu;
        this.discipline = discipline;
        this.categorie = categorie;
        this.nomConcours = nomConcours;
        this.classement = classement;
        this.numLicence = numLicence;
        this.idCheval = idCheval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumEngagement() {
        return numEngagement;
    }

    public void setNumEngagement(int numEngagement) {
        this.numEngagement = numEngagement;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNomConcours() {
        return nomConcours;
    }

    public void setNomConcours(String nomConcours) {
        this.nomConcours = nomConcours;
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public String getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(String numLicence) {
        this.numLicence = numLicence;
    }

    public int getIdCheval() {
        return idCheval;
    }

    public void setIdCheval(int idCheval) {
        this.idCheval = idCheval;
    }
}
