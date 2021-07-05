package spielcontroller;


import data.DataHandler;
import gui.EinstellungenGUI;
import gui.HauptseiteGUI;
import gui.SpielfeldGUI;
import model.Einstellungen;
import model.Memorykarte;
import model.Spieler;
import model.Spielfeld;

import javax.swing.*;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Diese Klasse beinhaltet die gesamte Spiellogik
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class Spiellogik {
    private SpielfeldGUI spielfeldGUI;
    private Spiellogik instance;
    private DataHandler dataHandler;
    private Spielfeld spielfeld;
    private Vector<Memorykarte> memorykarten;
    private Einstellungen einstellungen;
    private Timer timer;
    private Spieler spieler1;
    private Spieler spieler2;

    /**
     * Konstruktor
     */
    public Spiellogik(){
        instance = this;
        dataHandler = DataHandler.getInstance();
        spielfeld = new Spielfeld();

        if (dataHandler.loadGame()){
            einstellungen = new Einstellungen(dataHandler.getEinstellungen());
        }
        else {
            einstellungen = new Einstellungen();
        }

        if (einstellungen.getSchwierigkeitsstufe() == 2){
            timer = new Timer();
        }

        spieler1 = new Spieler("Spieler 1", dataHandler.getPunktezahlSpieler1());
        spieler2 = new Spieler("Spieler 2", dataHandler.getPunktezahlSpieler2());

    }


    //Getter & Setter

    public Spiellogik getInstance(){
        return instance;
    }



    //Methoden

    /**
     * Beinhaltet die Spiellogik
     */
    public void startGame(){
        System.out.println("Spiel wurde gestartet");    //DEBUG

        //Wenn Schwierigkeitsstufe 1 ist, heisst das, dass es profimodus ist. Dies heisst, dass es einen Timer gibt
        spielfeldGUI = new SpielfeldGUI(kartenAufbereiten(), einstellungen.getSchwierigkeitsstufe() == 1 ? true : false);
    }

    /**
     * Startet die Einstellungen
     * @param parentOfEinstellungen
     */
    public void startEinstellungen(JFrame parentOfEinstellungen) {
        if (einstellungen == null) {
            einstellungen = new Einstellungen();
        }
        EinstellungenGUI einstellungenGUI = new EinstellungenGUI(parentOfEinstellungen, einstellungen);
        einstellungen = einstellungenGUI.getEinstellungen();
    }

    /**
     * Speichert den Spielstand
     */
    public void speichern(){
        dataHandler.saveGame(spieler1.getPunktestand(),spieler2.getPunktestand(), einstellungen);
    }

    /**
     * Dient, im Kern, als Zwischenmethoden zwischen GUI's und der randomBilder()-Methode im DataHandler
     * Jedoch wird hier auch gleich der Memorykarten-Vector, mit der jeweiligen Referenz zum Bild in der Memorykarte, erstellt
     * @return imageIcons
     */
    public Vector<ImageIcon> kartenAufbereiten(){
        Vector<ImageIcon> imageIcons = dataHandler.randomBilder((int)(Math.pow(einstellungen.getSpielfeldGroesse(), 2) / 2));

        memorykarten = new Vector<>((int)Math.pow(einstellungen.getSchwierigkeitsstufe(), 2));

        //Füllt den memorykarten-Vector mit neuen Memorykarten, die in der ID den Index des Bildes, im bilder-Vector, gespeichert hat
        for (int i = 0; i < imageIcons.size(); i++) {
            if (einstellungen.getJokerkarten()){
                if (Math.random() > 0.9){   //Chance von 1 zu 10, dass es eine Jokerkarte gibt
                    memorykarten.add(new Memorykarte(i,2));
                    //memorykarten.add(new Memorykarte(i,2));   --???   Macht man das 2 mal??????????????????
                    //memorykarten.add(new Memorykarte(i));  <-- Ansonsten so   //TODO
                }
                else {
                    memorykarten.add(new Memorykarte(i));
                    memorykarten.add(new Memorykarte(i));
                }
            }
            else {
                memorykarten.add(new Memorykarte(i));
                memorykarten.add(new Memorykarte(i));
            }

        }

        return imageIcons;
    }


    //Main

    /**
     * main-Methode
     * @param args
     */
    public static void main(String[] args) {
        Spiellogik spiellogik = new Spiellogik();
        new HauptseiteGUI(spiellogik.getInstance());


        //spiellogik.speichern();
    }
}
