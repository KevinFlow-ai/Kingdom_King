package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagN_Raid_England_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag14_raid_england);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            return insets;
        });

        // Botón manual: PARA VOLVER ATRÁS
        ImageView btnFlechaAtras = findViewById(R.id.btn_volver_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            Intent intent = new Intent(this, PagM_Raid_Incursiones_Activity.class);
            startActivity(intent);
        });

        // Botón manual para unirse a la raid
        ConstraintLayout btnIrARaid = findViewById(R.id.contenedor_btn_join);
        btnIrARaid.setOnClickListener(v -> UnirseALaRaid());

        TextView textJoin = findViewById(R.id.JoinRaidd);
        textJoin.setOnClickListener(v -> UnirseALaRaid());

        comprobarPermisoYEmpezar();

        // Configuración del VIDEO
        VideoView videoRaid = findViewById(R.id.videoView_raid);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pag14_video_raid;
        videoRaid.setVideoPath(videoPath);

        // SILENCIAR EL VIDEO (MUTE)
        videoRaid.setOnPreparedListener(mp -> {
            mp.setVolume(0f, 0f); // Volumen izquierdo y derecho a 0
            mp.setLooping(true);  // También configuramos el bucle aquí para mayor estabilidad
        });

        videoRaid.start();

        // Configurar bucle infinito como respaldo
        videoRaid.setOnCompletionListener(mp -> videoRaid.start());

        /*



        // ****************para añadir un el VIDEO CON SONIDO..DESDE ****AQUI***********************


        VideoView videoRaid = findViewById(R.id.videoView_raid);

        // Ruta al video en la carpeta res/raw
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pag14_video_raid;
        videoRaid.setVideoPath(videoPath);

        // Iniciar reproducción automática
        videoRaid.start();

        // Configurar bucle infinito
        videoRaid.setOnCompletionListener(mp -> videoRaid.start());

        // ******************HASTA AQUÍ*********************
         */
    }

    private void UnirseALaRaid() {
        Intent intent = new Intent(this, PagO_Joined_Succes_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onVoiceCommand(String comando) {
        // Normalización básica
        String comandoNormalizado = comando.toLowerCase().trim();

        // Manejamos variaciones fonéticas de la palabra "unirse"
        if (comandoNormalizado.contains("unirse") || 
            comandoNormalizado.contains("unirce") || 
            comandoNormalizado.contains("unirze") ||
            comandoNormalizado.contains("unirme")) {
            
            hablar("unido al raid");
            UnirseALaRaid();
        }
    }
}
