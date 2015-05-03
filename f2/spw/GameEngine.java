package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;

	Massage f = new Massage();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Missile>  missiles = new ArrayList<Missile>();
	private ArrayList<Lifeheart> lifehearts = new ArrayList<Lifeheart>();
	private ArrayList<BigEnemy>  bigEnemys = new ArrayList<BigEnemy>();
	private ArrayList<BulletBigEnemy> bulletbigEnemys = new ArrayList<BulletBigEnemy>(); 	
	private SpaceShip v;	
	private Lifeheart l;
	private Bomb bo;
	private BigEnemy boss;
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	private int countdie = 5;
	private long count = 0;
	private int level = 0;
	private int threeposition = 1 ;
	private int scoreboss = 1000 ;
	private int x ;
	private int s = 0;
	Random rn = new Random();
	int answer ;

	public GameEngine(GamePanel gp, SpaceShip v, Lifeheart l,Bomb bo , BigEnemy boss) {
		this.gp = gp;
		this.v = v;		
		this.l = l ;
		this.bo = bo;
		this.boss = boss;
		gp.sprites.add(v);
		gp.sprites.add(l);

		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				processEnemy();
				processMissile();
				processLifeheart();
				processBulletBigEnemy();
				process();
				
			}
		});
		timer.setRepeats(true);

		
	}
	
	public void start(){
		timer.start();
	}
	

	public void generateBulletBigEnemy1(int x){
	    BulletBigEnemy bu = new BulletBigEnemy(x ,120);
		gp.sprites.add(bu);
		bulletbigEnemys.add(bu);
		
	}
	public void generateBulletBigEnemy2(int x){
	    BulletBigEnemy bu = new BulletBigEnemy(x ,120);
		gp.sprites.add(bu);
		bulletbigEnemys.add(bu);
		
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
				if(getLevel() == 7)
				  scoreboss -= 125;
				
			}
		}


	}		
		
	
	private void processEnemy(){
		if(Math.random() < difficulty){
			if(level < 7)
			   generateEnemy();
			
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

	}		
			    	
	private void process(){	

		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double mr1;
		Rectangle2D.Double mr2;
		Rectangle2D.Double mr3;
		Rectangle2D.Double lr;
		Rectangle2D.Double bur ;
		Rectangle2D.Double burr ;

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
	    	if(getLevel() <= 7)
	    		count += 5000;
	    	difficulty += 0.05 ;
	    	if(getLevel() < 7)
	    	   level++;
	    }


		for(Missile mi : missiles){    
		    mr2 = mi.getRectangle();
		    // if(mr2.intersects(br.x , br.y ,br.width ,br.height)){
		   if(getBossScore() > 0 ){ 
		   	Rectangle2D.Double bos = boss.getRectangle();
		    if(mr2.intersects(bos)){ 		    	   
		    	mi.notAlive();  
		        score += 1000;	
		         return ;			
			}

		   } 

        }	
                 
	    if(getLevel() == 7 && getBossScore() > 0){
	    	randomBulletBigEnemy();
	    }

	    for(Lifeheart l : lifehearts){
	    	lr = l.getRectangle();
	    	if(lr.intersects(vr)){
	    		countdie++;
	    		l.notAlive();
	    		return;
	    	}
	    }

	    for (BulletBigEnemy bu : bulletbigEnemys){
	    	bur = bu.getRectangle();
	    	for(Missile mm : missiles){
	    		mr3 = mm.getRectangle();
	    		if(mr3.intersects(bur)){
	    			mm.notAlive();
	    			bu.notAlive();
	    			return ;
	    		}
	    	}

	    }
	    for(BulletBigEnemy bb : bulletbigEnemys){
	    	burr = bb.getRectangle();
			if(burr.intersects(vr)){
				bb.notAlive();
				todie();
				return ;
				
			}
	    }
	    if(getBossScore() == 0){
	    	gp.sprites.add(bo);
	    	//die();
	    }
	   
	   
	} 

	public void randomBulletBigEnemy(){
		answer = rn.nextInt(10) + 1;	
		if(answer == 1 || answer ==  5 || answer == 4){			
			x = randInt(110,220); 
			if(Math.random() < 0.13){
		      generateBulletBigEnemy1(x);
			}		
		}  
		 else if(answer == 3 || answer == 6 || answer == 9){ 
			x = randInt(110,220);
			if(Math.random() < 0.13){
			 generateBulletBigEnemy1(x);	
			}
		}		
		else if(answer == 7 || answer == 8|| answer == 2 ){ 
			  x = randInt(110,220);
			  if(Math.random() < 0.13){ 		
		     	 generateBulletBigEnemy1(x);
			 }	
	    }
							       
	}

	public static int randInt(int min, int max) {
		 Random rand = new Random();
		 int randomNum = rand.nextInt((max - min) + 1) + min;
		 return randomNum;
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
		case KeyEvent.VK_SPACE:
			 if(getLevel() >= 4 && getLevel() != 7){
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
		if(countdie < 0){	
			f.massageshowGameOver(this);		
			die();
		}	
	}

	

	public long countscorefordif(){
		return count ;
	}

	public double getDifficulty(){
		return difficulty;
	}

	public  int getLevel(){
		if(level == 7 && scoreboss > 0){
	    	//generateBigEnemy();
	    	gp.sprites.add(boss);
	    }	

		return level;
	}
	
	public int getBossScore(){	
		if(scoreboss < 0){
			gp.sprites.remove(boss);
			//gp.sprites.add(bo);
			return 0;	
		}	
		return scoreboss;
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
