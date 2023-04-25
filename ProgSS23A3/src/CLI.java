package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.exceptions.*;
import de.ostfalia.prog.ss23.felder.*;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) throws FalscheSpielerzahlException, IOException {
        Paradiesspiel spiel = spielErstellen();
        Wuerfel zahlenwuerfel = new Wuerfel(6);
        Wuerfel farbenwuerfel = new Wuerfel(spiel.getAlleSpieler());
        int rundenCounter = 1;
        int[] wurf = new int[2];
        while (spiel.getGewinner() == null) {
            System.out.println("Runde " + rundenCounter);
            boolean mussAussetzen = true;
            while (mussAussetzen) {
                spiel.setFarbeAmZug(farbenwuerfel.farbeWuerfeln());
                if (spiel.getSpieler(spiel.getFarbeAmZug()).getAussetzen()){
                    System.out.println(spiel.getFarbeAmZug() + " muss Aussetzen\n");
                    spiel.getSpieler(spiel.getFarbeAmZug()).setAussetzen(false);
                } else {
                    mussAussetzen = false;
                }
            }
            System.out.println("\nFarbe am zug: " + spiel.getFarbeAmZug());
            wurf[0] = zahlenwuerfel.zahlWuerfeln();
            wurf[1] = zahlenwuerfel.zahlWuerfeln();
            System.out.println("Wurf: " + wurf[0] + ", " + wurf[1]);
            System.out.println("\nWelche Figur?");
            int i = 1;
            for (Figur figur : spiel.getSpieler(spiel.getFarbeAmZug()).getFiguren()) {
                System.out.println(i + ". " + figur.getName());
                i++;
            }
            System.out.println("0. Spiel Speichern");
            int input = SCAN.nextInt();
            if (input == 0) {
                spiel.speichern("ProgSS23A3/gespeichertesSpiel.txt");
                break;
            }
            String figurZuBewegen = spiel.getSpieler(spiel.getFarbeAmZug()).getFiguren()[input - 1].getName();
            spiel.bewegeFigur(figurZuBewegen, wurf);
            for (Feld feld : spiel.getSpielfeld()) {
                System.out.println(feld.toString() + feld.figurenToString());
            }
            System.out.println(figurZuBewegen + " steht auf Feld " + spiel.getFigurposition(figurZuBewegen) + "\n");
            rundenCounter++;
        }
        System.out.println(spiel.getGewinner() + " gewinnt");
        SCAN.close();
    }

    private static Paradiesspiel spielErstellen() throws FalscheSpielerzahlException, IOException {
        while (true) {
            System.out.println("1. Neues Spiel");
            System.out.println("2. Spiel Laden");
            int input = SCAN.nextInt();
            if (input == 1) {
                System.out.println("1. Paradiesspiel");
                System.out.println("2. paradiesspielSommer");
                input = SCAN.nextInt();
                while (true) {
                    if (input == 1) {
                        return neuesParadiesspiel();
                    } else if (input == 2) {
                        return neuesParadiesspielSommer();
                    } else {
                        System.out.println("Unguelige Eingabe");
                    }
                }
            } else if (input == 2) {
                return (Paradiesspiel) Paradiesspiel.laden("ProgSS23A3/gespeichertesSpiel.txt");
            } else {
                System.out.println("Unguelige Eingabe");
            }
        }
    }

    private static Paradiesspiel neuesParadiesspiel() {
        boolean[] spieltMit = new boolean[Farbe.values().length];
        Farbe[] mitspieler;
        int input;
        while (true) {
            System.out.println("Welche Spieler spielen mit?");
            for (int i = 0; i < Farbe.values().length; i++) {
                System.out.print(i+1 + ". " + Farbe.values()[i]);
                if (spieltMit[i]) {
                    System.out.println(" spielt mit");
                } else {
                    System.out.println();
                }
            }
            System.out.println("\n0. Fertig");
            input = SCAN.nextInt();
            if (input == 0) {
                int counter = 0;
                for (boolean b : spieltMit) {
                    if (b) {
                        counter++;
                    }
                }
                mitspieler = new Farbe[counter];
                int j = 0;
                for (int i = 0; i < spieltMit.length; i++) {
                    if (spieltMit[i]) {
                        mitspieler[j] = Farbe.values()[i];
                        j++;
                    }
                }
                try {
                    return new Paradiesspiel(mitspieler);
                } catch (FalscheSpielerzahlException e) {
                    System.out.println("Zu wenig Spieler");
                }
            } else if (input > Farbe.values().length) {
                System.out.println("Unguelige Eingabe");
            } else {
                spieltMit[input-1] = !spieltMit[input-1];
            }
        }
    }
    private static Paradiesspiel neuesParadiesspielSommer() {
        boolean[] spieltMit = new boolean[Farbe.values().length];
        Farbe[] mitspieler;
        int input;
        while (true) {
            System.out.println("Welche Spieler spielen mit?");
            for (int i = 0; i < Farbe.values().length; i++) {
                System.out.print(i+1 + ". " + Farbe.values()[i]);
                if (spieltMit[i]) {
                    System.out.println(" spielt mit");
                } else {
                    System.out.println();
                }
            }
            System.out.println("0. Fertig");
            input = SCAN.nextInt();
            if (input == 0) {
                int counter = 0;
                for (boolean b : spieltMit) {
                    if (b) {
                        counter++;
                    }
                }
                mitspieler = new Farbe[counter];
                int j = 0;
                for (int i = 0; i < spieltMit.length; i++) {
                    if (spieltMit[i]) {
                        mitspieler[j] = Farbe.values()[i];
                        j++;
                    }
                }
                try {
                    return new ParadiesspielSommer(mitspieler);
                } catch (FalscheSpielerzahlException e) {
                    System.out.println("Zu wenig Spieler");
                }
            } else if (input > Farbe.values().length) {
                System.out.println("Unguelige Eingabe");
            } else {
                spieltMit[input-1] = !spieltMit[input-1];
            }
        }
    }
}
