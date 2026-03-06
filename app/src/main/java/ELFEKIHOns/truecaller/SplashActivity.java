package ELFEKIHOns.truecaller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Attendre 2 secondes (2000 ms) avant de passer à l'écran suivant
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Rediriger vers MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Empêcher le retour sur le splash screen
            }
        }, 2000);
    }
}
