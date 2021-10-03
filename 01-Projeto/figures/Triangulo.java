package figures;

import java.awt.*;

public class Triangulo extends Figure{
    int x, y;
    int w, h;
    Color line, background;
    Polygon triangulo = new Polygon();

    public Triangulo (int x, int y, int w, int h, Color line, Color background){
        super(x, y, w, h, line, background);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = 0;
        this.line = line;
        this.background = background;

        triangulo.npoints = 3;
        triangulo.xpoints[0] = x;
        triangulo.xpoints[1] = y;
        triangulo.xpoints[2] = w;

        triangulo.ypoints[0] = y;
        triangulo.ypoints[1] = w;
        triangulo.ypoints[2] = y;

    }

    public void print(){
        System.out.format("Triangulo de base (%d, %d)", this.x, this.y);
    }
    
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.line);
        g2d.drawPolygon(triangulo.xpoints, triangulo.ypoints, triangulo.npoints);
        g2d.setColor(this.background);
        g2d.fill(triangulo);
    }
}
