package figures;
import java.awt.*;
import ivisible.IVisible;

public abstract class Figure implements IVisible{
    int x, y;
    int w, h;
    Color line, background;

    public Figure (int x, int y, int w, int h, Color line, Color background){
    	
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.line = line;
        this.background = background;
    }

    public abstract void print();

    public abstract void paint(Graphics g);
    
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public int getw(){
        return w;
    }
    public int geth(){
        return h;
    }
    public void drag(int newx, int newy){
        this.x = newx;
        this.y = newy;
    }
    public boolean clicked (int x, int y) {
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }
}
