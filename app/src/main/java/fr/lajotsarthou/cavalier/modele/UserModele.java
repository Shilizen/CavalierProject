package fr.lajotsarthou.cavalier.modele;

import androidx.lifecycle.ViewModel;

public class UserModele extends ViewModel {
    private String username;
    private String password;
    private boolean isConnected;

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

    public boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }
}
