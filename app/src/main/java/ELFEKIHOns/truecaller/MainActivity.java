package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Declaration des composants
    Button btnQt_auth,btnct_auth;
    EditText edPassd_auth,edUsername_auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //récupération
        btnct_auth=findViewById(R.id.btnct_auth);
        btnQt_auth=findViewById(R.id.btnQt_auth);
        edUsername_auth=findViewById(R.id.edUsername_auth);
        edPassd_auth=findViewById(R.id.edPassd_auth);

        //event
        btnQt_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

        btnct_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername_auth.getText().toString();
                String password=edPassd_auth.getText().toString();
                if(username.equalsIgnoreCase("azer")&&password.equalsIgnoreCase("111"))
                {
                    //passage vers l'activité home
                    /*
                    Intent i=new Intent();//rani bach na3ml haja
                    i.setData(Url.parse("tel:1233"));
                    startActivity(i);

                    */
                    Intent i=new Intent(MainActivity.this,Home.class);
                    startActivity(i);
                    finish();

                }else {
                    //msg d'erreurs
                    Toast.makeText(MainActivity.this, "Vérifier les infos...", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}