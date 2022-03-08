package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.lajotsarthou.cavalier.modele.Equide;

public class EquideActivity extends AppCompatActivity {
    private SearchView barreRecherche;
    private Button bRechercheEquide;
    private TextView tId;
    private TextView tNomC;
    private TextView tNomE;
    private TextView tAge;
    private TextView tSexe;
    private TextView tRace;
    private TextView tRobe;
    private TextView tProprio;
    private TextView tEcurie;

    private List<Equide> equideListBis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equide);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEquide);
        setSupportActionBar(toolbar);

        init();
        bRechercheEquide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RechercheEquide", "Arrivé au début du click");
                String search = barreRecherche.getQuery().toString();
                Log.d("RechercheEquide", search);
               if (!search.equals("")){
                    equideListBis = Equide.getAllEquide();
                   Log.d("RechercheEquide", "connexion successful" + equideListBis.toString());
               }
            }
        });
    }

    public void init(){
        barreRecherche = (SearchView) findViewById(R.id.sView);
        bRechercheEquide = (Button) findViewById(R.id.bSearchEq);

        tId = (TextView) findViewById(R.id.tId);
        tNomC = (TextView) findViewById(R.id.tNomC);
        tNomE = (TextView) findViewById(R.id.tNomE);
        tAge = (TextView) findViewById(R.id.tAgeE);
        tSexe = (TextView) findViewById(R.id.tSexeE);
        tRace = (TextView) findViewById(R.id.tRace);
        tRobe = (TextView) findViewById(R.id.tRobe);
        tProprio = (TextView) findViewById(R.id.tProprietaire);
        tEcurie = (TextView) findViewById(R.id.tEcurie);
    }

    public void searchByName(String nomEcurie){
        Equide.getByName(nomEcurie);
    }
}