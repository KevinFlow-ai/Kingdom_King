package com.example.kingdom_king;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class PagN_Raid_England_Activity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pag14_raid_england);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            /*
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

             */
            return insets;
        });


        // Botón manual: PARA VOLVER ATRÁS (Vuelve a PagC)
        ImageView btnFlechaAtras = findViewById(R.id.btn_volver_atras);
        btnFlechaAtras.setOnClickListener(v -> {
            Intent intent = new Intent(this, PagM_Raid_Incursiones_Activity.class);
            startActivity(intent);
        });




        // boton manual para pasar a la pestaña de las join succesfull (N->O)
        ConstraintLayout btnIrARaid = findViewById(R.id.contenedor_btn_join);
        btnIrARaid.setOnClickListener(v -> UnirseALaRaid());



        // boton manual para pasar a la pestaña de las join succesfull (N->O)
        TextView textJoin = findViewById(R.id.JoinRaidd);
        textJoin.setOnClickListener(v -> UnirseALaRaid());


        comprobarPermisoYEmpezar();

    }

    private void UnirseALaRaid() {
        Intent intent = new Intent(this, PagO_Joined_Succes_Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onVoiceCommand(String comando) {
        String comandoNormalizado = comando.toLowerCase().trim();

        if (comandoNormalizado.contains("unirse"))  {
            hablar("unido al raid");
            UnirseALaRaid();

        }


    }
}