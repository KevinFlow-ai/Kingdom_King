package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            return insets;
        });

        // Botón Atrás
        ImageView btnFlechaAtras = findViewById(R.id.btn_volver_atras);
        btnFlechaAtras.setOnClickListener(v -> finish());

        // Botón Editar Perfil
        ConstraintLayout btnEditarPerfil = findViewById(R.id.btn_editar_perfil);
        btnEditarPerfil.setOnClickListener(v -> irAPaginaEditarPerfil());

        // --- FUNCIONALIDAD CERRAR SESIÓN ---
        ConstraintLayout layoutCerrarSesion = findViewById(R.id.Constraint_cerrar_cesion);
        TextView txtCerrarSesion = findViewById(R.id.btn_cerrar_sesion);

        // Configuramos el clic en ambos elementos
        layoutCerrarSesion.setOnClickListener(v -> cerrarSesion());
        txtCerrarSesion.setOnClickListener(v -> cerrarSesion());

        // Iniciamos la escucha de voz
        comprobarPermisoYEmpezar();
    }

    private void cerrarSesion() {
        // 1. Mensaje de voz y visual
        hablar("Se ha cerrado la sesión correctamente");
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

        // 2. Navegamos al Login (PagB)
        Intent intent = new Intent(this, PagB_Login_Activity.class);
        
        // 3. LIMPIAR EL HISTORIAL: Esto es vital para una "app verdadera". 
        // Evita que el usuario pueda volver atrás a su cuenta una vez cerrada la sesión.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        
        startActivity(intent);
        finish(); // Cerramos la actividad actual
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
        
        // También añadimos el comando de voz para cerrar sesión por comodidad
        if (comandoNormalizado.contains("cerrar sesión") || comandoNormalizado.contains("salir")) {
            cerrarSesion();
        }
    }
}
