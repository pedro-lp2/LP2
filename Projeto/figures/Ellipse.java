package figures;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Elipse extends Figure{
    private static final long serialVersionUID = 1L;
   
    public Elipse (int x, int y, int w, int h, Color line, Color background){
        super(x, y, w, h, line, background);
    }
	
	/** dimensões agora são private
	public void print(){
        System.out.format("Elipse tamanho (%d, %d) // posicao(%d, %d).\n", this.w, this.h, this.x, this.y);
    }**/
	
	
    public void paint(Graphics g, boolean focused){
    	Color Cfocus;
    	if(focused == true) {
    		Cfocus = Color.red;
    	}
    	else {
    		Cfocus = new Color(0,0,0,0);
    	}
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(this.getBorder());
        g2d.draw(new Ellipse2D.Double(this.getX(),this.getY(),this.getW(),this.getH()));
        g.setColor(this.getFill());
        g2d.fill(new Ellipse2D.Double(this.getX(),this.getY(),this.getW(),this.getH()));
        g.setColor(Cfocus);
        g2d.draw(new Ellipse2D.Double(this.getX()-1, this.getY()-1, this.getW()+2, this.getH()+2));
    }
    
}
