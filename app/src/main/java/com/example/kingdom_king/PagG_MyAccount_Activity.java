package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagG_MyAccount_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag7_my_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            /*

             Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
             */

            return insets;
        });

        ImageView btnFlechaAtras = findViewById(R.id.btn_volver_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            // Esto cierra la pestaña actual y te "suelta" en la anterior
            finish();
        });

        // Botón manual para ir a editar usuario
        ConstraintLayout btnEditarPerfil = findViewById(R.id.btn_editar_perfil);
        btnEditarPerfil.setOnClickListener(v -> irAPaginaEditarPerfil());



        // Iniciamos la escucha de voz
        comprobarPermisoYEmpezar();
    }


    private void irAPaginaEditarPerfil() {
        Intent intent = new Intent(this, PagH_Edit_Profile_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("editar")) {
            hablar("editando perfil");
            irAPaginaEditarPerfil();

        }



    }
}