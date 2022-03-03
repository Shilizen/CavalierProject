package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import fr.lajotsarthou.cavalier.modele.UserModele;

public class AccueilActivity extends AppCompatActivity {
    private Button bLogin;
    private UserModele user;
    private WebView wActu;
    private TextView tWelcome;
    private Button bDeco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       // toolbar.setNavigationIcon(R.drawable.hamburger_menu);


        //toolbar.getNavigationIcon();
        //toolbar.inflateMenu(R.menu.menuhamburger);

        init();
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navLogin = new Intent(AccueilActivity.this, LoginActivity.class);
                startActivity(navLogin);
            }
        });
        wActu.getSettings().setDomStorageEnabled(true);
        wActu.getSettings().setJavaScriptEnabled(true);
        wActu.setWebViewClient(new WebViewClient());
        wActu.loadUrl("https://www.ffe.com/actualites");

        estConnecte();
        if(estConnecte() == true){
            deconnexion();
        } else {
            Log.d("Deconnexion", "Le boulet est pas connecté ahahaha");
        }

    }


    public void init(){
        user = new ViewModelProvider(AccueilActivity.this).get(UserModele.class);

        bLogin = (Button) findViewById(R.id.bLoginA);
        wActu = (WebView) findViewById(R.id.wActuFfe);
        tWelcome = (TextView) findViewById(R.id.tWelcome);
        bDeco = (Button) findViewById(R.id.bDisconnect);
        bDeco.setVisibility(View.VISIBLE);

        tWelcome.setText("Vous n'êtes pas connectés");
    }

    public boolean estConnecte(){
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = preferences.getString("nom", "");

        if(!username.equals("")){
            tWelcome.setText("Bienvenu " + username);
            Log.d("Login", "préférence isConnected après bouton login / register " + username);
            return true;
        }else{
            tWelcome.setText("Vous n'êtes pas connectés");
            return false;
        }
    }

    public void  deconnexion(){
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String username = preferences.getString("nom", "");

        if(!username.equals("")){
            bDeco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editor.clear();
                    editor.commit();
                    tWelcome.setText("Vous n'êtes pas connectés");
                    Log.d("Login", "préférence isConnected après bouton deconnexion " + username);
                    Log.d("Login", "C'est le ponpon !!! " + tWelcome.getText().toString());
                    Intent navDeco = new Intent(AccueilActivity.this, LoginActivity.class);
                    startActivity(navDeco);
                }
            });

        }
    }


}