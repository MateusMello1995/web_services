package com.example.web_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView texto = findViewById(R.id.nome);
        String retorno = null;

        try {
            retorno = new HttpRequisition().execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(retorno != null){
            texto.setText(retorno);
        }

    }
}
