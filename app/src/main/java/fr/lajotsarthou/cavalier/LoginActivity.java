package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
    private UserModele loginUser;
    private CavalierDbOpenHelper maBase;
    private SQLiteDatabase maBaseDonnees;

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
                verifUsername(username, password);
                if (loginUser.getConnected() == true) {
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

        loginUser = new ViewModelProvider(LoginActivity.this).get(UserModele.class);
    }

    public boolean verifUsername(EditText eUsername, EditText ePassword){
        String sUser = eUsername.getText().toString();
        String sPass = ePassword.getText().toString();
        if (maBase.checkAccount(sUser, sPass) == true){
            loginUser.setConnected(true);
            return loginUser.getConnected();
        } else {
            loginUser.setConnected(false);
            return loginUser.getConnected();
        }
    }

}