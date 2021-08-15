

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Modificação 1: Background.
        g2d.fill(getBounds());
        // Fim
        
        g2d.setPaint(Color.yellow);
        int w = getWidth();
        int h = getHeight();
        g2d.drawLine(0,0, w,h);
        g2d.drawLine(0,h, w,0);
        
        //Modificação 2: Adicionei uma Elipse.
        g2d.drawOval(0, 0, 350, 350);
        //Fim
        
        //Modificação 3(de minha escolha): Adicionei uma String.
        g2d.setFont(new Font("Arial",Font.BOLD,100));
        g2d.drawString("A", 140, 210);
        // Fim
    }
    
}
