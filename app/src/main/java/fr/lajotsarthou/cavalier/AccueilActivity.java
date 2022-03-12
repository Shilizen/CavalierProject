package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.lajotsarthou.cavalier.modele.UserModele;

public class AccueilActivity extends AppCompatActivity implements SensorEventListener {
    private Button bLogin;
    private UserModele user;
    private WebView wActu;
    private TextView tWelcome;
    private Button bDeco;
    private ImageView logoImg;

    private long lastUpdate;
    private SensorManager senSensorManager;
    private boolean color = false;

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
        chargerimage();
        /*wActu.getSettings().setDomStorageEnabled(true);
        wActu.getSettings().setJavaScriptEnabled(true);
        wActu.setWebViewClient(new WebViewClient());
        wActu.loadUrl("https://www.ffe.com/actualites");*/

        estConnecte();
        if(estConnecte() == true){
            bDeco.setVisibility(View.VISIBLE);
            bLogin.setVisibility(View.INVISIBLE);
            deconnexion();
        } else {

            Log.d("Deconnexion", "Le boulet est pas connecté ahahaha");
        }

        //mis en place de l'accéléromètre (sensor)
        lastUpdate = System.currentTimeMillis();
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }


    public void init(){

        user = new ViewModelProvider(AccueilActivity.this).get(UserModele.class);

        bLogin = (Button) findViewById(R.id.bLoginA);
        //wActu = (WebView) findViewById(R.id.wActuFfe);
        tWelcome = (TextView) findViewById(R.id.tWelcome);
        bDeco = (Button) findViewById(R.id.bDisconnect);

        tWelcome.setText("Vous n'êtes pas connectés");
    }
    public void chargerimage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                logoImg = (ImageView) findViewById(R.id.imageView);
                Drawable image = ContextCompat.getDrawable(AccueilActivity.this, R.drawable.logo_cavalier_recadrer);
                logoImg.post(new Runnable() {
                    public void run() {
                        logoImg.setImageDrawable(image);
                    }
                });

                wActu = (WebView) findViewById(R.id.wActuFfe);
                wActu.post(new Runnable() {
                    @Override
                    public void run() {
                        wActu.getSettings().setDomStorageEnabled(true);
                        wActu.getSettings().setJavaScriptEnabled(true);
                        wActu.setWebViewClient(new WebViewClient());
                        wActu.loadUrl("https://www.ffe.com/actualites");
                    }
                });
            }

        }).start();


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
                    tWelcome.setText("Vous n'êtes pas connecté");
                    Log.d("Login", "préférence isConnected après bouton deconnexion " + username);
                    Log.d("Login", "C'est le ponpon !!! " + tWelcome.getText().toString());
                    Intent navDeco = new Intent(AccueilActivity.this, LoginActivity.class);
                    startActivity(navDeco);
                }
            });

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
           getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void getAccelerometer(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z *z)/(SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long heureActu = event.timestamp;
        if(accelationSquareRoot >=2){
            if(heureActu - lastUpdate < 200){
                return;
            }
            lastUpdate = heureActu;
            Toast.makeText(this, "Le téléphone a été secoué", Toast.LENGTH_LONG).show();
            if(color){
                bLogin.setBackgroundColor(this.getResources().getColor(R.color.cbrown_fonce));
                bDeco.setBackgroundColor(this.getResources().getColor(R.color.cbrown_fonce));
            } else{
                bLogin.setBackgroundColor(this.getResources().getColor(R.color.shakeColorPink));
                bDeco.setBackgroundColor(this.getResources().getColor(R.color.shakeColorPink));
            }
            color = !color;
        }

    }

}