import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import buttons.BDeletar;
import buttons.BDeletarTudo;
import buttons.BElipse;
import buttons.BMudaCor;
import buttons.BPentagono;
import buttons.BRetangulo;
import buttons.BTriangulo;
import buttons.Button;

import java.util.ArrayList;
import java.util.Random;
import figures.*;
import ivisible.IVisible;


import java.io.*;



class App {
    public static void main(String[] args){
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setResizable(false);
        
    }
}

class Frame extends JFrame{
	// Criar lista
	private static final long serialVersionUID = 1L;
	ArrayList<Figure> figs = new ArrayList<Figure>();
	ArrayList<Button> buts = new ArrayList<Button>();
    
	Random rand = new Random();
    Figure ffocus = null;
    Button bfocus = null;
    Figure colorAux = null;
    
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
    		JOptionPane.showMessageDialog(null, "Erro 101",
    			      "Erro!", JOptionPane.ERROR_MESSAGE);
    	}
	buts.add(new BRetangulo(1, new Retangulo(20, 50, 30, 30, Color.BLACK, Color.BLACK)));
    	buts.add(new BTriangulo(3, new Triangulo(20,130,30,30, Color.BLACK, Color.BLACK)));
    	buts.add(new BElipse(2, new Elipse(20,90,30,30, Color.BLACK, Color.BLACK)));
    	buts.add(new BPentagono(5, new Pentagono(20,170,30,30, Color.BLACK, Color.BLACK)));
    	buts.add(new BDeletar(7, new Retangulo(20, 270, 70, 20, Color.BLACK, Color.BLACK))); // Deleta figura em foco
    	buts.add(new BDeletarTudo(8, new Retangulo(20, 300, 70, 20, Color.BLACK, Color.BLACK))); // Deleta Todas as figuras da tela
    	buts.add(new BMudaCor(9, new Elipse(20, 240, 70, 20, Color.BLACK, Color.BLACK))); // Mudar a Cor da figura
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
                    	figs.remove(ffocus);
                    } else if(evt.getKeyChar() == '+'){
                    	if(ffocus.getW() < 400 && ffocus.getH() < 400) {
                        	ffocus.setH(ffocus.getH() + ffocus.getH()/10);
                        	ffocus.setW(ffocus.getW() + ffocus.getW()/10);
                            if(ffocus.getH()/10 == 0 || ffocus.getW()/10 == 0) {
                            	ffocus.setH(ffocus.getH() + 1);
                            	ffocus.setW(ffocus.getW() + 1);
                            }
                        }
                    } else if(evt.getKeyChar() == '-'){
                    	if(ffocus.getW() > 0 && ffocus.getH() > 0) {
                    		ffocus.setH(ffocus.getH() - ffocus.getH()/10);
                        	ffocus.setW(ffocus.getW() - ffocus.getW()/10);
                    	}
                    } else if(evt.getKeyCode() == KeyEvent.VK_UP){
                    	ffocus.setY(ffocus.getY() - 1);
                    } else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
                    	ffocus.setY(ffocus.getY() + 1);
                    } else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                    	ffocus.setX(ffocus.getX() - 1); 
                    } else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                    	ffocus.setX(ffocus.getX() + 1);
                    } else if(evt.getExtendedKeyCode() == KeyEvent.VK_SPACE){
                    	if(figs.size() > 0) {
                    		if(ffocus == null) {
                    			ffocus = figs.get(0);
                    		}
                    		ffocus = null;
                        	if(aux == 0) {
                        		ffocus = figs.get(figs.size() - 1);
                        	}
                        	if(aux != 0) {
                        		ffocus = figs.get(aux - 1);
                        	}
                        	ffocus = null;
                        	if(aux >= figs.size()) {
                        		aux = 0;
                        	}
                        	ffocus = figs.get(aux);
                        	ffocus.setContour(Color.red);
                        	aux += 1;
                    	}
                    } else if(evt.getKeyChar() == 'f'){
                    	ffocus.setFill(colors[colorPaintAux]);
                    	colorPaintAux += 1;
                    	if(colorPaintAux >= 6) {
                    		colorPaintAux = 0;
                    	}
                    } else if(evt.getKeyChar() == 'c'){
                    	ffocus.setBorder(colors[colorPaintAux]);
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
        			int dif_x = (evt.getX() - ffocus.getX()) - ffocus.getW()/2;
        			int dif_y = (evt.getY() - ffocus.getY()) - ffocus.getH()/2;
        			ffocus.drag(dif_x, dif_y);
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
    	        				bfocus = but;
    	        				if(but.getIdx() == 7 && ffocus != null) {
    								figs.remove(ffocus);
    								bfocus = null;
    								repaint();
    							}
    							if(but.getIdx() == 8 && !figs.isEmpty()) {
    								figs.clear();
    								bfocus = null;
    								repaint();
    							}
    							if(but.getIdx() == 9 && ffocus != null) {
    								ffocus.setFill(colors[colorPaintAux]);
    		                    	colorPaintAux += 1;
    		                    	if(colorPaintAux >= 6) {
    		                    		colorPaintAux = 0;
    		                    	}
    		                    	bfocus = null;
    		                    	repaint();
    							}
    	    					repaint();
    	    					auxClick = true;
            				}		
            			}
            			if(bfocus != null && !(bfocus.clicked(evt.getX(),evt.getY()))) {
    						if(bfocus.getIdx() == 1) {
    							figs.add(new Retangulo(evt.getX(),evt.getY(), 30,30, Color.WHITE,Color.BLACK));
    						}
    						if(bfocus != null &&(bfocus.getIdx() == 2)) {
    							figs.add(new Elipse(evt.getX(),evt.getY(), 30,30, Color.WHITE,Color.BLACK));
    						}   			
    						if(bfocus != null &&(bfocus.getIdx() == 3)) {
    							figs.add(new Triangulo(evt.getX(),evt.getY(), 30,30, Color.WHITE,Color.BLACK));
    						}
    						if(bfocus != null &&(bfocus.getIdx() == 5)) {
    							figs.add(new Pentagono(evt.getX(),evt.getY(), 30,30, Color.WHITE,Color.BLACK));
    						}    						
            			}
	
            			for(Figure fig: figs) {
            				if(fig.clicked(evt.getX(),evt.getY())) {
    	        				ffocus = fig;
    	        				figs.remove(fig);
    	        				figs.add(ffocus);
    	    					ffocus.setContour(Color.red);
    	    					repaint();
    	    					break;
            				} else {
            					if(auxClick == false) {
            						ffocus = null;
            					}
            				}
            			}
            			repaint();
            		}
            	}
            );
        		/**JANELA**/
        setDefaultCloseOperation(EXIT_ON_CLOSE);	
        this.setTitle("Figuras");
        this.setSize(700,700);/** Ajustar para o tamanho da tela**/

    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.dispose();
        for(Figure fig: this.figs){
        	if(fig == ffocus) {
        		fig.paint(g, true);
        	}
        	else {
        		fig.paint(g, false);
        	}
        }
        for(Button but: this.buts){
        	if(but == bfocus) {
        		but.paint(g, true);
        	}
        	else {
        		but.paint(g, false);
        	}
        }
    }
}
