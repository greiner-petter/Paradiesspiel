package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;

import java.util.Random;

public class Wuerfel {
    Random rand = new Random();
    private int augenWuerfel;
    private Farbe[] farbenWuerfel;

    public Wuerfel(int augen) {
        this.augenWuerfel = augen;
    }

    public Wuerfel(Farbe[] farben) {
        this.farbenWuerfel = new Farbe[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.farbenWuerfel[i] = farbe;
            i++;
        }
    }

    public int zahlWuerfeln() {
        int min = 1;
        int max = augenWuerfel;
        return rand.nextInt(max - min + 1) + min;
    }

    public Farbe farbeWuerfeln() {
        int min = 0;
        int max = farbenWuerfel.length - 1;
        return farbenWuerfel[rand.nextInt(max - min + 1) + min];
    }
}
