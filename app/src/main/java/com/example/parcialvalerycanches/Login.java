package com.example.parcialvalerycanches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    private EditText usuario;
    private EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario = findViewById(R.id.etUsuario);
        contraseña = findViewById(R.id.etContraseña);
        Button iniciarsesion = findViewById(R.id.btnIniciar);
        TextView registrarse = findViewById(R.id.btnRegistrarse);

        iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSesion(v);
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrarse(v);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("USERNAME");
            String password = intent.getStringExtra("PASSWORD");
            if (username != null && password != null) {
                usuario.setText(username);
                contraseña.setText(password);
            }
        }
    }

    public void Registrarse(View view) {
        Intent i = new Intent(this, register.class);
        startActivity(i);
        finish();
    }

    public void IniciarSesion(View view) {
        String usuarios = usuario.getText().toString().trim();
        if (usuarios.length() > 10) {
            return;
        }

        String contrasenas = contraseña.getText().toString().trim();

        if (usuarios.isEmpty() || contrasenas.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show();
              } else if (!register.verificarCredenciales(usuarios, contrasenas)) {
                 Toast.makeText(this, "Usuario o contraseña incorrectos," +
                         "PORFAVOR REGISTRATE", Toast.LENGTH_SHORT).show();

        } else {
            Intent i = new Intent(this, play.class);
            startActivity(i);
            finish();
        }
    }
}
