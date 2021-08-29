import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Retangulo r1;
    Ellipse e1;
    Linha l1;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Figuras");
        this.setSize(700, 700);
		this.r1 = new Retangulo(50,200, 100,30, Color.yellow, Color.black);
        this.e1 = new Ellipse(250,200, 100,30, Color.black, Color.yellow);
        this.l1 = new Linha(400, 500, 100, 250, Color.green, Color.red);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
        this.l1.paint(g);
    }
}
