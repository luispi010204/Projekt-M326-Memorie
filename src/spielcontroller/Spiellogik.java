package spielcontroller;


import data.DataHandler;
import model.Einstellungen;
import model.Spieler;
import model.Spielfeld;

/**
 * Diese Klasse beinhaltet die gesamte Spiellogik
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class Spiellogik {
    private DataHandler dataHandler;
    private Spielfeld spielfeld;
    private Einstellungen einstellungen;
    private Timer timer;
    private Spieler spieler1;
    private Spieler spieler2;

    /**
     * Konstruktor
     */
    public Spiellogik(){
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


    /**
     * main-Methode
     * @param args
     */
    public static void main(String[] args) {
        Spiellogik spiel = new Spiellogik();
        spiel.startGame();
    }

    /**
     * Beinhaltet die Spiellogik
     */
    public void startGame(){
        //TODO
    }

}
