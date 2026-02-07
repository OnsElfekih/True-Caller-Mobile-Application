package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Ajout extends AppCompatActivity {

    EditText edNom_ajout, edPrenom_ajout, edNumber_ajout;
    Button btnBack_ajout, btnInit_ajout, btnSave_ajout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajout);
        //récupération
        edNom_ajout = findViewById(R.id.edNom_ajout);
        edPrenom_ajout = findViewById(R.id.edPrenom_ajout);
        edNumber_ajout = findViewById(R.id.edNumber_ajout);
        btnBack_ajout = findViewById(R.id.btnBack_ajout);
        btnInit_ajout = findViewById(R.id.btnInit_ajout);
        btnSave_ajout = findViewById(R.id.btnSave_ajout);

        //event
        btnSave_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = edNom_ajout.getText().toString();
                String last = edPrenom_ajout.getText().toString();
                String phone = edNumber_ajout.getText().toString();

                if (!first.isEmpty() && !last.isEmpty() && !phone.isEmpty()) {
                    // Ajout dans la liste statique
                    Contact c = new Contact(first, last, phone);
                    Home.contacts.add(c);

                    // Message de succès
                    Toast.makeText(Ajout.this, "Contact enregistré avec succès !", Toast.LENGTH_SHORT).show();

                    // Redirection vers l'activité Affiche (qui utilise view_contact.xml pour ses items)
                    Intent i = new Intent(Ajout.this, Affiche.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Ajout.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ajout.this, Home.class);
                startActivity(i);
                finish();
            }
        });
        btnInit_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Vider les textes
                edNom_ajout.setText("");
                edPrenom_ajout.setText("");
                edNumber_ajout.setText("");

                // Remettre le curseur sur le premier champ
                edNom_ajout.requestFocus();
            }
        });
    }
}