package com.app.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MainActivity2_Settings extends AppCompatActivity {

    String correo_rec;
    Usuario userAux;

    private EditText user, passwd;

    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__settings);

        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();


        user = findViewById(R.id.username);
        passwd = findViewById(R.id.password);

        correo_rec = getIntent().getStringExtra("correo");

        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot: snapshot.getChildren()){

                    userAux = datasnapshot.getValue(Usuario.class);
                    assert userAux != null;

                    if(correo_rec.equals(userAux.getCorreo())){

                         Toast.makeText(MainActivity2_Settings.this, userAux.getNombre(), Toast.LENGTH_LONG).show();
                         Toast.makeText(MainActivity2_Settings.this, userAux.getCorreo(), Toast.LENGTH_LONG).show();
                         Toast.makeText(MainActivity2_Settings.this, userAux.getUid(), Toast.LENGTH_LONG).show();

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(this, correo_rec, Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(MainActivity2_Settings.this, MainActivity2_Profile.class);
            intent.putExtra("correo", correo_rec);
            startActivity(intent);
            finish();
        }
        if(id == R.id.menu_edit){
            Intent intent = new Intent(MainActivity2_Settings.this, MainActivity2_EditProfile.class);
            intent.putExtra("correo", correo_rec);
            startActivity(intent);
            finish();
        }
        if(id == R.id.menu_settings){

        }

        return true;
    }

    public void borrarCuenta(View view){
        onCreateDialog();
    }

    private void deleteAccount() {

        final FirebaseUser currentUser = mAuth.getCurrentUser();
        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    databaseReference.child("Persona").child(userAux.getUid()).setValue(null);
                    startActivity(new Intent(MainActivity2_Settings.this, MainActivity.class));
                    finish();

                } else {
                    Toast.makeText(MainActivity2_Settings.this, R.string.correo_no, Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void onCreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                // Add action buttons
                .setPositiveButton(R.string.eliminar_cuenta, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        deleteAccount();

                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).show();
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