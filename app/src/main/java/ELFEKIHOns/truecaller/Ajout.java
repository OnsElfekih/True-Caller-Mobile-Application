package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ajout extends AppCompatActivity {

    EditText edNom_ajout, edPrenom_ajout, edNumber_ajout;
    Button btnBack_ajout, btnInit_ajout, btnSave_ajout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        // Récupération des composants
        edNom_ajout = findViewById(R.id.edNom_ajout);
        edPrenom_ajout = findViewById(R.id.edPrenom_ajout);
        edNumber_ajout = findViewById(R.id.edNumber_ajout);
        btnBack_ajout = findViewById(R.id.btnBack_ajout);
        btnInit_ajout = findViewById(R.id.btnInit_ajout);
        btnSave_ajout = findViewById(R.id.btnSave_ajout);

        // Événement pour le bouton Sauvegarder
        btnSave_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = edNom_ajout.getText().toString();
                String last = edPrenom_ajout.getText().toString();
                String phone = edNumber_ajout.getText().toString();

                if (!first.isEmpty() && !last.isEmpty() && !phone.isEmpty()) {
                    // 1. Ouvrir le manager et la base de données
                    ContactManager manager = new ContactManager(Ajout.this);
                    manager.Ouvrir();

                    // 2. Ajouter le contact à la base de données
                    long result = manager.ajout(first, last, phone);

                    // 3. Fermer la base de données
                    manager.fermer();

                    if (result != -1) {
                        Toast.makeText(Ajout.this, "Contact enregistré !", Toast.LENGTH_SHORT).show();
                        finish(); // Ferme l'activité Ajout et retourne à la précédente
                    } else {
                        Toast.makeText(Ajout.this, "Erreur lors de l'enregistrement.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Ajout.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Simplement fermer l'activité actuelle
            }
        });

        btnInit_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edNom_ajout.setText("");
                edPrenom_ajout.setText("");
                edNumber_ajout.setText("");
                edNom_ajout.requestFocus();
            }
        });
    }
}
