package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagJ_Live_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag10_live);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */
            return insets;
        });

        // Botón manual: PARA VOLVER ATRAS CON EL BOTON, Esto vuelve al home(pagC)
        ImageView btnFlechaAtras = findViewById(R.id.flecha_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            Intent intent = new Intent(this, PagC_Home_Activity.class);
            startActivity(intent);
        });


        // Botón manual para ver el combate vikingo
        ConstraintLayout VerFotoCombate = findViewById(R.id.constraintLayout_hombre_combate);
        VerFotoCombate.setOnClickListener(v -> VerCombate());


        // Botón manual para ver el combate vikingo
        ImageView ImagenCombate = findViewById(R.id.imagen_hombre_lucha);
        ImagenCombate.setOnClickListener(v -> VerCombate());


        // Botón manual para ver el combate vikingo
        ConstraintLayout ConstraintTextoCombate = findViewById(R.id.texto_combate);
        ConstraintTextoCombate.setOnClickListener(v -> VerCombate());

        comprobarPermisoYEmpezar();


    }




    private void VerCombate() {
        Intent intent = new Intent(this, PagK_VikingCombat_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("combate")) {
            hablar("Abriendo el combate vikingo");
            VerCombate();

        }


    }


    }
