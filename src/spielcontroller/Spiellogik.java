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
import java.util.TimerTask;
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
    private Vector<JButton> buttons;
    private Vector<ImageIcon> imageIcons;
    private Einstellungen einstellungen;
    private java.util.Timer timer;
    private Spieler spieler1;
    private Spieler spieler2;

    private Spieler spielerAnDerReihe;
    private Memorykarte letzteKarte;
    private int letzerButtonIndex;
    private int streak = 0;
    private int count = 0;

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
            timer = new java.util.Timer();
        }

        spieler1 = new Spieler("Spieler 1", dataHandler.getPunktezahlSpieler1());
        spieler2 = new Spieler("Spieler 2", dataHandler.getPunktezahlSpieler2());
        spielerAnDerReihe = spieler1;

    }


    //Getter & Setter

    public Spiellogik getInstance(){
        return instance;
    }

    public int getLetzerButtonIndex(){
        return letzerButtonIndex;
    }

    public int getSpielstaende(int spielerNr){
        if (spielerNr == 1){
            return spieler1.getPunktestand();
        }
        return spieler2.getPunktestand();

    }

    //Methoden

    public int buttonGedrueckt(int indexOfButton){
        int code = -1;
        if (letzteKarte == null){
            letzteKarte = memorykarten.get(imageIcons.indexOf(buttons.get(indexOfButton).getIcon()));
            letzerButtonIndex = indexOfButton;
            code = 0;
            if (einstellungen.getSchwierigkeitsstufe() == 2){
                //Timer erstellten
            }
        }
        else {
            Memorykarte jetzigeKarte = memorykarten.get(imageIcons.indexOf(buttons.get(indexOfButton).getIcon()));
            code = 1;
            if (letzteKarte.getId() == jetzigeKarte.getId()){
                if (einstellungen.getBonusstreak()){
                    jetzigeKarte.streak(streak);
                    streak++;
                }
                spielerAnDerReihe.punkteHinzufuegen(jetzigeKarte.getPunkte());
                spielerAnDerReihe = spielerAnDerReihe == spieler1 ? spieler2 : spieler1;    //Damit er weiterspielen kann
                count++;
                code = 2;
            }
            if (einstellungen.getBonusstreak() && code != 2){
                streak = 0;
            }
            spielerAnDerReihe = spielerAnDerReihe == spieler1 ? spieler2 : spieler1;
            letzteKarte = null;

            if (count >= memorykarten.size() / 2){  //Spiel ist vorbei
                dataHandler.saveGame(spieler1.getPunktestand(), spieler2.getPunktestand(), einstellungen);
                code = 3;
            }
        }

        return code;    //0 = 1.Zug  |  1 = 2.Zug  |  2 = 2.Zug, Paar gefunden  |  3 = Spiel fertig
    }

    private int sekunden = 0;
    private void timerPanel(int s){
        this.sekunden = s;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setTimerPanel();
            }
        };
        timer.scheduleAtFixedRate(task,0, 1000);

    }

    private void setTimerPanel(){
        if (sekunden <= 1){
            spielfeldGUI.setTimer(sekunden);
            sekunden--;
        }
        else {
            timer.cancel();
        }

    }

    /**
     * Beinhaltet die Spiellogik
     */
    public void startGame(){
        //Wenn Schwierigkeitsstufe 1 ist, heisst das, dass es profimodus ist. Dies heisst, dass es einen Timer gibt
        spielfeldGUI = new SpielfeldGUI(this,kartenAufbereiten(),einstellungen.getSchwierigkeitsstufe() == 1);


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
    private Vector<JButton> kartenAufbereiten(){
        Vector<JButton> buttons = new Vector<>((int)Math.pow(einstellungen.getSpielfeldGroesse(), 2));
        Vector<ImageIcon> imageIcons = dataHandler.randomBilder((int)(Math.pow(einstellungen.getSpielfeldGroesse(), 2) / 2));

        memorykarten = new Vector<>((int)Math.pow(einstellungen.getSchwierigkeitsstufe(), 2));

        //Füllt den memorykarten-Vector mit neuen Memorykarten, die in der ID den Index des Bildes, im bilder-Vector, gespeichert hat
        for (int i = 0; i < imageIcons.size(); i++) {
            if (einstellungen.getJokerkarten()){
                if (Math.random() > 0.9){   //Chance von 1 zu 10, dass es eine Jokerkarte gibt
                    memorykarten.add(new Memorykarte(i,2));
                    memorykarten.add(new Memorykarte(i,2));
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
        //damit sie auf dem Spielfeld random verteilt sind
        Collections.shuffle(memorykarten);

        for (int i = 0; i < memorykarten.size(); i++) {
            buttons.add(new JButton(imageIcons.get(memorykarten.get(i).getId())));
        }

        this.buttons = buttons;
        this.imageIcons = imageIcons;

        return buttons;
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
