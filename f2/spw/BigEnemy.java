package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class BigEnemy extends Sprite{
	public static final int Y_TO_FADE = 400 ;
	public static final int Y_TO_DIE = 600 ;

	private int step = 30;
	private boolean alive = true;
	Toolkit w;
	Image ime;

	public BigEnemy(int x,int y ,int width, int height){
		super(x,y,width,height);

	}

	@Override
	public void draw(Graphics2D g){

		w = Toolkit.getDefaultToolkit();
		ime = w.getImage("C:/Users/NextSpeed/Documents/GitHub/spw/f2/swer.gif");
		g.drawImage(ime , x, y, width ,height, null);




	}

	public void proceed(){
		
    	
		

	}
	public boolean isAlive(){
		return alive;
	}
	public void notAlive(){
		alive = false ;
	}
}