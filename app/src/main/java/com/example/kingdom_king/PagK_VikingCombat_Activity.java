package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagK_VikingCombat_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag11_viking_combat);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            return insets;
        });

        // Botón manual: PARA VOLVER ATRAS CON EL BOTON, Esto vuelve al home(pagC)
        ImageView btnFlechaAtras = findViewById(R.id.flecha_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            Intent intent = new Intent(this, PagJ_Live_Activity.class);
            startActivity(intent);
        });

        /*
        ======= PARA LA IMAGEN, POR SI QUEREMOS QUITAR EL VIDEO Y MOSTRAR LA IMAGEN

        Botón manual para ver foto de combate vikingo
        ImageView ImagenCombateAmpliado = findViewById(R.id.imageHombres);
        ImagenCombateAmpliado.setOnClickListener(v -> VerCombate());


        // Botón manual para ver foto de combate vikingo
        ConstraintLayout ConstraintFotoAmpliadoCombate = findViewById(R.id.combate);
        ConstraintFotoAmpliadoCombate.setOnClickListener(v -> VerCombate());


         */


        // Configuración del VIDEO
        VideoView videoHombres = findViewById(R.id.videoHombres);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.pag11_1_hombres_combate;
        videoHombres.setVideoPath(path);


        videoHombres.setOnPreparedListener(mp -> {
            // mp.setVolume(0f, 0f); // Muteado
            mp.setLooping(true);  // Bucle infinito
        });
        
        videoHombres.start();

        videoHombres.setOnClickListener(v -> VerCombate());

        // Mantenemos el clic en el contenedor por si acaso
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

        if (comandoNormalizado.contains("ampliar") ||
                comandoNormalizado.contains("amplar") ||
                comandoNormalizado.contains("amplia") ||
                comandoNormalizado.contains("amplía") ||
                comandoNormalizado.contains("amplie") ||
                comandoNormalizado.contains("amplir") ||
                comandoNormalizado.contains("ampliarlo") ||
                comandoNormalizado.contains("ampliarla")) {

            hablar("Abriendo la foto ampliada");
            VerCombate();
        }
    }

}




