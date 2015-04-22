package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class BulletBigEnemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 12;
	private boolean alive = true;
	Toolkit q;
	Image mi;
	
	
	public BulletBigEnemy(int x, int y) {
		super(x, y, 17, 30);
		
	}

	@Override
	public void draw(Graphics2D g) {
        q = Toolkit.getDefaultToolkit();
		mi = q.getImage("C:/Users/NextSpeed/Documents/GitHub/spw/f2/Banzai.png"); 
		g.drawImage(mi, x, y, width, height, null);
	}	

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
		   alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	public void notAlive(){
		
		alive = false ;
		
	}

}	
