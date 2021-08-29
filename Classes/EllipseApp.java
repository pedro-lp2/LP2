import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class EllipseApp {
    public static void main (String[] args) {
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}
class RectEllipseFrame extends JFrame {
    Ellipse e1;
    Ellipse e2;
    Ellipse e3;

    RectEllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Ellipse");
        this.setSize(700, 700);
        this.e1 = new Ellipse(120,100, 100,30, new Color(255, 255, 0), new Color(0, 0, 0));
        this.e2 = new Ellipse(350,350, 90,80, new Color(0, 0, 0), new Color(255, 255, 0));
        this.e3 = new Ellipse(200,200, 150,130, new Color(0, 128, 0), new Color(255, 0, 0));
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
    }
}
class Ellipse {
    int x, y;
    int w, h;
    Color background;
    Color line;
    Ellipse (int x, int y, int w, int h, Color background, Color line) {
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
        g2d.setColor(line);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(background);
        g2d.fillOval(this.x, this.y, this.w, this.h);
    }
}
