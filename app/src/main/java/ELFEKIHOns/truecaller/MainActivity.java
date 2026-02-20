package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declaration des composants
    Button btnQt_auth, btnct_auth;
    EditText edPassd_auth, edUsername_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation du Manager pour tester la base de données (Optionnel)
        ContactManager manager = new ContactManager(MainActivity.this);
        manager.Ouvrir();
        // manager.ajout("Test", "User", "12345678"); // Correction : demande 3 arguments
        ArrayList<Contact> data = manager.getAllContact(); // Correction : ajout de la déclaration ArrayList
        manager.fermer();

        // Récupération des composants
        btnct_auth = findViewById(R.id.btnct_auth);
        btnQt_auth = findViewById(R.id.btnQt_auth);
        edUsername_auth = findViewById(R.id.edUsername_auth);
        edPassd_auth = findViewById(R.id.edPassd_auth);

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
