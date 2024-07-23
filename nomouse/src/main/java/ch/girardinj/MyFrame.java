package ch.girardinj;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    
    private static int WIDTH = 400;
    private static int HEIGHT = 300;

    public MyFrame() {
        this.setTitle("Keyboard, no mouse");
        this.setSize(WIDTH, HEIGHT);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
    }

}
