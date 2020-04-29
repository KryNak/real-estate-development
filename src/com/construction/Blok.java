package com.construction;

import java.util.Objects;

public class Blok {

    private static int nextId = 0;
    private int id = nextId++;
    private String street;

    private int index = 0;
    private Mieszkanie[] flats;
    private Osiedle containThisAparrtamentBuilding;

    public Blok(Osiedle housingEstate, String street) {
        this.street = street;
        housingEstate.addAppartamentBuilding(this);
        containThisAparrtamentBuilding = housingEstate;
        flats = new Mieszkanie[20];
    }

    public Osiedle getContainThisAparrtamentBuilding() {
        return containThisAparrtamentBuilding;
    }

    public void addFlat(Mieszkanie flat){

        if(index < flats.length){
            flats[index++]=flat;
        }
        else throw new UnsupportedOperationException(
                "Nie mozna juz dodac nastepnego mieszkania");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blok blok = (Blok) o;
        return id == blok.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getContainThisAparrtamentBuilding().toString() + ", Blok na ul. " + street + " " + id;
    }
}
