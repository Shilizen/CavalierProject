package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.lajotsarthou.cavalier.modele.UserModele;

import static fr.lajotsarthou.cavalier.CavalierDbOpenHelper.COLONNE_NOM;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button bConnexion;
    private Button bInscritToi;
    private Boolean isConnected = false;
    private CavalierDbOpenHelper maBase = new CavalierDbOpenHelper(this);
    private SQLiteDatabase maBaseDonnees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.eCoNumLicence);
        password = (EditText) findViewById(R.id.eCoMdp);
        bConnexion = (Button) findViewById(R.id.bConnexion);
        bInscritToi = (Button) findViewById(R.id.bInscription);

        bConnexion.setOnClickListener(this::onClick);
        bInscritToi.setOnClickListener(this::onClick);

        verifUsername(username);
    }

    public void onClick(View btn){
        int id = btn.getId();
        switch (id){
            case R.id.bConnexion:
                if (isConnected == true) {
                    Intent navConnexion = new Intent(this, AccueilActivity.class);
                    startActivity(navConnexion);
                } else {
                    username.setText("Numéro de licence incorrect");
                    password.setText("Mot de passe incorrect");
                }
            case R.id.bInscription:
                Intent navVersInscription = new Intent(this, RegisterActivity.class);
                startActivity(navVersInscription);
        }
    }

    public Boolean getIsConnected(){
        return isConnected;
    }

    public Boolean setIsConnected(Boolean c){
        return this.isConnected = c;
    }

    public boolean verifUsername(EditText eUsername){
        if (maBase.getUser().equals(eUsername.getText().toString())){
            return setIsConnected(true);
        } else {
            return  setIsConnected(false);
        }
    }

}