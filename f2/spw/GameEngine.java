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
	private ArrayList<Lifeheart> lifehearts = new ArrayList<Lifeheart>();
	private ArrayList<BigEnemy>  bigEnemys = new ArrayList<BigEnemy>();
	private ArrayList<BulletBigEnemy> bulletbigEnemys = new ArrayList<BulletBigEnemy>(); 	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	private int countdie = 5;
	private long count = 0;
	private int level = 0;
	private int threeposition = 1 ;
	private int countthreposition = 0 ;

	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);

	
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				processEnemy();
				processMissile();
				processLifeheart();
				processBigEnemy();
				processBulletBigEnemy();
			}
		});
		timer.setRepeats(true);

		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateBigEnemy(){
	    BigEnemy b = new BigEnemy(110,20,150,200);
		gp.sprites.add(b);
		bigEnemys.add(b);
		
	}

	private void generateBulletBigEnemy1(){
	    BulletBigEnemy bu = new BulletBigEnemy(180 ,90);
		gp.sprites.add(bu);
		bulletbigEnemys.add(bu);
		
	}
	private void generateBulletBigEnemy2(){
	    BulletBigEnemy bs = new BulletBigEnemy(240 ,90);
		gp.sprites.add(bs);
		bulletbigEnemys.add(bs);
		
	}
	private void generateBulletBigEnemy3(){
	    BulletBigEnemy bs = new BulletBigEnemy(120 ,90);
		gp.sprites.add(bs);
		bulletbigEnemys.add(bs);
		
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


	private void generateLifeheart(){
		Lifeheart l = new Lifeheart((int)(Math.random()*390), 30);
		gp.sprites.add(l);
		lifehearts.add(l);

	}
	private void processBigEnemy(){

		Iterator<BigEnemy> b_iter = bigEnemys.iterator();
		while(b_iter.hasNext()){
			BigEnemy b = b_iter.next();
			b.proceed();

			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
			}

		}
		

	}

	private void processBulletBigEnemy(){
		Iterator<BulletBigEnemy> bu_iter = bulletbigEnemys.iterator();
		while(bu_iter.hasNext()){
			BulletBigEnemy bu = bu_iter.next();
			bu.proceed();

			if(!bu.isAlive()){
				bu_iter.remove();
				gp.sprites.remove(bu);
			}

		}
		

	}
	
	private void processLifeheart(){
		Iterator<Lifeheart> l_iter = lifehearts.iterator();
		while(l_iter.hasNext()){
			Lifeheart l = l_iter.next();
			l.proceed();

			if(!l.isAlive()){
				l_iter.remove();
				gp.sprites.remove(l);
			}

		}

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
			if(level != 2)
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
		Rectangle2D.Double br;
		Rectangle2D.Double er;
		Rectangle2D.Double mr1;
		Rectangle2D.Double mr2;
		Rectangle2D.Double lr;

		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				todie();
				e.notAlive();
								   			

			}

			for(Missile m : missiles){
			    mr1 = m.getRectangle();
			    if(mr1.intersects(er)){
			        score += 1000 ;
			        e.notAlive();
			        m.notAlive();			       	
			       
				    return;
	        	   
		        }
		        
	        }
	    
	    }    
	    if(getScore() > countscorefordif() ){
	    	count += 70000;
	    	difficulty += 0.05 ;
	    	level++;
	    }


	    for(BigEnemy b : bigEnemys){
		    br = b.getRectangle();
		    for(Missile mi : missiles){    
		    	mr2 = mi.getRectangle();
		       if(mr2.intersects(br.x , br.y-55 ,br.width ,br.height)){
		           mi.notAlive();  
					  generateBulletBigEnemy1();
					  generateBulletBigEnemy2();
					  generateBulletBigEnemy3();
				}
			   	
            }	
	    }

	    

	    for(Lifeheart l : lifehearts){
	    	lr = l.getRectangle();
	    	if(lr.intersects(vr)){
	    		countdie++;
	    		l.notAlive();

	    		return;
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
		//case KeyEvent.VK_D:
		//	difficulty += 0.1;
		//	break;
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

	public int getNumberalive(){
		return countdie ;
	}

	public void todie(){
		--countdie ;
		if(countdie < 2)
			generateLifeheart();
		if(countdie < 0)
			die();
	}

	public long countscorefordif(){
		return count ;
	}

	public double getDifficulty(){
		return difficulty;
	}

	public  int getLevel(){
		if(level == 7){
	    	generateBigEnemy();
	    	
	    }	
		return level;
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
