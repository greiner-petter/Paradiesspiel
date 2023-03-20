package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Bruecke extends Feld {
    public Bruecke(Feld davor, int position) {
        super(davor, position);
    }

    @Override
    public void ereignis() {
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
        getDanach().getDanach().getDanach().getDanach().getDanach().getDanach().figurAufFeldSetzen(figur);
        figurVonFeldEntfernen(figur);
        figur.setPosition(figur.getPosition() + 6);
    }

    @Override
    public boolean figurNachVorneBewegen(Figur figur) {
        getDanach().getDanach().getDanach().getDanach().getDanach().getDanach().getDanach().figurAufFeldSetzen(figur);
        figurVonFeldEntfernen(figur);
        figur.setPosition(figur.getPosition() + 7);
        return true;
    }

    @Override
    public String toString() {
        return "Bruecke";
    }
}
