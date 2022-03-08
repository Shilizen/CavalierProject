package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class JouvActivity extends AppCompatActivity {
    private WebView wJouv;
    private ImageView logoImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarJouv);
        setSupportActionBar(toolbar);

        init();
        chargerImage();
        wJouv.getSettings().setDomStorageEnabled(true);
        wJouv.getSettings().setJavaScriptEnabled(true);
        wJouv.setWebViewClient(new WebViewClient());
        wJouv.loadUrl("https://www.telemat.org/FFE/sif/?cs=4.a7ccfe68227955029bfcdf8dafa954dd7cdfa0e23bff2444f44bfe875488f2c8910f");
    }

    private void init(){

    }

    private void chargerImage(){
        new Thread(new Runnable() {
            public void run() {
                wJouv = (WebView) findViewById(R.id.wJouv);
                // a potentially time consuming task
                logoImg = (ImageView) findViewById(R.id.imageView);
                Drawable image = ContextCompat.getDrawable(JouvActivity.this, R.drawable.logo_cavalier_recadrer);
                logoImg.post(new Runnable() {
                    public void run() {
                        logoImg.setImageDrawable(image);
                    }
                });
            }
        }).start();
    }
}