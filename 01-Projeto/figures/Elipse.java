package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Elipse extends Figure{
    int x, y;
    int w, h;
    Color line, background;
    Ellipse2D poly;

    public Elipse (int x, int y, int w, int h, Color line, Color background){
        super(x, y, w, h, line, background);
        this.x = x; 
        this.y = y;
        this.w = w;
        this.h = h;
        this.line = line;
        this.background = background;
        
        this.poly = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.line);
        g2d.draw(this.poly);
        g2d.setColor(this.background);
        g2d.fill(this.poly);
    }
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
}
