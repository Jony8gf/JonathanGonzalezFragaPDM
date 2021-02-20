package com.app.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2_Profile extends AppCompatActivity {

    private TextView tvName;
    private BottomNavigationView navigationView;

    String correo_rec;

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__profile);

        tvName = findViewById(R.id.textView_nombreProfile);

        //correo_rec = getIntent().getStringExtra("correo");

        bbdd = FirebaseDatabase.getInstance().getReference();

        /*
        bbdd.orderByChild("correo").equalTo(correo_rec).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot: snapshot.getChildren()){

                    Usuario user = datasnapshot.getValue(Usuario.class);

                    assert user != null;
                    String correo = user.getCorreo();
                    Usuario userAux = user;

                    tvName.setText(correo_rec);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //Asignacion de BotonNavigation
        navigationView = findViewById(R.id.menuBotonNavegacion_Perfil);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() ==  R.id.menu_home){
                    Intent intent = new Intent(MainActivity2_Profile.this, MainActivity2_Index.class);
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

         */

    }

    public void creacionCita(View view){

        Intent intent = new Intent(MainActivity2_Profile.this, MainActivity2_CreacionCita.class);
        startActivity(intent);
        finish();
    }

    public void conseguirFlechas(View view){

        Intent intent = new Intent(MainActivity2_Profile.this, MainActivity_SlotMachine.class);
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