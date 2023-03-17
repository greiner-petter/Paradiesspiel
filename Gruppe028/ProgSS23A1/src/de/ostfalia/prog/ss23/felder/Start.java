package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;
import de.ostfalia.prog.ss23.Spieler;
import de.ostfalia.prog.ss23.enums.Feld;

public class Start extends Felder {
    Feld feld;

    public Start(){
        feld = Feld.START;
    }

    @Override
    public void ereignis(Spieler spieler, Figur figur) {}

    @Override
    public Feld getFeld() {
        return feld;
    }
}
