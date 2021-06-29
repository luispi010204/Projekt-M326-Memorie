package model;

/**
 * Modelklasse der Memorykarte
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class Memorykarte {
    private String id;
    private int punkte;
    private final int BASISPUNKTE = 1;


    /**
     * Konstruktor
     * @param id
     */
    public Memorykarte(String id){
        this.id = id;
        punkte = BASISPUNKTE;
    }


    /**
     * Fügt zusätzliche Punkte zu einer Karte hinzu
     * eigentlich für Streaks gedacht, funktioniert aber auch für Jokerkarte
     * @param hoehe
     */
    public void streak(int hoehe){
        punkte = BASISPUNKTE + hoehe;
    }
}
