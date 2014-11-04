package me.zero.cc.listener;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import me.zero.cc.utils.Player.Speicher;

public class StartWindowListener implements ComponentListener{

	private Speicher speicher;
	
	public StartWindowListener(Speicher speicher){
		this.speicher = speicher;
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent c) {
		for(int i = 0; i < speicher.getButtons().size();i++){
			speicher.getButtons().get(i).setBounds(speicher.getStartframe().getSize().width/2 - 100,speicher.getStartframe().getSize().height/8 + (i*speicher.getStartframe().getSize().height / 5),200,50);	
		}
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
