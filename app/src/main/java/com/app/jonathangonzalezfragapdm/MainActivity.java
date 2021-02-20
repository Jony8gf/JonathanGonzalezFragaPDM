package com.app.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView olvidarContrasena ;
    private EditText et_email, et_passwd;
    private String email, passwd;

    private ImageButton btnVolumen;
    private MediaPlayer mpMusica;
    private boolean volumen = true;

    private final static String urlWHO = "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/advice-for-public";

    private FirebaseAuth mAuth;

    //private HiloInternet hiloInternet = new HiloInternet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        mpMusica = MediaPlayer.create(this, R.raw.musica_bienvenida);
        mpMusica.start();
        mpMusica.setLooping(true);

        //Asignacion de componentes con la layout
        olvidarContrasena = findViewById(R.id.text_forgotenPass);

        //EditText
        et_email = findViewById(R.id.editText_UsuarioLogin);
        et_passwd = findViewById(R.id.editText_PasswordLogin);

        //ImageView
        btnVolumen = findViewById(R.id.imageButtonVolumen);

        dialogCovid19();

    }

    public void VolumenOnOff(View view){

        if(volumen){
            mpMusica.pause();
            btnVolumen.setImageResource(R.drawable.ic_volumen_off_foreground);
            //mpMusica.setVolume(0,0);
            volumen=false;
        }else{
            mpMusica.start();
            btnVolumen.setImageResource(R.drawable.ic_volumen_on_foreground);
            //mpMusica.setVolume(100,100);
            volumen=true;
        }

    }


    public void pasarReinciarContrasena(View view){

        mpMusica.stop();
        mpMusica.release();

        Intent intent = new Intent(this, MainActivity2_ReiniciarContrasena.class);
        //Intent intent = new Intent(this, MainActivity2_Profile.class);
        //Intent intent = new Intent(this, MainActivity2_ImagenesDePerfil.class);
        //Intent intent = new Intent(this, MainActivity2_DatosPersonales.class);
        startActivity(intent);
        //Finalizar Activity
        finish();

    }

    public void pasarBienvenida(View view){

        mpMusica.stop();
        mpMusica.release();

        Intent intent = new Intent(this, MainActivity2_BienvenidaNormas.class);
        //Intent intent = new Intent(this, MainActivity2_BienvenidaNormas.class);
        startActivity(intent);
        //Finalizar Activity
        finish();

    }


    public void pasarIndex(View view){
        
        email = et_email.getText().toString();
        passwd = et_passwd.getText().toString();

        if (!email.isEmpty() && !passwd.isEmpty()){

            mpMusica.stop();
            mpMusica.release();
            loginUser();

        }else{

            Toast.makeText(this, R.string.correo_no, Toast.LENGTH_SHORT).show();
        }

    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, MainActivity2_Index.class);
                    intent.putExtra("correo", email);
                    startActivity(intent);
                    //Finalizar Activity
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.correo_no, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void dialogCovid19(){

        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_covid);
        dialog.setTitle("Covid 19 tips");

        Button btn = findViewById(R.id.btnSaberCovid);

        dialog.show();


        CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {

                }

            public void onFinish() {
                dialog.dismiss();
            }
        }.start();

    }

    public void saberMas(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlWHO));
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        try {
            mpMusica.start();
            mpMusica.setLooping(true);
        } catch (IllegalStateException e) {
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").
        //View view = new View(getApplicationContext());
        mpMusica.start();


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

        try {
            mpMusica.pause();
            mpMusica.setLooping(false);
        }catch (IllegalStateException e){

        }
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