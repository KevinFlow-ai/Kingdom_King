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

        // Configurar la barra de navegación inferior
        configurarNavegacionInferior();

        // Botón manual: PARA VOLVER ATRAS
        ImageView btnFlechaAtras = findViewById(R.id.flecha_atras);
        if (btnFlechaAtras != null) {
            btnFlechaAtras.setOnClickListener(v -> finish());
        }

        // Configuración del VIDEO
        VideoView videoHombres = findViewById(R.id.videoHombres);
        if (videoHombres != null) {
            String path = "android.resource://" + getPackageName() + "/" + R.raw.pag11_1_hombres_combate;
            videoHombres.setVideoPath(path);

            videoHombres.setOnPreparedListener(mp -> {
                mp.setLooping(true);  // Bucle infinito
            });
            
            videoHombres.start();
            videoHombres.setOnClickListener(v -> VerCombate());
        }

        // Mantenemos el clic en el contenedor por si acaso
        ConstraintLayout ConstraintFotoAmpliadoCombate = findViewById(R.id.combate);
        if (ConstraintFotoAmpliadoCombate != null) {
            ConstraintFotoAmpliadoCombate.setOnClickListener(v -> VerCombate());
        }

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
