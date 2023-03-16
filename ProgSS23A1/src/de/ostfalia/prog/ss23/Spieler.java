package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;

public class Spieler {
    private Farbe farbe;
    private final Figur[] figuren = new Figur[2];
    private boolean gewinner;
    private boolean amZug;

    public Spieler(Farbe farbe) {
        setFarbe(farbe);
        this.figuren[0] = new Figur(farbe + "-A");
        this.figuren[1] = new Figur(farbe + "-B");
        setGewinner(false);
        setAmZug(false);
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setGewinner(boolean gewinner) {
        this.gewinner = gewinner;
    }

    public boolean isGewinner(){
        return gewinner;
    }

    public void setAmZug(boolean amZug){
        this.amZug = amZug;
    }

    public boolean isAmZug(){
        return amZug;
    }

    public Figur getFigur(String name) {
        for (Figur figur : figuren) {
            if (figur.getName().equals(name)) {
                return figur;
            }
        }
        return null;
    }

    public Figur[] getFiguren(){
        return figuren;
    }
}
