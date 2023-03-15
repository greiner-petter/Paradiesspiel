package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.interfaces.IParadiesspiel;

public class Paradiesspiel implements IParadiesspiel {
    private Spieler[] spieler;

    public Paradiesspiel(Farbe... farben) {
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
        for (Spieler spieler : spieler) {
            if (spieler.getFiguren()[0].getName().equals(figur)){
                for (int i : augenzahlen) {
                    spieler.getFigur(figur).setPosition(getFigurposition(figur) + i);
                }
                return true;
            } else if (spieler.getFiguren()[1].getName().equals(figur)) {
                for (int i : augenzahlen) {
                    spieler.getFigur(figur).setPosition(getFigurposition(figur) + i);
                }
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

    public static void main(String[] args) {
        Paradiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT);
        Wuerfel augenWuerfel = new Wuerfel(6);
        Wuerfel farbenWuerfel = new Wuerfel(spiel.getAlleSpieler());
        Spielfeld spielfeld = new Spielfeld(64);
        while (spiel.getGewinner() == null) {
            spiel.setFarbeAmZug(farbenWuerfel.farbeWuerfeln());
            for (Figur figur : spiel.getSpieler(spiel.getFarbeAmZug().toString()).getFiguren()) {
                spiel.bewegeFigur(figur.toString(), augenWuerfel.zahlWuerfeln());
                System.out.println(figur.getPosition());
            }
        }
    }
}
