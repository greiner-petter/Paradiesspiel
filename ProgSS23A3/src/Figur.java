package de.ostfalia.prog.ss23;

public class Figur {
    private int position;
    private final String name;
    private boolean paradies;
    private int[] wurf;
    private final Spieler spieler;

    public Figur(String name, Spieler spieler) {
        setPosition(0);
        this.name = name;
        setParadies(false);
        setWurf(new int[]{0, 0});
        this.spieler = spieler;
    }

    public void setPosition(int position) {

        this.position = position;
    }

    public int getPosition() {

        return position;
    }

    public String getName() {

        return name;
    }

    public void setParadies(Boolean paradies) {

        this.paradies = paradies;
    }

    public boolean getParadies() {

        return paradies;
    }

    public void setWurf(int[] wurf) {

        this.wurf = wurf;
    }

    public int[] getWurf() {

        return wurf;
    }

    public Spieler getSpieler() {
        return spieler;
    }
}
