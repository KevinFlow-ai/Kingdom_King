package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagH_Edit_Profile_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag8_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */

            return insets;
        });

        ImageView btnFlechaAtras = findViewById(R.id.btn_volver);
        btnFlechaAtras.setOnClickListener(v -> {
            // Esto cierra la pestaña actual y te "suelta" en la anterior
            finish();
        });

        // Botón manual para ir a ver pedidos
        ConstraintLayout btnVerMispedidos = findViewById(R.id.btn_mis_pedidos);
        btnVerMispedidos.setOnClickListener(v -> irAPaginaDeVerPedidos());



        comprobarPermisoYEmpezar();

    }



    private void irAPaginaDeVerPedidos() {
        Intent intent = new Intent(this, PagI_MyOders_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("pedidos")) {
            hablar("procesando pedidos");
            irAPaginaDeVerPedidos();

        }



    }

}