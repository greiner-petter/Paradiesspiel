package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.interfaces.IParadiesspiel;

public class Paradiesspiel implements IParadiesspiel {

    private Farbe farbeAmZug;
    private Farbe gewinner;
    private Farbe[] farben;
    private Spieler[] spieler;

    public Paradiesspiel(Farbe... farben) {
        this.farben = new Farbe[farben.length];
        this.spieler = new Spieler[farben.length];
        int i = 0;
        for (Farbe farbe : farben) {
            this.farben[i] = farbe;
            this.spieler[i] = new Spieler(farbe.toString(), farbe);
            i++;
        }
        setFarbeAmZug(farben[0]);
    }

    @Override
    public Farbe getFarbeAmZug() {
        return farbeAmZug;
    }

    @Override
    public void setFarbeAmZug(Farbe farbe) {
        this.farbeAmZug = farbe;
    }

    @Override
    public int getFigurposition(String figur) {
        int pos = -1;
        for (Spieler spieler : spieler) {
             if (spieler.getFigur()[0].getName().equals(figur.toUpperCase())) {
                 pos = spieler.getFigur()[0].getPosition();
             } else if (spieler.getFigur()[1].getName().equals(figur.toUpperCase())) {
                 pos = spieler.getFigur()[1].getPosition();
             }
        }
        return pos;
    }

    @Override
    public boolean bewegeFigur(String figur, int... augenzahlen) {
        return false;
    }

    @Override
    public Farbe getGewinner() {
        return gewinner;
    }

    @Override
    public Farbe[] getAlleSpieler() {
        return farben;
    }

    public static void main(String[] args) {
        Paradiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB, Farbe.GRUEN, Farbe.ROT);
        Wuerfel augenWuerfel = new Wuerfel(6);
        Wuerfel farbenWuerfel = new Wuerfel(spiel.getAlleSpieler());
        System.out.println(spiel.getFigurposition("blau-A"));
        System.out.println(augenWuerfel.zahlWuerfeln());
        System.out.println(farbenWuerfel.farbeWuerfeln());
    }
}
