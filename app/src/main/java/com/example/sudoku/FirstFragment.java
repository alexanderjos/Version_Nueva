package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sudoku.databinding.FragmentFirstBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private StringBuilder codigoIngresado = new StringBuilder();
    private String correoIngresado;
    Map<String, String> usuarios = new HashMap<>();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        usuarios.put("hola@gmail.com","1234");
        usuarios.put("olam@gmail.com","1594");
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asignarNumeros();

        binding.btn1.setOnClickListener(v->agregarDigito(binding.btn1.getText().toString()));
        binding.btn2.setOnClickListener(v->agregarDigito(binding.btn2.getText().toString()));
        binding.btn3.setOnClickListener(v->agregarDigito(binding.btn3.getText().toString()));
        binding.btn4.setOnClickListener(v->agregarDigito(binding.btn4.getText().toString()));
        binding.btn5.setOnClickListener(v->agregarDigito(binding.btn5.getText().toString()));
        binding.btn6.setOnClickListener(v->agregarDigito(binding.btn6.getText().toString()));
        binding.btn7.setOnClickListener(v->agregarDigito(binding.btn7.getText().toString()));
        binding.btn8.setOnClickListener(v->agregarDigito(binding.btn8.getText().toString()));
        binding.btn9.setOnClickListener(v->agregarDigito(binding.btn9.getText().toString()));
        binding.btn10.setOnClickListener(v->agregarDigito(binding.btn10.getText().toString()));
    }



    private void asignarNumeros(){
        List<Integer> numeros = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        Collections.shuffle(numeros);
        binding.btn1.setText(String.valueOf(numeros.get(0)));
        binding.btn2.setText(String.valueOf(numeros.get(1)));
        binding.btn3.setText(String.valueOf(numeros.get(2)));
        binding.btn4.setText(String.valueOf(numeros.get(3)));
        binding.btn5.setText(String.valueOf(numeros.get(4)));
        binding.btn6.setText(String.valueOf(numeros.get(5)));
        binding.btn7.setText(String.valueOf(numeros.get(6)));
        binding.btn8.setText(String.valueOf(numeros.get(7)));
        binding.btn9.setText(String.valueOf(numeros.get(8)));
        binding.btn10.setText(String.valueOf(numeros.get(9)));
    }
    private void agregarDigito(String digito){
        codigoIngresado.append(digito);
        if(codigoIngresado.length()==4){
            String codigoCorrecto;
            correoIngresado = binding.edtCorreo.getText().toString();
            codigoCorrecto = usuarios.get(correoIngresado);
            if(codigoIngresado.toString().equals(codigoCorrecto)){
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment2 action =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment2(correoIngresado);
                NavHostFragment.findNavController(FirstFragment.this).navigate(action);
            }else{
                Toast.makeText(getActivity(), "Incorrecto", Toast.LENGTH_SHORT).show();
                codigoIngresado.setLength(0);
                asignarNumeros();
            }
        }


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}