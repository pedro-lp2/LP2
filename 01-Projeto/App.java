import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import figures.*;
import ivisible.IVisible;

import java.io.*;



class App {
    public static void main(String[] args){
        Frame frame = new Frame();
        frame.setVisible(true);
    }
}

class Frame extends JFrame{
	// Criar lista
	private static final long serialVersionUID = 1L;
	ArrayList<Figure> figs = new ArrayList<Figure>();
	ArrayList<Button> buts = new ArrayList<Button>();
    
	Random rand = new Random();
    Figure Ffocus = null;
    Button Bfocus = null;
    Figure ColorAux = null;
    
    int aux = 0;
    int colorPaintAux = 0;
    
    Color colors[] = {Color.blue, Color.gray, Color.green, Color.magenta, Color.cyan, Color.red, Color.yellow};
    

	Frame() {
        this.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                	try {
                		FileOutputStream f = new FileOutputStream("proj.bin");
                		ObjectOutputStream o = new ObjectOutputStream(f);
                		o.writeObject(figs);
                		o.flush();
                		o.close();
                		
                	} catch (Exception x){
                		
                	}
                    System.exit(0);
                }
            }
        );
        //
        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                	Point p = MouseInfo.getPointerInfo().getLocation();
                	int x = p.x - getLocation().x;
                	int y = p.y - getLocation().y;
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    
                    		// Adiciona figuras
                    
                    
                    if(evt.getKeyChar() == 'r'){
                        figs.add(new Retangulo(x,y, w,h, Color.white,Color.black));
                    } else if(evt.getKeyChar() == 'e'){
                        figs.add(new Elipse(x,y, w,h, Color.white,Color.black));
                    } else if(evt.getKeyChar() == 't') {
                        figs.add(new Triangulo(x,y, w,h, Color.white,Color.black));
                    } else if(evt.getKeyChar() == 'p') {
                        figs.add(new Pentagono(x,y, w,h, Color.white,Color.black));                   
                    }else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                    	figs.remove(Ffocus);
                    } else if(evt.getKeyChar() == '+'){
                    	if(Ffocus.getW() < 400 && Ffocus.getH() < 400) {
                        	Ffocus.setH(Ffocus.getH() + Ffocus.getH()/10);
                        	Ffocus.setW(Ffocus.getW() + Ffocus.getW()/10);
                            if(Ffocus.getH()/10 == 0 || Ffocus.getW()/10 == 0) {
                            	Ffocus.setH(Ffocus.getH() + 1);
                            	Ffocus.setW(Ffocus.getW() + 1);
                            }
                        }
                    } else if(evt.getKeyChar() == '-'){
                    	if(Ffocus.getW() > 0 && Ffocus.getH() > 0) {
                    		Ffocus.setH(Ffocus.getH() - Ffocus.getH()/10);
                        	Ffocus.setW(Ffocus.getW() - Ffocus.getW()/10);
                    	}
                    } else if(evt.getKeyCode() == KeyEvent.VK_UP){
                    	Ffocus.setY(Ffocus.getY() - 1);
                    } else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
                    	Ffocus.setY(Ffocus.getY() + 1);
                    } else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                    	Ffocus.setX(Ffocus.getX() - 1); 
                    } else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                    	Ffocus.setX(Ffocus.getX() + 1);
                    } else if(evt.getExtendedKeyCode() == KeyEvent.VK_SPACE){
                    	if(figs.size() > 0) {
                    		if(Ffocus == null) {
                    			Ffocus = figs.get(0);
                    		}
                    		Ffocus = null;
                        	if(aux == 0) {
                        		Ffocus = figs.get(figs.size() - 1);
                        	}
                        	if(aux != 0) {
                        		Ffocus = figs.get(aux - 1);
                        	}
                        	Ffocus = null;
                        	if(aux >= figs.size()) {
                        		aux = 0;
                        	}
                        	Ffocus = figs.get(aux);
                        	Ffocus.setContour(Color.red);
                        	aux += 1;
                    	}
                    } else if(evt.getKeyChar() == 'f'){
                    	Ffocus.setFill(colors[colorPaintAux]);
                    	colorPaintAux += 1;
                    	if(colorPaintAux >= 6) {
                    		colorPaintAux = 0;
                    	}
                    } else if(evt.getKeyChar() == 'c'){
                    	Ffocus.setBorder(colors[colorPaintAux]);
                    	colorPaintAux += 1;
                    	if(colorPaintAux >= 6) {
                    		colorPaintAux = 0;
                    	}
                    }
                    repaint();
                }
            }
        );
        
        this.addMouseMotionListener(
        	new MouseMotionAdapter() {
        		public void mouseDragged(MouseEvent evt) {
        			int dif_x = (evt.getX() - Ffocus.getX()) - Ffocus.getW()/2;
        			int dif_y = (evt.getY() - Ffocus.getY()) - Ffocus.getH()/2;
        			Ffocus.drag(dif_x, dif_y);
        			repaint();
        		}
        	}
        );
        this.addMouseListener(
                new MouseAdapter() {
            	public void mousePressed(MouseEvent evt) {
            	    Ffocus = null;
            	    for(Figure fig: figs) {
            		if(fig.x <= evt.getX() && fig.y <= evt.getY() && (fig.w + fig.x)>= evt.getX() && (fig.h + fig.y) >= evt.getY()) {
    	        	   Ffocus = fig;
    	    		    Ffocus.contour = Color.red;
    	    		    repaint();
            		} else {
            		    fig.contour = null;
            		}
            	    }
            	    if(Ffocus == null) {
            	        repaint();
            	    }
            	}
                }
            );
        		/**JANELA**/
        //setDefaultCloseOperation(EXIT_ON_CLOSE);	
        this.setTitle("Figuras");
        this.setSize(700,700);/** Ajustar para o tamanho da tela**/
    }

    public void paint(Graphics g){
        super.paint(g);
        for(Figure fig: this.figs){
        	if(fig == Ffocus) {
        		fig.paint(g, true);
        	}
        	else {
        		fig.paint(g, false);
        	}
        }
    }
}
