// C:/Users/El Jefe/AndroidStudioProjects/Kingdom_King/app/src/main/java/com/example/kingdom_king/PagE_Pago_Espada_Activity.java

package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagE_Pago_Espada_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Esto permite que el fondo se vea detrás de la barra de estado
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag5_pago_espada);

        // HE ELIMINADO EL SETPADDING AQUÍ PARA QUE NO SE DESPLACE
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
             */
            return insets; // Solo retornamos los insets quitamos los padding, lo que esta en comentarios
        });


        // boton manual para cuando pulse el flecha atras vuelva a la pantalla anterior
        ImageView btnFlechaAtras = findViewById(R.id.flecha_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            Intent intent = new Intent(this, PagD_Espada_Activity.class);
            startActivity(intent);
        });



        // boton manual para cuando pulse el contenedor de pagar de apple pase a la siguiente pantalla
        ConstraintLayout btnComprarEspada_Apple = findViewById(R.id.btn_pago_apple);
        btnComprarEspada_Apple.setOnClickListener(v -> irAPaginaF());

        // boton manual para cuando pulse el contenedor de pagar de Google pase a la siguiente pantalla
        ConstraintLayout btnComprarEspada_Google = findViewById(R.id.btn_pago_google);
        btnComprarEspada_Google.setOnClickListener(v -> irAPaginaF());




        // Iniciamos la escucha de voz
        comprobarPermisoYEmpezar();

    }


    private void irAPaginaF() {
        Intent intent = new Intent(this, PagF_Payment_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        // Si el usuario dice "comprar"
        if (comandoNormalizado.contains("pagar")) {
            hablar("Procesando compra");
            irAPaginaF();
            return;
        }

        // Si el usuario dice "comprar"
        if (comandoNormalizado.contains("pagar con google")) {
            hablar("Procesando compra");
            irAPaginaF();

        }


    }
}