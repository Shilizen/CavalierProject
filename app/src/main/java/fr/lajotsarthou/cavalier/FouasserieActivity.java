package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class FouasserieActivity extends AppCompatActivity {
    private WebView wFouasserie;
    private ImageView logoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fouasserie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarClub);
        setSupportActionBar(toolbar);

        init();
        chargerImage();

        wFouasserie.getSettings().setDomStorageEnabled(true);
        wFouasserie.getSettings().setJavaScriptEnabled(true);
        wFouasserie.setWebViewClient(new WebViewClient());
        wFouasserie.loadUrl("https://centreequestredelafouasserie.ffe.com/");
    }

    private void init(){
        wFouasserie = (WebView) findViewById(R.id.wFouasserie);
    }

    private void chargerImage(){
        new Thread(new Runnable() {
            public void run() {
                // a potentially time consuming task
                logoImg = (ImageView) findViewById(R.id.imageView);
                Drawable image = ContextCompat.getDrawable(FouasserieActivity.this, R.drawable.logo_cavalier_recadrer);
                logoImg.post(new Runnable() {
                    public void run() {
                        logoImg.setImageDrawable(image);
                    }
                });
            }
        }).start();
    }
}