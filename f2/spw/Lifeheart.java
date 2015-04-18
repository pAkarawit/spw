package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class Lifeheart extends Sprite{
	private static final int Y_TO_FADE = 400 ;
	private static final int Y_TO_DIE = 600 ;


	private int step = 5 ;
	private boolean alive = true ;
	Toolkit s;
	Image img;


	public Lifeheart(int x,int y){

		super(x,y,20,20);
	}

	@Override
	public void draw(Graphics2D g){

		s = Toolkit.getDefaultToolkit();
		img = s.getImage("C:/Users/NextSpeed/Documents/GitHub/spw/f2/aasd.png");
		g.drawImage(img,x,y,width,height,null);



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

		alive = false;
	}




}