package model;

import model.Memorykarte;

import java.util.Vector;

/**
 * Diese Klasse beinhaltet die Memorykarten und ist für diesen Verwaltung verantwortlich
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class Spielfeld {
    private Vector<Memorykarte> memorykarten;


    /**
     * Konstruktor
     */
    public Spielfeld(){
        memorykarten = new Vector<>();
        randomKarten();
    }

    /**
     * Mischelt die Karten und fügt sie dem Vector hinzu
     */
    private void randomKarten(){
        //TODO
    }
}
