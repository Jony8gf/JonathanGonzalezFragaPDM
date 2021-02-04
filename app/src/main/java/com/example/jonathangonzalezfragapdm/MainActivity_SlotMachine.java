package com.example.jonathangonzalezfragapdm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Random;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class MainActivity_SlotMachine extends AppCompatActivity {

    private LottieAnimationView lottieAnim;
    private DatabaseReference databaseReference;

    //Creacion de Objeto InterstitialAd
    private InterstitialAd mInterstitialAd;
    private RewardedAd rewardedAd;

    //Cracion de Objeto AdView (Google Admod)
    private AdView mAdView;

    private int walletDiamond;
    private String auxDiamond;

    private ImageView img1, img2, img3;
    private TextView tvDiamantes;
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
        tvDiamantes = (TextView) findViewById(R.id.textView_Diamantes);


        lottieAnim = (LottieAnimationView) findViewById(R.id.animation_win_slotMachine);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        diamantesDisponibles();


        //Preparacion de y asignacion de id
        //Asignacion de Id a Ads y Asignacion a InterstitialAd
        MobileAds.initialize(this,
                "ca-app-pub-8043381776244583~7687473041");

        // Use an activity context to get the rewarded video instance.
        rewardedAd = new RewardedAd(this,
                "ca-app-pub-8043381776244583/6578227890");

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        //Preparacion Adview y asignacion de id
        mAdView = findViewById(R.id.adView);
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-8043381776244583/9427752515");

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //databaseReference.child("Persona").push().setValue(user);
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //auxDiamond = snapshot.child("MMquV8nwYwIMwoV8BZH").child("correo").getValue().toString();
                    //tvDiamantes.setText(auxDiamond);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public  void diamantesDisponibles(){

        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    databaseReference.child("Persona").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Usuario user = snapshot.getValue(Usuario.class);
                            auxDiamond = snapshot.child("correo").getValue().toString();
                            //walletDiamond = Integer.parseInt(auxDiamond);
                            tvDiamantes.setText("0");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void tiradaSlot(View v){

        runWheells();

        loadRewardedVideoAd();

        if(walletDiamond > 10){



        }else{
            if (isStarted) {

                CountDownTimer ct1 = new CountDownTimer(7000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {

                        stopWheells();
                        walletDiamond=walletDiamond-10;

                    }

                }.start();
            }
        }
    }



    public void runWheells(){


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
        }, 190, randomLong(150, 400));

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
        }, 190, randomLong(150, 400));

        wheel3.start();

        isStarted = true;
    }

    public void stopWheells(){

        wheel1.stopWheel();
        wheel2.stopWheel();
        wheel3.stopWheel();


        if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
            switch (wheel1.currentIndex){

                case  0:
                    dialogWinDiamonds(1);
                    break;
                case  1:
                    dialogWinDiamonds(2);
                    break;
                case  2:
                    dialogWinDiamonds(3);
                    break;
                case  3:
                     dialogWinDiamonds(5);
                    break;
                case  4:
                    dialogWinArrows();
                    break;
                default:
                    break;
            }

        } else {
            dialogNotWinArrows();
        }

        isStarted = false;

    }


    public void dialogWinArrows(){

        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_win_slot_machine);
        dialog.setTitle("Get Arrows");
        dialog.show();
    }


    public void dialogNotWinArrows(){

        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_loss_slot_machine);
        dialog.setTitle("Get Arrows");
        dialog.show();
    }

    public void dialogWinDiamonds(int diamonds){

        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_diamond_slot_machine);
        dialog.setTitle("Get Arrows");
        TextView tvDiamonds = (TextView)findViewById(R.id.textView_diamondSlot);
        String auxDiamond1, auxDiamond2;
        auxDiamond1 = getString(R.string.slot_diamond1);
        auxDiamond2 = getString(R.string.slot_diamond2);


        switch (diamonds){
            case 1:

                walletDiamond = walletDiamond + 1;
                tvDiamonds.setText(auxDiamond1 + diamonds + auxDiamond2 );
                break;
            case 2:

                walletDiamond = walletDiamond + 2;
                tvDiamonds.setText(auxDiamond1 + diamonds + auxDiamond2 );
                break;
            case 3:

                walletDiamond = walletDiamond + 3;
                tvDiamonds.setText(auxDiamond1 + diamonds + auxDiamond2 );
                break;
            case 5:

                walletDiamond = walletDiamond + 5;
                tvDiamonds.setText(auxDiamond1 + diamonds + auxDiamond2 );
                break;
            default:
                break;
        }

        dialog.show();

        //Modificar WalletDiamondFirebase

    }

    private void loadRewardedVideoAd() {
        if (rewardedAd.isLoaded()) {
            Activity activityContext = MainActivity_SlotMachine.this;
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {
                    // Ad opened.
                }

                @Override
                public void onRewardedAdClosed() {
                    // Ad closed.
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {
                    // User earned reward.
                }

                @Override
                public void onRewardedAdFailedToShow(AdError adError) {
                    // Ad failed to display.
                }
            };
            rewardedAd.show(activityContext, adCallback);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Pasar de una Activity a otra
        Intent intent = new Intent(this, MainActivity2_Perfil.class);
        startActivity(intent);
        //Finalizar Activity
        finish();
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