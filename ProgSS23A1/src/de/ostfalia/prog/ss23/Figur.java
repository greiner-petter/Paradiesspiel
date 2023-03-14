package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;

public class Figur {
    private Farbe farbe;
    private int position;
    private String name;

    public Figur(Farbe farbe, String name) {
        setFarbe(farbe);
        setPosition(0);
        setName(name);
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
