package com.construction;

import com.person.Osoba;

import java.time.LocalDate;

public abstract class Pomieszczenie {

    private double volume;

    public Pomieszczenie(double volume) {
        this.volume = volume;
    }

    public Pomieszczenie(double lenght, double width, double height){
        volume = lenght * width * height;
    }

    public double getVolume() {
        return volume;
    }

    public abstract String getId();
    public abstract void setRentTime(LocalDate from, LocalDate to);
    public abstract void setNowTime(LocalDate now);
    public abstract boolean getIsRented();
    public abstract void cancellation(Osoba person);
    public abstract String getShortDescription();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getId();
    }
    
}
