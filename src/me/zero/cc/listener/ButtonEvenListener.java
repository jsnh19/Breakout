package me.zero.cc.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import me.zero.cc.Graphics.GameJFrame;
import me.zero.cc.Graphics.StatsFrame;
import me.zero.cc.utils.Player.Speicher;

public class ButtonEvenListener implements ActionListener{

	private Speicher speicher;
	
	public ButtonEvenListener(Speicher speicher){
		this.speicher = speicher;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().contains("author")| e.getActionCommand().contains("Author")){
			showAuthor();
		}else
		if(e.getActionCommand().contains("close")| e.getActionCommand().contains("Close")){
			closeCommand();
		}else
		if(e.getActionCommand().contains("start")| e.getActionCommand().contains("Start")){
			startGame();
		}
		if(e.getActionCommand().contains("bestenliste")| e.getActionCommand().contains("Bestenliste")){
			showStats();
		}
		
	}
	private void closeCommand(){
		speicher.getLog().info("Beende Programm durch Button!");
		System.exit(0);
	}
	private void startGame(){
		speicher.setJframe(new GameJFrame(speicher));
	}
	private void showAuthor(){
		JOptionPane.showMessageDialog(null, "coded by Julius Schönhut");
	}
	private void showStats(){
		speicher.setStatsframe(new StatsFrame(speicher));
	}

}
