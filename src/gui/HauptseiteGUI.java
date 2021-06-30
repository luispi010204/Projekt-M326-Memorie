package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Dies ist eine Klasse die das GUI erstellt.
 *
 * @Autor Luigi Spina
 * @Datum 30.6.21
 *
 */
public class HauptseiteGUI extends JFrame {

    private JButton buttonSpielen;
    private JButton buttonEinstellungen;
    private JButton buttonVerlassen;


    JPanel buttonPanel = new JPanel();
    JLabel titel = new JLabel();



    /**
     * das ist der Konstruktor für das GUI
     */
public HauptseiteGUI() {

    this.setTitle("Hauptseite");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //GUI wird geschlossen bei Exit. (x)



    buttonSpielen = new JButton("Spielen");
    buttonEinstellungen = new JButton("Einstellungen");
    buttonVerlassen = new JButton("Verlassen");


    buttonPanel.setLayout(new BorderLayout());

    titel.setText("Memory");
    titel.setFont(new Font("Monaco", Font.ITALIC, 80));

    buttonPanel.setLayout(new GridBagLayout());

    buttonPanel.add(buttonSpielen);
    buttonPanel.add(buttonEinstellungen);
    buttonPanel.add(buttonVerlassen);


    this.getContentPane().add(buttonPanel);
    this.getContentPane().add(titel, BorderLayout.NORTH);



    setResizable(false);
    setVisible(true); //setzt die Sichtbarkeit auf true.
    pack();

}

    public static void main(String[] args) {

    HauptseiteGUI gui = new HauptseiteGUI();
    gui.setSize(800, 600);
    }

}
