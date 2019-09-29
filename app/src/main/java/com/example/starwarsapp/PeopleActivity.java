package com.example.starwarsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.starwarsapp.Models.Persona;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class PeopleActivity extends AppCompatActivity {

    private TextView nombre;
    private TextView altura;
    private TextView peso;
    private TextView colorCabello;
    private TextView colorPiel;
    private TextView colorOjos;
    private Button anterior;
    private Button siguiente;
    private int indice;
    public static final String urlApiPeople = "https://swapi.co/api/people/";
    public static ArrayList<Persona> people;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        nombre = findViewById(R.id.nombre);
        altura = findViewById(R.id.altura);
        peso = findViewById(R.id.peso);
        colorCabello = findViewById(R.id.colorCabello);
        colorPiel = findViewById(R.id.colorPiel);
        colorOjos = findViewById(R.id.colorOjos);

        anterior = findViewById(R.id.anterior);
        siguiente = findViewById(R.id.siguiente);
        indice = 1;
        getJson(urlApiPeople, indice);

    }
    public void getJson(final String urlApi, final int index){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(urlApi + index);
                    URL urlService = new URL(urlApi + index);
                    HttpURLConnection connection = (HttpURLConnection) urlService.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream response = connection.getInputStream();
                    if(connection.getResponseCode() == 200){
                        InputStreamReader responseBody = new InputStreamReader(response);
                        final JsonReader jsonRead = new JsonReader(responseBody);
                        jsonRead.beginObject();
                        final String keyName = jsonRead.nextName();
                        final String name = jsonRead.nextString();
                        final String keyHeight = jsonRead.nextName();
                        final String height = jsonRead.nextString();
                        final String keyMass = jsonRead.nextName();
                        final String mass = jsonRead.nextString();
                        final String keyHairColor = jsonRead.nextName();
                        final String hairColor = jsonRead.nextString();
                        final String keySkinColor = jsonRead.nextName();
                        final String skinColor = jsonRead.nextString();
                        final String keyEyeColor = jsonRead.nextName();
                        final String eyeColor = jsonRead.nextString();
                        new Persona(name, height, mass, hairColor, skinColor, eyeColor);

                        nombre.post(new Runnable() {
                            @Override
                            public void run() { nombre.setText(name);}});
                        altura.post(new Runnable() {
                            @Override
                            public void run() { altura.setText(height); }});
                        peso.post(new Runnable() {
                            @Override
                            public void run() { peso.setText(mass); }});
                        colorCabello.post(new Runnable() {
                            @Override
                            public void run() { colorCabello.setText(hairColor); }});
                        colorPiel.post(new Runnable() {
                            @Override
                            public void run() { colorPiel.setText(skinColor); }});
                        colorOjos.post(new Runnable() {
                            @Override
                            public void run() { colorOjos.setText(eyeColor); }});
                    }

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void siguienteAction(View v){
        if(indice == 87) indice = 1;
        else indice+=1;
        getJson(urlApiPeople, indice);

    }
    public void anteriorAction(View v){
        if(indice == 1) indice = 87;
        else indice-=1;
        getJson(urlApiPeople, indice);
    }

}
