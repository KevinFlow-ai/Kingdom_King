package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagD_Espada_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag4_espada);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */
            return insets;
        });


        // Botón manual: PARA VOLVER ATRÁS (Vuelve a PagC)
        ImageView btnFlechaAtras = findViewById(R.id.flecha_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            Intent intent = new Intent(this, PagC_Home_Activity.class);
            startActivity(intent);
        });

          /*
            ************POR SINO FUNCIONA LO PRIMERO******************
             ImageView btnFlechaAtras = findViewById(R.id.flecha_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            // Esto cierra la pestaña actual y te "suelta" en la anterior
            finish();
        });
         */

        // ACCIÓN MANUAL: Al pulsar el contenedor del precio (D -> E)
        LinearLayout btnComprarEspada = findViewById(R.id.contenedor_precio);
        btnComprarEspada.setOnClickListener(v -> irAPaginaM());

        // Iniciamos la escucha de voz
        comprobarPermisoYEmpezar();
    }

        /**
        * Método para navegar a la pantalla de pago (PagE)
        */
    private void irAPaginaM() {
        Intent intent = new Intent(this, PagE_Pago_Espada_Activity.class);
        startActivity(intent);
    }

    /**
     * ACCIÓN POR VOZ: Control de comandos detectados
     */
    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        // Si el usuario dice "comprar"
        if (comandoNormalizado.contains("comprar")) {
            hablar("Procesando compra");
            irAPaginaM();
            return;
        }


    }
}