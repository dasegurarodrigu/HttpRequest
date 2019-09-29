package com.example.starwarsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.starwarsapp.Models.Persona;
import com.example.starwarsapp.Models.Planeta;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class PlanetActivity extends AppCompatActivity {
    private TextView nombre;
    private TextView periodoRotacion;
    private TextView periodoOrbital;
    private TextView diametro;
    private TextView clima;
    private TextView gravedad;
    private Button anterior;
    private Button siguiente;
    private int indice;

    public static final String urlApiPlanet = "https://swapi.co/api/planets/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        nombre = findViewById(R.id.nombre);
        periodoRotacion = findViewById(R.id.periodoRotacion);
        periodoOrbital = findViewById(R.id.periodoOrbital);
        diametro = findViewById(R.id.diametro);
        clima = findViewById(R.id.clima);
        gravedad = findViewById(R.id.gravedad);

        anterior = findViewById(R.id.anterior);
        siguiente = findViewById(R.id.siguiente);
        indice = 1;
        getJson(urlApiPlanet, indice);
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
                        final String keyRotationPeriod = jsonRead.nextName();
                        final String rotationPeriod = jsonRead.nextString();
                        final String keyOrbitalPeriod = jsonRead.nextName();
                        final String orbitalPeriod = jsonRead.nextString();
                        final String keyDiameter = jsonRead.nextName();
                        final String diameter = jsonRead.nextString();
                        final String keyClimate = jsonRead.nextName();
                        final String climate = jsonRead.nextString();
                        final String keyGravity = jsonRead.nextName();
                        final String gravity = jsonRead.nextString();
                        new Planeta(name, rotationPeriod, orbitalPeriod, diameter, climate, gravity);

                        nombre.post(new Runnable() {
                            @Override
                            public void run() { nombre.setText(name);}});
                        periodoRotacion.post(new Runnable() {
                            @Override
                            public void run() { periodoRotacion.setText(rotationPeriod); }});
                        periodoOrbital.post(new Runnable() {
                            @Override
                            public void run() { periodoOrbital.setText(orbitalPeriod); }});
                        diametro.post(new Runnable() {
                            @Override
                            public void run() { diametro.setText(diameter); }});
                        clima.post(new Runnable() {
                            @Override
                            public void run() { clima.setText(climate); }});
                        gravedad.post(new Runnable() {
                            @Override
                            public void run() { gravedad.setText(gravity); }});
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
        if(indice == 61) indice = 1;
        else indice+=1;
        getJson(urlApiPlanet, indice);

    }
    public void anteriorAction(View v){
        if(indice == 1) indice = 61;
        else indice-=1;
        getJson(urlApiPlanet, indice);
    }
}
