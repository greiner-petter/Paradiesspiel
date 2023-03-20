package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

import java.util.ArrayList;

public abstract class Feld {
    private final Feld davor;
    private Feld danach;
    private ArrayList<Figur> figurenAufFeld;
    private final int position;

    public Feld(Feld davor, int position) {
        this.davor = davor;
        this.figurenAufFeld = new ArrayList<Figur>();
        this.position = position;
    }

    public abstract void ereignis();

    public Feld getDanach() {
        return danach;
    }

    public void setDanach(Feld danach) {
        this.danach = danach;
    }

    public int getPosition(){
        return position;
    }

    public void figurAufFeldSetzen(Figur figur) {
        figurenAufFeld.add(figur);
    }

    public void figurVonFeldEntfernen(Figur figur) {
        figurenAufFeld.remove(figur);
    }

    public ArrayList<Figur> getFigurenAufFeld() {
        return figurenAufFeld;
    }

    public boolean figurNachVorneBewegen(Figur figur) {
        if (danach != null) {
            danach.figurAufFeldSetzen(figur);
            figurVonFeldEntfernen(figur);
            figur.setPosition(danach.getPosition());
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
            figur.setPosition(danach.getPosition());
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
