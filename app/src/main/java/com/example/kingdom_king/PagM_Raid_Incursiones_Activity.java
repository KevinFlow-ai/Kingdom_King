package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagM_Raid_Incursiones_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag13_incursiones_raid);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */
            return insets;
        });

        // Configurar la barra de navegación inferior
        configurarNavegacionInferior();

        // Botón manual: PARA VOLVER ATRÁS
        ImageView btnFlechaAtras = findViewById(R.id.btn_volver_atras);
        if (btnFlechaAtras != null) {
            btnFlechaAtras.setOnClickListener(v -> finish());
        }

        // boton manual para pasar a la pestaña de las Raid
        ConstraintLayout btnIrARaid = findViewById(R.id.contenedor_raid_england);
        if (btnIrARaid != null) {
            btnIrARaid.setOnClickListener(v -> irAPaginaRaidInglaterra());
        }

        ConstraintLayout btnJoin = findViewById(R.id.btn_join_england);
        if (btnJoin != null) {
            btnJoin.setOnClickListener(v -> irAPaginaRaidInglaterra());
        }

        ImageView imagenJoin = findViewById(R.id.img_raid_england);
        if (imagenJoin != null) {
            imagenJoin.setOnClickListener(v -> irAPaginaRaidInglaterra());
        }

        TextView textJoin = findViewById(R.id.text_join_england);
        if (textJoin != null) {
            textJoin.setOnClickListener(v -> irAPaginaRaidInglaterra());
        }

        comprobarPermisoYEmpezar();

    }


    private void irAPaginaRaidInglaterra() {
        Intent intent = new Intent(this, PagN_Raid_England_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("inglaterra") ||
                comandoNormalizado.contains("inglaterrra") ||
                comandoNormalizado.contains("inglatera") ||
                comandoNormalizado.contains("inglaterrah") ||
                comandoNormalizado.contains("inglaterraaa") ||
                comandoNormalizado.contains("ir a inglaterra")) {

            hablar("abriendo raid inglaterra");
            irAPaginaRaidInglaterra();
        }
    }


}
