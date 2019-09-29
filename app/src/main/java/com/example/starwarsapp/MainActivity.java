package com.example.starwarsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.starwarsapp.Models.Persona;
import com.example.starwarsapp.Models.Planeta;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Persona> people;
    public static ArrayList<Planeta> planets;

    public TextView test;
    public Button peopleBtn;
    public Button planetBtn;


    String nameResponse = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peopleBtn = findViewById(R.id.peopleBtn);
        planetBtn = findViewById(R.id.planetsBtn);
        people = new ArrayList<Persona>();
        planets = new ArrayList<Planeta>();


    }

    public static ArrayList<Persona> getPersonas(){
        return people;
    }
    public static ArrayList<Planeta> getPlanetas(){
        return planets;
    }


    public void onclickPeople(View v){
        Intent intent = new Intent(MainActivity.this,PeopleActivity.class);
        startActivity(intent);

    }
    public void onclickPlanet(View v){
        Intent intent = new Intent(MainActivity.this,PlanetActivity.class);
        startActivity(intent);

    }
}