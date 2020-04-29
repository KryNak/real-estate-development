package com.construction;

import com.content.item.Item;
import com.exception.TooManyThingsException;
import com.person.Osoba;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Mieszkanie extends Pomieszczenie {

    private static int nextId = 1;
    private String id = (nextId++) + "flat";

    private Blok containThisFlat;
    ArrayList<Osoba> tenantList;
    ArrayList<Item> flatContentList;

    private Osoba mainHirer;
    private Osoba blackList;
    private int blackListCounter = 0;

    private LocalDate from;
    private LocalDate to;
    private LocalDate now;

    private boolean isRented = false;
    private boolean trouble = false;

    public Mieszkanie(Blok appartmentBuilding, double volume){
        super(volume);
        flatContentList = new ArrayList<>();
        containThisFlat = appartmentBuilding;
        appartmentBuilding.addFlat(this);
        tenantList = new ArrayList<>();
    }

    public Mieszkanie(Blok appartmentBuilding, double lenght, double width, double height){
        super(lenght, width, height);
        flatContentList = new ArrayList<>();
        containThisFlat = appartmentBuilding;
        appartmentBuilding.addFlat(this);
        tenantList = new ArrayList<>();
    }

    @Override
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
                    System.err.println("Wyslane pismo do: " + mainHirer.toString());
                    blackList = mainHirer;
                    mainHirer = null;
                    trouble = true;
                }
                blackListCounter++;

                if(blackListCounter > 15){
                    System.err.println("Nastapila eksmisja: "+blackList.toString());
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

    public Blok getContainThisFlat() {
        return containThisFlat;
    }

    public void addPerson(Osoba person){

        if(!tenantList.contains(person))
            tenantList.add(person);

    }

    public boolean removePerson(Osoba person){
        return tenantList.remove(person);
    }

    public void addFlatContent(Item gc)
            throws TooManyThingsException {

        double sum = 0;
        for (Item g : flatContentList) {
            sum += g.getVolume();
        }

        if(getVolume() >= sum + gc.getVolume()){
            flatContentList.add(gc);
        }
        else throw new TooManyThingsException();

        flatContentList.sort(Comparator.comparing(Item::getVolume).reversed().thenComparing(Item::getName));

    }

    public boolean removeFlatContent(Item content){
        return flatContentList.remove(content);
    }

    public String getShortDescription(){
        return getContainThisFlat().toString() + "\\" + getId() + ", Objetosc: " + getVolume();
    }

    public ArrayList<Item> getFlatContentList() {
        return flatContentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mieszkanie that = (Mieszkanie) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(containThisFlat, that.containThisFlat) &&
                Objects.equals(tenantList, that.tenantList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, containThisFlat, tenantList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getContainThisFlat().toString() + "\\" + getId() + ", Objetosc: " + getVolume() +  "\n");

        if (flatContentList.size() > 0) {
            builder.append("Lista przedmiotow: \n");

            flatContentList.sort(Comparator.comparing(Item::getVolume).reversed().thenComparing(Item::getName));

            flatContentList.forEach(c -> {
                builder.append("- " + c.toString() + "\n");
            });
        }

        if(tenantList.size() > 0){
            builder.append("Lista wspollokatorow: \n");

            tenantList.sort(Comparator.comparing(Osoba::toString));

            tenantList.forEach( c ->{
                builder.append("- " + c.toString() + "\n");
            });
        }

        return builder.toString();
    }

    private void jettisoning(){
        makeInitialState();
    }

    private void makeInitialState(){
        blackListCounter = 0;
        blackList = null;
        mainHirer = null;
        from = null;
        to = null;
        isRented = false;
        trouble = false;
        tenantList = new ArrayList<>();
        flatContentList = new ArrayList<>();
    }

}