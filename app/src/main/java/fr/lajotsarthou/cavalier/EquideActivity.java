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
    private Equide equide;

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
                    searchByName(search);
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

        equide = new Equide();
    }

    public void searchByName(String nomEcurie){
        equideListBis = equide.getAllEquide();
        for(Equide cheval : equideListBis){
            if(cheval.getNomEcurie().equals(nomEcurie)){
                Log.d("SEARCHBYNAME", "searchByName: " + cheval.getNomEcurie().toString());
                tId.setText(String.valueOf(cheval.getId()));
                tNomC.setText(cheval.getNomComplet());
                tNomE.setText(cheval.getNomEcurie());
                tAge.setText(String.valueOf(cheval.getAge()));
                tSexe.setText(cheval.getSexe());
                tRace.setText(cheval.getRace());
                tRobe.setText(cheval.getRobe());
                tProprio.setText(cheval.getProprietaire());
                tEcurie.setText(cheval.getEcurie());
            } else {
                barreRecherche.setQuery("Cheval non trouvé", true);
            }
        }
    }
}