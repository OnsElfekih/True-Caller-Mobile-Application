package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    Button btnAjt_acc, btnAff_acc, btnExit_acc, btnDec_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Récupération
        btnAjt_acc = findViewById(R.id.btnAjt_acc);
        btnAff_acc = findViewById(R.id.btnAff_acc);
        btnExit_acc = findViewById(R.id.btnExit_acc);
        btnDec_acc = findViewById(R.id.btnDec_acc);

        // --- NAVIGATION ---
        btnAjt_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Ajout.class);
                startActivity(i);
            }
        });

        btnAff_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Affiche.class);
                startActivity(i);
            }
        });

        // --- ACTION : QUITTER (STOP RUN) ---
        btnExit_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cette commande ferme l'application et toutes ses activités
                finishAffinity();
                // Optionnel : Pour arrêter le processus de force (équivalent au bouton rouge de l'IDE)
                System.exit(0);
            }
        });

        // --- ACTION : DECONNEXION ---
        btnDec_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. Supprimer la session enregistrée (Remember Me)
                SharedPreferences sp = getSharedPreferences("session_truecaller", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();

                // 2. Retourner à l'écran de Login
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);
                finish(); // Ferme uniquement la page Home
            }
        });
    }
}