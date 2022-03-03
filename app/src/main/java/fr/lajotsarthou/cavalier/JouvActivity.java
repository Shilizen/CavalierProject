package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JouvActivity extends AppCompatActivity {
    private WebView wJouv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarJouv);
        setSupportActionBar(toolbar);

        init();

        wJouv.getSettings().setDomStorageEnabled(true);
        wJouv.getSettings().setJavaScriptEnabled(true);
        wJouv.setWebViewClient(new WebViewClient());
        wJouv.loadUrl("https://www.telemat.org/FFE/sif/?cs=4.a7ccfe68227955029bfcdf8dafa954dd7cdfa0e23bff2444f44bfe875488f2c8910f");
    }

    private void init(){
        wJouv = (WebView) findViewById(R.id.wJouv);
    }
}