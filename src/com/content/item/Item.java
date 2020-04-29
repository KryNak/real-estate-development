package com.content.item;

import com.content.Content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Item implements Content {

    private final static ArrayList<Item> AllItemsList = new ArrayList<>(Arrays.asList(
            getToolCrib(), getTable(), getArmchair(), getBed(), getChair(), getTableSaw(), getBike(), getCartoonBox(),
            getTumbleDryer(), getTV(), getWardrobe()));

    private String name;
    private double volume;

    private Item(String name, double volume){
        this.name = name;
        this.volume = volume;
    }

    public static Item getToolCrib(){
        return new Item("Tool crib", 0.648);
    }

    public static Item getTableSaw(){
        return new Item("Table saw", 0.43);
    }

    public static Item getBike(){
        return new Item("Bike", 0.15);
    }

    public static Item getCartoonBox(){
        return new Item("Cartoon box", 0.1);
    }

    public static Item getTumbleDryer(){
        return new Item("Tumble dryer", 0.225);
    }

    public static Item getBed(){
        return new Item("Bed", 1.5);
    }

    public static Item getWardrobe(){
        return new Item("Wardrobe", 1.44);
    }

    public static Item getTV(){
        return new Item("TV",0.09);
    }

    public static Item getArmchair(){
        return new Item("Armchair", 0.57);
    }

    public static Item getTable(){
        return new Item("Table",0.385);
    }

    public static Item getChair(){
        return new Item("Chair",0.2);
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
        Item item = (Item) o;
        return Double.compare(item.volume, volume) == 0 &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, volume);
    }

    @Override
    public String toString() {
        return "Nazwa: " + getName() + ", Objetosc: " + getVolume();
    }

    public static ArrayList<Item> getAllItemsList() {
        return AllItemsList;
    }
}
