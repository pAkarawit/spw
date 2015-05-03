package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main  {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);

		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(180, 550, 40, 70);
		Lifeheart l = new Lifeheart(150,5);
		Bomb bo = new Bomb(125,40,125,80);
		BigEnemy b = new BigEnemy(125,40,125,80);
		
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v,l,bo,b);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();		
    	   		   
	} 
}
