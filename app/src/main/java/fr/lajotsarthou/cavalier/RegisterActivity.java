package fr.lajotsarthou.cavalier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.lajotsarthou.cavalier.modele.UserModele;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button register;
    private CavalierDbOpenHelper myBase;
    private SQLiteDatabase db;
    private UserModele user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbarRegister);
        setSupportActionBar(toolbar);

        init();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBase.insertValue(username, password);
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setConnected(true);
                myBase.close();
                username.setText("");
                password.setText("");

                Intent navAcceuilCo = new Intent(RegisterActivity.this, AccueilActivity.class);
                navAcceuilCo.putExtra("getConnected", user.getConnected());
                startActivity(navAcceuilCo);
            }
        });
    }

    public void init(){
        user = new ViewModelProvider(RegisterActivity.this).get(UserModele.class);

        username = findViewById(R.id.eUsername);
        password = findViewById(R.id.ePassword);
        register = findViewById(R.id.bRegister);
        myBase = new CavalierDbOpenHelper(this);
    }
}