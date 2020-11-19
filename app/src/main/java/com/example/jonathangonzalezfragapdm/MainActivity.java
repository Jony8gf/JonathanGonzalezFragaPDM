package com.example.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView olvidarContrasena ;
    private EditText et_email, et_passwd;
    private String email, passwd;

    private final static String urlWHO = "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/advice-for-public";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        //Asignacion de componentes con la layout
        olvidarContrasena = findViewById(R.id.text_forgotenPass);

        //EditText
        et_email = findViewById(R.id.editText_UsuarioLogin);
        et_passwd = findViewById(R.id.editText_PasswordLogin);

        dialogCovid19();

    }


    public void pasarReinciarContrasena(View view){

        Intent intent = new Intent(this, MainActivity2_ReiniciarContrasena.class);
        startActivity(intent);
        //Finalizar Activity
        finish();

    }



    public void pasarBienvenida(View view){

        Intent intent = new Intent(this, MainActivity2_CreacionCita.class);
        startActivity(intent);
        //Finalizar Activity
        finish();

    }

    public void pasarIndex(View view){
        
        email = et_email.getText().toString();
        passwd = et_passwd.getText().toString();

        if (!email.isEmpty() && !passwd.isEmpty()){

            loginUser();

        }else{

            Toast.makeText(this, "La direccion de correo o la contraseña no es correcto", Toast.LENGTH_SHORT).show();
        }

    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, MainActivity2_Index.class);
                    startActivity(intent);
                    //Finalizar Activity
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "La direccion de correo o la contraseña no es correcto", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").
        if (UtilsNetwork.isOnline(this)){

            Toast.makeText(this, "No dispones de conexión a intenet",Toast.LENGTH_LONG).show();
        }else{

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