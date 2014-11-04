package me.zero.cc.Graphics.Ui.Level;

import java.util.ArrayList;

public class Level {

	private ArrayList<String> aufbau = new ArrayList<>();
	private String pathtomusik = "";
	private int framesizex = 0;
	private int framesizey = 0;
	private int playersizex = 0;
	private int playersizey = 0;
	private int ballsizex = 0;
	private int ballsizey = 0;
	
	
	public int getPlayersizex() {
		return playersizex;
	}
	public void setPlayersizex(int playersizex) {
		this.playersizex = playersizex;
	}
	public int getPlayersizey() {
		return playersizey;
	}
	public void setPlayersizey(int playersizey) {
		this.playersizey = playersizey;
	}
	public int getBallsizex() {
		return ballsizex;
	}
	public void setBallsizex(int ballsizex) {
		this.ballsizex = ballsizex;
	}
	public int getBallsizey() {
		return ballsizey;
	}
	public void setBallsizey(int ballsizey) {
		this.ballsizey = ballsizey;
	}
	public String getPathtomusik() {
		return pathtomusik;
	}
	public void setPathtomusik(String pathtomusik) {
		this.pathtomusik = pathtomusik;
	}
	public int getFramesizex() {
		return framesizex;
	}
	public void setFramesizex(int framesizex) {
		this.framesizex = framesizex;
	}
	public int getFramesizey() {
		return framesizey;
	}
	public void setFramesizey(int framesizey) {
		this.framesizey = framesizey;
	}
	public ArrayList<String> getAufbau() {
		return aufbau;
	}
	public void addAufbau(String lvl){
		aufbau.add(lvl);
	}
	
}
