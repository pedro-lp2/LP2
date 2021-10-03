package figures;

import java.awt.*;

public abstract class Figure {
    public int x, y;
    public int w, h;
    Color line, background;

    public Figure(int x, int y, int w, int h, Color line, Color background){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.line = line;
        this.background=background;
    }

    public abstract void print();

    public abstract void paint(Graphics g);
    
}
