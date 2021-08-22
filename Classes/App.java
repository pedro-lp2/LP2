import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class App {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1, r2, r3;
    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Figuras");
        this.setSize(700, 700);
        this.r1 = new Rect(300,200, 150,30, Color.yellow, Color.black);
        this.r2 = new Rect(300,250, 150,30, Color.black, Color.yellow);
        this.r3 = new Rect(300,300, 150,30, Color.green, Color.red);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
    }
}
class Rect {
    int x, y;
    int w, h;
    Color background;
    Color line;
    
    Rect (int x, int y, int w, int h, Color background, Color line) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.background = background;
        this.line = line;
    }
    

    void print () {
        System.out.format("Tamanho (%d,%d). posicao (%d,%d).\n",this.w, this.h, this.x, this.y);
    }
    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.background);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(this.line);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
