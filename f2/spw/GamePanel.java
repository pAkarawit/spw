package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Image;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	private Image img;
	Graphics2D big ;
	Toolkit e;
	
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		//big.setBackground(Color.BLACK);		

		e = Toolkit.getDefaultToolkit();
		img = e.getImage("C:/Users/NextSpeed/Documents/GitHub/spw/f2/blue.png");
        
	}

	public void updateGameUI(GameReporter reporter){
		//big.clearRect(0, 0, 400, 600);

        big.drawImage(img , 0, 0, 400 ,600, null);
		//big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		big.drawString(String.format("%d", reporter.getNumberalive()), 180 , 20);
		if(reporter.getLevel() < 9){
			big.drawString(String.format("Level : %d", reporter.getLevel()), 30, 20 );
		}
		if(reporter.getLevel() == 9){
			big.drawString(String.format("Score BOSS : %d ",reporter.getBossScore()), 30, 20);
	    }

		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
