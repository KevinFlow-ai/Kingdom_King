package com.example.kingdom_king;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
// import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagC_Home_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag3_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botón manual para comprar espada
        ImageView btnComprarEspada = findViewById(R.id.btn_comprar_espada);
        btnComprarEspada.setOnClickListener(v -> irAPaginaD());

        // Iniciar la escucha de comandos de voz
        comprobarPermisoYEmpezar();
    }

    /**
     * Navega a la pantalla de detalle de la espada (PagD)
     */
    private void irAPaginaD() {
        Intent intent = new Intent(this, PagD_Espada_Activity.class);
        startActivity(intent);
    }

    /**
     * Manejo de comandos de voz detectados
     * @param comando Texto reconocido por el SpeechToText
     */
    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        // Si el usuario dice "comprar espada"
        if (comandoNormalizado.contains("comprar espada")) {
            hablar("Accediendo a la forja para comprar la espada");
            irAPaginaD();
        }
    }
}