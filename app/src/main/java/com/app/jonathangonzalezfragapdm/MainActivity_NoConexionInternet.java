package com.app.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity_NoConexionInternet extends AppCompatActivity {

    private Button btnReintentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__no_conexion_internet);

        btnReintentar = findViewById(R.id.buttonReintentarInternet);


    }

    public void Reconectar(View view){

            if (UtilsNetwork.isOnline(this)){

                Intent intent = new Intent(this,  MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, R.string.help_internet,Toast.LENGTH_LONG).show();
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