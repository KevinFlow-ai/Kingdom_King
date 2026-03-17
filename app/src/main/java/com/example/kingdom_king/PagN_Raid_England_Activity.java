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

        // Configurar la barra de navegación inferior
        configurarNavegacionInferior();

        // Botón manual: PARA VOLVER ATRÁS
        ImageView btnFlechaAtras = findViewById(R.id.btn_volver_atras);
        if (btnFlechaAtras != null) {
            btnFlechaAtras.setOnClickListener(v -> finish());
        }

        // Botón manual para unirse a la Raid
        ConstraintLayout btnJoinRaid = findViewById(R.id.contenedor_btn_join);
        if (btnJoinRaid != null) {
            btnJoinRaid.setOnClickListener(v -> irAPaginaExito());
        }

        TextView textJoin = findViewById(R.id.JoinRaidd);
        if (textJoin != null) {
            textJoin.setOnClickListener(v -> irAPaginaExito());
        }

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

    private void irAPaginaExito() {
        Intent intent = new Intent(this, PagO_Joined_Succes_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("unirse") || comandoNormalizado.contains("join")) {
            hablar("uniéndose a la raid");
            irAPaginaExito();
        }
    }
}
