package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2_ImagenesDePerfil extends AppCompatActivity {

    private Button bt_continuar;
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;

    private int cuentaFotos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__imagenes_de_perfil);

        //Asignacion de Button
        bt_continuar = findViewById(R.id.button_ContinuarImagenes);

        //Asigancion de ImageViews
        img1 = findViewById(R.id.imageView_foto1);
        img2 = findViewById(R.id.imageView_foto2);
        img3 = findViewById(R.id.imageView_foto3);
        img4 = findViewById(R.id.imageView_foto4);
        img5 = findViewById(R.id.imageView_foto5);
        img6 = findViewById(R.id.imageView_foto6);
        img7 = findViewById(R.id.imageView_foto7);
        img8 = findViewById(R.id.imageView_foto8);
        img9 = findViewById(R.id.imageView_foto9);

        //Ejecucion del metodo
        MostrarDialogAyuda();


    }


    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_ayuda_dialog, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones-menu
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == R.id.menu_ayuda){

            MostrarDialogAyuda();
        }

        return true;
    }


    //Metodo para mostrar un dialog de ayuda al usuario
    public void MostrarDialogAyuda(){

        //Creacion de Dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.ayuda_fotos)
                .setPositiveButton(R.string.acepto, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dar a aceptar
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


    //Metodo para pasar al index si cumple los requisitos minimos
    public void pasarIndex(View view){

        if(cuentaFotos > 3) {

            //Pasar a otra activity
            Intent intent = new Intent(this, MainActivity2_Index.class);
            startActivity(intent);
            finish();

        }
        else{
            //Mostrar Dialogo de ayuda al usuario
            MostrarDialogAyuda();
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