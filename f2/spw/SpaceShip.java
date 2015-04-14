package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class SpaceShip extends Sprite{

	int step = 8;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}



	@Override
	public void draw(Graphics2D g) {


		Toolkit t = Toolkit.getDefaultToolkit(); // Gets the default toolkit.
        Image image = t.getImage ("C:/Users/NextSpeed/Documents/GitHub/spw/f2/fights.png");  //Returns an image which gets pixel data
		g.drawImage(image, x, y, width, height, null);
		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, width, height);

		
	}

	public void move(int direction,Boolean  leftright){
		
		if(leftright == true ) {
		x += (step * direction);
		
        if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	    }

	    if(leftright == false){
        y += (step * direction);
		
		if(y < 0)
		    y = 0;

		if(y > 650 - height)
			y = 650 - height;	

	    }
		
	}

}
