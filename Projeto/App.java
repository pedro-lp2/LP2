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
    Color cor[] = {Color.blue, Color.gray, Color.green, Color.magenta, Color.cyan, Color.red, Color.yellow};
    
    private int posx, posy;
	private Figure focus = null;
	
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
                    }else if (evt.getKeyChar() == 'l') {
                    	figs.add(new Linha(x,y, w,h, cor[line], cor[background]));
                    	
                    	// Deleta do final da lista
                    	
                    } else if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
                    	figs.remove(figs.size() -1);
                    	
                    }
                    repaint();
                    
                }
                
            }
            
        );
        // Mover figs + Z-order
/**        
        private void mover() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    onMouseDown(e);
                    Graphics2D g = (Graphics2D) getGraphics();
                    int x = e.getX();
                    int y = e.getY();
                    if (e.isMetaDown()) {
                        figs.contains(g)
                    }
                }
                
            this.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    mouseDragged(e);
                }
            });
        }

        protected void onMouseDown(MouseEvent e) {
            ultimo = e.getPoint();
            for (Polygon polygon : polygons) {
                if (polygon.contains(ultimo)) {
                    selecionado = figs;
                    return;
                }
            }
            selecionado = null;
        }

        protected void onMouseDragged(MouseEvent e) {
            Point now = e.getPoint();
            if (ultimo != null) {
                int xt = now.x - ultimo.x;
                int yt = now.y - ultimo.y;
                if (selecionado != null) {
                    selecionado.translate(xt, yt);
                    figs.set(x, selecionado);  // pré z-order (Criar null final da lista para trocar)
                    repaint();
                }
                ultimo = now;
            }   
        }**/
       
        		/**JANELA**/
        //setDefaultCloseOperation(EXIT_ON_CLOSE);		
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
