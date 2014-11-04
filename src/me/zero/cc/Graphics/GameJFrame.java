package me.zero.cc.Graphics;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import me.zero.cc.BallThread;
import me.zero.cc.Graphics.Ui.Ball;
import me.zero.cc.Graphics.Ui.Block;
import me.zero.cc.Graphics.Ui.Player;
import me.zero.cc.Graphics.Ui.Level.Level;
import me.zero.cc.Graphics.WindowClosingAdapter.GameClosingAdapter;
import me.zero.cc.Sound.SoundThread.SoundMP3Thread;
import me.zero.cc.listener.KeyPressListener;
import me.zero.cc.utils.Player.Speicher;

public class GameJFrame extends Frame implements Runnable{

	private static final long serialVersionUID = 1L;
	private Thread t;
	private Speicher speicher;
	private Image dbImage;
	private Graphics dbGraphics;
	private boolean alive = true;
	
	public GameJFrame(Speicher speicher){
		super("Breakout");
		this.speicher = speicher;
		CreateWindow();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(alive){
			try {
				t.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}			
	}
	
	public void CreateWindow(){
		speicher.getStartframe().setVisible(false);
		this.setBounds(((speicher.getMaxX()/2)-250),(speicher.getMaxY()/2) -250, 500, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.addKeyListener(new KeyPressListener(speicher));
		this.addWindowListener(new GameClosingAdapter(speicher));
		
		LoadFromLevel();
		
		speicher.setStarted(true);
		speicher.setFrame(this);
		t = new Thread(this);
		t.start();		
		BallThread ballt = new BallThread(speicher);
		ballt.start();
		speicher.setBt(ballt);
	}
	public void LoadFromLevel(){
		if(speicher.getAmountlvlplayed() <= speicher.getLevel().size()-1){
			System.out.println("starte lvl " + speicher.getAmountlvlplayed());
			Level level = speicher.getLevel().get(speicher.getAmountlvlplayed());
			this.setBounds(((speicher.getMaxX()/2)-(level.getFramesizex()/2)),(speicher.getMaxY()/2) -(level.getFramesizey()/2), level.getFramesizex(), level.getFramesizey());		
		
			int xperblock = (level.getFramesizex()/level.getAufbau().get(1).length());
			int yperblock = (level.getFramesizey()/level.getAufbau().size());
			int height = yperblock;
			int width = xperblock;
			
			
			
			for(int i = 0; i < level.getAufbau().size();i++){	
				width = 0;
				for(int x = 0; x < level.getAufbau().get(i).length();x++){
					
					if(level.getAufbau().get(i).charAt(x) == 'x' | level.getAufbau().get(i).charAt(x) == 'X'){
						speicher.addBlock(new Block(width, height, yperblock, xperblock));
						width = width + xperblock;						
					}else if(level.getAufbau().get(i).charAt(x) == '0'){
						width = width + xperblock;	
					}else if(level.getAufbau().get(i).charAt(x) == 'b' | level.getAufbau().get(i).charAt(x) == 'B'){
						speicher.setBall(new Ball(width, height, level.getBallsizex(), level.getBallsizey()));
						width = width + xperblock;						
					}else if(level.getAufbau().get(i).charAt(x) == 'P' | level.getAufbau().get(i).charAt(x) == 'P'){
						speicher.setPlayer(new Player(width, height, level.getPlayersizey(), level.getPlayersizex()));
						width = width + xperblock;	
					}				
				}	
				height = height + yperblock;
			}			
					speicher.getMp3thread().setTalive(false);
					speicher.getMp3thread().getP().close();
					SoundMP3Thread st = new SoundMP3Thread(level.getPathtomusik());
					st.start();
					speicher.setMp3thread(st);	
		}					
	}
	
	public void update(Graphics g){
	    //Double-Buffer initialisieren
	    if (dbImage == null) {
	      dbImage = createImage(this.getSize().width,this.getSize().height);
	      dbGraphics = dbImage.getGraphics();
	    }
	    //Hintergrund löschen
	    dbGraphics.setColor(getBackground());
	    dbGraphics.fillRect(0,0,this.getSize().width,this.getSize().height);
	    //Vordergrund zeichnen
	    dbGraphics.setColor(getForeground());
	    paint(dbGraphics);
	    //Offscreen anzeigen
	    g.drawImage(dbImage,0,0,this);
	  }
	public void paint(Graphics g){
		//Clear everything
		
		for(int i = 0; i < speicher.getBlocks().size();i++){
			if(speicher.getBlocks().get(i) != null){
				speicher.getBlocks().get(i).clear(g);
			}
		}
		speicher.getPlayer().clear(g);
		//Mal los!
		//g.clearRect(0, 0, 500, 500);
		for(int i = 0; i < speicher.getBlocks().size();i++){
			if(speicher.getBlocks().get(i) != null){
				speicher.getBlocks().get(i).draw(g);
			}
		}
		speicher.getPlayer().draw(g);
		speicher.getBall().draw(g);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
