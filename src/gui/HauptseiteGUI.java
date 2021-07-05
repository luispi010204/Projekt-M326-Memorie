package gui;

import spielcontroller.Spiellogik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dies ist eine Klasse die das GUI erstellt.
 *
 * @Autor Luigi Spina
 * @Datum 30.6.21
 *
 */
public class HauptseiteGUI extends JFrame {

    private HauptseiteGUI instance;
    private Spiellogik spiellogik;
    private JButton buttonSpielen;
    private JButton buttonEinstellungen;
    private JButton buttonVerlassen;


    JPanel buttonPanel = new JPanel();
    JLabel titel = new JLabel();



    /**
     * das ist der Konstruktor für das GUI
     */
public HauptseiteGUI(Spiellogik spiellogik) {

    this.setTitle("Hauptseite");

    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //GUI wird nicht geschlossen bei Exit. (x)


    this.instance = this;
    this.spiellogik = spiellogik;

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


    addListeners();


    setResizable(false);
    setVisible(true); //setzt die Sichtbarkeit auf true.
    pack();
    setSize(800, 600);

}



    private void addListeners(){
        buttonSpielen.addActionListener(new SpielenButton());
        buttonEinstellungen.addActionListener(new EinstellungenButton());
        buttonVerlassen.addActionListener(new VerlassenButton());
    }


    class SpielenButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            spiellogik.startGame();
        }
    }

    class EinstellungenButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            spiellogik.startEinstellungen(instance);
        }
    }

    class VerlassenButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

}
