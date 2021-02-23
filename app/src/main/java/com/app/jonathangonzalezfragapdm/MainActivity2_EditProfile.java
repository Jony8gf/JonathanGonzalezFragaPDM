package com.app.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity2_EditProfile extends AppCompatActivity {

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Fecha
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    int anio = c.get(Calendar.YEAR);

    private int comprobadorAno;

    String correo_rec, req;
    DatabaseReference bbdd;
    FirebaseDatabase firebaseDatabase;

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


    private EditText tvName, tvFecha;
    private Spinner spinner;
    private boolean validar_muestrame = false, validar_busco= false, validar_sexo = false;
    private String aux_muestrame, aux_busco, aux_genero;
    private Usuario userAux;
    private RadioButton rb_mujeres, rb_hombres, rb_otro, rb_relacion, rb_lio, rb_amistad, rb_muestraHombres, rb_muestraMujeres, rb_muestraAmbos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__edit_profile);

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        bbdd = firebaseDatabase.getReference();

        //EditText
        tvName = findViewById(R.id.editText_NombreEditProfile);
        tvFecha = findViewById(R.id.editText_FechaNacimientoEditProfile);

        //RadioButtons
        rb_mujeres = findViewById(R.id.radioButton_MujerEditProfile);
        rb_hombres = findViewById(R.id.radioButton_HombreEditProfile);
        rb_otro = findViewById(R.id.radioButton_OtroEditProfile);

        rb_lio = findViewById(R.id.radioButton2radioButton_BuscoNocheEditProfile);
        rb_relacion = findViewById(R.id.radioButton_BuscoRelacionEditProfile);
        rb_amistad = findViewById(R.id.radioButton_BuscoAmistadEditProfile);

        rb_muestraMujeres = findViewById(R.id.radioButton_MuestremeMujeresEditProfile);
        rb_muestraHombres = findViewById(R.id.radioButton_MuestremeHombresEditProfile);
        rb_muestraAmbos = findViewById(R.id.radioButton_MuestremeAmobsEditProfile);

        //Asignacion de Spinner
        spinner = findViewById(R.id.spinner_GeneroEditProfile);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, generos);
        spinner.setAdapter(adapter);

        correo_rec = getIntent().getStringExtra("correo");
        Toast.makeText(this, correo_rec, Toast.LENGTH_LONG).show();

        bbdd.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot: snapshot.getChildren()){

                    userAux = datasnapshot.getValue(Usuario.class);
                    assert userAux != null;

                    if (correo_rec.equals(userAux.getCorreo())){
                        tvName.setText(userAux.getNombre());
                        tvFecha.setText(userAux.getFecha_nacimiento());

                        String sexo = userAux.getGenero();
                        switch (sexo){
                            case "mujer": rb_mujeres.setChecked(true); aux_muestrame = "mujer";
                                break;
                            case "hombre": rb_hombres.setChecked(true); aux_muestrame = "hombre";
                                break;
                            case "otro": rb_otro.setChecked(true); aux_muestrame = "otro";
                                break;
                            default:
                        }

                        String muestrame = userAux.getMuestrame();
                        switch (muestrame){
                            case "mujeres": rb_muestraMujeres.setChecked(true); spinner.setVisibility(View.INVISIBLE); aux_muestrame = "hombres";
                                break;
                            case "hombres": rb_muestraHombres.setChecked(true); spinner.setVisibility(View.INVISIBLE); aux_muestrame = "mujeres";
                                break;
                            case "ambos": rb_muestraAmbos.setChecked(true); spinner.setVisibility(View.VISIBLE); aux_muestrame = "ambos";
                                break;
                            default:
                        }

                        String busco = userAux.getBusco();
                        switch (busco){
                            case "noche": rb_lio.setChecked(true); aux_busco = "noche";
                                break;
                            case "relacion": rb_relacion.setChecked(true); aux_busco = "relacion";
                                break;
                            case "amistad": rb_amistad.setChecked(true); aux_busco = "amistad";
                                break;
                            default:
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void guardarCambios(View v) {

        Usuario user = userAux;

        String nombre = tvName.getText().toString();
        if(nombre.equals("")){

            req = getString(R.string.requerido);
            tvName.setError(req);
        }
        else{
            calcularEdad();

            user.setNombre(tvName.getText().toString());
            user.setGenero(aux_genero);

            user.setMuestrame(aux_muestrame);
            user.setBusco(aux_busco);
            user.setFecha_nacimiento(tvFecha.getText().toString());

            if (comprobadorAno < 18) {


                Toast.makeText(this, "No dispones de la edad suficiente", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this, user.getUid(), Toast.LENGTH_LONG).show();
                bbdd.child("Persona").child(user.getUid()).setValue(null);
                bbdd.child("Persona").child(user.getUid()).setValue(user);
                //bbdd.child("Persona").push().setValue(user);
                //actualizar(user);

            }
        }
    }

    public void actualizar(Usuario user){
        bbdd.child(user.getCorreo()).setValue(user);
    }

    public void obtenerFecha(View view){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int mesActual = month + 1;

                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                tvFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

                anio = year;
                mes = month;
                dia = dayOfMonth;
            }
        },anio, mes, dia);

        recogerFecha.show();
    }

    public void calcularEdad(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            LocalDate birthdate = LocalDate.of(anio, mes, dia);
            Period p = Period.between(birthdate, today);
            //Toast.makeText(this, "Tienes " + p.getYears() + " años, " + p.getMonths() + " meses, " + p.getDays() + " días de edad.", Toast.LENGTH_LONG).show();
            comprobadorAno = p.getYears();
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

        if (id == R.id.radioButton_OtroEditProfile){
            aux_genero = "otro";
            spinner.setVisibility(View.VISIBLE);
            validar_sexo = true;

        }

        if (id == R.id.radioButton_MujerEditProfile){
            aux_genero = "mujer";
            spinner.setVisibility(View.INVISIBLE);
            validar_sexo = true;
        }

        if (id == R.id.radioButton_HombreEditProfile){
            aux_genero = "hombre";
            spinner.setVisibility(View.INVISIBLE);
            validar_sexo = true;
        }
    }


    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_settings_profile, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == R.id.menu_perfil){
            Intent intent = new Intent(MainActivity2_EditProfile.this, MainActivity2_Profile.class);
            intent.putExtra("correo", correo_rec);
            startActivity(intent);
            finish();
        }
        if(id == R.id.menu_edit){

        }
        if(id == R.id.menu_settings){
            Intent intent = new Intent(MainActivity2_EditProfile.this, MainActivity2_Settings.class);
            intent.putExtra("correo", correo_rec);
            startActivity(intent);
            finish();
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").

        if (UtilsNetwork.isOnline(this)){



        }else{
            Intent intent = new Intent(this,  MainActivity_NoConexionInternet.class);
            startActivity(intent);
            finish();
        }
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