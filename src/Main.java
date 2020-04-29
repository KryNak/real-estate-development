import com.RoomBox;
import com.Time;
import com.construction.*;
import com.content.Content;
import com.content.item.Item;
import com.content.vehicles_factory.FactoryImplementation;
import com.content.vehicles_factory.model.AmfibiaModel;
import com.content.vehicles_factory.model.MotocyklModel;
import com.content.vehicles_factory.model.PojazdMiejskiModel;
import com.content.vehicles_factory.model.PojazdTerenowyModel;
import com.exception.ProblematicTenantException;
import com.exception.TooManyThingsException;
import com.person.Osoba;
import com.thread.CheckPlaces;
import com.thread.TimeRunning;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Time.setNow(LocalDate.now());
        FactoryImplementation factory = FactoryImplementation.getInstance();


        Osiedle o1 = new Osiedle("Owocowe");

        Blok b1 = new Blok(o1,"Truskawkowa");
        Blok b2 = new Blok(o1, "Arbuzowa");

        Mieszkanie m1 = new Mieszkanie(b1, 60);
        Mieszkanie m2 = new Mieszkanie(b1, 140);
        Mieszkanie m3 = new Mieszkanie(b1, 5, 10.2, 3);
        Mieszkanie m4 = new Mieszkanie(b1, 12, 7, 2);
        Mieszkanie m5 = new Mieszkanie(b2, 70);
        Mieszkanie m6 = new Mieszkanie(b2, 120.5);
        Mieszkanie m7 = new Mieszkanie(b2, 10, 12, 3);
        MiejsceParkingowe m8 = new MiejsceParkingowe(o1, "Morelowa", 5, 5, 5);
        MiejsceParkingowe m9 = new MiejsceParkingowe(o1, "Morelowa", 3, 3, 3);
        MiejsceParkingowe m10 = new MiejsceParkingowe(o1, "Bananowa", 10,10,3);
        MiejsceParkingowe m11 = new MiejsceParkingowe(o1, "Wisniowa", 4, 10, 4);
        MiejsceParkingowe m12 = new MiejsceParkingowe(o1, "Truskawkowa", 78.09);

        List<Pomieszczenie> places = Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12);

        Osoba os1 = new Osoba("Jan", "Kowlczyk", "Jarzebinowa 7", 1981, 1, 2);
        Osoba os2 = new Osoba("Krystian", "Nowak", "Waszyngtona 10", 1992, 2, 1);
        Osoba os3 = new Osoba("Witold", "Kowlczuk", "Pileckiego 11/10", 1987, 3, 22);
        Osoba os4 = new Osoba("Maciej", "Nowogrodzki", "Grota 9", 1982, 6, 12);
        Osoba os5 = new Osoba("Adam", "Bity", "Sikorskiego 1/12", 1991, 7, 24);
        Osoba os6 = new Osoba("Franz", "Mere", "Aleje 8/12", 1999, 5, 6);
        Osoba os7 = new Osoba("Wojciech", "Pszczola", "Lipna 9/34", 1997, 12, 8);
        Osoba os8 = new Osoba("Fred", "Mercury", "Wrotna 11", 1985, 11, 9);

        ArrayList<Osoba> people = new ArrayList<>(Arrays.asList(os1, os2, os3, os4, os5, os6, os7, os8));

        try {
            os1.rentMieszkanie(m1, Time.getNow(), LocalDate.of(2020, 11, 3));
            os1.addFlatMate(os2, m1);
            os1.rentMiejsceParkingowe(m8, Time.getNow(), LocalDate.of(2021, 3, 12));
            os1.addFlatContent(Item.getArmchair(), m1);
            os1.addFlatContent(Item.getTable(), m1);
            os1.addFlatContent(Item.getBike(), m1);
            os1.addGarageContent(factory.buildMotocykl(MotocyklModel.BMW_K1200S), m8);
            os1.addGarageContent(factory.buildPojazdMiejski(PojazdMiejskiModel.FORD_FOCUS_RS), m8);

            os3.rentMieszkanie(m2, Time.getNow(), LocalDate.of(2020, 7, 5));
            os3.addFlatMate(os4, m2);
            os3.addFlatMate(os5, m2);
            os3.addFlatMate(os6, m2);
            os3.addFlatContent(Item.getTV(), m2);
            os3.addFlatContent(Item.getBed(), m2);
            os3.addFlatContent(Item.getChair(), m2);
            os3.addFlatContent(Item.getTable(), m2);
            os3.rentMieszkanie(m3, Time.getNow(), LocalDate.of(2020, 7, 5));
            os3.addFlatContent(Item.getCartoonBox(), m3);
            os3.addFlatContent(Item.getTableSaw(), m3);
            os3.addFlatContent(Item.getToolCrib(), m3);
            os3.rentMieszkanie(m4, Time.getNow(), LocalDate.of(2020, 7, 5));
            os3.addFlatContent(Item.getArmchair(), m4);
            os3.rentMiejsceParkingowe(m11, Time.getNow(), LocalDate.of(2020, 11, 12));
            os3.addGarageContent(factory.buildPojazdTerenowy(PojazdTerenowyModel.JEEP_WRANGLER), m11);
            os3.addGarageContent(Item.getToolCrib(), m11);

            os7.rentMieszkanie(m7, Time.getNow(), LocalDate.of(2021, 1, 23));
            os7.addFlatContent(Item.getTable(), m7);
            os7.addFlatContent(Item.getTumbleDryer(), m7);

            os8.rentMieszkanie(m6, Time.getNow(), LocalDate.of(2020, 6, 11));
            os8.rentMiejsceParkingowe(m9, Time.getNow(), LocalDate.of(2022, 10, 12));
            os8.addGarageContent(factory.buildAmfibia(AmfibiaModel.VW_SCHWIMMWAGEN), m9);
            os8.addFlatContent(Item.getWardrobe(), m6);
            os8.addFlatContent(Item.getTable(), m6);
            os8.addFlatContent(Item.getBed(), m6);
            os8.addFlatContent(Item.getChair(), m6);
            os8.addFlatContent(Item.getTableSaw(), m6);
            os8.addFlatContent(Item.getArmchair(), m6);
        } catch (ProblematicTenantException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        String start;
        do{
            System.out.println("Czy rozpoczac dzialanie programu? [start|exit]");
            start = scanner.next();
            if(start.toLowerCase().equals("exit")) return;
        }while(!start.toLowerCase().equals("start"));


        TimeRunning t1 = new TimeRunning();
        CheckPlaces t2 = new CheckPlaces(places);
        t1.setNextThread(t2);
        t2.setNextThread(t1);
        synchronized (t1){
            t1.notify();
        }

        Osoba chosenPerson = null;
        boolean exit;
        String input = "";
        do{
            try{
                Panel.printMenu();
                input = scanner.next();

                switch (input){
                    case "0":
                        Panel.printPeople(people);
                        input = scanner.next();
                        if( !(Panel.checkReturnCondition(input) || Panel.checkExitCondition(input)) ){
                            chosenPerson = people.get(Integer.parseInt(input));
                        }
                        break;
                    case "1":
                        if(chosenPerson != null){
                            Panel.printInformationAboutChosenPerson(chosenPerson);
                        }
                        else {
                            System.err.println("Nie wybrales osoby.");
                        }
                        break;
                    case "2":
                        Panel.checkUntenantedPlaces(places);
                        break;
                    case "3":
                        if(chosenPerson == null){
                            System.err.println("Nie wybrales osoby. \n");
                            break;
                        }
                        Panel.checkUntenantedPlaces(places);
                        Panel.getReturnPrint();
                        Panel.getExitPrint();
                        input = scanner.next();
                        if(Panel.checkExitCondition(input) || Panel.checkReturnCondition(input))
                            break;
                        int number = Integer.parseInt(input);
                        boolean check = Panel.checkUntenantedPlaces(places, number);
                        if(!check){
                            System.out.println("Wybierz termin zakonczenia najmu: [rok/miesiac/dzien]");
                            Panel.getReturnPrint();
                            Panel.getExitPrint();
                            input = scanner.next();
                            if(Panel.checkReturnCondition(input) || Panel.checkExitCondition(input)) break;
                            Panel.rentFlat(places, number, chosenPerson, input);

                        }
                        break;
                    case "4":
                        System.out.println("Wybierz osobe: ");
                        Panel.getReturnPrint();
                        Panel.printPeople(people);
                        input = scanner.next();
                        if(Panel.checkReturnCondition(input)) break;
                        Osoba temp = people.get(Integer.parseInt(input));
                        Panel.getReturnPrint();
                        Panel.getExitPrint();
                        System.out.println("Wybierz mieszkanie do wyswietlenia: ");
                        System.out.println("Osoba wynajmuje: ");
                        Panel.printPersonRentList(temp);
                        input = scanner.next();
                        if(Panel.checkReturnCondition(input) || Panel.checkExitCondition(input)) break;
                        System.out.println(temp.getHirePlaces().get(Integer.parseInt(input)).toString());
                        break;
                    case "5":
                        if(chosenPerson == null){
                            System.err.println("Nie wybrales osoby");
                            break;
                        }
                        System.out.println("Wybierz pomieszczenie: ");
                        Panel.printPersonRentList(chosenPerson);
                        Panel.getReturnPrint();
                        Panel.getExitPrint();
                        input = scanner.next();
                        if(Panel.checkExitCondition(input) || Panel.checkReturnCondition(input)) break;
                        ArrayList<Pomieszczenie> tab = Panel.getPersonRentList(chosenPerson);
                        if(tab == null){
                            System.err.println("Nie masz pomieszczen.");
                            break;
                        }
                        Pomieszczenie place = tab.get(Integer.parseInt(input));
                        if(Panel.instance(place) == 1) {
                            RoomBox<Mieszkanie> flat = new RoomBox<>( (Mieszkanie) place );
                            System.out.println("Wybierz rzecz ktora chcesz dodac: ");
                            Panel.printAllFlatContent();
                            Panel.getReturnPrint();
                            Panel.getExitPrint();
                            input = scanner.next();
                            if(Panel.checkReturnCondition(input) || Panel.checkExitCondition(input)) break;
                            chosenPerson.addFlatContent(Item.getAllItemsList().get(Integer.parseInt(input)), flat.getRoom());

                        }
                        else if(Panel.instance(place) == -1) {
                            RoomBox<MiejsceParkingowe> garage = new RoomBox<>( (MiejsceParkingowe) place );
                            System.out.println("Wybierz rzecz ktora chcesz dodac: ");
                            ArrayList<Content> mergeList =  Panel.printAllGarageContent(factory);
                            Panel.getReturnPrint();
                            Panel.getExitPrint();
                            input = scanner.next();
                            if(Panel.checkReturnCondition(input) || Panel.checkExitCondition(input)) break;
                            chosenPerson.addGarageContent(mergeList.get(Integer.parseInt(input)), garage.getRoom());
                        }
                        else break;
                        break;
                    case "6":
                        if(chosenPerson == null){
                            System.err.println("Nie wybrales osoby.");
                            break;
                        }
                        Panel.printPersonRentList(chosenPerson);
                        Panel.getReturnPrint();
                        Panel.getExitPrint();
                        ArrayList<Pomieszczenie> list = Panel.getPersonRentList(chosenPerson);
                        if(list == null){
                            System.out.println("Osoba nie wynajmuje zadnych mieszkan.");
                            break;
                        }
                        input = scanner.next();

                        if(Panel.checkExitCondition(input) || Panel.checkReturnCondition(input)) break;

                        int flatOrGarage = Panel.instance(list.get(Integer.parseInt(input)));

                        if( flatOrGarage == 1 ) {
                            RoomBox<Mieszkanie> room = new RoomBox<>((Mieszkanie) list.get(Integer.parseInt(input)));
                            ArrayList<Item> roomList = room.getRoom().getFlatContentList();

                            System.out.println("Wybierz przedmiot do usuniecia: ");

                            for(int i = 0; i < roomList.size(); i++){

                                System.out.println("[" + i + "]" + roomList.get(i));

                            }
                            Panel.getReturnPrint();
                            Panel.getExitPrint();

                            input = scanner.next();
                            if(Panel.checkReturnCondition(input) || Panel.checkExitCondition(input)) break;
                            chosenPerson.removeFlatContent(roomList.get(Integer.parseInt(input)), room.getRoom());

                        }
                        else {
                            RoomBox<MiejsceParkingowe> room = new RoomBox<>(
                                    (MiejsceParkingowe) list.get(Integer.parseInt(input)) );
                            ArrayList<Content> roomlist = room.getRoom().getGarageContentList();

                            System.out.println("Wybierz przedmiot / pojazd do usuniecia:");

                            for(int i = 0; i < roomlist.size(); i++){

                                System.out.println("[" + i + "]" + roomlist.get(i));

                            }
                            Panel.getReturnPrint();
                            Panel.getExitPrint();

                            input = scanner.next();
                            if(Panel.checkExitCondition(input) || Panel.checkReturnCondition(input)) break;
                            chosenPerson.removeGarageContent(roomlist.get(Integer.parseInt(input)), room.getRoom());
                        }
                        break;
                    case "save":
                        Panel.printToFile(people);
                        break;
                    case "exit":
                        input = "exit";
                        break;
                    default:
                        System.out.println();
                        break;
                }
            } catch (ProblematicTenantException e) {
                System.err.println(e.getMessage());
            } catch (TooManyThingsException e){
                System.err.println(e.getMessage());
            }catch (Exception e){
                System.err.println("Zly format danych wejsciowych.");
            }

            exit = input.toLowerCase().equals("exit");
            if (exit) {
                t1.interrupt();
                t2.interrupt();
            }
        }while (!(exit));
    }
}

class Panel{

    static void getReturnPrint(){
        System.out.println("[return] -Powroc do menu glownego.");
    }

    static void getExitPrint(){
        System.out.println("[exit] -Zakoncz dzialanie programu.");
    }

    static boolean checkExitCondition(String input){
        return input.toLowerCase().equals("exit");
    }

    static boolean checkReturnCondition(String input){
        return input.toLowerCase().equals("return");
    }

    static void printMenu(){

        System.out.println("[0] -Wybierz osobe.");
        System.out.println("[1] -Wyswietl dane o wybranej osobie." );
        System.out.println("[2] -Wyswietl wolne pomieszczenia.");
        System.out.println("[3] -Wynajmij wolne mieszkanie.");
        System.out.println("[4] -Wyswietl pomieszczenie za posrednictwem osoby.");
        System.out.println("[5] -Wloz przedmiot / pojazd do pomieszczenia.");
        System.out.println("[6] -Wyjmij przedmiot / pojazd z pomieszczenia.");
        System.out.println("[save] -Zapisz do pliku.");
        System.out.println("[exit] -Zakoncz dzialanie programu.");

    }

    static void printPeople(ArrayList<Osoba> list){

        for(int i = 0; i < list.size(); i++){
            System.out.println( "[" + i + "]" + list.get(i).toString());
        }

        getReturnPrint();
        getExitPrint();

    }

    static void printPersonRentList(Osoba osoba){
        ArrayList<Pomieszczenie> list = osoba.getHirePlaces();

        if(list.size() == 0) {
            System.out.println(" -Nic");
            return;
        }

        for(int i = 0; i < list.size(); i++){

            System.out.println("[" + i + "] " + list.get(i).getShortDescription());

        }

    }

    static ArrayList<Pomieszczenie> getPersonRentList(Osoba osoba){
        ArrayList<Pomieszczenie> list = osoba.getHirePlaces();

        if(list.size() == 0) return null;

        return list;
    }

    static void printInformationAboutChosenPerson(Osoba os){
        System.out.println(os.getAll());
    }

    static void checkUntenantedPlaces(List<Pomieszczenie> list){

        for(int i = 0; i < list.size(); i++){

            if( !list.get(i).getIsRented() ) System.out.println("[" + i + "] " + list.get(i).getShortDescription());

        }

        System.out.println();

    }

    static boolean checkUntenantedPlaces(List<Pomieszczenie> list, int index){
        if(index > list.size() || index < 0) return false;

        return list.get(index).getIsRented();
    }

    static void rentFlat(List<Pomieszczenie> list, int index, Osoba person, String to) throws ProblematicTenantException {

        if(!to.matches("[2][0][0-9][0-9]/([0-9]||[1][0-2])/[0-3][0-9]")) throw new InputMismatchException();

        Pattern pattern = Pattern.compile("[0-9]{1,4}");
        Matcher matcher = pattern.matcher(to);

        int[] tab = new int[3];

        int counter = 0;
        while(matcher.find()){
            tab[counter++] = Integer.parseInt(matcher.group());
        }

        if(list.get(index) instanceof Mieszkanie){
            Mieszkanie flat = (Mieszkanie) list.get(index);
            person.rentMieszkanie(flat, Time.getNow(), LocalDate.of(tab[0], tab[1], tab[2]));
        }
        else {
            MiejsceParkingowe garage = (MiejsceParkingowe) list.get(index);
            person.rentMiejsceParkingowe(garage, Time.getNow(), LocalDate.of(tab[0], tab[1], tab[2]));
        }
    }

    static int instance(Pomieszczenie pom){

        if(pom instanceof Mieszkanie) return 1;
        if(pom instanceof MiejsceParkingowe) return -1;

        return 0;
    }

    static void printAllFlatContent(){

        ArrayList<Item> temp = Item.getAllItemsList();

        for(int i = 0; i < temp.size(); i++){
            System.out.println("[" + i + "] " + temp.get(i));
        }
    }

    static ArrayList<Content> printAllGarageContent(FactoryImplementation factory){
        ArrayList<Content> mergeList = new ArrayList<>(factory.getAllVehicles());
        mergeList.addAll(Item.getAllItemsList());

        for(int i = 0; i < mergeList.size(); i++){

            System.out.println("[" + i + "] " + mergeList.get(i));

        }

        return mergeList;
    }

    static void printToFile(ArrayList<Osoba> people){
        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\kryst\\Desktop\\save.txt");

            people.forEach(p -> {

                writer.println(p.getAll());

            });

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
