package me.zero.cc.Sound.SoundThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SoundMP3Thread extends Thread{

	private String filename;
	private boolean talive = true;
	private Player p;
	
	public boolean isTalive() {
		return talive;
	}
	public void setTalive(boolean talive) {
		this.talive = talive;
	}
	public SoundMP3Thread(String filename){
		this.filename = filename;
	}	
	public void run(){
		while(talive){
			startMP3Player(filename);
		}
	}
	public void startMP3Player(String filename) {
		try {
		//Player-Instanz
		if(filename.equalsIgnoreCase("standart")){
			filename = "/sounds/mp3/standart.mp3";
			p = new Player(getClass().getResourceAsStream(filename));
		}else if(filename.equalsIgnoreCase("secret")){
			filename = "/sounds/mp3/standart2.mp3";
			p = new Player(getClass().getResourceAsStream(filename));
		}else{	
			if(new File(filename).exists()){
				filename = filename.replace("\\", "/");
				FileInputStream in = new FileInputStream(filename);
				 p = new Player(in);
			}else{
				filename = "/sounds/mp3/standart.mp3";
				p = new Player(getClass().getResourceAsStream(filename));
			}			
		}
			//Abspielen
			p.play();
		} catch (JavaLayerException | IOException jle) {
			System.err.println("Error: " + jle);
		}
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
}
