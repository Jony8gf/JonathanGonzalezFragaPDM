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
import android.widget.Toast;

public class MainActivity2_BienvenidaNormas extends AppCompatActivity {

    private Button bt_aceptar;

    private final static String urlWHO = "https://github.com/Jony8gf/JonathanGonzalezFragaPDM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__bienvenida_normas);

        bt_aceptar = findViewById(R.id.button_AceptoNormas);

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


    public void pasarActivityRegistrarse(View view){
        Intent intent = new Intent(this, MainActivity2_Registro.class);
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