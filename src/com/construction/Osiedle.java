package com.construction;

import java.util.ArrayList;
import java.util.Objects;

public class Osiedle {

    private String name;
    private static int nextId = 0;
    private int id = nextId++;

    private ArrayList<Blok> appartamentBuildingList;
    private ArrayList<MiejsceParkingowe> garageList;

    public Osiedle(String name){
        this.name = name;
        appartamentBuildingList = new ArrayList<>();
        garageList = new ArrayList<>();
    }

    public void addAppartamentBuilding(Blok appartamentBuilding){

        appartamentBuildingList.add(appartamentBuilding);

    }

    public void addGarage(MiejsceParkingowe garage){

        garageList.add(garage);

    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osiedle osiedle = (Osiedle) o;
        return id == osiedle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Osiedle: " + "" + getName();
    }
}
