package model;

/**
 * Modelklasse des Spielers
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class Spieler {
    private String name;
    private int punktestand;
    private int streak;


    /**
     * Konstruktor
     * @param name
     * @param punktestand
     */
    public Spieler(String name, int punktestand){
        this.name = name;
        this.punktestand = punktestand;
    }


    /**
     * Fügt Punkte zum Punktestand hinzu
     * @param punkte
     */
    public void punkteHinzufuegen(int punkte){
        this.punktestand += punkte;
    }
}
