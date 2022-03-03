package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.lajotsarthou.cavalier.modele.User;
import fr.lajotsarthou.cavalier.modele.UserModele;

import static fr.lajotsarthou.cavalier.CavalierDbOpenHelper.COLONNE_NOM;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button bConnexion;
    private Button bInscritToi;
    private boolean isConnected;
    private User userVerif;

    private UserModele loginUser;

    private CavalierDbOpenHelper maBase;
    private SQLiteDatabase maBaseDonnees;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);

        init();
        bConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uName = username.getText().toString();
                String sPwd = password.getText().toString();
                if (verifUsername(uName,sPwd) == true) {
                    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    String clef = "nom";
                    editor.putString(clef, uName);
                    editor.putBoolean("isConnected", true);
                    editor.apply();
                    String testPref = preferences.getString(clef, "");

                    Log.d("Login",  "préférence sauvegardée dans login " + testPref);

                    Intent navConnexion = new Intent(LoginActivity.this, AccueilActivity.class);
                    startActivity(navConnexion);
                } else {
                    username.setText("Numéro de licence incorrect");
                    password.setText("");
                }
            }
        });
        bInscritToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navVersInscription = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(navVersInscription);
            }
        });
    }

    public void init(){
        maBase = new CavalierDbOpenHelper(this);
        username = (EditText) findViewById(R.id.eCoNumLicence);
        password = (EditText) findViewById(R.id.eCoMdp);
        bConnexion = (Button) findViewById(R.id.bConnexion);
        bInscritToi = (Button) findViewById(R.id.bInscription);
        bundle = new Bundle();

        userVerif = new User();
    }

    public boolean verifUsername(String sUser, String sPwd){
        for(User user : maBase.getAllUsers()){
            Log.d("Login", user.toString());
            if(user.getNom().equals(sUser) && user.getMdp().equals(sPwd))
                return true;
        }
        return false;
    }

}