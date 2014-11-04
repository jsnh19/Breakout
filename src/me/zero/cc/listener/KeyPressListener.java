package me.zero.cc.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import me.zero.cc.utils.Player.Speicher;

public class KeyPressListener implements KeyListener{

	private Speicher speicher;
	
	public KeyPressListener(Speicher speicher){
		this.speicher = speicher;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_LEFT | e.getKeyCode() == KeyEvent.VK_A){
				if((0 - speicher.getPlayer().getX() *-1) > 50){
					speicher.getPlayer().setX((speicher.getPlayer().getX() - 50));					
				}else{
					speicher.getPlayer().setX(speicher.getPlayer().getX() - (0 - speicher.getPlayer().getX() *-1));
				}			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT | e.getKeyCode() == KeyEvent.VK_D){
			if((speicher.getPlayer().getX() + speicher.getPlayer().getXsize()) < speicher.getFrame().getWidth()){				
				if(speicher.getPlayer().getX() + speicher.getPlayer().getXsize() - speicher.getFrame().getWidth() *-1 < 50){
					speicher.getPlayer().setX(speicher.getPlayer().getX() - speicher.getPlayer().getX() + speicher.getPlayer().getXsize() - speicher.getFrame().getWidth() *-1);
				}else{
					speicher.getPlayer().setX((speicher.getPlayer().getX() + 50));
				}
			}			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
