package com.example.kingdom_king;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
// import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagB_Login_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag2_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Botones de inicio de sesión con Apple y Google
        AppCompatButton btnApple = findViewById(R.id.button_loggin_apple); // identificación del botón apple
        AppCompatButton btnGoogle = findViewById(R.id.button_loggin_google); // identificación del botón google


        btnApple.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://appleid.apple.com/"));
            startActivity(intent);
        });

        btnGoogle.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://accounts.google.com/"));
            startActivity(intent);
        });


        // Botón de inicio de sesión principal (ID: btnLogin)
        AppCompatButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> irAHome());

        comprobarPermisoYEmpezar();

    }

    /**
     * Navega a la pantalla principal (PagC) y cierra la actual
     */
    private void irAHome() {
        Intent intent = new Intent(this, PagC_Home_Activity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Se activa cuando BaseVoiceActivity reconoce un comando de voz
     */
    @Override
    protected void onVoiceCommand(String comando) {
        // Normalizamos el comando para detectar variaciones con o sin tilde
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("iniciar sesión") || comandoNormalizado.contains("iniciar sesion")) {
            hablar("Iniciando sesión, bienvenido Jarl");
            irAHome();
        }
    }
}