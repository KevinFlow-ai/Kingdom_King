package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagF_Payment_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag6_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            /*

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */

            return insets;
        });


        // boton manual para cuando pulse el contenedor de seguir comprando pase al home
        ConstraintLayout btnContinuarComprando = findViewById(R.id.btn_continuar_comprando);
        btnContinuarComprando.setOnClickListener(v -> irAPaginaHome());

    }



    private void irAPaginaHome() {
        Intent intent = new Intent(this, PagC_Home_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Esto activa el micrófono cada vez que vuelves a la pantalla
        comprobarPermisoYEmpezar();
    }

    @Override
    protected void onVoiceCommand(String comando) {
        // Eliminamos tildes y normalizamos para evitar fallos por "continúa" vs "continuar"
        String comandoNormalizado = comando.toLowerCase().trim()
                .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");

        // Si el comando contiene cualquiera de estas palabras clave
        if (comandoNormalizado.contains("comprando") ||
                comandoNormalizado.contains("continuar")) {

            hablar("Volviendo al inicio");
            irAPaginaHome();
        } else {
            // Solo hablamos si no hemos reconocido nada, para no interrumpir
            hablar("No he entendido " + comando + ". Prueba a decir continuar comprando");
        }
    }


}