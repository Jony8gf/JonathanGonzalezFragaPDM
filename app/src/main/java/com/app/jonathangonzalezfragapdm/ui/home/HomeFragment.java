package com.app.jonathangonzalezfragapdm.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.jonathangonzalezfragapdm.MainActivity2_CreacionCita;
import com.app.jonathangonzalezfragapdm.MainActivity2_Perfil;
import com.app.jonathangonzalezfragapdm.MainActivity_SlotMachine;
import com.app.jonathangonzalezfragapdm.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button creacionCita, conseguirFlechas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        creacionCita = view.findViewById(R.id.button_creacioncita);
        conseguirFlechas = view.findViewById(R.id.button_ConseguirFlechas);

        creacionCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity2_CreacionCita.class);
                startActivity(intent);

            }
        });


        conseguirFlechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity_SlotMachine.class);
                startActivity(intent);

            }
        });

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


            }
        });
        return view;
    }
}