package fr.lajotsarthou.cavalier;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
import android.widget.TextView;

import java.util.List;

import fr.lajotsarthou.cavalier.modele.CavalierModele;
import fr.lajotsarthou.cavalier.modele.User;
import fr.lajotsarthou.cavalier.modele.UserModele;

public class ProfilActivity extends AppCompatActivity {
    private ImageView iProfile;
    private CavalierDbOpenHelper myBase;
    private Button bAfficher;
    private User user;;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);
        init();
        chargerImage();
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = preferences.getString("nom", "");
        afficherInfo(username);
       /* bAfficher.setVisibility(View.INVISIBLE);
        bAfficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.equals("")){
                    Log.d("TESTPO", "Je suis un poney et toi ?");
                } else {
                    afficherInfo(username);
                    Log.d("TESTPO", "Information affichée");
                }
            }
        });*/

    }

    public void init(){

        myBase = new CavalierDbOpenHelper(this);

        tNom = (TextView) findViewById(R.id.tNom);
        tPrenom = (TextView) findViewById(R.id.tPrenom);
        tAge = (TextView) findViewById(R.id.tAge);
        tSexe = (TextView) findViewById(R.id.tGenre);
        tNiveau = (TextView) findViewById(R.id.tNiveau);
        tNumeroLicence = (TextView) findViewById(R.id.tNumLicence);
        bAfficher = (Button) findViewById(R.id.bAfficherInfo);

    }
/*
    public void editClick(){
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navEdit = new Intent(ProfilActivity.this, EditCompteActivity.class);
                startActivity(navEdit);
            }
        });
    }
*/
    public void afficherInfo(String prenom){
        String prenomValide = prenom;
        List<CavalierModele> allCavalier = myBase.getAllCavalier();
        Log.d("Cavalier", "prenom dans le shared du profil " + prenom.toString());
        if (!prenom.equals("")){
            tPrenom.setText(prenom);
        } else {
            tPrenom.setText("");
        }
        for (CavalierModele cavalierModele : allCavalier){
            Log.d("Cavalier", "dans la boucle");
            if (cavalierModele.getPrenom().equals(prenomValide)){
                Log.d("Cavalier", "lancement des changements" + cavalierModele.toString());
                tNom.setText(cavalierModele.getNomFamille());
                Log.d("TESTPO", tNom.getText().toString());
                tPrenom.setText(cavalierModele.getPrenom());
                tAge.setText(String.valueOf(cavalierModele.getAge()));
                tSexe.setText(cavalierModele.getSexe());
                tNiveau.setText(cavalierModele.getNiveau());
                tNumeroLicence.setText(cavalierModele.getNumLicence());
            } else {
                Log.d("Verif", "Non fonctionnel");
            }

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
    }

}