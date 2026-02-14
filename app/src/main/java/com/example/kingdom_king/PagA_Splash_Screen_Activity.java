package com.example.kingdom_king;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PagA_Splash_Screen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag1_splash_screen);

        VideoView videoSplash = findViewById(R.id.video_splash);

        // Ruta del video en la carpeta raw
        String path = "android.resource://" + getPackageName() + "/" + R.raw.pag1_1_video_splash_activity;
        videoSplash.setVideoURI(Uri.parse(path));

        // Cuando el video termine o pasen 6 segundos, pasamos a la siguiente pantalla
        videoSplash.setOnCompletionListener(mp -> {
            irALogin();
        });

        // Iniciamos la reproducción
        videoSplash.start();


    }

    private void irALogin() {
        Intent intent = new Intent(PagA_Splash_Screen_Activity.this, PagB_Login_Activity.class);
        startActivity(intent);
        // Transición suave entre actividades
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}



/*

=============================================
ANIMACIÓN DE ZOOM Y EL SONIDO AGREGADO
=============================================


    package com.example.kingdom_king;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PagA_Splash_Screen_Activity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag1_splash_screen);

        // Inicializar y reproducir el sonido (tipo Netflix)
        mediaPlayer = MediaPlayer.create(this, R.raw.sonido_medieval_splash_batalla  );
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }

        ImageView fondoAnimado = findViewById(R.id.imagen_fondo_animada);

        // CONFIGURACIÓN DE LA ANIMACIÓN DE "ZOOM"
        AnimationSet conjuntoAnimaciones = new AnimationSet(true);

        // 1. Animación de Escalado: Empieza en miniatura (0.1) y llega al tamaño real (1.0)
        ScaleAnimation zoomIn = new ScaleAnimation(
                0.1f, 1.0f, // X
                0.1f, 1.0f, // Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivote en el centro
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        zoomIn.setDuration(2000); // 2 segundos para que sea épico

        // 2. Animación de Aparecer (Alpha)
        AlphaAnimation aparecer = new AlphaAnimation(0.0f, 1.0f);
        aparecer.setDuration(2000);

        conjuntoAnimaciones.addAnimation(zoomIn);
        conjuntoAnimaciones.addAnimation(aparecer);
        conjuntoAnimaciones.setInterpolator(new AccelerateDecelerateInterpolator());

        // Iniciar la animación
        fondoAnimado.startAnimation(conjuntoAnimaciones);

        // TRANSICIÓN A LA SIGUIENTE PANTALLA
        new Handler().postDelayed(() -> {
            // Detener el sonido antes de cambiar de pantalla
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }

            Intent intent = new Intent(PagA_Splash_Screen_Activity.this, PagB_Login_Activity.class);
            startActivity(intent);
            // Efecto de transición suave entre actividades
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 3000); // Esperamos 3 segundos en total
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar el reproductor de medios para evitar fugas de memoria
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

/*

====================================
ANIMACIÓN DE ZOON SIN SONIDO
====================================
package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PagA_Splash_Screen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag1_splash_screen);

        ImageView fondoAnimado = findViewById(R.id.imagen_fondo_animada);

        // CONFIGURACIÓN DE LA ANIMACIÓN DE "ZOOM"
        AnimationSet conjuntoAnimaciones = new AnimationSet(true);

        // 1. Animación de Escalado: Empieza en miniatura (0.1) y llega al tamaño real (1.0)
        ScaleAnimation zoomIn = new ScaleAnimation(
                0.1f, 1.0f, // X
                0.1f, 1.0f, // Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivote en el centro
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        zoomIn.setDuration(2000); // 2 segundos para que sea épico

        // 2. Animación de Aparecer (Alpha)
        AlphaAnimation aparecer = new AlphaAnimation(0.0f, 1.0f);
        aparecer.setDuration(2000);

        conjuntoAnimaciones.addAnimation(zoomIn);
        conjuntoAnimaciones.addAnimation(aparecer);
        conjuntoAnimaciones.setInterpolator(new AccelerateDecelerateInterpolator());

        // Iniciar la animación
        fondoAnimado.startAnimation(conjuntoAnimaciones);

        // TRANSICIÓN A LA SIGUIENTE PANTALLA
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(PagA_Splash_Screen_Activity.this, PagB_Login_Activity.class);
            startActivity(intent);
            // Efecto de transición suave entre actividades
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 3000); // Esperamos 3 segundos en total
    }
}

 */








/*

=============================================
ANIMACION DE REBOTE DE ABAJO HACIA ARRIBA
=============================================


package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PagA_Splash_Screen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag1_splash_screen);

        ImageView fondoAnimado = findViewById(R.id.imagen_fondo_animada);

        // CONFIGURACIÓN DE ANIMACIÓN: DESPLAZAMIENTO + REBOTE
        AnimationSet conjuntoAnimaciones = new AnimationSet(true);

        // 1. Desplazamiento: Viene desde abajo (100% de la pantalla) hasta su posición (0)
        TranslateAnimation deslizarArriba = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        deslizarArriba.setDuration(1800);

        // 2. Desvanecimiento: De invisible a visible
        AlphaAnimation aparecer = new AlphaAnimation(0.0f, 1.0f);
        aparecer.setDuration(1000);

        // APLICAMOS EL INTERPOLADOR OVERSHOOT (Efecto elástico/rebote)
        conjuntoAnimaciones.setInterpolator(new OvershootInterpolator(1.2f));
        conjuntoAnimaciones.addAnimation(deslizarArriba);
        conjuntoAnimaciones.addAnimation(aparecer);

        fondoAnimado.startAnimation(conjuntoAnimaciones);

        // Transición automática tras 3 segundos
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(PagA_Splash_Screen_Activity.this, PagB_Login_Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 3000);
    }
}
 */





/*

=============================================
ANIMACION DE VOLTERETA/GIROS
=============================================

package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PagA_Splash_Screen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag1_splash_screen);

        ImageView fondoAnimado = findViewById(R.id.imagen_fondo_animada);

        // CONFIGURACIÓN DE LA ANIMACIÓN DE "ZOOM"
        // CONFIGURACIÓN: GIRO + ZOOM
        AnimationSet conjuntoAnimaciones = new AnimationSet(true);

// 1. Rotación: Da una vuelta completa (360 grados)
        RotateAnimation girar = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        girar.setDuration(2000);

// 2. Escalado: De pequeño a grande
        ScaleAnimation crecer = new ScaleAnimation(
                0.2f, 1.0f, 0.2f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        crecer.setDuration(2000);

// 3. Aparecer
        AlphaAnimation aparecer = new AlphaAnimation(0.0f, 1.0f);
        aparecer.setDuration(1000);

        conjuntoAnimaciones.addAnimation(girar);
        conjuntoAnimaciones.addAnimation(crecer);
        conjuntoAnimaciones.addAnimation(aparecer);
        conjuntoAnimaciones.setInterpolator(new AccelerateDecelerateInterpolator());

        fondoAnimado.startAnimation(conjuntoAnimaciones);

        // TRANSICIÓN A LA SIGUIENTE PANTALLA
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(PagA_Splash_Screen_Activity.this, PagB_Login_Activity.class);
            startActivity(intent);
            // Efecto de transición suave entre actividades
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 3000); // Esperamos 3 segundos en total
    }
}
 */










/*

=============================================
ANIMACION DE Entrada Diagonal (Efecto "Corte de Espada")
=============================================

package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PagA_Splash_Screen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag1_splash_screen);

        ImageView fondoAnimado = findViewById(R.id.imagen_fondo_animada);

        // CONFIGURACIÓN DE LA ANIMACIÓN DE "ZOOM"
        // CONFIGURACIÓN: ENTRADA DIAGONAL
        AnimationSet conjuntoAnimaciones = new AnimationSet(true);

// Viene desde fuera de la pantalla (-100% en X y Y)
        TranslateAnimation diagonal = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        diagonal.setDuration(1500);

// Añadimos un poco de rebote al final para que no sea seco
        conjuntoAnimaciones.setInterpolator(new android.view.animation.OvershootInterpolator(1.0f));
        conjuntoAnimaciones.addAnimation(diagonal);

        fondoAnimado.startAnimation(conjuntoAnimaciones);

        // TRANSICIÓN A LA SIGUIENTE PANTALLA
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(PagA_Splash_Screen_Activity.this, PagB_Login_Activity.class);
            startActivity(intent);
            // Efecto de transición suave entre actividades
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 3000); // Esperamos 3 segundos en total
    }
}
 */