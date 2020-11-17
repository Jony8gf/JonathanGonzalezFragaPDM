package com.example.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2_ReiniciarContrasena extends AppCompatActivity {

    private EditText et_emailReset;
    private TextView tv_info;
    private String email , auxInfo;
    private Button bt_resetPasswd;
    private FirebaseAuth mAuth;
    View view;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__reiniciar_contrasena);

        //Asignacion de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //Asignacio de ProgressDialog
        mDialog = new ProgressDialog(this);

        //Asignacion de TextView
        tv_info = findViewById(R.id.textView_infoResetPasswd);
        //Asigacion de EditText
        et_emailReset = findViewById(R.id.editText_EmailResetPassword);
        //Asignacion de Button
        bt_resetPasswd = findViewById(R.id.button_reiniciarContraseña);


        auxInfo = tv_info.getText().toString();

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
            //Mostar Toast
            Toast.makeText(this, "Enviando e-mail", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


    public void RestablecerPassword(View view){

        email = et_emailReset.getText().toString();

        if(!email.isEmpty()){

            mDialog.setMessage("Espere un momento...");
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();
            ReiniciarPassword();

        }else{
            Toast.makeText(this, "Debes ingresar un email", Toast.LENGTH_SHORT).show();
        }

    }

    private void ReiniciarPassword() {

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



                if(task.isSuccessful()){

                    tv_info.setText(auxInfo +" "+email);
                    tv_info.setVisibility(view.VISIBLE);

                    et_emailReset.setVisibility(view.INVISIBLE);
                    bt_resetPasswd.setVisibility(view.INVISIBLE);

                }else{
                    Toast.makeText(MainActivity2_ReiniciarContrasena.this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
                }

                mDialog.dismiss();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

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