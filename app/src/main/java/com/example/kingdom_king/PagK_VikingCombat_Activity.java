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

public class PagK_VikingCombat_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag11_viking_combat);
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
            Intent intent = new Intent(this, PagJ_Live_Activity.class);
            startActivity(intent);
        });


        // Botón manual para ver foto de combate vikingo
        ImageView ImagenCombateAmpliado = findViewById(R.id.imageHombres);
        ImagenCombateAmpliado.setOnClickListener(v -> VerCombate());


        // Botón manual para ver foto de combate vikingo
        ConstraintLayout ConstraintFotoAmpliadoCombate = findViewById(R.id.combate);
        ConstraintFotoAmpliadoCombate.setOnClickListener(v -> VerCombate());

        comprobarPermisoYEmpezar();


    }


    private void VerCombate() {
        Intent intent = new Intent(this, PagL_FotoCombate_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("ampliar")) {
            hablar("Abriendo la foto ampliada");
            VerCombate();

        }


    }

}