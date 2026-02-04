package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import java.util.Arrays;
import java.util.List;

public class PagO_Joined_Succes_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag15_joine_succes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */
            return insets;
        });


        ConstraintLayout btnok = findViewById(R.id.btn_ok);
        btnok.setOnClickListener(v -> regresarAlHOME());


        TextView txtok = findViewById(R.id.txt_btn_volver);
        txtok.setOnClickListener(v -> regresarAlHOME());

        comprobarPermisoYEmpezar();

    }

    private void regresarAlHOME() {
        Intent intent = new Intent(this, PagC_Home_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Aseguramos que empiece a escuchar al entrar/volver a la actividad
        comprobarPermisoYEmpezar();
    }

    @Override
    protected void onVoiceCommand(String comando) {
        String cmd = comando.toLowerCase().trim();

        // Definimos los sinónimos
        List<String> sinonimosVolver = Arrays.asList("volver", "regresar", "bolber", "bolve", "volve");

        // Verificamos si alguna de esas palabras está en el comando
        for (String palabra : sinonimosVolver) {
            if (cmd.contains(palabra)) {
                hablar("Volviendo al inicio");
                regresarAlHOME();
                break; // Salimos del bucle una vez que lo encuentre
            }
        }
    }
}