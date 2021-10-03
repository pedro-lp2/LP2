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
    int colorPaintAux = 0;
    Figure ColorAux = null;
    Figure Ffocus = null;
    Color cor[] = {Color.blue, Color.gray, Color.green, Color.magenta, Color.cyan, Color.red, Color.yellow};
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
                    int x = rand.nextInt(700);
                    int y = rand.nextInt(700);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    int line = rand.nextInt(6);
                    int background = rand.nextInt(6);
                    
                    	// Adiciona figuras
                    
                    if (evt.getKeyChar() == 'r') {
                        Retangulo r = new Retangulo(x,y, w,h, cor[line], cor[background]);
                        figs.add(r);
                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Elipse(x,y, w,h, cor[line], cor[background]));
                    } else if (evt.getKeyChar() == 't') {
                    	figs.add(new Triangulo(x,y, w,h, cor[line], cor[background]));
                    }else if (evt.getKeyChar() == 'l') {
                    	figs.add(new Linha(x,y, w,h, cor[line], cor[background]));
                    	
                    	// Deleta do final da lista
                    	
                    } else if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
                    	figs.remove(figs.size() -1);
                    	
                    	// Mudar cor da figura
/**                  	
                    } else if(evt.getKeyChar() == 'f'){
                    	figs.setBackground(cor[colorPaintAux]);
                    	colorPaintAux += 1;
                    	if(colorPaintAux >= 6) {
                    		colorPaintAux = 0;
                    	}
                    } else if(evt.getKeyChar() == 'c'){
                    	figs.setLine(cor[colorPaintAux]);
                    	colorPaintAux += 1;
                    	if(colorPaintAux >= 6) {
                    		colorPaintAux = 0;
                    	}
**/                 	
                    }
                    repaint();
                    
                }
                
            }
            
        );
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
