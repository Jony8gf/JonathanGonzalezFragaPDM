package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity2_Muestrame extends AppCompatActivity {

    private boolean validar_muestrame = false, validar_busco= false;
    private String aux_muestrame, aux_busco;
    private String nombre_rec, correo_rec, fecha_rec, genero_rec, descripcion_rec;
    private RadioButton rb_mujeres, rb_hombres, rb_ambos, rb_relacion, rb_lio, rb_amistad;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__muestrame);

        //Asignacion de RadioButtons
        rb_mujeres = findViewById(R.id.radioButton_MuestremeMujeres);
        rb_hombres = findViewById(R.id.radioButton_MuestremeHombres);
        rb_ambos = findViewById(R.id.radioButton_MuestremeAmobs);

        //Asignacion de RadioButtons
        rb_relacion = findViewById(R.id.radioButton_BuscoRelacion);
        rb_lio = findViewById(R.id.radioButton2radioButton_BuscoNoche);
        rb_amistad = findViewById(R.id.radioButton_BuscoAmistad);

    }


    public void PasarImagenesDePerfil(View view){

        nombre_rec = getIntent().getStringExtra("nombre");
        correo_rec = getIntent().getStringExtra("correo");
        fecha_rec = getIntent().getStringExtra("fecha");
        genero_rec = getIntent().getStringExtra("genero");
        descripcion_rec = getIntent().getStringExtra("descripcion");

        if(validar_busco && validar_muestrame){

            Intent intent = new Intent(this, MainActivity2_ImagenesDePerfil.class);
            intent.putExtra("nombre", nombre_rec);
            intent.putExtra("correo", correo_rec);
            intent.putExtra("fecha", fecha_rec);
            intent.putExtra("genero", genero_rec);
            intent.putExtra("descipcion", descripcion_rec);
            intent.putExtra("muestrame", aux_muestrame);
            intent.putExtra("busco", aux_busco);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, R.string.help_muestrame_busco, Toast.LENGTH_SHORT).show();
        }

    }

    public void seleccion(View view){

        int id = view.getId();

        if(id == R.id.radioButton_MuestremeHombres){
            aux_muestrame = "hombres";
            validar_muestrame = true;
            //Toast.makeText(this , aux_muestrame, Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.radioButton_MuestremeMujeres){
            aux_muestrame = "mujeres";
            validar_muestrame = true;
            //Toast.makeText(this , aux_muestrame, Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.radioButton_MuestremeAmobs){
            aux_muestrame = "ambos";
            validar_muestrame = true;
            //Toast.makeText(this , aux_muestrame, Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.radioButton_BuscoAmistad){
            aux_busco = "amistad";
            validar_busco = true;
            //Toast.makeText(this , aux_busco, Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.radioButton_BuscoRelacion){
            aux_busco = "relacion";
            validar_busco = true;
            //Toast.makeText(this , aux_busco, Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.radioButton2radioButton_BuscoNoche){
            aux_busco = "noche";
            validar_busco = true;
            //Toast.makeText(this , aux_busco, Toast.LENGTH_SHORT).show();
        }

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