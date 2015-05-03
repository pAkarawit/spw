package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class Bomb extends Sprite{
	Toolkit w;
	Image ime;

	public Bomb(int x,int y ,int width, int height){
		super(x,y,width,height);

	}

	@Override
	public void draw(Graphics2D g){
		w = Toolkit.getDefaultToolkit();
		ime = w.getImage("C:/Users/NextSpeed/Documents/GitHub/spw/f2/expl.png");
		g.drawImage(ime , x, y, width ,height, null);


	}
}	