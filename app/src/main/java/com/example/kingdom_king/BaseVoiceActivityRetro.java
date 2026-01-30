package com.example.kingdom_king;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

public abstract class BaseVoiceActivityRetro extends AppCompatActivity {

    protected TextToSpeech tts;

    private static final int REQUEST_CODE_MIC = 200;
    private static final int REQUEST_CODE_STT = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("es", "ES"));
            }
        });
    }

    // --------- VOZ A TEXTO (modo clásico con cuadro de micrófono) ---------

    /**
     * Comprueba el permiso de micrófono y, si lo tiene, lanza el Intent
     * que abre la ventana clásica de reconocimiento de voz.
     */
    protected void comprobarPermisoYEmpezar() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_CODE_MIC
            );
        } else {
            lanzarReconocimientoVoz();
        }
    }

    /**
     * Lanza el Intent clásico de reconocimiento de voz (se abre la ventana con el micrófono).
     */
    protected void lanzarReconocimientoVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        );
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora");

        try {
            startActivityForResult(intent, REQUEST_CODE_STT);
        } catch (Exception e) {

            // Toast.makeText(this, "Este dispositivo no soporta reconocimiento de voz", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Procesa el resultado del Intent de reconocimiento y llama a onVoiceCommand().
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_STT && resultCode == RESULT_OK && data != null) {
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
            );
            if (matches != null && !matches.isEmpty()) {
                String texto = matches.get(0);
                String normalizado = texto.toLowerCase(new Locale("es", "ES")).trim();
                onVoiceCommand(normalizado);
            }
        }
    }

    /**
     * Respuesta a la petición de permisos.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_MIC) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                lanzarReconocimientoVoz();
            } else {
                // Toast opcional
                // Toast.makeText(this, "Se necesita el micrófono para usar la voz", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // --------- TEXTO A VOZ ---------

    protected void hablar(String texto) {
        if (tts != null) {
            tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null, "TTS_ID");
        }
    }

    /**
     * Cada Activity hija implementa qué hacer con el comando reconocido.
     */
    protected abstract void onVoiceCommand(String comando);

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        */
    }
}
