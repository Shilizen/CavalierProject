package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ClubActivity extends AppCompatActivity {
    private Button bFouasserie;
    private Button bJouv;
    private ImageView logoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarClub);
        setSupportActionBar(toolbar);

        init();
        chargerImage();
        bFouasserie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navFouasserie = new Intent(ClubActivity.this, FouasserieActivity.class);
                startActivity(navFouasserie);
            }
        });
        bJouv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navJouv = new Intent(ClubActivity.this, JouvActivity.class);
                startActivity(navJouv);
            }
        });
    }

    public void init(){
        bFouasserie = (Button) findViewById(R.id.bFouasserie);
        bJouv = (Button) findViewById(R.id.bJouv);
    }

    public void chargerImage(){
        new Thread(new Runnable() {
            public void run() {
                // a potentially time consuming task
                logoImg = (ImageView) findViewById(R.id.imageView);
                Drawable image = ContextCompat.getDrawable(ClubActivity.this, R.drawable.logo_cavalier_recadrer);
                logoImg.post(new Runnable() {
                    public void run() {
                        logoImg.setImageDrawable(image);
                    }
                });
            }
        }).start();
    }

}