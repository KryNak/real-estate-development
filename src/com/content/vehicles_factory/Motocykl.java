package com.content.vehicles_factory;

class Motocykl extends Pojazd {

    Motocykl(double volume, String name) {
        super(volume, name);
    }

    Motocykl(double width, double length, double heigh, String name) {
        super(width, length, heigh, name);
    }

    @Override
    public String getTypeOfVehicle() {
        return "Pojazd dwukolowy";
    }
}
