import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import figures.*;
import java.io.*;
import buttons.*;
import buttons.Button;


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
    	try {
    		FileInputStream f = new FileInputStream("proj.bin");
    		ObjectInputStream o = new ObjectInputStream(f);
    		ArrayList<Figure> readObject = (ArrayList<Figure>) o.readObject();
			this.figs = readObject;
    		o.close();
    		
    	} catch (Exception x) {
    		System.out.println("ERROR 404 NOT FOUND XD !");
    	}
    	buts.add(new BRetangulo(1, new Retangulo(20, 50, 30, 30, Color.BLACK, Color.BLACK)));
    	buts.add(new BElipse(2, new Elipse(20,90,30,30, Color.BLACK, Color.BLACK)));
    	buts.add(new BTriangulo(3, new Triangulo(20,130,30,30, Color.BLACK, Color.BLACK))); 
    	buts.add(new BDeletar(7, new Retangulo(20, 200, 70, 20, Color.BLACK, Color.BLACK))); // Deleta figura em foco
    	buts.add(new BMudaCor(9, new Elipse(20, 230, 70, 20, Color.BLACK, Color.BLACK))); // Mudar a Cor da figura
    	
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
                    } else if(evt.getKeyChar() == 'c'){
                    	Ffocus.setFill(colors[colorPaintAux]);
                    	colorPaintAux += 1;
                    	if(colorPaintAux >= 12) {
                    		colorPaintAux = 0;
                    	}
                    } else if(evt.getKeyChar() == 'C'){
                    	Ffocus.setBorder(colors[colorPaintAux]);
                    	colorPaintAux += 1;
                    	if(colorPaintAux >= 12) {
                    		colorPaintAux = 0;
                    	}
                    } else if(evt.getKeyChar() == 'b'){
                    	Bfocus = null;
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
        			boolean auxClick = false;
        			for(Button but: buts) {
        				if(but.clicked(evt.getX(),evt.getY())) {
	        				Bfocus = but;
	        				if(but.getIdx() == 7 && Ffocus != null) {
								figs.remove(Ffocus);
								Bfocus = null;
								repaint();
							}
							if(but.getIdx() == 8 && !figs.isEmpty()) {
								figs.clear();
								Bfocus = null;
								repaint();
							}
							if(but.getIdx() == 9 && Ffocus != null) {
								Ffocus.setFill(colors[colorPaintAux]);
		                    	colorPaintAux += 1;
		                    	if(colorPaintAux >= 12) {
		                    		colorPaintAux = 0;
		                    	}
		                    	Bfocus = null;
		                    	repaint();
							}
	    					repaint();
	    					auxClick = true;
        				}		
        			}
        			if(Bfocus != null && !(Bfocus.clicked(evt.getX(),evt.getY()))) {
						if(Bfocus.getIdx() == 1) {
							figs.add(new Retangulo(evt.getX(),evt.getY(), 30,30, Color.white,Color.black));
						}
						if(Bfocus.getIdx() == 2) {
							figs.add(new Elipse(evt.getX(),evt.getY(), 30,30, Color.white,Color.black));
						}
						if(Bfocus.getIdx() == 3) {
							figs.add(new Triangulo(evt.getX(),evt.getY(), 30,30, Color.white,Color.black));
						}
        			}
        			
        			for(Figure fig: figs) {
        				if(fig.clicked(evt.getX(),evt.getY())) {
	        				Ffocus = fig;
	        				figs.remove(fig);
	        				figs.add(Ffocus);
	    					Ffocus.setContour(Color.red);
	    					repaint();
	    					break;
        				} else {
        					if(auxClick == false) {
        						Ffocus = null;
        					}
        				}
        			}
        			repaint();
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
        for(Button but: this.buts){
        	if(but == Bfocus) {
        		but.paint(g, true);
        	}
        	else {
        		but.paint(g, false);
        	}
        }
    }
}
