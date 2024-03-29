import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp{
    public static void main(String[] args){
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame{
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    ListFrame(){
        this.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    System.exit(0);
                }
            }
        );

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    int x = rand.nextInt(700);
                    int y = rand.nextInt(700);
                    int w = rand.nextInt(100);
                    int h = rand.nextInt(100);
                    if(evt.getKeyChar() == 'r'){
                        Rect r = new Rect(x,y, w,h);
                        figs.add(r);
                    } else if(evt.getKeyChar() == 'e'){
                        figs.add(new Ellipse(x,y, w,h));
                    } else if(evt.getKeyChar() == 'l'){
                        Line l = new Line(x,y, w,h);
                        figs.add(l);
                    }
                    repaint();
                }
            }
        );

        this.setTitle("Figuras");
        this.setSize(700,700);
    }

    public void paint(Graphics g){
        super.paint(g);
        for(Figure fig: this.figs){
            fig.paint(g);
        }
    }
}
