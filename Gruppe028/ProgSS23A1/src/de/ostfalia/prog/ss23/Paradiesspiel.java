package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.felder.Felder;
import de.ostfalia.prog.ss23.interfaces.IParadiesspiel;

import java.util.Scanner;

public class Paradiesspiel implements IParadiesspiel {
    private final Spieler[] spieler;
    private final Spielfeld spielfeld;

    public Paradiesspiel(Farbe... farben) {
        this.spielfeld = new Spielfeld(64);
        this.spieler = new Spieler[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.spieler[i] = new Spieler(farbe);
            i++;
        }
    }

    @Override
    public Farbe getFarbeAmZug() {
        for (Spieler spieler : spieler) {
            if (spieler.isAmZug()) {
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
        boolean kannVorwaerts = true;
        for (Spieler spieler : spieler) {
            if (spieler.getFigur(figur) != null){
                for (int augenzahl : augenzahlen) {
                    for (int i = 0; i < augenzahl; i++) {
                        if (kannVorwaerts){
                            if (spieler.getFigur(figur).getPosition() < spielfeld.getGroesse() - 1) {
                                spieler.getFigur(figur).setPosition(getFigurposition(figur) + 1);
                            } else if (spieler.getFigur(figur).getPosition() == spielfeld.getGroesse() - 1)  {
                                break;
                            } else {
                                kannVorwaerts = false;
                            }
                        } else {
                            spieler.getFigur(figur).setPosition(getFigurposition(figur) - 1);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Farbe getGewinner() {
        for (Spieler spieler : spieler) {
            if (spieler.isGewinner()) {
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

    public Spieler getSpieler(String name) {
        for (Spieler spieler : spieler) {
            if (spieler.getFarbe().toString().equals(name)) {
                return spieler;
            }
        }
        return null;
    }

    public Spielfeld getSpielfeld(){
        return spielfeld;
    }

    public static void main(String[] args) {
        Paradiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT);
        Wuerfel augenWuerfel = new Wuerfel(6);
        Wuerfel farbenWuerfel = new Wuerfel(spiel.getAlleSpieler());
        Scanner scan = new Scanner(System.in);
        for (Felder feld : spiel.getSpielfeld().getFelder()) {
            System.out.println(feld.getFeld());
        }
        int i = 1;
        Spieler currentSpielerAmZug;
        while (spiel.getGewinner() == null) {
            System.out.println("round " + i);
            i++;
            spiel.setFarbeAmZug(farbenWuerfel.farbeWuerfeln());
            System.out.println("Farbe am zug: " + spiel.getFarbeAmZug());
            currentSpielerAmZug = spiel.getSpieler(spiel.getFarbeAmZug().toString());
            for (Figur figur : currentSpielerAmZug.getFiguren()) {
                System.out.println(spiel.bewegeFigur(figur.getName(), augenWuerfel.zahlWuerfeln()));

                spiel.getSpielfeld().getFelder()[spiel.getFigurposition(figur.getName())].
                        ereignis(spiel.getSpieler(spiel.getFarbeAmZug().toString()), figur);

                System.out.println(figur.getName());
                System.out.println(figur.getPosition());
            }
//            scan.nextLine();
        }
        System.out.println(spiel.getGewinner() + " gewinnt");
        scan.close();
    }
}
