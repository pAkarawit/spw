package f2.spw;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Massage {

	public void massageshowGameOver(GameReporter g){	
		
		String st ,sv;  
		long sc = g.getScore();
		int lv = g.getLevel();
		st = "" + sc;
		sv = "" + lv;


		JFrame f = new JFrame("Game Over");
		f.setSize(300,100);
		f.setLocation(100,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel s = new JLabel("Maximum Score : "+ st + "  Maxmum Level :" + sv,JLabel.CENTER);
		
		f.add(s);
		f.setVisible(true);
    }
}	