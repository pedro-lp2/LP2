import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import figures.*;


class App {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {

	// Criar lista
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Color cor[] = {Color.blue, Color.gray, Color.green, Color.magenta, Color.cyan, Color.red, Color.yellow, Color.black};
    ListFrame () {
    	
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(500);
                    int y = rand.nextInt(500);
                    int w = rand.nextInt(100);
                    int h = rand.nextInt(100);
                    int line = rand.nextInt(7);
                    int background = rand.nextInt(8);
                    
                    	// Adiciona figuras
                    
                    if (evt.getKeyChar() == 'r') {
                        Rect r = new Rect(x,y, w,h, cor[line], cor[background]);
                        figs.add(r);
                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(x,y, w,h, cor[line], cor[background]));
                    } else if (evt.getKeyChar() == 't') {
                    	figs.add(new Triangulo(x,y, w,h, cor[line], cor[background]));
                    	// Hexagono Não está Funcionando
                    }/* else if (evt.getKeyChar() == 'h') {
                    	figs.add(new Hexagono(x,y, w,h, cor[line], cor[background]));
                    }*/else if (evt.getKeyChar() == 'l') {
                    	figs.add(new Linha(x,y, w,h, cor[line], cor[background]));
                    	
                    	// Deleta do final da lista
                    	
                    } else if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
                    	figs.remove(figs.size() -1);
                    }
                    repaint();
                    
                }
            }
        );        
        		/**JANELA**/
        setDefaultCloseOperation(EXIT_ON_CLOSE);		
        this.setTitle("Figuras");
        this.setSize(700, 700); /** Ajustar para o tamanho da tela**/
        
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}
