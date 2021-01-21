package com.example.jonathangonzalezfragapdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity_SlotMachine extends AppCompatActivity {

    private ImageView img1, img2, img3;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private boolean isStarted;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__slot_machine);

        //Asignacion de componentes con layout
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        btn = (Button) findViewById(R.id.btnPlay);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                runWhells();

                if (isStarted) {

                    CountDownTimer ct1 = new CountDownTimer(5000, 1000) {

                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {

                            wheel1.stopWheel();
                            wheel2.stopWheel();
                            wheel3.stopWheel();

                            if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                                //Iguales
                            } else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex
                                    || wheel1.currentIndex == wheel3.currentIndex) {
                                //F
                            } else {
                                //ffffffffffffffffff
                            }

                            isStarted = false;

                        }

                    }.start();

                }
            }
        });
    }


    public void runWhells(){


        wheel1 = new Wheel(new Wheel.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        img1.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(0, 200));

        wheel1.start();

        wheel2 = new Wheel(new Wheel.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        img2.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(150, 400));

        wheel2.start();

        wheel3 = new Wheel(new Wheel.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        img3.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(150, 400));

        wheel3.start();

        isStarted = true;
    }

}