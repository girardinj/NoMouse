package ch.girardinj;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyFrame extends JFrame {
    
    private static int WIDTH = 400;
    private static int HEIGHT = 300;

    private KeyTracker keyTracker;

    public MyFrame() {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setTitle("Keyboard, no mouse");
        
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        ArrayList<JLabel> labels = new ArrayList<>();
        JLabel isActivatedLabel = new JLabel();
        labels.add(isActivatedLabel);
        labels.add(new JLabel("<html><font color='red'>[T]</font> to activate</html>"));
        labels.add(new JLabel("<html><font color='red'>[arrow keys]</font> to move</html>"));
        labels.add(new JLabel("<html><font color='red'>[ctrl]</font> to move faster</html>"));
        labels.add(new JLabel("<html><font color='red'>[alt]</font> to move slower</html>"));
        labels.add(new JLabel("<html><font color='red'>[Y]</font> to left click</html>"));
        labels.add(new JLabel("<html><font color='red'>[X]</font> to right click</html>"));
        labels.add(new JLabel("<html><font color='red'>[S]</font> to middle click</html>"));

        Font font = new Font("Calibri", Font.PLAIN, 16);
        for (JLabel label : labels) {
            label.setFont(font);
            this.add(label);
        }

        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE)); // transparent icon
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null); // center on screen

        try {
            this.keyTracker = new KeyTracker();
            this.keyTracker.addKeyListener(activated -> {
                if (activated)
                    isActivatedLabel.setText("<html><font color='green'>ACTIVATED</font></html>");
                else
                    isActivatedLabel.setText("<html><font color='red'>DEACTIVATED</font></html>");
            });
        } catch (AWTException e) {
            System.exit(-1);
        }

        this.keyTracker.start();
        this.setVisible(true);
    }
}
