package com.example.kingdom_king;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
// import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PagC_Home_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag3_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botón manual para comprar espada
        ImageView btnComprarEspada = findViewById(R.id.btn_comprar_espada);
        btnComprarEspada.setOnClickListener(v -> irAPaginaD());

        // Botón manual para ir a la pagina de usuario
        ImageView btnperfilusuario = findViewById(R.id.imagenPerfilUsuario);
        btnperfilusuario.setOnClickListener(v -> irAPaginaG());


        // Iniciar la escucha de comandos de voz
        comprobarPermisoYEmpezar();
    }

    /**
     * Navega a la pantalla de detalle de la espada (PagD)
     */
    private void irAPaginaD() {
        Intent intent = new Intent(this, PagD_Espada_Activity.class);
        startActivity(intent);
    }

    private void irAPaginaG() {
        Intent intent = new Intent(this, PagG_MyAccount_Activity.class);
        startActivity(intent);
    }

    /**
     * Manejo de comandos de voz detectados
     * @param comando Texto reconocido por el SpeechToText
     */
    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        // 1. Si el comando está vacío o es muy corto (ruido), ignoramos
        if (comandoNormalizado.length() < 3) return;

        if (comandoNormalizado.contains("comprar espada")) {
            hablar("Accediendo a la forja para comprar la espada");
            irAPaginaD();
            return; // <--- AGREGA ESTO: Detiene la ejecución aquí
        }

        if (comandoNormalizado.contains("mi usuario")) {
            hablar("Abriendo tu perfil de usuario");
            irAPaginaG();
            return; // <--- AGREGA ESTO: Evita que llegue al else de abajo
        }

        // 2. Solo si NO entró en ninguno de los anteriores, ejecuta el error
        hablar("Comando no reconocido, intenta de nuevo"); // SI DA PROBLEMAS LOS COMANDO DE VOZ ELIMINAR ESTO Y YA
    }


}

/*
    =======EXPLICACION DEL COMANDO DE if (comandoNormalizado.length() < 3) return;====

    Filtro de ruido: El if (comandoNormalizado.length() < 3) return; evita que sonidos como un
     suspiro o un golpe (que el STT interpreta como un punto o una letra) activen el mensaje de error

 */


/*

    SI EL ELSE DE COMANDO NO ENCONTRADO NO FUNCIONA

    else if (comandoNormalizado.length() > 4) {
        hablar("No entendí: " + comando);
    }
 */