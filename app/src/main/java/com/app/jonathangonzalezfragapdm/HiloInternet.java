package com.app.jonathangonzalezfragapdm;

import android.content.Context;
import android.content.Intent;

public class HiloInternet extends Thread {

    Context context;

    public HiloInternet() {
    }

    public HiloInternet(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        super.run();

        while (true){

            if(UtilsNetwork.isOnline(this.context)){

            }else{

                Intent intent = new Intent(context, MainActivity_NoConexionInternet.class);
                context.startActivity(intent);

            }
        }
    }

}
