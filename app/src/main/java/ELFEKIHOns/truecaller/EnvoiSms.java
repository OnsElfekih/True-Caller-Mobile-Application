package ELFEKIHOns.truecaller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class EnvoiSms extends AppCompatActivity {
    TextView tvNom;
    EditText edMsg;
    Button btnSend, btnQuit;
    String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoi_sms);

        tvNom = findViewById(R.id.tvNomContact_sms);
        edMsg = findViewById(R.id.edMessage_sms);
        btnSend = findViewById(R.id.btnEnvoi_sms);
        btnQuit = findViewById(R.id.btnQuitter_sms);

        // Récupérer les infos envoyées par l'adapter
        String nom = getIntent().getStringExtra("NOM");
        numero = getIntent().getStringExtra("NUMERO");

        // Afficher la phrase personnalisée
        tvNom.setText("Ecrire un message à " + nom);

        btnSend.setOnClickListener(v -> {
            // Vérifier/Demander la permission lors du clic
            if (ActivityCompat.checkSelfPermission(EnvoiSms.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EnvoiSms.this, new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {
                envoyerLeSms();
            }
        });

        // Le bouton Quitter retourne désormais à la page d'affichage (Affiche)
        btnQuit.setOnClickListener(v -> {
            finish(); 
        });
    }

    private void envoyerLeSms() {
        String message = edMsg.getText().toString();
        if (!message.isEmpty()) {
            try {
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(numero, null, message, null, null);
                Toast.makeText(this, "Message envoyé", Toast.LENGTH_SHORT).show();
                finish(); // Retourner à la liste après envoi réussi
            } catch (Exception e) {
                Toast.makeText(this, "Erreur d'envoi", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Veuillez saisir un message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                envoyerLeSms();
            } else {
                // Si la permission n'est pas accordée, on ferme toute l'application
                Toast.makeText(this, "Permission refusée, fermeture de l'application...", Toast.LENGTH_LONG).show();
                finishAffinity();
            }
        }
    }
}
