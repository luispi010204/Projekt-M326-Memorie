package gui;

import spielcontroller.Spiellogik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * @author Marko Joksimovic
 * @version 1.0
 * @date
 */
public class SpielfeldGUI extends JFrame {

    private Spiellogik spiellogik;
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
    private Vector<JButton> origButtons;

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
        init();
        addListener();
        this.pack();
        setVisible(true);
    }

    public void init() {

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

        int size = (int)Math.sqrt(buttons.size());
        spielraster = new JPanel(new GridLayout(size, size));


        for (int x = 0; x < buttons.size(); x++){
            buttons.get(x).setIcon(null);
            buttons.get(x).setIcon(origButtons.get(x).getIcon());
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

    private void addListener(){
        for (JButton button : buttons){
            button.addActionListener(new MemoryButtonListener());
        }
    }


    class MemoryButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = buttons.indexOf(e.getSource());
            spiellogik.buttonGedrueckt(index);
        }
    }
}
