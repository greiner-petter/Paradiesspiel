package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;
import de.ostfalia.prog.ss23.Spieler;
import de.ostfalia.prog.ss23.enums.Feld;

public class Paradies extends Felder {
    Feld feld;

    public Paradies(){
        feld = Feld.PARADIES;
    }

    @Override
    public void ereignis(Spieler spieler, Figur figur) {
        if (spieler.getFigur(spieler.getFarbe() + "-A").getPosition() == 63 &&
                spieler.getFigur(spieler.getFarbe() + "-A").getPosition() == 63) {
            spieler.setGewinner(true);
        }
    }

    @Override
    public Feld getFeld() {
        return feld;
    }
}
