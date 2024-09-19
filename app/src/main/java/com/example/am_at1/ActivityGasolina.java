package com.example.am_at1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityGasolina extends AppCompatActivity {

    Button btHome;
    EditText inputPrecoGasolina, inputPrecoEtanol;
    RadioGroup radioGroup;
    RadioButton rbGasolina, rbEtanol;
    TextView relacaoPreco, melhorCombust;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gasolina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        RadioGroup radioGroup = findViewById(R.id.rGOpcoes);
        RadioButton rbGasolina = findViewById(R.id.rbGasolina);
        RadioButton rbEtanol = findViewById(R.id.rbEtanol);

        inputPrecoGasolina = findViewById(R.id.tinputPrecoGasolina);
        inputPrecoEtanol = findViewById(R.id.tinputPrecoEtanol);

        TextView relacaoPreco = findViewById(R.id.tvRelacaoPreco);
        TextView melhorCombustivel = findViewById(R.id.tvMelhorPreco);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calcularRelacao();
            }
            private void calcularRelacao(){
                String gasolinaString = inputPrecoGasolina.getText().toString();
                String etanolString = inputPrecoEtanol.getText().toString();

                double gasolina = Double.parseDouble(gasolinaString);
                double etanol = Double.parseDouble(etanolString);

                double relacaoIdeal = 0.7;

                double relacaoAtualEtanol = etanol / gasolina;
                double relacaoAtualGasolina = gasolina / etanol;

                if (rbEtanol.isChecked()){
                    if (relacaoAtualEtanol <= relacaoIdeal) {
                        melhorCombustivel.setText("Abasteça com Etanol");
                    } else {
                        melhorCombustivel.setText("Abasteça com Gasolina");
                    }

                    relacaoPreco.setText("Diferença de %" + String.format("%.2f", (relacaoAtualEtanol * 100)));

                } else if (rbGasolina.isChecked()){
                    if (relacaoAtualGasolina >= relacaoIdeal) {
                        melhorCombustivel.setText("Abasteça com Etanol");
                    } else {
                        melhorCombustivel.setText("Abasteça com Gasolina");
                    }

                    relacaoPreco.setText("Diferença de %" + String.format("%.2f", (relacaoAtualGasolina * 100)));

                };


            };
        });





        Button botaoLimpar = findViewById(R.id.btLimpar);
        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPrecoGasolina.setText("");
                inputPrecoEtanol.setText("");
                relacaoPreco.setText("Relação do Preço");
                melhorCombustivel.setText("Melhor Combustível");
            }
        });


        btHome=(Button)findViewById(R.id.btHome);
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityGasolina.this, MainActivity.class);
                startActivity(intent);
            }
        });







    }
}