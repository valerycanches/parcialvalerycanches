package com.example.parcialvalerycanches;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class register extends AppCompatActivity {

    private EditText nombre;
    private EditText apellido;
    private EditText crearUsuario;
    private EditText crearContraseña;
    private Spinner genero;
    private Button registrarse;
    private Button volver;

    // Simulando una base de datos en memoria
    private static HashMap<String, String> usuariosRegistrados = new HashMap<>();

    public static void verificarCredenciales() {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.etNombre);
        apellido = findViewById(R.id.etApellido);
        crearUsuario = findViewById(R.id.etCrearUsuario);
        crearContraseña = findViewById(R.id.etCrearContraseña);
        genero = findViewById(R.id.genderSpinner);
        registrarse = findViewById(R.id.btnRegistrar);
        volver = findViewById(R.id.btnVolver);


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaRegistro()) {
                    registrarUsuario();
                }
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });
    }

    private boolean validaRegistro() {
        String firstName = nombre.getText().toString();
        String lastName = apellido.getText().toString();
        String username = crearUsuario.getText().toString();
        String password = crearContraseña.getText().toString();
        String gender = genero.getSelectedItem().toString();

        if (TextUtils.isEmpty(firstName)) {
            nombre.setError("El campo nombre no puede estar vacío");
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            apellido.setError("El campo apellido no puede estar vacío");
            return false;
        }

        if (TextUtils.isEmpty(username)) {
            crearUsuario.setError("El campo usuario no puede estar vacío");
            return false;
        }
        if (username.length() > 10) {
            crearUsuario.setError("El usuario no debe exceder los 10 caracteres");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            crearContraseña.setError("El campo contraseña no puede estar vacío");
            return false;
        }

        if (gender.equals("Seleccionar género")) {
            Toast.makeText(this, "Seleccione un género", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void registrarUsuario() {
        String username = crearUsuario.getText().toString();
        String password = crearContraseña.getText().toString();

        // Almacenar el nuevo usuario en la "base de datos" en memoria
        usuariosRegistrados.put(username, password);

        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("USERNAME",username);
        intent.putExtra("PASSWORD",password);
        startActivity(intent);
        finish();
    }

    private void volver() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    // Método estático para verificar credenciales
    public static boolean verificarCredenciales(String username, String password) {
        // Verificar si el usuario y la contraseña coinciden con los registros almacenados
        if (usuariosRegistrados.containsKey(username)) {
            String storedPassword = usuariosRegistrados.get(username);
            return storedPassword.equals(password);
        }
        return false;
    }

}