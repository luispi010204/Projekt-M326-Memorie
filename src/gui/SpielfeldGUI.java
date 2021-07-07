package gui;

import spielcontroller.Spiellogik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.Vector;

/**
 * @author Marko Joksimovic
 * @author Martin Düppenbecker
 * @version 1.0
 * @date
 */
public class SpielfeldGUI extends JFrame {

    private Spiellogik spiellogik;
    private JLabel timerPanel;
    private JLabel spielstand1;
    private JLabel spielstand2;
    private JLabel spieler1;
    private JLabel spieler2;
    private JPanel spielraster;
    private JButton hauptmenu;
    private JButton weiterspielen;
    private JPanel untenPanel;
    private JPanel obenPanel;
    private JPanel obenLinks;
    private JPanel obenRechts;
    private JPanel rasterPanel;
    private Font labelFont;

    private Vector<JButton> buttons;
    private Vector<JButton> origButtons;
    JButton alterAlterButton;
    JButton alterButton;
    int letzerCode;

    /**
     * Konstruktor
     * @param spiellogik
     * @param buttons
     * @param timer
     */
    public SpielfeldGUI(Spiellogik spiellogik, Vector<JButton> buttons, boolean timer){
        super("Memory");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));
        this.spiellogik = spiellogik;
        origButtons = new Vector<>(buttons.size());
        for (JButton x : buttons){
            origButtons.add(new JButton(x.getIcon()));
        }
        this.buttons = buttons;
        letzerCode = -1;
        init(timer);
        addListener();
        this.pack();
        setVisible(true);
    }

    /**
     * Initialisiert das GUI
     * @param timer
     */
    public void init(boolean timer) {

        labelFont = new Font("Monaco", Font.ITALIC, 20);

        spieler1 = new JLabel("Spieler 1");
        spieler1.setFont(labelFont);
        spieler2 = new JLabel("Spieler 2");
        spieler2.setFont(labelFont);
        if (timer){
            timerPanel = new JLabel("10");
            timerPanel.setFont(labelFont);
        }

        spielstand1 = new JLabel(String.valueOf(spiellogik.getSpielstaende(1)));
        spielstand1.setFont(labelFont);
        spielstand2 = new JLabel(String.valueOf(spiellogik.getSpielstaende(2)));
        spielstand2.setFont(labelFont);
        spielstand1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        spielstand2.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        hauptmenu = new JButton("Hauptmenu");
        weiterspielen = new JButton("Weiterspielen");
        //weiterspielen.setEnabled(false);

        int size = (int)Math.sqrt(buttons.size());
        spielraster = new JPanel(new GridLayout(size, size));


        for (int x = 0; x < buttons.size(); x++){
            buttons.get(x).setIcon(null);
            //buttons.get(x).setIcon(origButtons.get(x).getIcon());
            spielraster.add(buttons.get(x));
        }

        spielraster.setBackground(Color.RED);


        untenPanel = new JPanel(new BorderLayout());
        //obenPanel = new JPanel(new BorderLayout(0, 20));
        obenPanel = new JPanel(new GridLayout(1,3));
        obenLinks = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        obenRechts = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
        obenLinks.add(spielstand1);
        obenLinks.add(spieler1);

        obenRechts.add(spieler2);
        obenRechts.add(spielstand2);

        obenPanel.add(obenLinks);
        if (timer){
            obenPanel.add(timerPanel);
        }
        obenPanel.add(obenRechts);

        weiterspielen.setEnabled(false);
        untenPanel.add(hauptmenu, BorderLayout.CENTER);
        untenPanel.add(weiterspielen, BorderLayout.EAST);

        rasterPanel = new JPanel(new BorderLayout(20, 20));
        rasterPanel.add(spielraster, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout(10, 0));
        getContentPane().add(obenPanel, BorderLayout.NORTH);
        getContentPane().add(rasterPanel, BorderLayout.CENTER);
        getContentPane().add(untenPanel, BorderLayout.SOUTH);


    }

    /**
     * Fügt die Listeners den Buttons hinzu
     */
    private void addListener(){
        for (JButton button : buttons){
            button.addActionListener(new MemoryButtonListener());
        }
        hauptmenu.addActionListener(new HauptmenuButtonListener());
        weiterspielen.addActionListener(new WeiterspielenButtonListener());
    }

    /**
     * Setzt den Text der Timeranzeige
     * @param s
     */
    public void setTimer(int s){
        timerPanel.setText(String.valueOf(s));
    }

    class HauptmenuButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    class WeiterspielenButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            spiellogik.startGame();
        }
    }

    class MemoryButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = buttons.indexOf(e.getSource());

            buttons.get(index).setIcon(origButtons.get(index).getIcon());

            int code = spiellogik.buttonGedrueckt(index);

            switch (code){
                case -1:
                    break;
                case 0:     //1. Karte gewählt
                    if (alterButton != null && alterAlterButton != null){
                        alterButton.setIcon(null);
                        alterAlterButton.setIcon(null);
                        if (letzerCode == 2){
                            alterButton.setVisible(false);
                            alterAlterButton.setVisible(false);
                        }
                    }
                    break;
                case 1:     //2. Karte gewählt, aber nicht gleich
                    alterButton = buttons.get(index);
                    buttons.get(index).setEnabled(true);
                    alterAlterButton = buttons.get(spiellogik.getLetzerButtonIndex());
                    buttons.get(spiellogik.getLetzerButtonIndex()).setEnabled(true);

                    break;
                case 2:     //2. Karte gewählt, beide Karten sind gleich
                    alterButton = buttons.get(index);
                    alterAlterButton = buttons.get(spiellogik.getLetzerButtonIndex());

                    spielstand1.setText(String.valueOf(spiellogik.getSpielstaende(1)));
                    spielstand2.setText(String.valueOf(spiellogik.getSpielstaende(2)));

                    break;
                case 3:     //Spiel ist fertig
                    alterButton.setIcon(null);
                    alterAlterButton.setIcon(null);
                    if (letzerCode == 2){
                        alterButton.setVisible(false);
                        alterAlterButton.setVisible(false);
                    }
                    weiterspielen.setEnabled(true);

                    break;
            }
            letzerCode = code;
        }


    }
}
