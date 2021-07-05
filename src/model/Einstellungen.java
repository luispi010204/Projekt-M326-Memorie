package model;

/**
 * Modelklasse der Einstellungen
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 29.06.2021
 */

public class Einstellungen {
    private int schwierigkeitsstufe;
    private Boolean bonusstreak;
    private int spielfeldGroesse;
    private Boolean jokerkarten;


    /**
     * Default Konstruktor
     */
    public Einstellungen(){
        schwierigkeitsstufe = 0;
        bonusstreak = false;
        spielfeldGroesse = 6;
        jokerkarten = false;
    }

    /**
     * Konstruktor mit den einzelnen Attributen als Parameter
     * @param schwierigkeitsstufe
     * @param bonusstreak
     * @param spielfeldGroesse
     * @param jokerkarten
     */
    public Einstellungen(int schwierigkeitsstufe, Boolean bonusstreak, int spielfeldGroesse, Boolean jokerkarten) {
        this.schwierigkeitsstufe = schwierigkeitsstufe;
        this.bonusstreak = bonusstreak;
        this.spielfeldGroesse = spielfeldGroesse;
        this.jokerkarten = jokerkarten;
    }

    /**
     * Konsturkor mit einem Einstellungsobjekt als Parameter.
     * @param einstellungen
     */
    public Einstellungen(Einstellungen einstellungen){
        schwierigkeitsstufe = einstellungen.getSchwierigkeitsstufe();
        bonusstreak = einstellungen.getBonusstreak();
        spielfeldGroesse = einstellungen.getSpielfeldGroesse();
        jokerkarten = einstellungen.getJokerkarten();
    }


    //Getter & Setter

    /**
     * Gibt die Schwierigkeitsstufe zurück
     * @return int
     */
    public int getSchwierigkeitsstufe() {
        return schwierigkeitsstufe;
    }

    /**
     * Setzt die Schwierigkeitsstufe
     * @param schwierigkeitsstufe
     */
    public void setSchwierigkeitsstufe(int schwierigkeitsstufe) {
        this.schwierigkeitsstufe = schwierigkeitsstufe;
    }

    /**
     * Gibt zurück, ob mit Bonusstreak gespielt werden soll
     * @return Boolean
     */
    public Boolean getBonusstreak() {
        return bonusstreak;
    }

    /**
     * Setzt, ob mit Bonusstreak gespielt werden soll
     * @param bonusstreak
     */
    public void setBonusstreak(Boolean bonusstreak) {
        this.bonusstreak = bonusstreak;
    }

    /**
     * Gibt die Spielfeldgrösse zurück
     * @return int
     */
    public int getSpielfeldGroesse() {
        return spielfeldGroesse;
    }

    /**
     * Setzt die Spielfeldgroesse
     * Kann nur 6x6, 8x8 oder 10x10 sein
     * @param spielfeldGroesse
     */
    public void setSpielfeldGroesse(int spielfeldGroesse) {
        if (spielfeldGroesse == 6 || spielfeldGroesse == 8 || spielfeldGroesse == 10){
            this.spielfeldGroesse = spielfeldGroesse;
        }
    }

    /**
     * Gibt zurück, ob mit Jokerkarten gespielt werden soll
     * @return Boolean
     */
    public Boolean getJokerkarten() {
        return jokerkarten;
    }

    /**
     * Setzt, ob mit Jokerkarten gespielt werden soll
     * @param jokerkarten
     */
    public void setJokerkarten(Boolean jokerkarten) {
        this.jokerkarten = jokerkarten;
    }
}
