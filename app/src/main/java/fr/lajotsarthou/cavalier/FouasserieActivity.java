package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FouasserieActivity extends AppCompatActivity {
    private WebView wFouasserie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fouasserie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarClub);
        setSupportActionBar(toolbar);

        init();

        wFouasserie.getSettings().setDomStorageEnabled(true);
        wFouasserie.getSettings().setJavaScriptEnabled(true);
        wFouasserie.setWebViewClient(new WebViewClient());
        wFouasserie.loadUrl("https://centreequestredelafouasserie.ffe.com/");
    }

    private void init(){
        wFouasserie = (WebView) findViewById(R.id.wFouasserie);
    }
}