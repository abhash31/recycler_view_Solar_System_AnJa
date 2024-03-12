package com.example.solarsystem;

public class PlanetRecyclerModel {
    String planetName;
    String planetAbout;

    public String getPlanetName() {
        StringBuilder sb = new StringBuilder();
        for(char ch:planetName.toCharArray()){
            if(ch!=' ') sb.append(ch);
            else break;
        }
        return sb.toString();
    }

    public PlanetRecyclerModel(String planetName) {
        this.planetName = planetName;
    }
    public PlanetRecyclerModel(String planetName, String planetAbout) {
        this.planetName = planetName;
        this.planetAbout = planetAbout;
    }
}
