package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.felder.*;
import de.ostfalia.prog.ss23.interfaces.IParadiesspiel;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Paradiesspiel implements IParadiesspiel {
    private final Spieler[] spieler;
    private final Feld[] spielfeld;

    public Paradiesspiel(Farbe... farben) {
        this.spielfeld = new Feld[64];
        this.spieler = new Spieler[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.spieler[i] = new Spieler(farbe, 2);
            i++;
        }
        spielfeld[0] = new Start(null);
        Feld davor = spielfeld[0];
        for (int j = 1; j < spielfeld.length; j++) {
            if (j == spielfeld.length - 1) {
                spielfeld[j] = new Paradies(davor);
            } else if (Arrays.asList(14, 18, 27, 32, 36, 50).contains(j)) {
                spielfeld[j] = new Glueck(davor);
            } else if (j == 6) {
                spielfeld[j] = new Bruecke(davor);
            } else if (j == 52) {
                spielfeld[j] = new Aufschwung(davor);
            } else {
                spielfeld[j] = new Standard(davor);
            }
            davor = spielfeld[j];
            spielfeld[j-1].setDanach(davor);
        }
        for (Spieler spieler : spieler) {
            for (Figur figur : spieler.getFiguren()) {
                spielfeld[0].figurAufFeldSetzen(figur);
            }
        }
    }

    @Override
    public Farbe getFarbeAmZug() {
        for (Spieler spieler : spieler) {
            if (spieler.istAmZug()) {
                return spieler.getFarbe();
            }
        }
        return null;
    }

    @Override
    public void setFarbeAmZug(Farbe farbe) {
        for (Spieler spieler : spieler) {
            spieler.setAmZug(spieler.getFarbe().equals(farbe));
        }
    }

    @Override
    public int getFigurposition(String figur) {
        for (Spieler spieler : spieler) {
            if (spieler.getFigur(figur) != null){
                return spieler.getFigur(figur).getPosition();
            }
        }
        return -1;
    }

    @Override
    public boolean bewegeFigur(String figur, int... augenzahlen) {
        boolean kannNachVorne = true;
        if (getFigurposition(figur) == spielfeld.length - 1) {
            return  false;
        }
        for (int augenzahl : augenzahlen) {
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

    @Override
    public Farbe getGewinner() {
        for (Spieler spieler : spieler) {
            if (spieler.istGewinner()) {
                return spieler.getFarbe();
            }
        }
        return null;
    }

    @Override
    public Farbe[] getAlleSpieler() {
        Farbe[] alleSpieler = new Farbe[spieler.length];
        int i = 0;
        for (Spieler spieler : spieler) {
            alleSpieler[i] = spieler.getFarbe();
            i++;
        }
        return alleSpieler;
    }

    public Spieler getSpieler(Farbe farbe) {
        for (Spieler spieler : spieler) {
            if (spieler.getFarbe().equals(farbe)) {
                return spieler;
            }
        }
        return null;
    }

    public Figur getFigur(String figur) {
        for (Spieler spieler: spieler) {
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
        Paradiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT);
        Wuerfel augenWuerfel = new Wuerfel(6);
        Wuerfel farbenWuerfel = new Wuerfel(spiel.getAlleSpieler());
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        int rundenCounter = 1;
        String currentSpielerAmZug;
        String figur;
        String input;
        int wurf;

        while (spiel.getGewinner() == null) {
            for (Feld feld : spiel.getSpielfeld()) {
                System.out.println(feld.toString() + feld.figurenToString());
            }
            System.out.println("Runde " + rundenCounter);

            spiel.setFarbeAmZug(farbenWuerfel.farbeWuerfeln());
            System.out.println("Farbe am zug: " + spiel.getFarbeAmZug());

            currentSpielerAmZug = spiel.getSpieler(spiel.getFarbeAmZug()).getFarbe().toString();

            System.out.println("Figur A oder B?");
            input = scan.nextLine().toUpperCase();

            wurf = augenWuerfel.zahlWuerfeln();
            System.out.println("Wurf: " + wurf);

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
