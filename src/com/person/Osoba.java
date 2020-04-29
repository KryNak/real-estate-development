package com.person;

import com.construction.MiejsceParkingowe;
import com.construction.Mieszkanie;
import com.construction.Osiedle;
import com.construction.Pomieszczenie;
import com.exception.ProblematicTenantException;
import com.content.Content;
import com.content.item.Item;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class Osoba {

    private static int nextId = 0;
    private int id = nextId++;

    private String imie;
    private String nazwisko;
    private String pesel;
    private String adresZameldowania;
    private LocalDate dataUrodzenia;

    private ArrayList<Pomieszczenie> hirePlaces;
    private final int limit = 5;

    private HashMap<Pomieszczenie, File> actList;

    public Osoba(String imie, String nazwisko, String adresZameldowania,
                 int rok, int miesiac, int dzien) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adresZameldowania = adresZameldowania;
        dataUrodzenia = LocalDate.of(rok, miesiac, dzien);
        pesel = generatePesel(rok, miesiac, dzien);
        hirePlaces = new ArrayList<>();
        actList = new HashMap<>();
    }

    private String generatePesel(int rok, int miesiac, int dzien){

        StringBuilder pesel = new StringBuilder();
        pesel.append( (rok + "").substring(2) + (miesiac < 10 ? "0" + miesiac : miesiac)
                + (dzien < 10 ? "0" + dzien : dzien) );

        if(id < 10) pesel.append(id +"000");
        else if(id < 100) pesel.append(id +"00");
        else if(id < 1_000) pesel.append(id +"0");
        else if(id < 10_000) pesel.append(id);
        else throw new ArrayIndexOutOfBoundsException("Za duze id w Osobie");

        pesel.append( (int) (Math.random() * 10) );

        return pesel.toString();
    }

    public String getPesel() {
        return pesel;
    }

    public void rentMieszkanie(Mieszkanie flat, LocalDate from, LocalDate to) throws ProblematicTenantException{

        if(problematicTenant(flat)) throw new ProblematicTenantException(getAnnouncement());

        if(limit >= hirePlaces.size()) {
            if(( !hirePlaces.contains(flat) )){
                flat.rent(this);
                flat.setRentTime(from, to);
                hirePlaces.add(flat);
            }
        }
        else System.err.println("Masz za duzo lokali. Nie mozesz wynajac nasptepnego.");

    }

    public void rentMiejsceParkingowe(MiejsceParkingowe garage, LocalDate from, LocalDate to)
            throws ProblematicTenantException {

        if(problematicTenant(garage)) throw new ProblematicTenantException(getAnnouncement());


        boolean findTag = false;

        if(hirePlaces.contains(garage)) return;
        if(hirePlaces.size() >= limit) {
            System.err.println("Masz za duzo lokali. Nie mozesz wynajac nasptepnego.");
            return;
        }
        if(hirePlaces.size() == 0){
            System.err.println("Nie masz zadnego mieszkania");
            return;
        }

        boolean returnTag = false;

        for(int i = 0; i < hirePlaces.size() && !returnTag; i++){

            if(hirePlaces.get(i).getId().matches("[0-9]+flat")){
                Mieszkanie m = (Mieszkanie) hirePlaces.get(i);
                if(m.getContainThisFlat().getContainThisAparrtamentBuilding().
                        equals(garage.getContainThisGarage())
                ){

                    findTag = true;
                    hirePlaces.add(garage);
                    garage.rent(this);
                    garage.setRentTime(from, to);
                    returnTag = true;

                }
            }

        }

        if(!findTag) System.err.println("Nie masz mieszkania na tym osiedlu");

    }

    public void addFlatMate(Osoba person, Mieszkanie flat) {

        if(hirePlaces.contains(flat)){

            flat.addPerson(person);

        }
        else System.err.println("Nie posiadasz takiego mieszkania");

    }

    public void removeFlatMate(Osoba person, Mieszkanie flat) {

        if(hirePlaces.contains(flat)){

            if (!flat.removePerson(person)){
                System.err.println("Nie masz takiego wspolokatora");
            };

        }
        else System.err.println("Nie posiadasz takiego mieszkania");

    }

    public void addGarageContent(Content content, MiejsceParkingowe garage) throws Exception {

        if(!hirePlaces.contains(garage)){
            System.err.println("Nie posiadasz takiego miejsca parkingowego");
            return;
        }

        MiejsceParkingowe mp = (MiejsceParkingowe) hirePlaces.get( hirePlaces.indexOf(garage) );
        mp.addGarageContent(content);

    }

    public void removeGarageContent(Content content, MiejsceParkingowe garage){
        if(!hirePlaces.contains(garage)){
            System.err.println("Niie posiadasz takiego miejsca parkingowego");
            return;
        }

        MiejsceParkingowe mp = (MiejsceParkingowe) hirePlaces.get( hirePlaces.indexOf(garage) );
        if(!mp.removeGarageContent(content)){
            System.err.println("Nie posiadasz takiego przedmiotu w garażu");
        };
    }

    public void addFlatContent(Item content, Mieszkanie flat) throws Exception {

        if(!hirePlaces.contains(flat)){
            System.err.println("Nie posiadasz takiego miejsca parkingowego");
            return;
        }

        Mieszkanie mp = (Mieszkanie) hirePlaces.get( hirePlaces.indexOf(flat) );
        mp.addFlatContent(content);

    }

    public void removeFlatContent(Item content, Mieszkanie flat){
        if(!hirePlaces.contains(flat)){
            System.err.println("Niie posiadasz takiego miejsca parkingowego");
            return;
        }

        Mieszkanie mp = (Mieszkanie) hirePlaces.get( hirePlaces.indexOf(flat) );
        if(!mp.removeFlatContent(content)){
            System.err.println("Nie posiadasz takiego przedmiotu w garażu");
        };
    }

    public void receiveWriting(Pomieszczenie place, File writing){
        if(!actList.containsValue(place)){
            actList.put(place, writing);
        }
    }

    public void removeWriting(Pomieszczenie place){
        actList.remove(place);
    }

    public void sendCancellation(Pomieszczenie place){

        if(!hirePlaces.contains(place)){
            System.err.println("Brak takiego pommieszczenia. Nie mozn go usunac.");
            return;
        }

        hirePlaces.remove(place);
        place.cancellation(this);

    }

    public ArrayList<Pomieszczenie> getHirePlaces() {
        return hirePlaces;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Osoba os = (Osoba) obj;
        return getPesel().equals(os.getPesel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPesel());
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }

    public String getAll(){
        StringBuilder builder = new StringBuilder();
        builder.append(">> " + this.toString() + " <<\n");

        hirePlaces.sort(Comparator.comparing(Pomieszczenie::getVolume));

        builder.append("Wynajmuje: \n");
        for (Pomieszczenie pom : hirePlaces) {
            builder.append(pom.toString() + "\n");
        }

        return builder.toString();
    }

    private boolean problematicTenant(Mieszkanie flat){
        if(actList.size() > 3) return false;

        int counter = 0;

        Osiedle orientingPoint = flat.getContainThisFlat().getContainThisAparrtamentBuilding();

        Set<Pomieszczenie> pomieszczenieSet = actList.keySet();
        Pomieszczenie[] tab = pomieszczenieSet.toArray(Pomieszczenie[]::new);

        for (Pomieszczenie pomieszczenie : tab) {


            if (pomieszczenie instanceof Mieszkanie) {
                Mieszkanie temp = (Mieszkanie) pomieszczenie;
                Osiedle settlement = temp.getContainThisFlat().getContainThisAparrtamentBuilding();
                if (settlement.equals(orientingPoint)) counter++;
            } else {
                MiejsceParkingowe temp = (MiejsceParkingowe) pomieszczenie;
                Osiedle settlement = temp.getContainThisGarage();
                if (settlement.equals(orientingPoint)) counter++;
            }

        }

        return counter >= 3;

    }

    private boolean problematicTenant(MiejsceParkingowe garage){

        if(actList.size() > 3) return false;

        int counter = 0;

        Osiedle orientingPoint = garage.getContainThisGarage();

        Set<Pomieszczenie> pomieszczenieSet = actList.keySet();
        Pomieszczenie[] tab = pomieszczenieSet.toArray(Pomieszczenie[]::new);

        for (Pomieszczenie pomieszczenie : tab) {


            if (pomieszczenie instanceof Mieszkanie) {
                Mieszkanie temp = (Mieszkanie) pomieszczenie;
                Osiedle settlement = temp.getContainThisFlat().getContainThisAparrtamentBuilding();
                if (settlement.equals(orientingPoint)) counter++;
            } else {
                MiejsceParkingowe temp = (MiejsceParkingowe) pomieszczenie;
                Osiedle settlement = temp.getContainThisGarage();
                if (settlement.equals(orientingPoint)) counter++;
            }

        }

        return counter >= 3;


    }

    private String getAnnouncement(){
        return "Osoba " + imie + " " + nazwisko + " posiadala juz najem pomieszczen: " + actList.keySet().toString();
    }
}
