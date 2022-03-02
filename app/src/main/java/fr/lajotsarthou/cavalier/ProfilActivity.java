package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import fr.lajotsarthou.cavalier.modele.UserModele;

public class ProfilActivity extends AppCompatActivity {
    private ImageView iProfile;
    private SQLiteDatabase myBase;
    private CavalierDbOpenHelper dbAdapter;
    private Button bParametre;
    private UserModele userModele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
    }
}