package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2_Registro extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private Button bt_continuar;
    private EditText et_nombre, et_passwd1, et_passwd2, et_email;
    private CheckBox chk_politicas;
    private String nombre, passwd1, passwd2, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__registro);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Asignacion de Button
        bt_continuar = findViewById(R.id.button_Continuar);
        //Asignacion de EditTexts
        et_nombre = findViewById(R.id.editText_Nombre);
        et_passwd1 = findViewById(R.id.editText_Contraseña1);
        et_passwd2 = findViewById(R.id.editText_Contraseña2);
        et_email = findViewById(R.id.editText_Email);
        //Asignacion de CheckBox
        chk_politicas = findViewById(R.id.checkBox_privacidad);


    }


    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_simple_atras, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == R.id.menu_atras){
            //Pasar de una Activity a otra
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //Finalizar Activity
            finish();
        }

        return true;
    }


    public void RegistroDeUsuario(View view){

        if(chk_politicas.isChecked()){

            nombre = et_nombre.getText().toString();
            passwd1 = et_passwd1.getText().toString();
            passwd2 = et_passwd2.getText().toString();
            email = et_email.getText().toString();

            if(passwd1.equals(passwd2)){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, passwd1);
                Intent intent = new Intent(this, MainActivity2_DatosPersonales.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show();


            }else{

                et_passwd1.setText("");
                et_passwd2.setText("");
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();

            }

        }else{

            Toast.makeText(this, "Debes aceptar las politicas de uso", Toast.LENGTH_LONG).show();

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