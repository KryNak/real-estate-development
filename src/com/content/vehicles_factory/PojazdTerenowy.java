package com.content.vehicles_factory;

class PojazdTerenowy extends Pojazd {

    PojazdTerenowy(double volume, String name) {
        super(volume, name);
    }

    PojazdTerenowy(double width, double length, double heigh, String name) {
        super(width, length, heigh, name);
    }

    @Override
    public String getTypeOfVehicle() {
        return "Pojazd terenowy, czterokolowy";
    }
}
