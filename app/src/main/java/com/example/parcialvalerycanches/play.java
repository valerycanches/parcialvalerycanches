package com.example.parcialvalerycanches;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class play extends AppCompatActivity implements View.OnClickListener {

    private boolean playerone = true;
    private String valorx = "X";
    private int x = 0;
    private int o = 0;
    private String[][] matrizTR = new String[3][3];
    private TextView tvX;
    private TextView tvO;
    private LinearLayout ly1, ly2, ly3, ly4, ly5, ly6, ly7, ly8, ly9;
    private Map<Integer, int[]> idToCoordinatesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ly1 = findViewById(R.id.ly1);
        ly2 = findViewById(R.id.ly2);
        ly3 = findViewById(R.id.ly3);
        ly4 = findViewById(R.id.ly4);
        ly5 = findViewById(R.id.ly5);
        ly6 = findViewById(R.id.ly6);
        ly7 = findViewById(R.id.ly7);
        ly8 = findViewById(R.id.ly8);
        ly9 = findViewById(R.id.ly9);

        tvX = findViewById(R.id.tvx);
        tvO = findViewById(R.id.tvo);

        ly1.setOnClickListener(this);
        ly2.setOnClickListener(this);
        ly3.setOnClickListener(this);
        ly4.setOnClickListener(this);
        ly5.setOnClickListener(this);
        ly6.setOnClickListener(this);
        ly7.setOnClickListener(this);
        ly8.setOnClickListener(this);
        ly9.setOnClickListener(this);

        // Initialize the map
        idToCoordinatesMap = new HashMap<>();
        idToCoordinatesMap.put(R.id.ly1, new int[]{0, 0});
        idToCoordinatesMap.put(R.id.ly2, new int[]{0, 1});
        idToCoordinatesMap.put(R.id.ly3, new int[]{0, 2});
        idToCoordinatesMap.put(R.id.ly4, new int[]{1, 0});
        idToCoordinatesMap.put(R.id.ly5, new int[]{1, 1});
        idToCoordinatesMap.put(R.id.ly6, new int[]{1, 2});
        idToCoordinatesMap.put(R.id.ly7, new int[]{2, 0});
        idToCoordinatesMap.put(R.id.ly8, new int[]{2, 1});
        idToCoordinatesMap.put(R.id.ly9, new int[]{2, 2});
    }


    public void onClick(View v) {
        int[] coordinates = idToCoordinatesMap.get(v.getId());
        if (coordinates != null) {
            hacerTarea(v, coordinates[0], coordinates[1], valorx);
        }
    }

    public void Reiniciar(View view) {
        // Reinicia las celdas del tablero a su estado inicial
        ly1.setBackgroundResource(R.drawable.marcodos);
        ly1.setEnabled(true);
        ly2.setBackgroundResource(R.drawable.marcodos);
        ly2.setEnabled(true);
        ly3.setBackgroundResource(R.drawable.marcodos);
        ly3.setEnabled(true);
        ly4.setBackgroundResource(R.drawable.marcodos);
        ly4.setEnabled(true);
        ly5.setBackgroundResource(R.drawable.marcodos);
        ly5.setEnabled(true);
        ly6.setBackgroundResource(R.drawable.marcodos);
        ly6.setEnabled(true);
        ly7.setBackgroundResource(R.drawable.marcodos);
        ly7.setEnabled(true);
        ly8.setBackgroundResource(R.drawable.marcodos);
        ly8.setEnabled(true);
        ly9.setBackgroundResource(R.drawable.marcodos);
        ly9.setEnabled(true);
        matrizTR = new String[3][3];

        if (!playerone) {
            valorx = "O";
            Toast.makeText(this, "COMIENZA O", Toast.LENGTH_SHORT).show();
        } else {
            valorx = "X";
            Toast.makeText(this, "COMIENZA X", Toast.LENGTH_SHORT).show();
        }
    }

    private void hacerTarea(View v, int fila, int col, String valorX) {
        if (valorX.equals("X")) {
            v.setBackgroundResource(R.drawable.equis);
            this.valorx = "O";
        } else {
            v.setBackgroundResource(R.drawable.circulo);
            this.valorx = "X";
        }

        matrizTR[fila][col] = valorX;

        verificarGanador(valorX);
    }

    private void verificarGanador(String valorX) {
        boolean ganador = false;
        // Lógica para verificar si hay un ganador
        for (int i = 0; i < 3; i++) {
            if (matrizTR[i][0] != null && matrizTR[i][0].equals(matrizTR[i][1]) && matrizTR[i][1].equals(matrizTR[i][2])) {
                ganador = true;
                break;
            }
            if (matrizTR[0][i] != null && matrizTR[0][i].equals(matrizTR[1][i]) && matrizTR[1][i].equals(matrizTR[2][i])) {
                ganador = true;
                break;
            }
        }
        if (matrizTR[0][0] != null && matrizTR[0][0].equals(matrizTR[1][1]) && matrizTR[1][1].equals(matrizTR[2][2])) {
            ganador = true;
        }
        if (matrizTR[0][2] != null && matrizTR[0][2].equals(matrizTR[1][1]) && matrizTR[1][1].equals(matrizTR[2][0])) {
            ganador = true;
        }

        if (ganador) {
            if (valorX.equals("X")) {
                Toast.makeText(this, "¡GANASTE! X", Toast.LENGTH_SHORT).show();
                x++;
                tvX.setText("X: " + x);
            } else {
                Toast.makeText(this, "¡GANASTE! O", Toast.LENGTH_SHORT).show();
                o++;
                tvO.setText("O: " + o);
            }

            ly1.setEnabled(false);
            ly2.setEnabled(false);
            ly3.setEnabled(false);
            ly4.setEnabled(false);
            ly5.setEnabled(false);
            ly6.setEnabled(false);
            ly7.setEnabled(false);
            ly8.setEnabled(false);
            ly9.setEnabled(false);

            playerone = !playerone;
        }
    }
    public void Salir(View view) {
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}