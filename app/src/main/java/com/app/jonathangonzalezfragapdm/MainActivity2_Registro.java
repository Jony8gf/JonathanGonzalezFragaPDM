package com.app.jonathangonzalezfragapdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
    private String nombre, passwd1, passwd2, email, req;

    private final static String urlWHO = "https://github.com/Jony8gf/JonathanGonzalezFragaPDM";

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
        if(id == R.id.menu_contacto){
            MostrarDialogContacto();

        }

        return true;
    }

    //Metodo para mostrar un dialog de contacto al usuario
    public void MostrarDialogContacto(){

        //Creacion de Dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.ayuda_contacto)
                .setPositiveButton(R.string.acepto, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(urlWHO));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dar a cancelar
                    }
                });
        // Mostrar el dialog
        builder.show();

    }


    public void RegistroDeUsuario(View view){

        nombre = et_nombre.getText().toString();
        passwd1 = et_passwd1.getText().toString();
        passwd2 = et_passwd2.getText().toString();
        email = et_email.getText().toString();

        if(nombre.equals("")){

            req = getString(R.string.requerido);
            et_nombre.setError(req);
        }
        if(passwd1.equals("")){

            req = getString(R.string.requerido);
            et_passwd1.setError(req);
        }
        if(passwd2.equals("")){

            req = getString(R.string.requerido);
            et_passwd2.setError(req);
        }
        if(email.equals("")){

            req = getString(R.string.requerido);
            et_email.setError(req);

        }

        if(chk_politicas.isChecked()){

            if(passwd1.equals(passwd2) && !passwd1.equals("") && 6 <= passwd1.length()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, passwd1);
                Intent intent = new Intent(this, MainActivity2_DatosPersonales.class);
                intent.putExtra("correo", email);
                intent.putExtra("nombre", nombre);
                startActivity(intent);
                finish();
                Toast.makeText(this, R.string.registrado, Toast.LENGTH_LONG).show();


            }else{

                et_passwd1.setText("");
                et_passwd2.setText("");
                Toast.makeText(this, R.string.contrasenas_no_coinciden, Toast.LENGTH_LONG).show();
                Toast.makeText(this, R.string.contrasenas_caracteres, Toast.LENGTH_LONG).show();

            }

        }else{

            Toast.makeText(this, R.string.aceptar_usos, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Pasar de una Activity a otra
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