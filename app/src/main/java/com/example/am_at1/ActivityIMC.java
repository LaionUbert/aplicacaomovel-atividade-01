package com.example.am_at1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityIMC extends AppCompatActivity {

    Button btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btHome=(Button)findViewById(R.id.btHome);
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityIMC.this, MainActivity.class);
                startActivity(intent);
            }
        });

        EditText inputPeso = findViewById(R.id.tinputPeso);
        EditText inputAltura = findViewById(R.id.tinputAltura);


        Button botaoEnviar = findViewById(R.id.btEnviar);
        Button botaoLimpar = findViewById(R.id.btLimpar);
        TextView saidaResultado = findViewById(R.id.tvIMCResultado);
        TextView classificacaoImc = findViewById(R.id.tvClassificacao);


        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float altura = Float.parseFloat(String.valueOf(inputAltura.getText()));
                float peso = Float.parseFloat(String.valueOf(inputPeso.getText()));

                if (altura == 0 || peso == 0) {
                    saidaResultado.setText(String.valueOf("Favor inserir informações"));
                }

                float imc = (peso * 10000) / (altura * altura);
                saidaResultado.setText(String.format("%.1f", imc));

                if (imc <= 18.4) {
                    classificacaoImc.setText(String.valueOf("Magreza"));
                } else if (imc <= 24.9) {
                    classificacaoImc.setText(String.valueOf("Normal"));
                } else if (imc <= 29.9) {
                    classificacaoImc.setText(String.valueOf("Sobrepeso"));
                } else if (imc >= 30) {
                    classificacaoImc.setText(String.valueOf("Obeso"));
                }
            }
        });

        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPeso.setText("");
                inputAltura.setText("");
                saidaResultado.setText("Resultado IMC");
                classificacaoImc.setText("Classificação");

            }
        });



    }
}