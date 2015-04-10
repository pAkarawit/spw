package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class Missile extends Sprite{
	 public static final int Y_TO_FADE = 400 ;
	 public static final int Y_TO_DIE = 600 ;

	 private int step = 10 ;
	 private boolean alive = true;

	 public Missile(int x , int y){
	 	 super(x, y, 18,20);


	 }

	 @Override
	 public void draw(Graphics2D g){
	   /*	if(y < Y_TO_DIE)
	 		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

	 	else{
	 		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
	 			          (float)(Y_TO_DIE - y)/(Y_TO_DIE  - Y_TO_FADE)));
	 	}
	   */

	 	Toolkit r = Toolkit.getDefaultToolkit();
	 	Image ime = r.getImage("C:/Users/NextSpeed/Documents/GitHub/spw/f2/laun.png");
	 	g.drawImage(ime, x, y, width , height , null);

	 }

	 public void proceed(){
	 	 y -= step;
	 	// if(y > Y_TO_DIE){

	 	 //alive = false ;

	 	 	
	 	 //} 

	 }

	 public boolean isAlive(){

	 	return alive;
	 }

	 public void notAlive(){

	 	alive = false ;
	 }



}