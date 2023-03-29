package de.ostfalia.prog.ss23;

public class Figur {
    private int position;
    private String name;
    private boolean paradies;
    private int[] wurf;

    public Figur(String name) {
        setPosition(0);
        setName(name);
        setParadies(false);
        setWurf(new int[]{0, 0});
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
}
