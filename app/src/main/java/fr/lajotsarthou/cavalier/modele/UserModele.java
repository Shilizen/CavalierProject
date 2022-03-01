package fr.lajotsarthou.cavalier.modele;

public class UserModele {
    private String username;
    private String password;

    public UserModele(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserModele(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
