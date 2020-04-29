package com.content.vehicles_factory;

class PojazdMiejski extends Pojazd {

    PojazdMiejski(double volume, String name) {
        super(volume, name);
    }

    PojazdMiejski(double width, double length, double heigh, String name) {
        super(width, length, heigh, name);
    }

    @Override
    public String getTypeOfVehicle() {
        return "Pojazd miejski, czterokolowy";
    }
}
