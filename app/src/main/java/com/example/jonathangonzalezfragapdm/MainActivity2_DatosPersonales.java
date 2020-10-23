package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity2_DatosPersonales extends AppCompatActivity {

    private Spinner spinner;
    private RadioButton g_otro, g_masc, g_fem;

    String []generos ={"BiGenero",
            "Cross–Dresser",
            "Drag-King",
            "Drag-Queen",
            "Andrógino",
            "Femme",
            "Female to male",
            "Male to Female",
            "Gender Bender",
            "Genderqueer",
            "No Op",
            "Hijra",
            "Pangénero",
            "Transexual",
            "Transpersona",
            "Buch",
            "Two-Spirit",
            "Trans",
            "Agender",
            "Tercer Sexo",
            "Género fluido",
            "Transgénero no binario",
            "Hermafrodita",
            "Género Dotado",
            "Transgénero",
            " Femme Queen"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__datos_personales);

        //Asignacion de Spinner
        spinner = findViewById(R.id.spinner_Genero);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, generos);
        spinner.setAdapter(adapter);

        //Asignacion de Buttons
        g_otro = findViewById(R.id.radioButton_Otro);
        g_fem = findViewById(R.id.radioButton_Mujer);
        g_masc = findViewById(R.id.radioButton_Hombre);

        //OnClickListener de g_Otro
        g_otro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
            }
        });

        //OnClickListener de g_fem
        g_fem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.INVISIBLE);
            }
        });

        //OnClickListener de g_masc
        g_fem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void PasarIndex(View view){
        Intent intent = new Intent(this, MainActivity2_Index.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // La actividad ya no es visible (ahora está "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // La actividad está a punto de ser destruida.
    }
}