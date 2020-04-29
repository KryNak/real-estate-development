package com.content.vehicles_factory;

import com.content.vehicles_factory.model.*;

interface Factory {
    Pojazd buildLodz(LodzModel model);
    Pojazd buildPojazdMiejski(PojazdMiejskiModel model);
    Pojazd buildPojazdTerenowy(PojazdTerenowyModel model);
    Pojazd buildAmfibia(AmfibiaModel model);
    Pojazd buildMotocykl(MotocyklModel model);
}
