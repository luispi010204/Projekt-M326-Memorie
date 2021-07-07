package data;

import model.Einstellungen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

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
     * Ladet den Punktestand und die Einstellungen vom CSV-File
     * Gibt true zurück, wenn ein Spielstand gefunden wurde, ansonsten false
     * @return boolean
     */
    public Boolean loadGame(){
        Boolean returnValue = false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("resources/saves/saves.csv"));
            String[] line = reader.readLine().split(",");
            if (line.length == 2){
                punktezahlSpieler1 = Integer.parseInt(line[0]);
                punktezahlSpieler2 = Integer.parseInt(line[1]);
                returnValue = true;
            }
            line = reader.readLine().split(",");
            if (line.length == 4){
                if (einstellungen == null){
                    einstellungen = new Einstellungen();
                }
                einstellungen.setSchwierigkeitsstufe(Integer.parseInt(line[0]));
                einstellungen.setBonusstreak(Boolean.parseBoolean(line[1]));
                einstellungen.setSpielfeldGroesse(Integer.parseInt(line[2]));
                einstellungen.setJokerkarten(Boolean.parseBoolean(line[3]));
            }
            else {
                returnValue = false;
            }
        }
        catch (NullPointerException e){

        }
        finally {
            return returnValue;
        }
    }

    /**
     * Speichert den Punktestand und die Einstellungen in ein CSV-File
     * @param punktezahlSpieler1
     * @param punktezahlSpieler2
     * @param einstellungen
     */
    public void saveGame(int punktezahlSpieler1, int punktezahlSpieler2, Einstellungen einstellungen){
        try {
            File savesFile = new File("resources/saves/saves.csv");
            savesFile.createNewFile();      //Wenn es schon existiert, dann macht es nichts. Ansonsten erstellt es das File

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(savesFile.getPath()));
            writer.write(punktezahlSpieler1 + "," + punktezahlSpieler2);
            this.punktezahlSpieler1 = punktezahlSpieler1;
            this.punktezahlSpieler2 = punktezahlSpieler2;
            writer.newLine();
            writer.write(einstellungen.getSchwierigkeitsstufe() + "," + einstellungen.getBonusstreak() + "," + einstellungen.getSpielfeldGroesse() + "," + einstellungen.getJokerkarten());
            this.einstellungen = einstellungen;
            writer.close();


        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Gibt einen Vector mit zufällig von resources/bilder ausgewählten Bildern zurück
     * Die Anzahl der Bilder kann über den Parameter "anzahl" bestimmt werden
     * @param anzahl
     * @return bilderIcons
     */
    public Vector<ImageIcon> randomBilder(int anzahl){
        Vector<ImageIcon> imageIcons = new Vector<>(anzahl);

        //Listet die Pfäde aller Bilder auf
        String[] bilderPath = new File("resources/bilder/").list();

        //Konvertiert diese in eine Liste, damit sie random sortiert werden kann
        List randomPaths = Arrays.asList(bilderPath);
        Collections.shuffle(randomPaths);

        //Schneidet die Liste in die gewünschte Länge und konvertiert sie zum Array zurück
        //Leider ist es ein bisschen kompliziert Object[] zu String[] umzuwandeln
        Object[] tmpArr = randomPaths.subList(0, anzahl).toArray();
        bilderPath = Arrays.copyOf(tmpArr, tmpArr.length, String[].class);


        try {
            for (int i = 0; i < anzahl; i++) {
                //Füllt den Vector mit den Bildern ab
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("resources/bilder/" + bilderPath[i])));
                icon.setImage(icon.getImage().getScaledInstance(80,80,Image.SCALE_FAST));
                imageIcons.add(icon);

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return imageIcons;

    }

}
