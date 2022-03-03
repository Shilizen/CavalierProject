package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClubActivity extends AppCompatActivity {
    private Button bFouasserie;
    private Button bJouv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarClub);
        setSupportActionBar(toolbar);

        init();
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

}