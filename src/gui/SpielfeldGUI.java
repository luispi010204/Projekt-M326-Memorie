package gui;

import model.Memorykarte;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Collections;
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

    private Vector<JButton> buttons;

    public SpielfeldGUI(Vector<ImageIcon> imageIcons, boolean timer){
        super("Memory");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));
        init(imageIcons);
        this.pack();
        setVisible(true);
    }

    public void init(Vector<ImageIcon> imageIcons) {

        buttons = new Vector<>(imageIcons.size() * 2);

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

        spielraster = new JPanel(new GridLayout((imageIcons.size() + 1) / 2, (imageIcons.size() + 1) / 2));

        /*
        for (Memorykarte karte : karten){
            //so machsch es wenn Bilder dinne sind
        }

         */
        for (int x = 0; x < imageIcons.size(); x++){
            buttons.add(new JButton(imageIcons.get(x)));
            buttons.add(new JButton(imageIcons.get(x)));
        }

        Collections.shuffle(buttons);

        for (int x = 0; x < buttons.size(); x++){
            spielraster.add(buttons.get(x));
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
        Vector<ImageIcon> imageIcons = new Vector<>();

        new SpielfeldGUI(imageIcons,false);
    }
}
