package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import figures.Figure;

public class BDeletar extends Button{

	public BDeletar(int idx, Figure fig) {
		super(idx, fig);
	}
	
	public void paint(Graphics g, boolean focused) {
        Color CbackGround;
    	if(focused == true) {
    		CbackGround = Color.gray;
    	}
    	else {
    		CbackGround = Color.white;
    	}
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(CbackGround);
        g2d.fillRect(super.getFig().getX()-5, super.getFig().getY()-5, super.getFig().getW()+10, super.getFig().getH()+10);
        g2d.setColor(super.getFig().getFill());
        
        g2d.drawRect(super.getFig().getX()-5, super.getFig().getY()-5, super.getFig().getW()+10, super.getFig().getH()+10);
        g2d.setColor(super.getFig().getFill());
        
        int fontSize = 20;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g2d.setColor(Color.red);
        
        g2d.drawString("Deletar", super.getFig().getX() + 7, super.getFig().getY() + 15);
	}
}
