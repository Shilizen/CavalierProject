package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.lajotsarthou.cavalier.modele.UserModele;

public class ProfilActivity extends AppCompatActivity {
    private ImageView iProfile;
    private SQLiteDatabase myBase;
    private CavalierDbOpenHelper dbAdapter;
    private Button bParametre;
    private UserModele userModele;
    private TextView tNomUser;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
    }

    public void estConnecte(){
        boolean isCo = getIntent().getExtras().getBoolean("connexion");
        String nomUser = getIntent().getExtras().getString("nom");
        tNomUser.setText(nomUser);
    }
}