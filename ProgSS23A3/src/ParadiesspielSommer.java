package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.exceptions.*;
import de.ostfalia.prog.ss23.felder.*;

import java.util.Arrays;

public class ParadiesspielSommer extends Paradiesspiel {
    public ParadiesspielSommer(Farbe... farben) throws FalscheSpielerzahlException {
        super(farben);
    }

    public ParadiesspielSommer(String conf, Farbe... farben) throws FalscheSpielerzahlException, UngueltigePositionException {
        super(conf, farben);
    }

    @Override
    public void spielerErstellen(Farbe... farben) {
        this.mitspieler = new Spieler[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.mitspieler[i] = new Spieler(farbe, 3);
            i++;
        }
    }

    @Override
    public void spielfeldErstellen() {
        spielfeld = new Feld[72];
        spielfeld[0] = new Start(null, 0);
        Feld davor = spielfeld[0];
        for (int i = 1; i < spielfeld.length; i++) {
            if (i == spielfeld.length - 1) {
                spielfeld[i] = new Paradies(davor, i);
            } else if (Arrays.asList(14, 27, 32, 36, 50).contains(i)) {
                spielfeld[i] = new Glueck(davor, i);
            } else if (i == 6 || i == 42) {
                spielfeld[i] = new Bruecke(davor, i);
            } else if (i == 52) {
                spielfeld[i] = new Aufschwung(davor, i);
            } else if (i == 5 || i == 9) {
                spielfeld[i] = new Pech(davor, i);
            } else if (Arrays.asList(24, 41, 54).contains(i)) {
                spielfeld[i] = new Desaster(davor, i);
            } else if (i == 58) {
                spielfeld[i] = new Neuanfang(davor , i);
            } else if (i == 19 || i == 46) {
                spielfeld[i] = new Labyrinth(davor, i);
            } else {
                spielfeld[i] = new Standard(davor, i);
            }
            davor = spielfeld[i];
            spielfeld[i-1].setDanach(davor);
        }
        spielfeld[6].setDanach(spielfeld[12]);
    }

    @Override
    public boolean illegalePosition(int position) {
        return Arrays.asList(5, 6, 9, 14, 24, 27, 32, 36, 41, 42, 50, 54, 58).contains(position) ||
                position < 0 ||
                position > spielfeld.length;
    }
}
