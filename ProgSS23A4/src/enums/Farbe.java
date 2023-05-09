package de.ostfalia.prog.ss23.enums;

import javafx.scene.paint.Color;

/**
 * Die Aufzählungsklasse de.ostfalia.prog.ss22.enums.Farbe definiert die möglichen Farben für die Figuren
 * des Paradiesspiels und auch die Reihenfolge, wie sie vergeben werden. Dabei
 * ist zu achten, dass jede Figur nur eine de.ostfalia.prog.ss22.enums.Farbe haben darf und dass eine de.ostfalia.prog.ss22.enums.Farbe
 * maximal einer Figur zugeordnet werden darf (1:1 Beziehung).
 * 
 * 
 * @author D. Dick
 * @since SS 2022
 *
 */
public enum Farbe {
	/**
	 *
	 */
	BLAU(Color.BLUE),
	/**
	 *
	 */
 	GELB(Color.YELLOW),
	/**
	 *
	 */
 	GRUEN(Color.GREEN),
	/**
	 *
	 */
 	ROT(Color.RED),
	/**
	 *
	 */
 	SCHWARZ(Color.BLACK),
	/**
	 *
	 */
 	WEISS(Color.WHITE);

	private Color color;

	Farbe(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
