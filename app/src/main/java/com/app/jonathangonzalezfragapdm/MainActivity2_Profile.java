package com.app.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2_Profile extends AppCompatActivity {

    TextView tvName, tvFechaNac, tvArrows, tvBusco;
    private BottomNavigationView navigationView;

    ImageView imgGenero;

    String correo_rec;

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__profile);

        tvName = findViewById(R.id.textView_nombreProfile);
        tvFechaNac = findViewById(R.id.textView_FechaNacimientoProfile);
        tvArrows = findViewById(R.id.textView_NumeroFlecha);
        tvBusco = findViewById(R.id.textView_BuscoProfile);

        imgGenero = findViewById(R.id.imageView_GeneroProfile);

        //Asignacion de BotonNavigation
        navigationView = findViewById(R.id.menuBotonNavegacion_Profile);

        correo_rec = getIntent().getStringExtra("correo");

        Toast.makeText(this, correo_rec, Toast.LENGTH_LONG).show();

        bbdd = FirebaseDatabase.getInstance().getReference();


        bbdd.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot datasnapshot: snapshot.getChildren()){

                    Usuario user = datasnapshot.getValue(Usuario.class);

                    assert user != null;
                    if(correo_rec.equals(user.getCorreo())){

                        tvName.setText(user.getNombre());
                        tvArrows.setText(String.valueOf(user.getArrows()));
                        tvBusco.setText(tvBusco.getText()+": "+user.getBusco());
                        tvFechaNac.setText(tvFechaNac.getText()+": "+user.getFecha_nacimiento());
                        String genero = user.getGenero();

                        if(genero.equals("mujer")){
                            imgGenero.setImageResource(R.drawable.fem);
                        }
                        if(genero.equals("hombre")){
                            imgGenero.setImageResource(R.drawable.masc);
                        }
                        if(genero.equals("otro")){
                            imgGenero.setImageResource(R.drawable.notbinario);
                        }
                    }
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() ==  R.id.menu_home){
                    Intent intent = new Intent(MainActivity2_Profile.this, MainActivity2_Index.class);
                    intent.putExtra("correo", correo_rec);
                    startActivity(intent);
                    finish();
                }

                if(item.getItemId() ==  R.id.menu_msg){
                    Toast.makeText(MainActivity2_Profile.this, "Has pulsado Mensajes", Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId() ==  R.id.menu_notificaciones){
                    Toast.makeText(MainActivity2_Profile.this, "Has pulsado Notificaciones", Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId() ==  R.id.menu_perfil){


                }
                return true;
            }
        });



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

        }
        if(id == R.id.menu_edit){

            Intent intent = new Intent(MainActivity2_Profile.this, MainActivity2_EditProfile.class);
            intent.putExtra("correo", correo_rec);
            startActivity(intent);
            finish();
        }
        if(id == R.id.menu_settings){
            Intent intent = new Intent(MainActivity2_Profile.this, MainActivity2_Settings.class);
            intent.putExtra("correo", correo_rec);
            startActivity(intent);
            finish();
        }

        return true;
    }

    public void creacionCita(View view){

        Intent intent = new Intent(MainActivity2_Profile.this, MainActivity2_CreacionCita.class);
        intent.putExtra("correo", correo_rec);
        startActivity(intent);
        finish();
    }

    public void conseguirFlechas(View view){

        Intent intent = new Intent(MainActivity2_Profile.this, MainActivity_SlotMachine.class);
        intent.putExtra("correo", correo_rec);
        startActivity(intent);
        finish();
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