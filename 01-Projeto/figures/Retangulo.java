package figures;

import java.awt.*;

public class Retangulo extends Figure{
    int x, y;
    int w, h;
    Color line, background;

    public Retangulo (int x, int y, int w, int h, Color line, Color background){
        super(x, y, w, h, line, background);
        this.x = x; 
        this.y = y;
        this.w = w;
        this.h = h;
        this.line = line;
        this.background = background;
    }

    public void print(){
        System.out.format("Rentangulo de tamanho (%d, %d) e na posicao (%d, %d).\n", this.w, this.h, this.x, this.y);
    }
    
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.line);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.background);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
