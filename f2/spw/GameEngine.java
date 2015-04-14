package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Missile>  missiles = new ArrayList<Missile>();

	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				processEnemy();
				processMissile();
				//process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	private void generateMissile(){
		if(getScore() > 2000){
			Missile m = new Missile(v.x + (v.width/2) - 15 , v.y ) ;
			 gp.sprites.add(m);
		     missiles.add(m);

		}
		else{
			Missile m = new Missile(v.x + (v.width/2), v.y ) ;
			gp.sprites.add(m);
			missiles.add(m);
		}		
	}

	private void generateMissile2(){
		Missile m = new Missile(v.x + (v.width/2) + 15 , v.y ) ;
		gp.sprites.add(m);
		missiles.add(m);

	}


	private void processMissile(){	
		Iterator<Missile> m_iter = missiles.iterator();
		while(m_iter.hasNext()){
			Missile m = m_iter.next();
			m.proceed();

			if(!m.isAlive()){
				m_iter.remove();
				gp.sprites.remove(m);
				
			}
		}

	}		
		
	
	private void processEnemy(){
		if(Math.random() < difficulty){
			generateEnemy();
			//generateMissile();
		}

		Iterator<Enemy> e_iter = enemies.iterator();
			while(e_iter.hasNext()){
				Enemy e = e_iter.next();
				e.proceed();
					
				if(!e.isAlive()){
					e_iter.remove();
					gp.sprites.remove(e);
					score += 200;
				}
				
			}
			    

	
	//private void process(){	

		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double mr;


		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
			}

			for(Missile m : missiles){
			    mr = m.getRectangle();
			    if(mr.intersects(er)){
			        score += 1000 ;
			        e.notAlive();
			        m.notAlive();
			       	
			       
				    return;
	        	   }

		        }

	        }

	       
	}        
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1,true);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,true);
			break;
		case KeyEvent.VK_UP:
			v.move(-1,false);
			break;
		case KeyEvent.VK_DOWN:
			v.move(1,false);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			 if(getScore() > 2000){
			 	generateMissile2();
			 	generateMissile();
			 }	
			 generateMissile();
			 
			 break;	
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
