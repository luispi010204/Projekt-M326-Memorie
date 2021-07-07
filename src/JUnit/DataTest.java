package JUnit;

import data.DataHandler;
import model.Spieler;
import org.junit.Before;
import org.junit.Test;
import spielcontroller.Spiellogik;

import static org.junit.Assert.*;

/**
 * Diese Klasse testet den DataHandler
 *
 * @author Luigi Spina
 * @version 1.0
 * @since 07.07.2021
 */

public class DataTest {

    private static DataHandler dataHandler;
    private static Spieler spieler1;
    private static Spieler spieler2;


    @Before
    public void setUp() {
        dataHandler = new DataHandler();
        spieler1 = new Spieler("Luigi", 20);
        spieler2 = new Spieler("Martin", 16);

    }

    @Test
public void stimmtDerPunktestand(){

        assertEquals(20, spieler1.getPunktestand());
        assertEquals(16, spieler2.getPunktestand());
    }

    @Test
    public void testName(){

        assertEquals("Luigi", spieler1.getName());
        assertEquals("Martin", spieler2.getName());
    }



}
