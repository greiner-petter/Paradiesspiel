package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;
import de.ostfalia.prog.ss23.Spieler;
import de.ostfalia.prog.ss23.enums.Feld;

public class Standard extends Felder {
    Feld feld;

    public Standard(){
        feld = Feld.STANDARD;
    }

    @Override
    public void ereignis(Spieler spieler, Figur figur, int augenzahl) {}

    @Override
    public Feld getFeld() {
        return feld;
    }
}
