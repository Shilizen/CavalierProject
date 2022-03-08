package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.lajotsarthou.cavalier.modele.User;
import fr.lajotsarthou.cavalier.modele.UserModele;

public class ProfilActivity extends AppCompatActivity {
    private ImageView iProfile;
    private CavalierDbOpenHelper myBase;
    private Button bEdit;
    private User user;

    private TextView tNom;
    private TextView tPrenom;
    private TextView tAge;
    private TextView tSexe;
    private TextView tNiveau;
    private TextView tNumeroLicence;
    private TextView tPhoto;

    private ImageView logoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarClub);
        setSupportActionBar(toolbar);

    }

    /*public void init(){

        myBase = new CavalierDbOpenHelper(this);

        tNom = (TextView) findViewById(R.id.tNom);
        tPrenom = (TextView) findViewById(R.id.tPrenom);
        tAge = (TextView) findViewById(R.id.tAge);
        tSexe = (TextView) findViewById(R.id.tSexe);
        tNiveau = (TextView) findViewById(R.id.tNiveau);
        tNumeroLicence = (TextView) findViewById(R.id.tNumlicence);
        bEdit = (Button) findViewById(R.id.bEditCompte);

    }

    public void editClick(){
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navEdit = new Intent(ProfilActivity.this, EditCompteActivity.class);
                startActivity(navEdit);
            }
        });
    }

    public void afficherInfo(){
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String numeroLicence = preferences.getString("name", "");
        if (!numeroLicence.equals("")){
            tNumeroLicence.setText(numeroLicence);
        } else {
            tNumeroLicence.setText("Pas de numéro de licence détecté");
        }
    }

    public void chargerImage(){
        new Thread(new Runnable() {
            public void run() {
                // a potentially time consuming task
                logoImg = (ImageView) findViewById(R.id.imageView);
                Drawable image = ContextCompat.getDrawable(ProfilActivity.this, R.drawable.logo_cavalier_recadrer);
                logoImg.post(new Runnable() {
                    public void run() {
                        logoImg.setImageDrawable(image);
                    }
                });
            }
        }).start();
    }*/

}