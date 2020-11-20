package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2_DatosPersonales extends AppCompatActivity {

    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    private Spinner spinner;
    private RadioButton g_otro, g_masc, g_fem;
    private EditText et_fecha_nacimiento, et_descipcion;
    private String fecha, genero, descripcion, req;

    private String  nombre_rec, correo_rec;


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

        //Asigancion de EditText
        et_fecha_nacimiento = findViewById(R.id.editText_FechaNacimiento);
        et_descipcion = findViewById(R.id.editText_SobreMi);

        //Asignacion de Spinner
        spinner = findViewById(R.id.spinner_Genero);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, generos);
        spinner.setAdapter(adapter);

        //Asignacion de RadioButton
        g_otro = findViewById(R.id.radioButton_Otro);
        g_fem = findViewById(R.id.radioButton_Mujer);
        g_masc = findViewById(R.id.radioButton_Hombre);

    }

    public void radioButtonOtro(View view){
        spinner.setVisibility(View.VISIBLE);
        genero = "otro";
    }

    public void radioButtonMujer(View view){
        spinner.setVisibility(View.INVISIBLE);
        genero = "mujer";
    }

    public void radioButtonHombre(View view){
        spinner.setVisibility(View.INVISIBLE);
        genero = "hombre";
    }


    public void obtenerFecha(View view){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int mesActual = month + 1;

                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                et_fecha_nacimiento.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
        },anio, mes, dia);

        recogerFecha.show();

    }

    public void PasarMuestrameBusco(View view){

        //Incorpacion de datos Activty Registro
        nombre_rec = getIntent().getStringExtra("nombre");
        correo_rec = getIntent().getStringExtra("correo");

        fecha = et_fecha_nacimiento.getText().toString();
        descripcion = et_descipcion.getText().toString();

        if(fecha.equals("")){

            req = String.valueOf(R.string.requerido);
            et_fecha_nacimiento.setError(req);

        }
        if(descripcion.equals("")){

            req = String.valueOf(R.string.requerido);
            et_descipcion.setError(req);

        }
        /*
        if(genero.equals("")){

            Toast.makeText(this, "Debes selecionar un genero", Toast.LENGTH_SHORT).show();
        }

        && !genero.equals("")

         */

        if(!fecha.equals("") && !descripcion.equals("")) {
            Intent intent = new Intent(this, MainActivity2_Muestrame.class);
            intent.putExtra("nombre", nombre_rec);
            intent.putExtra("correo", correo_rec);
            intent.putExtra("fecha", fecha);
            intent.putExtra("descipcion", descripcion);
            intent.putExtra("genero", genero);
            startActivity(intent);
            finish();
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