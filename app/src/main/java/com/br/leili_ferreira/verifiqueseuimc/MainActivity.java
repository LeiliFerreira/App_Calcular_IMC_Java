package com.br.leili_ferreira.verifiqueseuimc;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.view.View;
import android.app.AlertDialog;
import android.widget.TextView;
import java.text.DecimalFormat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText entrada_peso;
    private EditText entrada_altura;
    private ImageView imagem;
    private ImageView borracha;
    private ImageView Voltar;
    private Button calcular;
    private TextView IMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada_peso = findViewById(R.id.input_peso);
        entrada_altura = findViewById(R.id.input_altura);
        calcular = findViewById(R.id.botao_calcular);
        imagem = findViewById(R.id.muito_abaixo_do_peso);
        IMC = findViewById(R.id.imc);
        borracha = findViewById(R.id.icone_borracha);
        Voltar = findViewById(R.id.icone_voltar);

        imagem.setImageResource(R.drawable.muito_abaixo_do_peso);
        imagem.setVisibility(View.INVISIBLE);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularIMC();
            }
        });

        borracha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entrada_peso.setText(String.valueOf(""));
                entrada_altura.setText(String.valueOf(""));

                IMC.setText(String.valueOf(""));

                imagem.setImageResource(R.drawable.muito_abaixo_do_peso);
                imagem.setVisibility(View.INVISIBLE);
            }
        });

        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });
    }

    public void calcularIMC() {

        String peso = entrada_peso.getText().toString();
        String altura = entrada_altura.getText().toString();

        if (peso.isEmpty() || altura.isEmpty()) {
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(MainActivity.this);
            cxMsg.setMessage("Por favor informar peso e altura!");
            cxMsg.setNeutralButton("OK", null);
            cxMsg.show();
        } else {

            double Peso = Double.parseDouble(peso);
            double Altura = Double.parseDouble(altura);

            double calculo = Peso / (Altura * Altura);

            DecimalFormat df = new DecimalFormat("#.##");
            String calculo_formatado = df.format(calculo);
            IMC.setText(String.valueOf(calculo_formatado));

            if (calculo < 17) {
                imagem.setImageResource(R.drawable.muito_abaixo_do_peso);
                imagem.setVisibility(View.VISIBLE);
            } else if (calculo >= 17 && calculo < 18.50) {
                imagem.setImageResource(R.drawable.abaixo_do_peso);
                imagem.setVisibility(View.VISIBLE);
            } else if (calculo >= 18.50 && calculo < 24.99) {
                imagem.setImageResource(R.drawable.peso_normal);
                imagem.setVisibility(View.VISIBLE);
            } else if (calculo >= 24.99 && calculo < 30) {
                imagem.setImageResource(R.drawable.acima_do_peso);
                imagem.setVisibility(View.VISIBLE);
            } else if (calculo >= 30 && calculo < 35) {
                imagem.setImageResource(R.drawable.obesidade_grau_1);
                imagem.setVisibility(View.VISIBLE);
            } else if (calculo >= 35 && calculo <= 40) {
                imagem.setImageResource(R.drawable.obesidade_grau_2);
                imagem.setVisibility(View.VISIBLE);
            } else if (calculo > 40) {
                imagem.setImageResource(R.drawable.obesidade_grau_3);
                imagem.setVisibility(View.VISIBLE);
            }
        }
    }
}