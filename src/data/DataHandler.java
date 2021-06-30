package data;

import model.Einstellungen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Datahandler für das Speichern und Laden der Saves vom Spielstand
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private Einstellungen einstellungen;
    private int punktezahlSpieler1;
    private int punktezahlSpieler2;

    /**
     * Konstruktor
     */
    public DataHandler(){
        punktezahlSpieler1 = 0;
        punktezahlSpieler2 = 0;
    }


    //Getter

    /**
     * Gibt den gespeicherten Punktestand des 1.Spielers zurück
     * @return punktestand
     */
    public int getPunktezahlSpieler1() {
        return punktezahlSpieler1;
    }

    /**
     * Gibt den gespeicherten Punktestand des 2.Spielers zurück
     * @return punktestand
     */
    public int getPunktezahlSpieler2() {
        return punktezahlSpieler2;
    }

    /**
     * Gibt die gespeicherten EInstellungen zurück
     * @return Einstellungen
     */
    public Einstellungen getEinstellungen(){
        return einstellungen;
    }

    /**
     * Gibt die Instanz des Datahandlers zurück
     * @return Instanz
     */
    public static DataHandler getInstance(){
        return instance;
    }


    //Methoden

    /**
     * Ladet den Punktestand vom CSV-File
     * Gibt true zurück, wenn ein spielstand gefunden wurde, ansonsten false
     * @return boolean
     */
    public Boolean loadGame(){
        Boolean returnValue = false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("/resources/saves/saves.csv"));
            String[] line = reader.readLine().split(",");
            if (line.length == 2){
                punktezahlSpieler1 = Integer.parseInt(line[0]);
                punktezahlSpieler2 = Integer.parseInt(line[1]);
                returnValue = true;
            }
        }
        catch (Exception e){
            //nichts
        }
        finally {
            return returnValue;
        }
    }

    /**
     * Speichert den Punktestand in ein CSV-File
     * @param punktezahlSpieler1
     * @param punktezahlSpieler2
     */
    public void saveGame(int punktezahlSpieler1, int punktezahlSpieler2){
        try {
            File savesFile = new File("../../resources/saves/saves.csv");
            savesFile.createNewFile();      //Wenn es schon existiert, dann macht es nichts. Ansonsten erstellt es das File

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(savesFile.getPath()));
            writer.write(punktezahlSpieler1 + ", " + punktezahlSpieler2);
            writer.close();


        } catch (Exception e){
            //nichts
        }

    }
}
