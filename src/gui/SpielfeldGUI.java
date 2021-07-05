package gui;

import model.Memorykarte;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;

/**
 * @author Marko Joksimovic
 * @version 1.0
 * @date
 */
public class SpielfeldGUI extends JFrame {

    private JLabel spielstand1;
    private JLabel spielstand2;
    private JLabel spieler1;
    private JLabel spieler2;
    private JPanel spielraster;
    private JButton hauptmenu;
    private JButton weiterspielen;
    private JPanel obenPanel;
    private JPanel obenLinks;
    private JPanel obenRechts;
    private JPanel rasterPanel;
    private Font labelFont;

    public SpielfeldGUI(Vector<Memorykarte> karten, boolean timer){
        super("Memory");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));
        init(karten);
        this.pack();
        setVisible(true);
    }

    public void init(Vector<Memorykarte> karten) {

        labelFont = new Font("Monaco", Font.ITALIC, 20);

        spieler1 = new JLabel("Spieler 1");
        spieler1.setFont(labelFont);
        spieler2 = new JLabel("Spieler 2");
        spieler2.setFont(labelFont);

        spielstand1 = new JLabel("1");
        spielstand1.setFont(labelFont);
        spielstand2 = new JLabel("2");
        spielstand2.setFont(labelFont);
        spielstand1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        spielstand2.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        hauptmenu = new JButton("Hauptmenu");
        weiterspielen = new JButton("Weiterspielen");
        spielraster = new JPanel(new GridLayout(6, 6));

        for (Memorykarte karte : karten){
            //so machsch es wenn Bilder dinne sind
        }
        for (int x = 0; x < karten.size(); x++){
            spielraster.add(new JButton(String.valueOf(x)),x);;
        }

        spielraster.setBackground(Color.RED);


        obenPanel = new JPanel(new BorderLayout(0, 20));
        obenLinks = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        obenRechts = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
        obenLinks.add(spielstand1);
        obenLinks.add(spieler1);

        obenRechts.add(spieler2);
        obenRechts.add(spielstand2);

        obenPanel.add(obenLinks, BorderLayout.WEST);
        obenPanel.add(obenRechts, BorderLayout.EAST);

        rasterPanel = new JPanel(new BorderLayout(20, 20));
        rasterPanel.add(spielraster, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout(10, 0));
        getContentPane().add(obenPanel, BorderLayout.NORTH);
        getContentPane().add(rasterPanel, BorderLayout.CENTER);


    }

    public static void main (String[] args){
        Vector<Memorykarte> karten = new Vector<>();
        for (int i = 0; i < 36; i++){
            karten.add(new Memorykarte(String.valueOf(i)));
        }
        new SpielfeldGUI(karten,false);
    }
}
