package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import fr.lajotsarthou.cavalier.modele.UserModele;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText nomFam;
    private EditText age;
    private EditText genre;
    private EditText numLicence;
    private EditText niveau;
    private Button register;


    private CavalierDbOpenHelper myBase;
    private SQLiteDatabase db;
    private UserModele user;
    private ImageView logoImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbarRegister);
        setSupportActionBar(toolbar);

        init();
        chargerImage();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                myBase.insertValue(username, password);
                myBase.insertValuesCavalier(nomFam, username, age, genre, niveau, numLicence);
                editor.putString("nom", username.getText().toString());
                editor.apply();
                myBase.close();
                editor.commit();
                Log.d("CAVALIER", "Preference partagée" + preferences.getString("nom", ""));
                username.setText("");
                password.setText("");
                nomFam.setText("");
                age.setText("");
                genre.setText("");
                niveau.setText("");
                numLicence.setText("");

                Intent navAcceuilCo = new Intent(RegisterActivity.this, AccueilActivity.class);
                startActivity(navAcceuilCo);
            }
        });
    }

    public void init(){

        username = findViewById(R.id.eUsername);
        password = findViewById(R.id.ePassword);
        nomFam = findViewById(R.id.eReNom);
        age = findViewById(R.id.eReAge);
        genre = findViewById(R.id.eReGenre);
        numLicence = findViewById(R.id.eReNumL);
        niveau = findViewById(R.id.eReNiveau);
        register = findViewById(R.id.bRegister);
        myBase = new CavalierDbOpenHelper(this);
    }

    public void chargerImage(){
        new Thread(new Runnable() {
            public void run() {
                // a potentially time consuming task
                logoImg = (ImageView) findViewById(R.id.imageView);
                Drawable image = ContextCompat.getDrawable(RegisterActivity.this, R.drawable.logo_cavalier_recadrer);
                logoImg.post(new Runnable() {
                    public void run() {
                        logoImg.setImageDrawable(image);
                    }
                });
            }
        }).start();
    }
}