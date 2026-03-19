package ELFEKIHOns.truecaller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Affiche extends AppCompatActivity {
    Button btnBack_affiche;
    EditText edSearch_affiche;
    RecyclerView rv_affiche;
    ContactManager manager;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        btnBack_affiche = findViewById(R.id.btnBack_affiche);
        edSearch_affiche = findViewById(R.id.edSearch_affiche);
        rv_affiche = findViewById(R.id.rv_affiche);

        manager = new ContactManager(Affiche.this);
        manager.Ouvrir();

        // Demander les permissions (Appel + SMS + Localisation)
        checkPermissions();

        // Recherche en temps réel
        edSearch_affiche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btnBack_affiche.setOnClickListener(view -> finish());
    }

    private void checkPermissions() {
        // Ajout de ACCESS_FINE_LOCATION dans la liste
        String[] permissions = {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(p);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Traitement si nécessaire
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList(edSearch_affiche.getText().toString());
    }

    private void updateList(String val) {
        ArrayList<Contact> contacts = manager.getContactByValue(val);
        MyContactRecylerAdapter adapter = new MyContactRecylerAdapter(Affiche.this, contacts);
        rv_affiche.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(Affiche.this, 1,
                LinearLayoutManager.VERTICAL, false);
        rv_affiche.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (manager != null) {
            manager.fermer();
        }
    }
}
