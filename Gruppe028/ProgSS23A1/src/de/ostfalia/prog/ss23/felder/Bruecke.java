package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Bruecke extends Feld {
    public Bruecke(Feld davor) {
        super(davor);
    }

    @Override
    public void ereignis() {}

    @Override
    public boolean figurNachVorneBewegen(Figur figur) {
        danach.getDanach().getDanach().getDanach().getDanach().getDanach().figurAufFeldSetzen(figur);
        figurVonFeldEntfernen(figur);
        figur.setPosition(figur.getPosition() + 6);
        return true;
    }

    @Override
    public String toString() {
        return "Bruecke";
    }
}
