package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView olvidarContrasena ;
    private EditText user, passwd;
    private Button login, registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignacion de componentes con la layout
        //TextView
        olvidarContrasena = findViewById(R.id.text_forgotenPass);

        //EditText
        user = findViewById(R.id.editText_UsuarioLogin);
        passwd = findViewById(R.id.editText_PasswordLogin);

        //Button
        login = findViewById(R.id.button_login);
        registrar = findViewById(R.id.button_registrar);




        //Creacion de OnClickListener para olvidarContraseña
        //Objetico: Efectuar un paso a otra activty para recuperar contraseña
        olvidarContrasena.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        //Creacion de OnClickListener para login
        //Objetico: Efectuar un paso a otra activty para inciar sesion en el sistema
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {



            }
        });

        //Creacion de OnClickListener para registrar
        //Objetico: Efectuar un paso a otra activty para registrarse en el sistema
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                pasarBienvenida();
            }
        });

    }

    public void pasarBienvenida(){

        Intent intent = new Intent(this, MainActivity2_BienvenidaNormas.class);
        startActivity(intent);
        //Finalizar Activity
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