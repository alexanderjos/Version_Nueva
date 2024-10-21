package com.example.sudoku;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.sudoku.databinding.FragmentSecondBinding;
import com.example.sudoku.databinding.FragmentTercerFragmentoBinding;

import java.util.HashSet;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TercerFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TercerFragmento extends Fragment {

    private FragmentTercerFragmentoBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TercerFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TercerFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static TercerFragmento newInstance(String param1, String param2) {
        TercerFragmento fragment = new TercerFragmento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTercerFragmentoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String  strCorreo = TercerFragmentoArgs.fromBundle(getArguments()).getEmailArg();
        binding.txtBienvenida.setText("Listo "+strCorreo+ " este es tu juego");
        String  dificultad = TercerFragmentoArgs.fromBundle(getArguments()).getDificultdadArg();

        int celdasbloqueadas = 0;
        if (dificultad.equals("Facil")){
            celdasbloqueadas = 40;
        } else if (dificultad.equals("Normal")) {
            celdasbloqueadas = 20;
        } else if (dificultad.equals("Dificil")) {
            celdasbloqueadas = 10;
        } else if (dificultad.equals("Muy Dificil")) {
            celdasbloqueadas = 5;
        }
        configurarGroidLayout(celdasbloqueadas);
    }


    private void configurarGroidLayout(int celdasBloqueadas){
        Random random = new Random();
        HashSet<Integer> indicesBloqueados = new HashSet<>();
        for(int i = 0 ; i <81 ;i++){
            EditText editText = new EditText(getContext());
            editText.setEms(1);
            editText.setTextSize(20);
            editText.setGravity(Gravity.CENTER);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);

            binding.gridSudoku.addView(editText);
        }
        while (indicesBloqueados.size()<celdasBloqueadas){
            int randomIndex = random.nextInt(81);
            if(indicesBloqueados.add(randomIndex)){
                EditText editText = (EditText) binding.gridSudoku.getChildAt(randomIndex);
                editText.setEnabled(false);
                editText.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));  // Cambiar color de fondo

            }
        }
    }
}