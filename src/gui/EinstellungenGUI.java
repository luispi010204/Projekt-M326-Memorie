package gui;

import data.DataHandler;
import model.Einstellungen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modales GUI, das die Änderungsmöglichkeiten der Einstellungen des Spiels beinhaltet
 *
 * @author Martin Düppenbecker
 * @version 1.0
 * @since 30.06.2021
 */

public class EinstellungenGUI extends JDialog {
    private Einstellungen einstellungen;
    private JDialog frame;
    private JButton hauptmenuButton;
    private JPanel einstellungenPanel;
    private JPanel grossesEinstellungenPanel;

    private JComboBox spielmodusComboBox;
    private JLabel spielmodusLabel;

    private JSlider spielfeldgroesseSlider;
    private JLabel spielfeldgroesseLabel;

    private JCheckBox jokerkarteCheckBox;
    private JLabel jokerkarteLabel;

    private JCheckBox bonusstreaksCheckBox;
    private JLabel bonusstreaksLabel;



    /**
     * Konstruktor
     * @param parent
     * @param einstellungen
     */
    public EinstellungenGUI(JFrame parent, Einstellungen einstellungen){
        frame = new JDialog(parent,"Einstellungen", true);
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.einstellungen = einstellungen;

        initComponent();

        addListeners();

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Gibt die geähnderten Einstellungen zurück
     * @return Einstellungen-Objekt
     */
    public Einstellungen getEinstellungen(){
        return einstellungen;
    }

    /**
     * initialisiert das GUI
     */
    private void initComponent(){
        einstellungenPanel = new JPanel(new GridLayout(4,2));
        grossesEinstellungenPanel = new JPanel(new BorderLayout());

        spielmodusLabel = new JLabel("Spielmodus");
        String spielmodi[] = {"Normal", "Profi"};
        spielmodusComboBox = new JComboBox(spielmodi);

        einstellungenPanel.add(spielmodusLabel);
        einstellungenPanel.add(spielmodusComboBox);


        spielfeldgroesseLabel = new JLabel("Spielfeldgrösse");
        spielfeldgroesseSlider = new JSlider(JSlider.HORIZONTAL,6,10,6);
        spielfeldgroesseSlider.setMinorTickSpacing(2);
        spielfeldgroesseSlider.setMajorTickSpacing(2);
        spielfeldgroesseSlider.setSnapToTicks(true);
        spielfeldgroesseSlider.setPaintTicks(true);
        spielfeldgroesseSlider.setPaintLabels(true);

        einstellungenPanel.add(spielfeldgroesseLabel);
        einstellungenPanel.add(spielfeldgroesseSlider);


        jokerkarteLabel = new JLabel("Jokerkarte");
        jokerkarteCheckBox = new JCheckBox();

        einstellungenPanel.add(jokerkarteLabel);
        einstellungenPanel.add(jokerkarteCheckBox);


        bonusstreaksLabel = new JLabel("Bonusstreaks");
        bonusstreaksCheckBox = new JCheckBox();


        if (einstellungen != null){
            spielmodusComboBox.setSelectedIndex(einstellungen.getSchwierigkeitsstufe());
            bonusstreaksCheckBox.setSelected(einstellungen.getBonusstreak());
            spielfeldgroesseSlider.setValue(einstellungen.getSpielfeldGroesse());
            jokerkarteCheckBox.setSelected(einstellungen.getJokerkarten());
        }

        einstellungenPanel.add(bonusstreaksLabel);
        einstellungenPanel.add(bonusstreaksCheckBox);


        hauptmenuButton = new JButton("Hauptmenu");


        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(einstellungenPanel, BorderLayout.CENTER);
        frame.getContentPane().add(hauptmenuButton, BorderLayout.SOUTH);
    }

    /**
     * Fügt die Listeners den Buttons hinzu
     */
    private void addListeners(){
        hauptmenuButton.addActionListener(new HauptmenuButton());
    }


    class HauptmenuButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            einstellungen.setSchwierigkeitsstufe(spielmodusComboBox.getSelectedIndex());
            einstellungen.setBonusstreak(bonusstreaksCheckBox.isSelected());
            einstellungen.setSpielfeldGroesse(spielfeldgroesseSlider.getValue());
            einstellungen.setJokerkarten(jokerkarteCheckBox.isSelected());

            frame.dispose();
        }
    }
}
