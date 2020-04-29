package com.content.vehicles_factory;

class Lodz extends Pojazd {

    Lodz(double volume, String name) {
        super(volume, name);
    }

    Lodz(double width, double length, double heigh, String name) {
        super(width, length, heigh, name);
    }

    @Override
    public String getTypeOfVehicle() {
        return "Pojazd wodny";
    }
}
