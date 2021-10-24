package figures;
import java.awt.Graphics;
import java.awt.*;

public class Triangulo extends Figure{
	private static final long serialVersionUID = 1L;
	public Polygon polygon;

	public Triangulo (int x, int y, int w, int h, Color line, Color background){
        super(x, y, w, h, line, background);
    }
    public void paint(Graphics g, boolean focused){
    	
    	int x_array[] = {this.getX(), this.getX() + this.getW()/2, this.getX() + this.getW()};
        int y_array[] = {this.getY() + this.getH(), this.getY(), this.getY() + this.getH()};
        Polygon polygon = new Polygon(x_array, y_array, 3);
        this.polygon = polygon;
        
        Color Cfocus;
    	if(focused == true) {
    		Cfocus = Color.red;
    	}
    	else {
    		Cfocus = new Color(0,0,0,0);
    	}
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(this.getBorder());
        g2d.drawPolygon(this.polygon);
        g.setColor(this.getFill());
        g2d.fillPolygon(this.polygon);
        g.setColor(Cfocus);
        g2d.drawPolygon(this.polygon);
    }
    
}
