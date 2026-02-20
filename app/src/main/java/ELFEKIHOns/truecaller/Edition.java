package ELFEKIHOns.truecaller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Edition extends AppCompatActivity {
    EditText edNom_edit, edPrenom_edit, edNumber_edit;
    Button btnBack_edit, btnInit_edit, btnUpdate_edit;
    int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edNom_edit = findViewById(R.id.edNom_edit);
        edPrenom_edit = findViewById(R.id.edPrenom_edit);
        edNumber_edit = findViewById(R.id.edNumber_edit);
        btnBack_edit = findViewById(R.id.btnBack_edit);
        btnInit_edit = findViewById(R.id.btnInit_edit);
        btnUpdate_edit = findViewById(R.id.btnSave_edit);

        // Récupération sécurisée des données
        contactId = getIntent().getIntExtra("ID", -1);
        
        // Diagnostic : si l'ID est -1, la modification échouera toujours
        if (contactId == -1) {
            Toast.makeText(this, "Erreur : ID du contact introuvable", Toast.LENGTH_LONG).show();
        }

        edNom_edit.setText(getIntent().getStringExtra("FIRST"));
        edPrenom_edit.setText(getIntent().getStringExtra("LAST"));
        edNumber_edit.setText(getIntent().getStringExtra("PHONE"));

        btnUpdate_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valNom = edNom_edit.getText().toString();
                String valPrenom = edPrenom_edit.getText().toString();
                String valPhone = edNumber_edit.getText().toString();

                if (!valNom.isEmpty() && !valPrenom.isEmpty() && !valPhone.isEmpty()) {
                    ContactManager manager = new ContactManager(Edition.this);
                    manager.Ouvrir();
                    
                    // On s'assure de passer les paramètres dans le bon ordre : id, first, last, phone
                    // Attention : ici j'utilise valNom pour 'first' et valPrenom pour 'last' 
                    // suivant votre logique dans activity_edit
                    int res = manager.modifier(contactId, valNom, valPrenom, valPhone);
                    manager.fermer();

                    if (res > 0) {
                        Toast.makeText(Edition.this, "Modification enregistrée !", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK); // Indique que la base a changé
                        finish(); 
                    } else {
                        Toast.makeText(Edition.this, "Aucune modification effectuée (ID=" + contactId + ")", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Edition.this, "Champs vides !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInit_edit.setOnClickListener(v -> {
            edNom_edit.setText("");
            edPrenom_edit.setText("");
            edNumber_edit.setText("");
        });

        btnBack_edit.setOnClickListener(v -> finish());
    }
}
