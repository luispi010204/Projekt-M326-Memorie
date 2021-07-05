package model;

/**
 * Modelklasse der Memorykarte
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class Memorykarte {
    private int id;
    private int punkte;
    private final int BASISPUNKTE = 1;


    //Konstruktoren

    /**
     * Konstruktor
     * @param id
     */
    public Memorykarte(int id){
        this.id = id;
        punkte = BASISPUNKTE;
    }

    /**
     * Konstruktor inklusive Punkte, für Jokerkarte gedacht
     * @param id
     * @param punkte
     */
    public Memorykarte(int id, int punkte){
        this.id = id;
        this.punkte = punkte;
    }


    //Getter & Setter

    public int getId() {
        return id;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }


    //Methoden

    /**
     * Multipliziert die jetztige Punktzahl mit der Streak
     * @param hoehe
     */
    public void streak(int hoehe){
        punkte = BASISPUNKTE * hoehe;
    }
}
