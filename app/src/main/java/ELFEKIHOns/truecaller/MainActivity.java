package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declaration des composants
    Button btnQt_auth, btnct_auth;
    EditText edPassd_auth, edUsername_auth;
    CheckBox cbResterCt_auth;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialiser SharedPreferences
        sp = getSharedPreferences("session_truecaller", MODE_PRIVATE);

        // Vérifier si l'utilisateur a déjà coché "Remember Me"
        if (sp.getBoolean("is_connected", false)) {
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
            finish();
            return; // Arrêter l'exécution de onCreate ici
        }

        setContentView(R.layout.activity_main);

        // Récupération des composants
        btnct_auth = findViewById(R.id.btnct_auth);
        btnQt_auth = findViewById(R.id.btnQt_auth);
        edUsername_auth = findViewById(R.id.edUsername_auth);
        edPassd_auth = findViewById(R.id.edPassd_auth);
        cbResterCt_auth = findViewById(R.id.cbResterCt_auth);

        // Event Quitter
        btnQt_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Event Connexion
        btnct_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername_auth.getText().toString();
                String password = edPassd_auth.getText().toString();

                // Logique de connexion simple
                if (username.equalsIgnoreCase("azer") && password.equals("111")) {
                    
                    // Si Remember Me est coché, enregistrer l'état
                    if (cbResterCt_auth.isChecked()) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("is_connected", true);
                        editor.apply();
                    }

                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Vérifier les infos...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
