package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.exceptions.*;
import de.ostfalia.prog.ss23.felder.*;

import java.util.Random;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args) throws FalscheSpielerzahlException {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        Paradiesspiel spiel = new Paradiesspiel(Farbe.GELB, Farbe.BLAU);

        Wuerfel zahlenwuerfel = new Wuerfel(6);
        Wuerfel farbenwuerfel = new Wuerfel(spiel.getAlleSpieler());

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

            wurf[0] = zahlenwuerfel.zahlWuerfeln();
            wurf[1] = zahlenwuerfel.zahlWuerfeln();
            System.out.println("Wurf: " + wurf[0] + ", " + wurf[1]);

            System.out.println("Figur A oder B?");
            input = scan.nextLine().toUpperCase();

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
