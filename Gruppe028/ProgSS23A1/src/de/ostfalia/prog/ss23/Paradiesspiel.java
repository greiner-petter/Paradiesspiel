package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.felder.*;
import de.ostfalia.prog.ss23.interfaces.IParadiesspiel;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Paradiesspiel implements IParadiesspiel {
    private final Spieler[] mitspieler;
    private final Feld[] spielfeld;
    private Farbe farbeAmZug;

    /**
     * Konstruktor erstellt die Spieler und das Spielfeld und setzt alle Figuren der Spieler auf das Startfeld
     *
     * @param farben Farben der Spieler
     */
    public Paradiesspiel(Farbe... farben) {
        this.spielfeld = new Feld[64];
        this.mitspieler = new Spieler[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.mitspieler[i] = new Spieler(farbe, 2);
            i++;
        }
        spielfeldErstellen();
        for (Spieler spieler : mitspieler) {
            for (Figur figur : spieler.getFiguren()) {
                spielfeld[0].figurAufFeldSetzen(figur);
                figur.setPosition(0);
            }
        }
    }

    /**
     * Konstruktor erstellt die Spieler und das Spielfeld und setzt die in conf angegebenen Figuren auf die jeweiligen
     * Felder und den rest der Figuren der Spieler auf das Startfeld
     *
     * @param conf Bestimmte Figuren mit Position
     * @param farben Farben der Spieler
     */
    public Paradiesspiel(String conf, Farbe... farben) {
        this.spielfeld = new Feld[64];
        this.mitspieler = new Spieler[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.mitspieler[i] = new Spieler(farbe, 2);
            i++;
        }
        spielfeldErstellen();
        String[] confSplit;
        String[] figurUndPosition;
        confSplit = conf.split(",");
        for (String config : confSplit) {
            config = config.trim();
            figurUndPosition = config.split(":");
            Figur figur = getFigur(figurUndPosition[0]);
            int position = Integer.parseInt(figurUndPosition[1]);
            spielfeld[position].figurAufFeldSetzen(figur);
            figur.setPosition(position);
        }
        for (Spieler spieler : mitspieler) {
            for (Figur figur : spieler.getFiguren()) {
                if (figur.getPosition() == 0) {
                    spielfeld[0].figurAufFeldSetzen(figur);
                }
            }
        }
    }

    /**
     * Erstellt das Spielfeld
     */
    public void spielfeldErstellen() {
        spielfeld[0] = new Start(null, 0);
        Feld davor = spielfeld[0];
        for (int i = 1; i < spielfeld.length; i++) {
            if (i == spielfeld.length - 1) {
                spielfeld[i] = new Paradies(davor, i);
            } else if (Arrays.asList(14, 18, 27, 32, 36, 50).contains(i)) {
                spielfeld[i] = new Glueck(davor, i);
            } else if (i == 6) {
                spielfeld[i] = new Bruecke(davor, i);
            } else if (i == 52) {
                spielfeld[i] = new Aufschwung(davor, i);
            } else {
                spielfeld[i] = new Standard(davor, i);
            }
            davor = spielfeld[i];
            spielfeld[i-1].setDanach(davor);
        }
    }

    @Override
    public Farbe getFarbeAmZug() {
        return farbeAmZug;
    }

    @Override
    public void setFarbeAmZug(Farbe farbe) {
        this.farbeAmZug = farbe;
    }

    /**
     * Gibt die Position der übergebenen Figur zurück, falls diese nicht existiert wird -1 zurückgegeben
     *
     * @param figur Der Name der gesuchten Figur (z.B. "BLAU-A")
     * @return Die Position der Figur
     */
    @Override
    public int getFigurposition(String figur) {
        for (Spieler spieler : mitspieler) {
            if (spieler.getFigur(figur) != null){
                return spieler.getFigur(figur).getPosition();
            }
        }
        return -1;
    }

    /**
     * Bewegt die übergebene Figur um die übergebenen Würfe
     *
     * @param figur       Der Name der Figur, welche bewegt werden soll (z.B. "BLAU-A")
     * @param augenzahlen Eine (oder mehrere) zufällig gewürfelte Zahl(en)
     * @return ob die figur bewegt werden konnte
     */
    @Override
    public boolean bewegeFigur(String figur, int... augenzahlen) {
        if (getFigurposition(figur) == -1 ||
                !figur.contains(getFarbeAmZug().toString()) ||
                    getGewinner() != null ||
                        getFigur(figur).getPosition() == spielfeld.length - 1) {
            return false;
        }
        boolean kannNachVorne = true;
        getFigur(figur).setWurf(augenzahlen);
        for (int augenzahl : augenzahlen) {
            if (getFigur(figur).getParadies()) {
                getFigur(figur).setParadies(false);
                kannNachVorne = false;
            }
            for (int i = 0; i < Math.abs(augenzahl); i++) {
                if (kannNachVorne) {
                    kannNachVorne = spielfeld[getFigurposition(figur)].figurNachVorneBewegen(getFigur(figur));
                } else {
                    spielfeld[getFigurposition(figur)].figurNachHintenBewegen(getFigur(figur));
                }
            }
            spielfeld[getFigurposition(figur)].ereignis();
        }
        return true;
    }

    /**
     * Gibt zurück die Farbe des gewinners, falls noch keiner gewonnen hat, wird null zurückgegeben
     *
     * @return den Gewinner
     */
    @Override
    public Farbe getGewinner() {
        for (Spieler spieler : mitspieler) {
            if (spieler.istGewinner()) {
                return spieler.getFarbe();
            }
        }
        return null;
    }

    /**
     * @return alle Spieler als Farben-Liste
     */
    @Override
    public Farbe[] getAlleSpieler() {
        Farbe[] alleSpieler = new Farbe[mitspieler.length];
        int i = 0;
        for (Spieler spieler : mitspieler) {
            alleSpieler[i] = spieler.getFarbe();
            i++;
        }
        return alleSpieler;
    }

    /**
     * @param farbe die Farbe des gesuchten Spielers
     * @return den Spieler dessen Farbe übergeben wurde als Objekt
     */
    public Spieler getSpieler(Farbe farbe) {
        for (Spieler spieler : mitspieler) {
            if (spieler.getFarbe().equals(farbe)) {
                return spieler;
            }
        }
        return null;
    }

    /**
     * @param figur die Figur als String
     * @return die Figur als Objekt
     */
    public Figur getFigur(String figur) {
        for (Spieler spieler: mitspieler) {
            if (spieler.getFigur(figur) != null) {
                return spieler.getFigur(figur);
            }
        }
        return null;
    }

    public Feld[] getSpielfeld() {
        return spielfeld;
    }

    public static void main(String[] args) {
        Paradiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GRUEN, Farbe.GELB, Farbe.ROT);
        Wuerfel zahlenwuerfel = new Wuerfel(6);
        Wuerfel farbenwuerfel = new Wuerfel(spiel.getAlleSpieler());
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        int rundenCounter = 1;
        String currentSpielerAmZug;
        String figur;
        String input;
        int[] wurf = new int[2];

        while (spiel.getGewinner() == null) {
            for (Feld feld : spiel.getSpielfeld()) {
                System.out.println(feld.toString() + feld.figurenToString());
            }
            System.out.println("Runde " + rundenCounter);

            spiel.setFarbeAmZug(farbenwuerfel.farbeWuerfeln());
            System.out.println("Farbe am zug: " + spiel.getFarbeAmZug());

            currentSpielerAmZug = spiel.getSpieler(spiel.getFarbeAmZug()).getFarbe().toString();

            System.out.println("Figur A oder B?");
            input = scan.nextLine().toUpperCase();

            wurf[0] = zahlenwuerfel.zahlWuerfeln();
            wurf[1] = zahlenwuerfel.zahlWuerfeln();
            System.out.println("Wurf: " + wurf[0] + ", " + wurf[1]);

            if (input.equals("A")) {
                figur = currentSpielerAmZug + "-A";
            } else if (input.equals("B")){
                figur = currentSpielerAmZug + "-B";
            } else {
                figur = spiel.getSpieler(spiel.getFarbeAmZug()).getFiguren()[rand.nextInt(2)].getName();
            }
            spiel.bewegeFigur(figur, wurf);

            System.out.println(figur + " steht auf Feld " + spiel.getFigurposition(figur));
            rundenCounter++;
        }
        System.out.println(spiel.getGewinner() + " gewinnt");
        scan.close();
    }
}
