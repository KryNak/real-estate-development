package com.content.vehicles_factory;

import com.content.vehicles_factory.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class FactoryImplementation implements Factory {

    private static FactoryImplementation instance;
    private final static String err = "No such model";
    private final ArrayList<Pojazd> allVehicles = new ArrayList<>(Arrays.asList( buildAmfibia(AmfibiaModel.VW_SCHWIMMWAGEN),
            buildAmfibia(AmfibiaModel.GIBBS_AQUADA), buildMotocykl(MotocyklModel.BMW_K1200S),
            buildMotocykl(MotocyklModel.KAWASAKI_NINJA), buildPojazdMiejski(PojazdMiejskiModel.FERRARI_599_GTO),
            buildPojazdMiejski(PojazdMiejskiModel.FORD_FOCUS_RS), buildPojazdTerenowy(PojazdTerenowyModel.JEEP_WRANGLER),
            buildPojazdTerenowy(PojazdTerenowyModel.MERCEDES_AMG_G63), buildLodz(LodzModel.DELFIN_650),
            buildLodz(LodzModel.SEA_RAY_SUNDANCER)));

    private FactoryImplementation(){}

    public static FactoryImplementation getInstance(){
        synchronized (FactoryImplementation.class){
            if(instance==null){
                instance = new FactoryImplementation();
            }
            return instance;
        }
    }

    @Override
    public Pojazd buildLodz(LodzModel model){

        switch (model){
            case DELFIN_650:
                return new Lodz(6.49, 2.48, 2.20, "Delfin 650");
            case SEA_RAY_SUNDANCER:
                return new Lodz(2.56, 8.07, 3.35, "Sea Ray Sundancer");
            default:
                throw new NoSuchElementException(err);

        }
    }

    @Override
    public Pojazd buildPojazdMiejski(PojazdMiejskiModel model) {
        switch (model){
            case FERRARI_599_GTO:
                return new PojazdMiejski(1.962, 4.71, 1.326, "Ferrari 599 GTO");
            case FORD_FOCUS_RS:
                return new PojazdMiejski(1.842, 4.402, 1.497, "Ford Focus RS");
            default:
                throw new NoSuchElementException(err);
        }
    }

    @Override
    public Pojazd buildPojazdTerenowy(PojazdTerenowyModel model) {
        switch (model){
            case MERCEDES_AMG_G63:
                return new PojazdTerenowy(1.76, 4.662, 1.951, "Mercedes AMG G63");
            case JEEP_WRANGLER:
                return new PojazdTerenowy(1.877, 4.751, 1.865, "JEEP Wrangler");
            default:
                throw new NoSuchElementException(err);
        }
    }

    @Override
    public Pojazd buildAmfibia(AmfibiaModel model) {
        switch (model){
            case GIBBS_AQUADA:
                return new Amfibia(2 * 4.5 * 1.8, "Gibbs Aquada");
            case VW_SCHWIMMWAGEN:
                return new Amfibia(1.5 * 3.8 * 1.6, "VW Schwimmwagen");
            default:
                throw new NoSuchElementException(err);
        }
    }

    @Override
    public Pojazd buildMotocykl(MotocyklModel model) {
        switch (model){
            case BMW_K1200S:
                return new Motocykl(1.571, 2.182, 0.82, "BMW K1200S");
            case KAWASAKI_NINJA:
                return new Motocykl(0.739, 2.055, 1.135, "Kawasaki Ninja");
            default:
                throw new NoSuchElementException(err);
        }
    }

    public ArrayList<Pojazd> getAllVehicles() {
        return allVehicles;
    }
}
