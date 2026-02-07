package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    public static ArrayList<Contact> contacts = new ArrayList<>();
    Button btnAjt_acc,btnAff_acc,btnExit_acc,btnDec_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        //récupération
        btnAjt_acc=findViewById(R.id.btnAjt_acc);
        btnAff_acc=findViewById(R.id.btnAff_acc);
        btnExit_acc=findViewById(R.id.btnExit_acc);
        btnDec_acc=findViewById(R.id.btnDec_acc);
        //event
        btnAjt_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Ajout.class);
                startActivity(i);
            }
        });
        btnAff_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Affiche.class);
                startActivity(i);
            }
        });

        btnExit_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home.this.finish(); //7ata finish wa7adha temchy
            }
        });

        btnDec_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    }