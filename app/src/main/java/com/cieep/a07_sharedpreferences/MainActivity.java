package com.cieep.a07_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnGuardar, btnLimpiar;
    private ImageButton btnEliminaNombre, btnEliminaEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asociar a la variable el fichero xml (usuario.xml)
        sharedPreferences = getSharedPreferences(Constantes.USER, MODE_PRIVATE);
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLimpiar = findViewById(R.id.btnLimipar);
        btnEliminaEdad = findViewById(R.id.btnEliminaEdad);
        btnEliminaNombre = findViewById(R.id.btnEliminaNombre);

        inicializarValores();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EScribrir -> Editor de SharedPreference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Inserto elementos
                editor.putString(Constantes.NOMBRE, txtNombre.getText().toString());
                editor.putInt(Constantes.EDAD, Integer.parseInt(txtEdad.getText().toString()));
                // Confirmo cambios -> comit (secuencial al programa)/ apply (asincrono con el programa)
                editor.apply();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });

        btnEliminaNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Constantes.NOMBRE);
                editor.apply();
            }
        });

        btnEliminaEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Constantes.EDAD);
                editor.apply();
            }
        });
    }

    private void inicializarValores() {
        String nombre = sharedPreferences.getString(Constantes.NOMBRE, null);
        int edad = sharedPreferences.getInt(Constantes.EDAD, -1);

        if (nombre != null) {
            txtNombre.setText(nombre);
        }
        if (edad != -1) {
            txtEdad.setText(String.valueOf(edad));
        }
    }
}