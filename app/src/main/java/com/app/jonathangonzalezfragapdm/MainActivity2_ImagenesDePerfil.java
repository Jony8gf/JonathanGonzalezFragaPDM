package com.app.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.airbnb.lottie.animation.content.Content;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MainActivity2_ImagenesDePerfil extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final int PICK_IMAGE = 100;
    private static final int PICK_IMAGE_GALERY = 102;
    private static final int REQUEST_PERMISSION_CODE = 101;

    private Button bt_continuar;
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    private View auxViex;
    private ImageView aux;
    private int auxid;
    private int cuentaFotos = 0;
    private Uri imageUri;
    private String nombre_rec, correo_rec, fecha_rec, genero_rec, descripcion_rec, muestrame_rec, busco_rec;
    private DatabaseReference databaseReference;
    private Usuario user;
    private View v;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__imagenes_de_perfil);

         firebaseStorage = FirebaseStorage.getInstance();
         storageReference = firebaseStorage.getReference();

        //Asignacion de Button
        bt_continuar = findViewById(R.id.button_ContinuarImagenes);

        //Intancia de Objeto Usuario
        user = new Usuario();

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

        img1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mostrarMenuPopUp(v);
                return false;
            }
        });


        //Asignacion de referencia de Database Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();



        //Ejecucion del metodo
        MostrarDialogAyuda();

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
            },100);

        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(MainActivity2_ImagenesDePerfil.this, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            }
            else {
                ActivityCompat.requestPermissions(MainActivity2_ImagenesDePerfil.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION_CODE);
            }
        }else {

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CODE){
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){

            }else{
                Toast.makeText(this,"Habilitar los permisos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    public  void onLongSelectorImage(View v){

        //Obtener Id del Botón
        auxid = v.getId();
        aux = v.findViewById(auxid);

        aux.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mostrarMenuPopUp(v);
                return false;
            }
        });
    }

    private void crearUsuario() {

        //Traemos todos los valores de intent
        nombre_rec = getIntent().getStringExtra("nombre");
        correo_rec = getIntent().getStringExtra("correo");
        fecha_rec = getIntent().getStringExtra("fecha");
        genero_rec = getIntent().getStringExtra("genero");
        descripcion_rec = getIntent().getStringExtra("descripcion");
        muestrame_rec = getIntent().getStringExtra("muestrame");
        busco_rec = getIntent().getStringExtra("busco");

        //Asignamos al objeto user valores
        user.setUid(UUID.randomUUID().toString());
        user.setNombre(nombre_rec);
        user.setCorreo(correo_rec);
        user.setFecha_nacimiento(fecha_rec);
        user.setGenero(genero_rec);
        user.setDescripcion(descripcion_rec);
        user.setMuestrame(muestrame_rec);
        user.setBusco(busco_rec);
        user.setLatitud(0);
        user.setLongitid(0);

        //Incorporamos datos a la base de datos
        databaseReference.child("Persona").child(user.getUid()).setValue(user);

    }

    public void  selectionImageView(View view){

        //Obtener Id del Botón
        auxid = view.getId();
        aux = view.findViewById(auxid);
    }

    public void  Camera(){

        Intent camaraIntent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camaraIntent, 100);
    }


    public void AbrirGalleria(){

        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, PICK_IMAGE_GALERY);
    }

    public void borrarFoto(){


        aux.setImageResource(R.drawable.previo);
        cuentaFotos--;

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 100) {
                if(resultCode == MainActivity2_Profile.RESULT_OK && data!= null) {
                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                    aux.setImageBitmap(imageBitmap);
                    cuentaFotos++;
                }
            }
            if (requestCode == 102){
                if(resultCode == MainActivity2_Profile.RESULT_OK && data!= null){
                    Uri photo = data.getData();
                    aux.setImageURI(photo);
                    cuentaFotos++;
                }
            }
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

        if(cuentaFotos >= 2) {

            //Llamamos al metodo crearUsuario
            crearUsuario();

            //Pasar a otra activity
            Intent intent = new Intent(this, MainActivity2_Index.class);
            intent.putExtra("correo", correo_rec);
            startActivity(intent);
            finish();

        }
        else{
            //Mostrar Dialogo de ayuda al usuario
            MostrarDialogAyuda();
        }
    }


    public void mostrarMenuPopUp(View v){
        PopupMenu popup = new PopupMenu(MainActivity2_ImagenesDePerfil.this, v);
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) MainActivity2_ImagenesDePerfil.this);
        popup.inflate(R.menu.menu_seleccion_imagenes);
        popup.show();

        selectionImageView(v);
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.borrar_foto:
                Toast.makeText(this, R.string.borrar_foto, Toast.LENGTH_SHORT).show();
                borrarFoto();
                return true;
            case R.id.sacar_foto:
                Camera();
                return true;
            case R.id.galeria:
                AbrirGalleria();
                Toast.makeText(this, R.string.copiar_foto, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

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