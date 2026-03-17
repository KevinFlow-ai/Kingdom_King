package com.example.kingdom_king;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

import android.Manifest;

public abstract class BaseVoiceActivity extends AppCompatActivity {

    protected SpeechRecognizer speechRecognizer;
    protected Intent recognizerIntent;
    protected boolean isListening = false;
    protected TextToSpeech tts;
    private static final int REQUEST_CODE_MIC = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("es", "ES"));
            }
        });

        setupSpeechRecognizer();
    }

    protected void setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        );
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override public void onReadyForSpeech(Bundle params) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float rmsdB) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                isListening = false;
                reiniciarEscucha();
            }

            @Override
            public void onResults(Bundle results) {
                isListening = false;
                manejarResultados(results);
                reiniciarEscucha();
            }

            @Override public void onPartialResults(Bundle partialResults) {}
            @Override public void onEvent(int eventType, Bundle params) {}
        });
    }

    private void manejarResultados(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches == null || matches.isEmpty()) return;

        // 1. Tomamos solo el primer resultado (el más probable)
        String textoMasProbable = matches.get(0);
        String normalizado = textoMasProbable.toLowerCase(new Locale("es", "ES")).trim();

        // 2. Detenemos el reconocimiento para que no procese ruidos mientras cambia de pantalla
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            isListening = false;
        }

        // 3. Ejecutamos el comando una sola vez
        onVoiceCommand(normalizado);
    }


    /*

        ESTE ES EL CODIGO ORIGINAL ESTO QUE ESTA COMENTADO, EL DE ARRIBA ES OTRO, POR SI EL CODIGO DE ARRIBA NO FUNCIONA
        POR SI SALE EL ERROR DE COMANDO NO RECONOCIDO, INTENTALO DE NUEVO


        private void manejarResultados(Bundle results) {
        ArrayList<String> matches =
                results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches == null || matches.isEmpty()) return;

        for (String texto : matches) {
            String normalizado = texto.toLowerCase(new Locale("es", "ES")).trim();
            onVoiceCommand(normalizado);
        }
    }


     */

    protected abstract void onVoiceCommand(String comando);

    protected void hablar(String texto) {
        if (tts != null) {
            tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null, "TTS_ID");
        }
    }

    protected void comprobarPermisoYEmpezar() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_CODE_MIC
            );
        } else {
            iniciarEscucha();
        }
    }

    protected void iniciarEscucha() {
        if (speechRecognizer != null && !isListening) {
            isListening = true;
            speechRecognizer.startListening(recognizerIntent);
        }
    }

    protected void reiniciarEscucha() {
        iniciarEscucha();
    }

    /**
     * Configura la navegación de la barra inferior buscando todos los IDs posibles usados en el proyecto.
     */
    protected void configurarNavegacionInferior() {
        // --- 1 y 3. HOME (Botón de inicio y Botón Central) ---
        int[] homeIds = {
            R.id.boton_inferior_inicio, R.id.nav_inicio, R.id.btn_nav_inicio, R.id.icon_home, R.id.imageView8, R.id.imageView_home,
            R.id.boton_inferior_central_principal, R.id.nav_central, R.id.btn_nav_central, R.id.icon_principal, R.id.imageView10, R.id.btn_central_inferior
        };
        configurarBoton(homeIds, PagC_Home_Activity.class);

        // --- 2. CARTERA / RAID / MISIONES (Botón maletín) ---
        int[] carteraIds = {R.id.boton_inferior_cartera, R.id.nav_cartera, R.id.btn_nav_cartera, R.id.icon_maletin, R.id.imageView9, R.id.imageView11};
        configurarBoton(carteraIds, PagM_Raid_Incursiones_Activity.class);

        // --- 4. TRANSMISIONES EN VIVO (Botón mochila/notificaciones) ---
        int[] liveIds = {R.id.boton_inferior_notificaciones, R.id.nav_notificaciones, R.id.btn_nav_objetos, R.id.icon_obj, R.id.imageView13, R.id.imageView_maletin};
        configurarBoton(liveIds, PagJ_Live_Activity.class);

        // --- 5. BOTÓN VACÍO (Próximamente...) ---
        int[] emptyIds = {R.id.boton_inferior_objetos, R.id.nav_objetos, R.id.btn_nav_perfil, R.id.icon_noti, R.id.imageView14, R.id.imageView12};
        for (int id : emptyIds) {
            View v = findViewById(id);
            if (v != null) {
                v.setOnClickListener(view -> {
                    // Se queda vacío por petición del usuario para futura funcionalidad
                    Toast.makeText(this, "Próximamente...", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    private void configurarBoton(int[] ids, final Class<?> targetActivity) {
        for (int id : ids) {
            View v = findViewById(id);
            if (v != null) {
                v.setOnClickListener(view -> {
                    // Evitar reiniciar la misma actividad si ya estamos en ella
                    if (!this.getClass().equals(targetActivity)) {
                        Intent intent = new Intent(this, targetActivity);
                        // Limpiar historial si es necesario
                        if (targetActivity.equals(PagC_Home_Activity.class)) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        }
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            isListening = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
            speechRecognizer = null;
        }
    }
}
