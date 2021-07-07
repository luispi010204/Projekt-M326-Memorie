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

    /**
     * Gibt die ID zurück
     * @return id ist der Index zur Referenz des Bildes
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt die Punkte zurück
     * @return punkte
     */
    public int getPunkte() {
        return punkte;
    }

    /**
     * Setzt die Punkte
     * @param punkte
     */
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
