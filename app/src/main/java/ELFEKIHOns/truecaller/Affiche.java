package ELFEKIHOns.truecaller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Affiche extends AppCompatActivity {
    Button btnBack_affiche;
    EditText edSearch_affiche;
    ListView lv_affiche;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_affiche);

        btnBack_affiche = findViewById(R.id.btnBack_affiche);
        edSearch_affiche= findViewById(R.id.edSearch_affiche);
        lv_affiche = findViewById(R.id.lv_affiche);

        //affichage
        /*ArrayAdapter adapter = new ArrayAdapter(Affiche.this, android.R.layout.simple_list_item_1, Home.contacts);*/
        //contacts=getAllFromDataBase();
        MyContactAdapter adapter=new MyContactAdapter(Affiche.this,Home.contacts);
        lv_affiche.setAdapter(adapter);

        btnBack_affiche.setOnClickListener(view -> {
            finish();
        });
    }
}