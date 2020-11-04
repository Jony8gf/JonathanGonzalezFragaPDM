package com.example.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2_Perfil extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__perfil);

        //Asignacion de BotonNavigation
        navigationView = findViewById(R.id.menuBotonNavegacion_Perfil);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() ==  R.id.menu_home){
                    Intent intent = new Intent(MainActivity2_Perfil.this, MainActivity2_Index.class);
                    startActivity(intent);
                    finish();
                }

                if(item.getItemId() ==  R.id.menu_msg){
                    Toast.makeText(MainActivity2_Perfil.this, "Has pulsado Mensajes", Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId() ==  R.id.menu_perfil){

                }


                return true;
            }
        });
    }
}