package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private TextView olvidarContrasena ;
    private EditText user, passwd;
    private Button login, registrar;

    private final static String urlWHO = "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/advice-for-public";

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

        dialogCovid19();




        //Creacion de OnClickListener para olvidarContraseña
        //Objetico: Efectuar un paso a otra activty para recuperar contraseña
        olvidarContrasena.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void pasarBienvenida(View view){

        Intent intent = new Intent(this, MainActivity2_BienvenidaNormas.class);
        startActivity(intent);
        //Finalizar Activity
        finish();

    }

    public void pasarIndex(View view){

        Intent intent = new Intent(this, MainActivity2_Index.class);
        startActivity(intent);
        //Finalizar Activity
        finish();

    }

    public void dialogCovid19(){

        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_covid);
        dialog.setTitle("Covid 19 tips");



        /*
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.playAnimation();

        int num_random = (int)(Math.random()*4);
        switch (num_random){
            case 0: num_random = R.raw.hand_sanitizer; animationView.setAnimation(num_random); break;
            case 1: num_random = R.raw.social_distancing; animationView.setAnimation(num_random); break;
            case 2: num_random = R.raw.wash_your_hands; animationView.setAnimation(num_random); break;
            case 3: num_random = R.raw.wear_mask; animationView.setAnimation(num_random); break;
        }
         */

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