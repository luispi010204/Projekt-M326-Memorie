package JUnit;

import org.junit.Before;
import org.junit.Test;
import spielcontroller.Spiellogik;
import static org.junit.Assert.*;


/**
 * Diese Klasse testet die Spiellogik
 *
 * @author Luigi Spina
 * @version 1.0
 * @since 07.07.2021
 */


public class SpielTest {

    private static Spiellogik spiellogik;


    @Before
    public void setUp() {
       spiellogik  = new Spiellogik();


    }

   @Test
    public void werBeginnt() {
    //TODO
    }
}
