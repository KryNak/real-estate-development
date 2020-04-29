package com.content.vehicles_factory;

import com.content.Content;

import java.util.Objects;

public abstract class Pojazd implements Content {

    private double volume;
    private String name;

    public Pojazd(double volume, String name) {
        this.volume = volume;
        this.name = name;
    }

    public Pojazd(double width, double length, double heigh, String name){
        volume = width * length * heigh;
        this.name = name;
    }

    abstract String getTypeOfVehicle();

    @Override
    public String toString() {
        return "Nazwa: " + getName() + ", Objetosc: " + getVolume();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojazd pojazd = (Pojazd) o;
        return Double.compare(pojazd.volume, volume) == 0 &&
                Objects.equals(name, pojazd.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, name);
    }


}
