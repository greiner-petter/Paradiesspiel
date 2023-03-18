package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Glueck extends Feld {
    public Glueck(Feld davor) {
        super(davor);
    }

    @Override
    public void ereignis() {
//        for (Figur figur : figurenAufFeld) {
//            for (int i = 0; i < 6; i++) {
//                figurNachVorneBewegen(figur);
//            }
//        }
    }

    @Override
    public String toString() {
        return "Glueck";
    }
}
