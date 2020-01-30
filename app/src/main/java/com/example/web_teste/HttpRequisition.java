package com.example.web_teste;

import android.os.AsyncTask;
import android.os.Build;
import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequisition extends AsyncTask<Void, Void, String> {

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Create URL
            URL myApiEndpoint = new URL("https://app-subsea-homolog.wideds.com.br/wp-json/wp/v2/posts");

            //autenticação
            String userCredentials = "72902175000:123456";
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

            // Create connection
            HttpsURLConnection myConnection = (HttpsURLConnection) myApiEndpoint.openConnection();

            // Inserir cabeçalho User-Agent e outros
            myConnection.setRequestProperty("Authorization", basicAuth);
            myConnection.setRequestMethod("GET");
            myConnection.setRequestProperty("Content-Type", "application/json");

            if (myConnection.getResponseCode() == 200) {

                Log.i("API", "Conectou com sucesso");
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject(); // Start processing the JSON object
                while (jsonReader.hasNext()) { // Loop through all keys
                    String key = jsonReader.nextName(); // Fetch the next key
                    if (key.equals("categoria")) { // Check if desired key
                        // Fetch the value as a String
                        String value = jsonReader.nextString();

                        // Fechar as conexões
                        jsonReader.close();
                        myConnection.disconnect();

                        // fazer algo com o value
                        return value;
                        // break; // Break out of the loop
                    } else {
                        jsonReader.skipValue(); // Skip values of other keys
                    }
                }
            } else {
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
