package com.example.kingdom_king;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class PagL_FotoCombate_Activity extends BaseVoiceActivity {

    /*para reproducir música en segundo plano parte 1****************************************************************
    private MediaPlayer musicaFondo;
    private VideoView videoFondo;

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag12_foto_combate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */
            return insets;
        });


        // Botón manual para ver foto de combate vikingo
        ConstraintLayout corazon_home = findViewById(R.id.contenedor_corazon);
        corazon_home.setOnClickListener(v -> irAHome());

        /*

        ========= SI SOLO QUEREMOS EL VIDEO DE FONDO, SIN LA MUSICA =========

         */

        VideoView videoFondo = findViewById(R.id.video_fondo_lucha);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.pag12_video_vikingo_pelea;
        videoFondo.setVideoPath(path);


        videoFondo.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            // Esto intenta estirar el video para llenar el layout (como el fitXY)
            mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            videoFondo.start();
        });





        /* para reproducir música en segundo plano parte 2**********************************************************************************************
        VideoView videoFondo = findViewById(R.id.video_fondo_lucha);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.pag12_video_vikingo_pelea;
        videoFondo.setVideoPath(path);

        videoFondo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 1. ELIMINAR MÁRGENES: Estira el video para llenar toda la vista
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

                // 2. REPETICIÓN: En bucle
                mp.setLooping(true);

                // 3. SONIDO: Si el video tuviera audio, aquí controlas el volumen (0.0f a 1.0f)
                mp.setVolume(1.0f, 1.0f);

                videoFondo.start();
            }
        });

        // 4. MÚSICA APARTE (Sin el "MediaPlayer" delante para usar la variable de clase)
        musicaFondo = MediaPlayer.create(this, R.raw.pag12_sonido_medieval_lucha);
        musicaFondo.setLooping(true);
        musicaFondo.start();



         */



    }// cuidado con quitar esto============================================================
    /*

    @Override
    protected void onPause() {
        super.onPause();
        // Detiene el video
        if (videoFondo != null && videoFondo.isPlaying()) {
            videoFondo.pause();
        }
        // Detiene y libera la música
        if (musicaFondo != null) {
            musicaFondo.stop();
            musicaFondo.release();
            musicaFondo = null;
        }
    }

     */

    // hasta aqui**********************************************************************************************************************************










    private void irAHome() {
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
                irAHome();
                break; // Salimos del bucle una vez que lo encuentre
            }
        }
    }

}
