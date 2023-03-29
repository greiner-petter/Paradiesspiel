package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;

import java.util.Random;

public class Wuerfel {
    Random rand = new Random();
    private int zahlenwuerfel;
    private Farbe[] farbenwuerfel;

    /**
     * Konstruktor für den Zahlenwürfel
     *
     * @param augen anzahl augen die gewürfelt werden können
     */
    public Wuerfel(int augen) {
        this.zahlenwuerfel = augen;
    }

    /**
     * Konstruktor für den Farbenwürfel
     *
     * @param farben alle Farben die gewürfelt werden können
     */
    public Wuerfel(Farbe[] farben) {
        this.farbenwuerfel = new Farbe[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.farbenwuerfel[i] = farbe;
            i++;
        }
    }

    /**
     * @return eine zufällige Zahl
     */
    public int zahlWuerfeln() {
        int min = 1;
        int max = zahlenwuerfel;
        return rand.nextInt(max - min + 1) + min;
    }

    /**
     * @return eine zufällige Farbe
     */
    public Farbe farbeWuerfeln() {
        int min = 0;
        int max = farbenwuerfel.length - 1;
        return farbenwuerfel[rand.nextInt(max - min + 1) + min];
    }
}
