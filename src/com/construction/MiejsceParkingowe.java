package com.construction;

import com.content.Content;
import com.content.vehicles_factory.Pojazd;
import com.exception.TooManyThingsException;
import com.person.Osoba;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class MiejsceParkingowe extends Pomieszczenie {

    private static int nextId = 1;
    private String id = (nextId++) + "garage";
    private String street;

    private ArrayList<Content> garageContentList;
    private Osiedle containThisGarage;

    private Osoba mainHirer;
    private Osoba blackList;
    private int blackListCounter = 0;

    private LocalDate from;
    private LocalDate to;
    private LocalDate now;

    private boolean isRented = false;
    private boolean trouble = false;

    public MiejsceParkingowe(Osiedle housingEstate, String street, double volume) {
        super(volume);
        this.street = street;
        this.containThisGarage = housingEstate;
        housingEstate.addGarage(this);
        garageContentList = new ArrayList<>();
    }

    public MiejsceParkingowe(Osiedle houseEstate, String street, double lenght, double width, double height) {
        super(lenght, width, height);
        this.street = street;
        this.containThisGarage = houseEstate;
        houseEstate.addGarage(this);
        garageContentList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void rent(Osoba osoba){
        if(osoba.equals(blackList) && blackListCounter <= 15){
            trouble = false;
            mainHirer = osoba;
            blackList = null;
            blackListCounter = 0;
        }
        isRented = true;
        mainHirer = osoba;
    }

    public String getShortDescription(){
        return getContainThisGarage().toString() + ", Garaz na ul. " + street + " " + getId() + ", Objetosc: " +getVolume();
    }

    @Override
    public void setRentTime(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void setNowTime(LocalDate now) {
        synchronized (this){
            this.now = now;

            if(to == null || from == null) return;

            if(now.isAfter(to) ) {

                if(!trouble){
                    mainHirer.receiveWriting(this, new File(
                            "D:\\Programowanie\\Programy java\\Projekt1_Java\\src\\com\\acts\\" + getId()));
                    System.err.println("wyslane zawiadomienie do: " + mainHirer.toString());
                    blackList = mainHirer;
                    mainHirer = null;
                    trouble = true;
                }
                blackListCounter++;

                if(blackListCounter > 15){
                    System.err.println("Niedlugo nastapi decyzja o eksmisji lub o sprzedaniu pojazdu.");
                    jettisoning();
                }

            }
        }
    }

    @Override
    public boolean getIsRented() {
        return !(mainHirer == null && blackList == null);
    }

    @Override
    public void cancellation(Osoba person) {
        if (person.equals(blackList) && blackListCounter <= 15){
            makeInitialState();
            person.removeWriting(this);
        }
    }

    public Osiedle getContainThisGarage() {
        return containThisGarage;
    }

    public void addGarageContent(Content gc)
            throws TooManyThingsException{

        double sum = 0;
        for (Content g : garageContentList) {
            sum += g.getVolume();
        }

        if(getVolume() >= sum + gc.getVolume()){
            garageContentList.add(gc);
        }
        else throw new TooManyThingsException();

        garageContentList.sort(Comparator.comparing(Content::getVolume).reversed().thenComparing(Content::getName));

    }

    public boolean removeGarageContent(Content content){
        return garageContentList.remove(content);
    }

    public ArrayList<Content> getGarageContentList() {
        return garageContentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiejsceParkingowe that = (MiejsceParkingowe) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(garageContentList, that.garageContentList) &&
                Objects.equals(containThisGarage, that.containThisGarage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, garageContentList, containThisGarage);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getContainThisGarage().toString() + ", Garaz na ul. " + street + " " + getId() + ", Objetosc: " +getVolume() + "\n");

        if (garageContentList.size() > 0) {
            builder.append("Lista samochodow i przedmiotow: \n");

            garageContentList.sort(Comparator.comparing(Content::getVolume).reversed().thenComparing(Content::getName));

            garageContentList.forEach(c -> {
                builder.append("- " + c.toString() + "\n");
            });
        }

        return builder.toString();
    }

    private void jettisoning(){
        boolean exitTag = false;

        for(int i = 0; i < garageContentList.size() && !exitTag; i++){

            if(garageContentList.get(i) instanceof Pojazd){

                garageContentList.remove(i);
                exitTag = true;

            }

        }

        if(exitTag) {

            System.err.println("Pojazd sprzedany");
            setRentTime(now, now.plusMonths(2));
            blackListCounter = 0;
            trouble = false;
            mainHirer = blackList;
            blackList = null;
        }
        else makeInitialState();

    }

    private void makeInitialState(){
        blackListCounter = 0;
        blackList = null;
        mainHirer = null;
        from = null;
        to = null;
        isRented = false;
        trouble = false;
        garageContentList = new ArrayList<>();
    }
}
