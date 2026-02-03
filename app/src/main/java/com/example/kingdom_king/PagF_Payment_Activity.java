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
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        // Si el usuario dice "comprar"
        if (comandoNormalizado.contains("continuar pagando")) {
            hablar("compra realizada, volviendo a la pagina principal");
            irAPaginaHome();

        }


    }

}