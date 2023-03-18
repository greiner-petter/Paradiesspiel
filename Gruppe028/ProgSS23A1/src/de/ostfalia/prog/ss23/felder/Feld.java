package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

import java.util.ArrayList;

public abstract class Feld {
    Feld davor;
    Feld danach;
    ArrayList<Figur> figurenAufFeld = new ArrayList<Figur>();

    public Feld(Feld davor) {
        this.davor = davor;
    }

    public abstract void ereignis();

    public Feld getDavor() {
        return davor;
    }

    public Feld getDanach() {
        return danach;
    }

    public void setDanach(Feld danach) {
        this.danach = danach;
    }

    public void figurAufFeldSetzen(Figur figur) {
        figurenAufFeld.add(figur);
    }

    public void figurVonFeldEntfernen(Figur figur) {
        figurenAufFeld.remove(figur);
    }

    public boolean figurNachVorneBewegen(Figur figur) {
        if (danach != null) {
            danach.figurAufFeldSetzen(figur);
            figurVonFeldEntfernen(figur);
            figur.setPosition(figur.getPosition() + 1);
            return true;
        } else {
            figurNachHintenBewegen(figur);
            return false;
        }
    }

    public void figurNachHintenBewegen(Figur figur) {
        if (davor != null) {
            davor.figurAufFeldSetzen(figur);
            figurVonFeldEntfernen(figur);
            figur.setPosition(figur.getPosition() - 1);
        }
    }

    public String figurenToString(){
        StringBuilder returnText = new StringBuilder(" ");
        for (Figur figur : figurenAufFeld) {
            returnText.append(figur.getName());
            returnText.append(" ");
        }
        return returnText.toString();
    }
}
