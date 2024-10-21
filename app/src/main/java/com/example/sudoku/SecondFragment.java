package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sudoku.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String strCorreo = SecondFragmentArgs.fromBundle(getArguments()).getEmailArg();
        binding.txtemail.setText("Bienvenido "+strCorreo);
        binding.btnRepartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int select = binding.rgSeleccion.getCheckedRadioButtonId();
                RadioButton selectRadio = binding.getRoot().findViewById(select);
                String textoSelecionado = selectRadio.getText().toString();
                SecondFragmentDirections.ActionSecondFragmentToTercerFragment2  action =
                        SecondFragmentDirections.actionSecondFragmentToTercerFragment2(strCorreo,textoSelecionado);
                NavHostFragment.findNavController(SecondFragment.this).navigate(action);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}