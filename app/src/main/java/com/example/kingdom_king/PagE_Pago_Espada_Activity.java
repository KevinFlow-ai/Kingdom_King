// C:/Users/El Jefe/AndroidStudioProjects/Kingdom_King/app/src/main/java/com/example/kingdom_king/PagE_Pago_Espada_Activity.java

package com.example.kingdom_king;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class PagE_Pago_Espada_Activity extends AppCompatActivity {

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
    }
}