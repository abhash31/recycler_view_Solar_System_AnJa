package com.example.solarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectRecyclerItems {
    ArrayList<PlanetRecyclerModel> planetList = new ArrayList<>();
    PlanetRecyclerAdapter adapter;
    Toolbar toolbar;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Solar System");
        toolbar.setSubtitle("the 8 planets");

        //recyclerview
        RecyclerView planetRecyclerView = findViewById(R.id.planetRecyclerView);
        planetRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        planetList.add(new PlanetRecyclerModel("Mercury (4,879 km)", "the smallest planet in the Solar System"));
        planetList.add(new PlanetRecyclerModel("Venus (12,104 km)", "the hottest planet in the Solar System"));
        planetList.add(new PlanetRecyclerModel("Earth (12,742 km)", "the only place in the Solar System where life is known to exist"));
        planetList.add(new PlanetRecyclerModel("Mars (6,779 km)", "there have been more missions to Mars than any other planet"));
        planetList.add(new PlanetRecyclerModel("Jupiter (139,822 km)", "jupiter has more than double the mass of all the other planets combined"));
        planetList.add(new PlanetRecyclerModel("Saturn (116,464 km)", "saturn has more moons than any other planet in the Solar System"));
        planetList.add(new PlanetRecyclerModel("Uranus (50,724 km)", "uranus has only been visited by a single spacecraft, Voyager 2"));
        planetList.add(new PlanetRecyclerModel("Neptune (49,244 km)", "it takes like more than 4 hours for light to reach Neptune from the Suns"));
        planetList.add(new PlanetRecyclerModel("Pluto (2,302 km)", "dwarf since 2006"));

        adapter = new PlanetRecyclerAdapter(this, planetList, this);
        planetRecyclerView.setAdapter(adapter);

        //search option
        searchView = findViewById(R.id.searchBar);
        searchView.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }
        });
    }

    //search filtered list
    private void filter(String s) {
        ArrayList<PlanetRecyclerModel> filteredList = new ArrayList<>();
        for(PlanetRecyclerModel item:planetList){
            if(item.getPlanetName().toLowerCase().startsWith(s.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterAdapter(filteredList);
    }

    @Override
    public void onItemClick(PlanetRecyclerModel planetRecyclerModel) {
        Toast.makeText(this, planetRecyclerModel.getPlanetName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.searchoption) {
            if(searchView.getVisibility()==View.VISIBLE){
                searchView.setVisibility(View.GONE);
            } else searchView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Search Triggered!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

//    public void testOnclick(MenuItem item) {
//        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
//    }
}