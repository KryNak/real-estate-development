package com.content.vehicles_factory;

class Amfibia extends Pojazd {

    Amfibia(double volume, String name) {
        super(volume, name);
    }

    Amfibia(double width, double length, double heigh, String name) {
        super(width, length, heigh, name);
    }

    @Override
    public String getTypeOfVehicle() {
        return "Pojazd wodno-ladowy, czterokolowy";
    }
}
