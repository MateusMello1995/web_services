package com.example.web_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pegar os dados da tela
        final EditText cep = findViewById(R.id.etMain_cep);
        final TextView resposta = findViewById(R.id.etMain_resposta);

        final Button btnBuscarCep = findViewById(R.id.btnMain_buscarCep);
        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pegar dados na tela
                EditText cep = findViewById(R.id.etMain_cep);
                //transforma=los em texto
                String cepString = cep.getText().toString();

                //verificar se campo CEP está vazio
                if (cepString.isEmpty()){

                    String resultado = "Campo CEP não pode estar vazio!";
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                }else {
                    try {
                        CEP retorno = new HttpService(cep.getText().toString()).execute().get();
                        resposta.setText(retorno.toString());
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    } catch (ExecutionException e) {

                        e.printStackTrace();
                    }
                }

            }
        });


    }

    public void proximaTela (View view){

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
