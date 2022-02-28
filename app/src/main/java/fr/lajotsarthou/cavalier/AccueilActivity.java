package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccueilActivity extends AppCompatActivity {
    private Button bClub;
    private Button bEquide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       // toolbar.setNavigationIcon(R.drawable.hamburger_menu);


        //toolbar.getNavigationIcon();
        //toolbar.inflateMenu(R.menu.menuhamburger);

        bClub = (Button) findViewById(R.id.bRechercheClub);
        bEquide = (Button) findViewById(R.id.bRechercheEquide);
        bClub.setOnClickListener(this::onClick);
        bEquide.setOnClickListener(this::onClick);

    }

    public void onClick(View btn) {
        int id = btn.getId();
        switch (id){
            case R.id.bRechercheClub:
                Intent navRechercheClub = new Intent(this, ClubActivity.class);
                startActivity(navRechercheClub);
            case R.id.bRechercheEquide:
                Intent navRechEq = new Intent(this, EquideActivity.class);
                startActivity(navRechEq);

        }
    }


}